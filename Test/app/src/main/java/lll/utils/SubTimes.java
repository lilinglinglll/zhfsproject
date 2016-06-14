package lll.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SubTimes {
    private long time;
    private long day;
    private long hour;
    private long minute;
    private long second;

    public long getTime() {
        return time;
    }

    public void subTimes(String start,String end) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse(start);
        Date date2 = sdf.parse(end);
        time=Math.abs(date1.getTime()-date2.getTime());
        day=time/1000/60/60/24;
        hour=(time-day*24*60*60*1000)/1000/60/60;
        minute=(time-day*24*60*60*1000-hour*60*60*1000)/1000/60;
        second=(time-day*24*60*60*1000-hour*60*60*1000-minute*60*1000)/1000;
    }
    @Override
    public String toString() {
        return day + "天" + hour + "小时" + minute + "分钟" + second + '秒';
    }
}

