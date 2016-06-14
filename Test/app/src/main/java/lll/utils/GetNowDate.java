package lll.utils;

import java.util.Calendar;

public class GetNowDate {

	private int day;
	private int month;
	private int year;
	private int dayofweek;
	private int dayofmonth;
	private int dayofyear;

	
	public int getDay() {
		return day;
	}


	public int getMonth() {
		return month;
	}


	public int getYear() {
		return year;
	}


	public int getDayofweek() {
		return dayofweek;
	}


	public int getDayofmonth() {
		return dayofmonth;
	}


	public int getDayofyear() {
		return dayofyear;
	}


	public void getdate(){
	    Calendar cal = Calendar.getInstance();
	    day = cal.get(Calendar.DATE);
	    month = cal.get(Calendar.MONTH) + 1;
	    year = cal.get(Calendar.YEAR);
	    dayofweek = cal.get(Calendar.DAY_OF_WEEK);
	    dayofmonth = cal.get(Calendar.DAY_OF_MONTH);
	    dayofyear = cal.get(Calendar.DAY_OF_YEAR);
	}
	
	public String getyear_month_day(){
		StringBuilder date = new StringBuilder();
		String ymd=date.append(year).append("-").append(month).append("-").append(day).toString();
		return ymd;
	}
}
