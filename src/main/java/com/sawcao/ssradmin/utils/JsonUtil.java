package com.sawcao.ssradmin.utils;

import com.sawcao.ssradmin.dto.VPS;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/
public class JsonUtil {

    public static VPS getVps(String vpsName){
        File file=new File("D://vpsData/" + vpsName + ".json");
        VPS vps = new VPS();
        try {
            String content= FileUtils.readFileToString(file,"UTF-8");
            JSONObject jsonObject=new JSONObject(content);
            vps.setIp(jsonObject.getString("ip"));
            vps.setUserName(jsonObject.getString("username"));
            vps.setPassword(jsonObject.getString("password"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return vps;
    }

    public static void setVps(String vpsName,String ip,String username,String password){
        Map<String,String>vps = new HashMap<>();
        JSONObject json = new JSONObject(vps);
        File file= new File("vpsData/" + vpsName);
        BufferedWriter writer = null;

        vps.put("ip",ip);
        vps.put("username",username);
        vps.put("password",password);

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file,false), "UTF-8"));
            writer.write(json.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
