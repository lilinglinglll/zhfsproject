package lll.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import lll.domain.LastTimes;
import lll.utils.SubTimes;

public class WarnService extends Service {
    private int sauthority;
    private MediaPlayer mMediaPlayer = null;
    private Vibrator vibrator;

    //查询数据库的结果
    private List<LastTimes> result = new ArrayList<LastTimes>();

    private Handler handler = new Handler();
    private Runnable runnable;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("===================================================service created");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            sauthority = intent.getIntExtra("authority",0);
        }catch (NullPointerException e){
            System.out.println("==========================service NullPointerException");}
            System.out.println("===================================================service onstart   "+sauthority);

        runnable = new Runnable() {
            @Override
            public void run() {
                //要做的事情
                ExecutorService pool = Executors.newCachedThreadPool();
                Callable callable = new CreateLastDate(sauthority);
                Future future = pool.submit(callable);
                try {
                    result = (List<LastTimes>) future.get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                System.out.println("====================================================servicerunnable" + result);
                pool.shutdown();
                for(LastTimes times:result){
                    String sub = null;
                    StringBuilder start = new StringBuilder();
                    if (times.getEnd().equals("未离开")){
                        SubTimes subTimes=new SubTimes();
                        try {
                            subTimes.subTimes(start.append(times.getStartDate()).append(" ").append(times.getStart()).toString(),getSystemCurrentTime());
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        sub=subTimes.toString();

                        if(subTimes.getTime()>2*60*1000) {
                            final Context mContext = getApplicationContext();
                            AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                            audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                            PlaySound(mContext);

                            vibrator = (Vibrator) mContext.getSystemService(mContext.VIBRATOR_SERVICE);
                            // 等待3秒，震动3秒，从第0个索引开始，一直循环
                            vibrator.vibrate(new long[]{3000, 3000}, 0);

                            //对话框
                            AlertDialog alertDialog = new AlertDialog.Builder(mContext).setTitle("系统提示")//设置对话框标题
                                    .setMessage("编号" + times.getName() + "已经在" + times.getRoomNum() + "号房间有" + sub + "，请速去查看！")//设置显示的内容
                                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                            StopSoundandViber(mContext);
                                        }
                                    }).setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {//响应事件
                                            StopSoundandViber(mContext);
                                        }
                                    }).create();
                            alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                            alertDialog.show();
                        }
                    }
                }

                handler.postDelayed(this, 30*60*1000);
            }
        };
        handler.postDelayed(runnable,30*60*1000);
        return super.onStartCommand(intent, flags, startId);
    }

    public  void PlaySound(final Context context) {
        Log.e("ee", "正在响铃");
        // 使用来电铃声的铃声路径
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        // 如果为空，才构造，不为空，说明之前有构造过
        if(mMediaPlayer == null)
            mMediaPlayer = new MediaPlayer();
        try {
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(context, uri);
            mMediaPlayer.setLooping(true); //循环播放
            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IllegalArgumentException | SecurityException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }
    public  void StopSoundandViber(final Context context) {
        if(mMediaPlayer != null){
            mMediaPlayer.pause();
            mMediaPlayer.seekTo(0);
            // 要释放资源，不然会打开很多个MediaPlayer
        }
        if(vibrator != null){
            vibrator.cancel();
        }
    }

    public String getSystemCurrentTime(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

}

