package lll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lll.domain.LastTimes;
import lll.domain.Times;

public class CreateLastDate implements Callable<List<LastTimes>> {
    private List<LastTimes> data=new ArrayList<LastTimes>();
    private int authority;

    public CreateLastDate(int authority) {
        this.authority = authority;
    }
    public List<LastTimes> call() throws Exception {
        data = LastDateservice.getJsonTimes(authority);
        return this.data;
    }
}
