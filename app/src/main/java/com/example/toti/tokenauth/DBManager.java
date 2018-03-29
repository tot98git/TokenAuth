package com.example.toti.tokenauth;

import android.content.Context;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Created by toti on 12.03.18.
 */

public class DBManager {
    private static String apiKey="gjSxl9Z-Wm4y98CXt993THkR-jan3LTT";
    private static String db="androidappdb";
    private static String coll="users";
    public static String getAddressApi(){
        String baseUrl=String.format("https://api.mlab.com/api/1/databases/%s/collections/%s",db,coll);
        StringBuilder stringBuilder=new StringBuilder(baseUrl);
        stringBuilder.append("?apiKey="+apiKey);
        return stringBuilder.toString();
    }
    public static String checkIfRecordExists(String usr, String password) {
        String query = getAddressApi()+"&q=";
        String query2="{\"username\":\"" + usr + "\",\"password\":\"" + password + "\"}";
        try {
            query+= URLEncoder.encode(query2,StandardCharsets.UTF_8.toString());
        }
        catch(UnsupportedEncodingException e){
            System.out.println(e);
        }
        return query+"&c=true";

    }
}

