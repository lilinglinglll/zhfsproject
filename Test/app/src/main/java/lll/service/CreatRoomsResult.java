package lll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lll.domain.Rooms;

public class CreatRoomsResult implements Callable<List<Rooms>> {
    private List<Rooms> data=new ArrayList<Rooms>();
    private int authority;

    public CreatRoomsResult(int authority) {
        this.authority = authority;
    }
    public List<Rooms> call() throws Exception {
        data = RoomsSservice.getJsonRooms(authority);
        return this.data;
    }
}
