package lll.test;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lll.domain.LastTimes;
import lll.service.CreatRoom_TimeHashMap;
import lll.utils.GetNowDate;
import lll.utils.SubTimes;

public class room_showtimeActivity extends Activity {

    private TextView choosedate = null;
    private String roomNum;//保存标签号
    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_timelist);

        //上个actibity传递的数据
        final Intent data = getIntent();
        roomNum=data.getStringExtra("roomNum");
        System.out.println("=========================================show接受到的tag" + roomNum);

        choosedate = (TextView)findViewById(R.id.room_choosedate);
        //给日期选择textview添加监听器
        choosedate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                //result 查数据库得到的结果
                List<LastTimes> result = new ArrayList<LastTimes>();
                result=selectTime();
                System.out.println("=========================================" + result);
                //显示数据
                if (result.isEmpty()) {
                    showtime(result);
                    Toast.makeText(getApplicationContext(),R.string.today_no_data, Toast.LENGTH_SHORT).show();
                } else {
                    showtime(result);
                }
            }
        });

        //设置初始日期
        final GetNowDate gnd = new GetNowDate();
        gnd.getdate();
        choosedate.setText(gnd.getyear_month_day());
        //日期选择控件相关
        //给button添加事件监听器：
        choosedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePicker=new DatePickerDialog(room_showtimeActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        choosedate.setText(new StringBuilder()
                                .append(year).append("-")
                                .append(monthOfYear + 1).append("-")//得到的月份+1，因为从0开始
                                .append(dayOfMonth));
                    }
                }, gnd.getYear(),gnd.getMonth()-1,gnd.getDay());
                datePicker.show();
            }
        });
    }
    //根据tag和日期查数据库 返回List<Times>
    public List<LastTimes> selectTime (){
        List<LastTimes> result = new ArrayList<LastTimes>();
        ExecutorService pool = Executors.newCachedThreadPool();
        Callable callable = new CreatRoom_TimeHashMap(roomNum, choosedate.getText().toString());
        Future future = pool.submit(callable);
        try {
            result = (List<LastTimes>) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();
        return result;

    }

    public void showtime(List<LastTimes> result){
    //显示出入时间
        ListView timelistview = (ListView)findViewById(R.id.room_timecontentlist);
        List<HashMap<String, Object>> timehashmap = new ArrayList<HashMap<String,Object>>();
        if(!result.isEmpty()) {
            for (LastTimes t : result) {
                HashMap<String, Object> item = new HashMap<String, Object>();
//                item.put("tagnum", t.getTagNum());
                item.put("Name",t.getName());
                item.put("start", t.getStart());
                item.put("end", t.getEnd());
                if(!t.getEnd().equals("未离开")) {
                    //让两个时间满足相减的格式
                    StringBuilder starttime = new StringBuilder();
                    starttime.append(t.getStartDate()).append(" ").append(t.getStart());
                    StringBuilder endtime = new StringBuilder();
                    endtime.append(t.getEndDate()).append(" ").append(t.getEnd());

                    SubTimes subTimes=new SubTimes();
                    try {
                        subTimes.subTimes(starttime.toString(), endtime.toString());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    item.put("sub", subTimes.toString());
                }else{
                    item.put("sub",null);
                }
                timehashmap.add(item);
            }
        }
        SimpleAdapter adapter = new SimpleAdapter(room_showtimeActivity.this, timehashmap, R.layout.item,
                new String[]{"Name","start","end","sub"}, new int[]{R.id.roomnum,R.id.starttime,R.id.endtime,R.id.subtime});
        timelistview.setAdapter(adapter);
    }
//显示数据
//    public void showroom(List<Times> result){
//        //显示房间号
//        ListView roomlist = (ListView) this.findViewById(R.id.roomlist);
//        List<String> room = new ArrayList<String>();
//        for(Times t : result){
//            String item=t.getRoomNum();
//            room.add(item);
//        }
//        room = clearListSameData.removeDuplicate(room);
//        List<HashMap<String, Object>> hashmaproom = new ArrayList<HashMap<String,Object>>();
//        for(String s : room){
//            HashMap<String, Object> item = new HashMap<String, Object>();
//            item.put("roomNum",s);
//            hashmaproom.add(item);
//        }
//        SimpleAdapter adapter = new SimpleAdapter(this, hashmaproom, R.layout.room_item,
//                new String[]{"roomNum"}, new int[]{R.id.room});
//        roomlist.setAdapter(adapter);
//    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
