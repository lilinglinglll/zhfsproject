package lll.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lll.domain.Times;
import lll.utils.ServiceIp;
import lll.utils.StreamTool;

public class Timesservice {
    /**
     * 获取时间
     * @return
     * @throws Exception
     */
    //http://192.168.1.106:8080/test1//Timesservlet?format=json&tagNum=001
    //219.246.178.161
    //[{userid:1,password:"516",authority:1,tagNum:"001"},{userid:1,password:"516",authority:1,tagNum:"002"}]
    public static List<Times> getJsonTimes(String tagNum,String startDate) throws Exception {
        StringBuilder path= new StringBuilder("http://");
        path.append(ServiceIp.serviceIp());
        path.append(":8080/test1//Timesservlet?format=json");
        path.append("&tagNum=").append(tagNum);
        path.append("&startDate=").append(startDate);

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
    private static List<Times> parseJSON(InputStream inStream) throws Exception{
        List<Times> result = new ArrayList<Times>();
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

            Times t =new Times(tagNum,roomNum,startDate,endDate,start,end);
            result.add(t);
        }
        return result;
    }
}
