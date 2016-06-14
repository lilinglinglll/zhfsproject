package lll.service;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import lll.domain.Rooms;
import lll.utils.ServiceIp;
import lll.utils.StreamTool;

public class RoomsSservice {
    /**
     * 获取时间
     * @return
     * @throws Exception
     */
    //http://219.246.178.161:8080/test1//Usersservlet?format=json&userid=1
    //[{userid:1,password:"516",authority:1},{userid:1,password:"516",authority:1,tagNum:"002"}]
    public static List<Rooms> getJsonRooms(int authority) throws Exception {
        StringBuilder path= new StringBuilder("http://");
        path.append(ServiceIp.serviceIp());
        path.append(":8080/test1//RoomsServlet?format=json");
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
    private static List<Rooms> parseJSON(InputStream inStream) throws Exception{
        List<Rooms> result = new ArrayList<Rooms>();
        byte[] data = StreamTool.read(inStream);
        String json = new String(data);
        JSONArray array = new JSONArray(json);
        for(int i = 0 ; i < array.length() ; i++){
            JSONObject jsonObject = array.getJSONObject(i);
            String roomNum=jsonObject.getString("roomNum");
            Rooms t = new Rooms(roomNum);
            result.add(t);
        }
        return result;
    }
}
