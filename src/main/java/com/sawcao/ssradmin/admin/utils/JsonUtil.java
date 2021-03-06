package com.sawcao.ssradmin.admin.utils;

import com.sawcao.ssradmin.admin.dto.User;
import com.sawcao.ssradmin.admin.dto.VPS;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: caorui
 * Time: 2018/8/31
 **/

/**
 * 数据存储格式为 [userName vpsName port month transfer speedPort speedThread userNum]
 */
@Deprecated
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
        File file=new File("D://vpsData/" + vpsName + ".json");
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

    public static String getUser(String userName) throws IOException {
        File file=new File("D://vpsData/userData.json");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        String[] userInfo;
        while ((line = br.readLine()) != null) {
            userInfo = line.split(" ");
            if (userInfo[0].equals(userName)) {
                return userInfo[1];
            }
        }
        br.close();
        return null;
    }

    public static List<User> getAllUser() throws IOException {
        File file=new File("D://vpsData/userData.json");
        BufferedReader br = new BufferedReader(new FileReader(file));
        List<User> userList = new ArrayList<>();
        User tempUser = null;
        String line;
        String[] userInfo;
        while ((line = br.readLine()) != null) {
            userInfo = line.split(" ");

            tempUser.setUserName(userInfo[0]);
            tempUser.setVpsName(userInfo[1]);
            tempUser.setPort(userInfo[2]);
            tempUser.setMonths(userInfo[3]);
            tempUser.setTransfer(userInfo[4]);
            tempUser.setSpeedPort(userInfo[5]);
            tempUser.setSpeedThread(userInfo[6]);
            tempUser.setUserNum(userInfo[7]);
            tempUser.setPassword("******");

            userList.add(tempUser);
        }
        return userList;
    }
    public static void addUser(String userName,String vpsName,String month,String port) throws IOException {
        File file=new File("D://vpsData/userData.json");
        BufferedWriter wr = new BufferedWriter(new FileWriter(file,true));
        wr.write(userName + " " + vpsName + " " + port + " " + month + "\n");
    }

    public static void upadateUserMonth(String userName, String month){
        File file = new File("D://vpsData/userData.json");
        List<String> userInfos = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            BufferedWriter wr = new BufferedWriter(new FileWriter(file,false));
            String line;
            String[] userInfo;
            while ((line = br.readLine()) != null) {
                userInfo = line.split(" ");
                if (userInfo[0].equals(userName)) {
                    userInfo[3] = month;
                    line = userInfo[0] + " " + userInfo[1] + " " + userInfo[2] + " " + userInfo[3] + "\n";
                }
                userInfos.add(line);
            }
            userInfos.forEach((e) ->{
                try {
                    wr.write(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
