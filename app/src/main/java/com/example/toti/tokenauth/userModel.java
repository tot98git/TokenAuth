package com.example.toti.tokenauth;

/**
 * Created by toti on 12.03.18.
 */

public class userModel {
    private String name;
    private String username;
    private String password;
    private String email;
    public userModel(String nm, String usr, String psw, String em){
        name=nm;
        username=usr;
        password=psw;
        email=em;
    }
    public String getName(){
        return name;
    }
    public void setName(String nm){
        name=nm;
    }
    public String getUser(){
        return username;
    }
    public void setUsername(String usr){
        username=usr;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String em){
        email=em;
    }
    public String ToString(){
        return "Name:"+name+"; Username"+username+"; Password:"+password+" ;Email:"+email+".";
    }
    public String toJson(){
        return "{Name:'"+name+"',username:'"+username+"',password:'"+password+"',email:'"+email+"'}";

    }
}
