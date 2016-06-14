package lll.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import lll.domain.Tags;

public class CreatTagsResult implements Callable<List<Tags>> {
    private List<Tags> data=new ArrayList<Tags>();
    private int authority;

    public CreatTagsResult(int authority) {
        this.authority = authority;
    }
    public List<Tags> call() throws Exception {
        data = TagsSservice.getJsonTags(authority);
        return this.data;
    }
}
