package lll.utils;

import java.util.HashSet;
import java.util.List;
public class clearListSameData {
    public   static List removeDuplicate(List list)  {
        HashSet h  =   new  HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }
}
