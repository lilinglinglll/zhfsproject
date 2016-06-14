package lab516.zhfs.service;

import java.util.List;

import javax.jws.WebService;

import lab516.zhfs.entity.TagTimeData;

@WebService
public interface TagDataService {
//	public List<TagTimeData> findLastInsertData();
	public List<TagTimeData> findTagDataByTagUserNameAndDate(String name,String date);
 }
