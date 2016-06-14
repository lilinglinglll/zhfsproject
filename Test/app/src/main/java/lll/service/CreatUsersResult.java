package lll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lll.domain.Users;

public class CreatUsersResult implements Callable<List<Users>> {
    private List<Users> data=new ArrayList<Users>();
    private String userid;

    public CreatUsersResult(String userid) {
        this.userid = userid;
    }

    public List<Users> call() throws Exception {
        data = Userssservice.getJsonUsers(userid);
        return this.data;
    }
}
