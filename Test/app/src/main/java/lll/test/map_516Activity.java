package lll.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lll.domain.LastTimes;
import lll.lab516view.Lab516View;
import lll.service.CreateLastDate;

public class map_516Activity extends Activity {
    //四个定位图标
    private ImageView room1p;
    private ImageView room2p;
    private ImageView room3p;
    private ImageView room4p;
    //四个要显示的字符
    private StringBuilder room1sb;
    private StringBuilder room2sb;
    private StringBuilder room3sb;
    private StringBuilder room4sb;
    //四个计数
    private int r1count;
    private int r2count;
    private int r3count;
    private int r4count;

    private Context mContext=this;
    private int authority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //上个actibity传递的数据
        final Intent data = getIntent();
        authority=data.getIntExtra("authority",0);

        //获取每个标签最新的记录
        List<LastTimes> result = new ArrayList<LastTimes>();
        result=selectLastTime(authority);
        System.out.println("=============================================LocationsFregment" + result);

        FrameLayout frameLayout = new FrameLayout(mContext);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        frameLayout.setLayoutParams(layoutParams);

        View partview = getPartUI();
        FrameLayout.LayoutParams partviewParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        partviewParams.gravity=Gravity.CENTER;
        partview.setLayoutParams(partviewParams);
        frameLayout.addView(partview);
        Lab516View lab516View = new Lab516View(mContext);
        initImageView(frameLayout, lab516View);


        boolean[] show=showImageView(result);
        if (show[0]==true){frameLayout.addView(room1p);}
        if (show[1]==true){frameLayout.addView(room2p);}
        if (show[2]==true){frameLayout.addView(room3p);}
        if (show[3] ==true){frameLayout.addView(room4p);}
        initEvent();

        setContentView(frameLayout);
    }
    private void initEvent() {
        room1p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                //设置对话框标题
                builder.setTitle("room1:" + r1count + "人");
                //设置对话框内的文本
                builder.setMessage(room1sb.substring(0,room1sb.length()-1));
                //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 执行点击确定按钮的业务逻辑
                    }
                });
                //使用builder创建出对话框对象
                AlertDialog dialog = builder.create();
                //显示对话框
                dialog.show();
            }
        });
        room2p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("room2:"+r2count+"人");
                builder.setMessage(room2sb.substring(0,room2sb.length()-1));
                //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 执行点击确定按钮的业务逻辑
                    }
                });
                //使用builder创建出对话框对象
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        room3p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("room3:"+r3count+"人");
                builder.setMessage(room3sb.substring(0,room3sb.length()-1));
                //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 执行点击确定按钮的业务逻辑
                    }
                });
                //使用builder创建出对话框对象
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        room4p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("room4:"+r4count+"人");
                builder.setMessage(room4sb.substring(0,room4sb.length()-1));
                //设置确定按钮，并给按钮设置一个点击侦听，注意这个OnClickListener使用的是DialogInterface类里的一个内部接口
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 执行点击确定按钮的业务逻辑
                    }
                });
                //使用builder创建出对话框对象
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }
    public List<LastTimes> selectLastTime(int authority){
        List<LastTimes> result = new ArrayList<LastTimes>();
        ExecutorService pool = Executors.newCachedThreadPool();
        Callable callable=new CreateLastDate(authority);
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
    private View getPartUI(){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return inflater.inflate(R.layout.lacationsinfo, null);
    }
    private void initImageView(FrameLayout frameLayout,Lab516View partview) {
//        int width=frameLayout.getMeasuredWidth();
//        int height=frameLayout.getMeasuredHeight();
//        int lab516width=partview.getMeasuredWidth();
//        int lab516height=partview.getMeasuredHeight();

        FrameLayout.LayoutParams params1 = new FrameLayout.LayoutParams(80,120);
        params1.setMargins((int) (  partview.getRoom1().centerX()-40),
                (int) (  partview.getRoom1().centerY()+340),
                (int) (  partview.getRoom3().width() + partview.getRoom1().width() / 2 ),
                (int) ( partview.getRoom1().height() / 2));
        room1p= new ImageView(mContext);
        room1p.setId(R.id.room1imageview);
        room1p.setImageResource(R.drawable.locationpicuture);
        room1p.setLayoutParams(params1);

        FrameLayout.LayoutParams params2 = new FrameLayout.LayoutParams(80,120);
        params2.setMargins((int) (partview.getRoom2().centerX()-40),
                (int) (partview.getRoom2().centerY()+340),
                (int) (partview.getRoom4().width() + partview.getRoom2().width() / 2),
                (int) (partview.getRoom1().height() + partview.getRoom2().height() / 2));
        room2p= new ImageView(mContext);
        room2p.setId(R.id.room2imageview);
        room2p.setImageResource(R.drawable.locationpicuture);
        room2p.setLayoutParams(params2);

        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(80,120);
        params3.setMargins((int) (partview.getRoom3().centerX()-40),
                (int) (partview.getRoom3().centerY()+340),
                (int) ( partview.getRoom3().width() / 2),
                (int) (partview.getRoom3().height() / 2));
        room3p= new ImageView(mContext);
        room3p.setId(R.id.room3imageview);
        room3p.setImageResource(R.drawable.locationpicuture);
        room3p.setLayoutParams(params3);

        FrameLayout.LayoutParams params4 = new FrameLayout.LayoutParams(80,120);
        params4.setMargins((int) (partview.getRoom4().centerX()-40),
                (int) (partview.getRoom4().centerY()+340),
                (int) (partview.getRoom4().width() / 2),
                (int) (partview.getRoom3().height() + partview.getRoom4().height() / 2));
        room4p= new ImageView(mContext);
        room4p.setId(R.id.room4imageview);
        room4p.setImageResource(R.drawable.locationpicuture);
        room4p.setLayoutParams(params4);
    }
    private boolean[] showImageView(List<LastTimes> result) {
        boolean[] show={false,false,false,false};
        room1sb=new StringBuilder();
        room2sb=new StringBuilder();
        room3sb=new StringBuilder();
        room4sb=new StringBuilder();

        LastTimes times=new LastTimes();
        for(int i=0;i<result.size();i++){
            times=result.get(i);
            if(times.getEnd().equals("未离开")) {
                switch (times.getRoomNum()) {
                    case "001":
                        show[0] = true;
                        room1sb.append(times.getName()+"、");
                        r1count++;
                        break;
                    case "002":
                        show[1] = true;
                        room2sb.append(times.getName()+"、");
                        r2count++;
                        break;
                    case "003":
                        show[2] = true;
                        room3sb.append(times.getName()+"、");
                        r3count++;
                        break;
                    case "004":
                        show[3] = true;
                        room4sb.append(times.getName()+"、");
                        r4count++;
                        break;
                    default:
                        break;
                }
            }
        }
        return show;
    }

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
