package com.example.toti.tokenauth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import org.w3c.dom.Text;

import java.util.Iterator;

import java.util.Map;

public class tokenActivity extends AppCompatActivity {
    private String username="";
    private TextView txtGivenUsername,tokenTest,txtResend;
    private Button btnLoginToken,btnBack;
    private tokenCollection tknColl = LoginScreen.tokenColl;
    private EditText token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_token);
        txtGivenUsername=(TextView)findViewById(R.id.txtGivenUsername);
        txtResend=(TextView)findViewById(R.id.txtResend);
        btnLoginToken=(Button)findViewById(R.id.btnLoginToken);
        btnBack=(Button)findViewById(R.id.btnBack);
        token=(EditText)findViewById(R.id.txtToken);
        Bundle extras = getIntent().getExtras();
        username=extras.getString("username");
        txtGivenUsername.setText(username.toUpperCase());
        final Toast tost = Toast.makeText(getApplicationContext(), "Code send!", Toast.LENGTH_SHORT    );
        txtResend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tost.show();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnLoginToken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               new checkToken().execute(token.getText().toString());
            }
        });
    }
    class checkToken extends AsyncTask<String,String,String>{
        private ProgressDialog pd = new ProgressDialog(tokenActivity.this);
        private String result="Fail!";
        protected void onPreExecute(){
            super.onPreExecute();
            pd.setTitle("Checking if token is correct");
            pd.show();
        }

        @Override
        protected String doInBackground(String... string) {
            int tokenInt = 0;
            try{
                tokenInt=Integer.parseInt(string[0]);
            }
            catch (Exception e){
                System.out.println(e);
            }
            Boolean ifExists =tknColl.ifExists(tokenInt,username);
            System.out.println(ifExists);
            if(ifExists){
                result= "Success!";
            }
            return result;
        }
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            pd.setTitle("Success!");
            pd.dismiss();
        }
    }
}
