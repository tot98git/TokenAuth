package com.example.toti.tokenauth;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;


public class LoginScreen extends AppCompatActivity {
    private Button btnLogin;
    private TextInputLayout usr,psw;
    private TextView register;
    private LinearLayout ln;
    Animation upToDown,downToUp;
    public static tokenCollection tokenColl = new tokenCollection();
    private TextInputEditText usrTxt,pswTxt;
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_login_screen);
        usr=(TextInputLayout)findViewById(R.id.txtLoginUsername);
        psw=(TextInputLayout) findViewById(R.id.txtLoginPassword);
        usrTxt=(TextInputEditText) findViewById(R.id.txtInpUsername);
        pswTxt=(TextInputEditText) findViewById(R.id.txtInpPsw);
        ln=(LinearLayout)findViewById(R.id.linearLayout);
        usr.setHint("Username");
        psw.setHint("Password");
        upToDown= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        downToUp=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        btnLogin=(Button)findViewById(R.id.btnLogin);
        register=(TextView)findViewById(R.id.txtRegister);
        ln.setAnimation(upToDown);
        btnLogin.setAnimation(downToUp);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginScreen.this,register.class));
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnLogin.setClickable(false);
                hideSoftKeyboard(LoginScreen.this);
                if(usrTxt!=null) {
                    new getData().execute(new DBManager().checkIfRecordExists(usrTxt.getText().toString(), pswTxt.getText().toString()), usrTxt.getText().toString(),usrTxt.getText().toString(),pswTxt.getText().toString());

                }
            }
        });
    }
    class getData extends AsyncTask<String,String, String>{
        private ProgressDialog pd = new ProgressDialog(LoginScreen.this);
        private Boolean ifExists=false;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.setTitle("Checking..");
            pd.show();
        }
        @Override
        protected String doInBackground(String... params) {
            String urlString = params[0];
            System.out.println(urlString);
            HTTPDataHandler hh = new HTTPDataHandler();
            if (params[1].length() != 0 || params[2].length() != 0){
                if (hh.getHttpData(urlString).contains("1")) {
                    ifExists = true;
                }
        }
            return "";
        }
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            if(ifExists==true){
                pd.setTitle("Success!");
                pd.dismiss();
                Intent i = new Intent(LoginScreen.this,tokenActivity.class);
                i.putExtra("username",usrTxt.getText().toString());
                startActivity(i);
            }
            btnLogin.setClickable(true);

        }
    }

}
