package lab516.zhfs.dao;

import java.util.List;

import lab516.zhfs.entity.TagTimeData;

public interface TagDataDao {
	public List<TagTimeData> searchLastInsertTimeData(String authority);
	public List<TagTimeData> searchTagDataByTagUserNameAndDate(String name,String date);
}
