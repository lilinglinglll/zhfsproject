package lll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lll.domain.Times;

public class CreatTimeHashMap implements Callable<List<Times>> {
    private List<Times> data=new ArrayList<Times>();
    private String tagNum;
    private String date;

    public CreatTimeHashMap(String tagNum,String date) {
        this.tagNum = tagNum;
        this.date=date;
    }

    public List<Times> call() throws Exception {
        data = Timesservice.getJsonTimes(tagNum,date);
        return this.data;
    }
}