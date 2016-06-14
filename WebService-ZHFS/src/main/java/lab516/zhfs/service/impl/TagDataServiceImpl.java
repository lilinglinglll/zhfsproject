package lab516.zhfs.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lab516.zhfs.dao.TagDataDao;
import lab516.zhfs.entity.TagTimeData;

@WebService
@Component("tagdataService")
@Transactional(readOnly = true)
public class TagDataServiceImpl implements lab516.zhfs.service.TagDataService {

	private TagDataDao tagdataDao;
	
	public TagDataDao getTagdataDao() {
		return tagdataDao;
	}

	@Resource(name = "tagdataDao")
	public void setTagdataDao(TagDataDao tagdataDao) {
		this.tagdataDao = tagdataDao;
	}

//	@Override
//	public List<TagTimeData> findLastInsertData() {
//		return tagdataDao.searchLastInsertTimeData();
//	}

	@Override
	public List<TagTimeData> findTagDataByTagUserNameAndDate(String name, String date) {
		return tagdataDao.searchTagDataByTagUserNameAndDate(name, date);
	}

}
