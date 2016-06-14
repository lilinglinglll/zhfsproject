package lll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lll.domain.LastTimes;

public class CreatRoom_TimeHashMap implements Callable<List<LastTimes>> {
    private List<LastTimes> data=new ArrayList<LastTimes>();
    private String roomNum;
    private String date;

    public CreatRoom_TimeHashMap(String roomNum, String date) {
        this.roomNum = roomNum;
        this.date=date;
    }

    public List<LastTimes> call() throws Exception {
        data = Room_Timesservice.getJsonRoomTimes(roomNum,date);
        return this.data;
    }
}