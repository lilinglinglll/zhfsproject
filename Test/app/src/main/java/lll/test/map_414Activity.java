package lll.test;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
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

public class map_414Activity extends Activity {
    private ImageView room1p;
    private ImageView tubiao;
    private StringBuilder room1sb;
    private int r1count;

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
        frameLayout.setBackgroundColor(Color.WHITE);

        FrameLayout.LayoutParams partviewParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        partviewParams.gravity=Gravity.CENTER;
        room1p= new ImageView(mContext);
        room1p.setImageResource(R.drawable.map414);
        room1p.setLayoutParams(partviewParams);

        frameLayout.addView(room1p);

        initImageView();

        boolean show=showImageView(result);
        if (show==true){frameLayout.addView(tubiao);}

        initEvent();

        setContentView(frameLayout);
    }
    private void initEvent() {
        tubiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                //设置对话框标题
                builder.setTitle("414:" + r1count + "人");
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

    private void initImageView() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(80,120);
        params.gravity=Gravity.CENTER;
        tubiao= new ImageView(mContext);
        tubiao.setImageResource(R.drawable.locationpicuture);
        tubiao.setLayoutParams(params);
    }
    private boolean showImageView(List<LastTimes> result) {
        boolean show=false;
        room1sb=new StringBuilder();
        LastTimes times=new LastTimes();
        for(int i=0;i<result.size();i++){
            times=result.get(i);
            if(times.getEnd().equals("未离开")) {
                switch (times.getRoomNum()) {
                    case "414":
                        show = true;
                        room1sb.append(times.getName()+"、");
                        r1count++;
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
