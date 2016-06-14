package lll.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lll.domain.LastTimes;
import lll.domain.Times;
import lll.utils.ServiceIp;
import lll.utils.StreamTool;

public class LastDateservice {
    /**
     * 获取时间
     * @return
     * @throws Exception
     */
    //http://192.168.1.106:8080/test1//Timesservlet?format=json&tagNum=001
    //219.246.178.161
    //[{userid:1,password:"516",authority:1,tagNum:"001"},{userid:1,password:"516",authority:1,tagNum:"002"}]
    public static List<LastTimes> getJsonTimes(int authority) throws Exception {
        StringBuilder path= new StringBuilder("http://");
        path.append(ServiceIp.serviceIp());
        path.append(":8080/test1//LastInsertservlet?format=json");
        path.append("&authority=").append(authority);

        URL url = new URL(path.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        if(conn.getResponseCode() == 200){
            InputStream inStream = conn.getInputStream();
            return parseJSON(inStream);
        }
        return null;
    }

    /**
     * 解析JSON数据
     * @param inStream
     * @return
     */
    private static List<LastTimes> parseJSON(InputStream inStream) throws Exception{
        List<LastTimes> result = new ArrayList<LastTimes>();
        byte[] data = StreamTool.read(inStream);
        String json = new String(data);
        JSONArray array = new JSONArray(json);
        for(int i = 0 ; i < array.length() ; i++){
            JSONObject jsonObject = array.getJSONObject(i);
            String tagNum=jsonObject.getString("tagNum");
            String roomNum=jsonObject.getString("roomNum");
            String startDate=jsonObject.getString("startDate");
            String endDate=jsonObject.getString("endDate");
            String start=jsonObject.getString("start");
            String end=jsonObject.getString("end");
            String Name=jsonObject.getString("Name");

            LastTimes t =new LastTimes(tagNum,roomNum,startDate,endDate,start,end,Name);
            result.add(t);
        }
        return result;
    }
}
