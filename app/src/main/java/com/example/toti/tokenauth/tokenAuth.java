package com.example.toti.tokenauth;

/**
 * Created by toti on 28.04.18.
 */

public class tokenAuth {
    private String key="&auth=androidapp";
    private StringBuilder url=new StringBuilder("http://10.0.2.2:7000/get?");
    public String generate(String username){
       url.append("type=generate&username="+username+key);
       return url.toString();
    }
    public String validate(String username,int token){
        url.append("type=validate&username="+username+"&token="+token+key);
        return url.toString();
    }

}
