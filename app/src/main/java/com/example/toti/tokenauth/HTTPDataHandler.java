package com.example.toti.tokenauth;

import android.annotation.SuppressLint;
import android.renderscript.ScriptGroup;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by toti on 19.03.18.
 */

public class HTTPDataHandler {
    static String stream = "";
    public HTTPDataHandler(){

    }
    public String getHttpData(String urlString){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConn = (HttpURLConnection)url.openConnection();
            if(urlConn.getResponseCode()==200){
                InputStream in = new BufferedInputStream(urlConn.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(in));
                StringBuilder sb =new StringBuilder();
                String line;
                while((line=br.readLine())!=null){
                    sb.append(line);
                    stream=sb.toString();
                    urlConn.disconnect();
                }
            }

        }
        catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return stream;
    }
    public void postHttpData(String urlString, String json){
        InputStream response = null;
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
            urlConn.setRequestMethod("POST");
            urlConn.setDoOutput(true);
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            urlConn.setFixedLengthStreamingMode(length);
            urlConn.setRequestProperty("Content-type","application/json");
            urlConn.connect();
            try(OutputStream os=urlConn.getOutputStream()){
                os.write(out);
            }
             response = urlConn.getInputStream();

        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void updateHttpData(String urlString, String newValue){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
            urlConn.setRequestMethod("PUT");
            urlConn.setDoOutput(true);
            byte[] out = newValue.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            urlConn.setFixedLengthStreamingMode(length);
            urlConn.setRequestProperty("Content-type","application/json; charset=UFT-8");
            urlConn.connect();
            try(OutputStream os = urlConn.getOutputStream()){
                os.write(out);
            }
            InputStream response  = urlConn.getInputStream();

        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void deleteHttpData(String urlString, String json){
        try{
            URL url = new URL(urlString);
            HttpURLConnection urlConn=(HttpURLConnection)url.openConnection();
            urlConn.setRequestMethod("DELETE");
            urlConn.setDoOutput(true);
            byte[] out = json.getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            urlConn.setFixedLengthStreamingMode(length);
            urlConn.setRequestProperty("Content-type","application/json; charset=UFT-8");
            urlConn.connect();
            try(OutputStream os = urlConn.getOutputStream()){
                os.write(out);
            }
            InputStream response  = urlConn.getInputStream();

        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
