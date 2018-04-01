package com.example.toti.tokenauth;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;


public class register extends AppCompatActivity {
    private TextInputLayout nm,usr,em,psw;
    private TextView txtLogin;
    private TextInputEditText nmInput,usrInput,emInput,pswInput;
    private Button btnReg;
    private ConstraintLayout cLayout;
    private Animation leftToRight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_register);
        nm=(TextInputLayout) findViewById(R.id.txtName);
        usr=(TextInputLayout) findViewById(R.id.txtUsername);
        em=(TextInputLayout) findViewById(R.id.txtEmail);
        psw=(TextInputLayout) findViewById(R.id.txtPassword);
        txtLogin=(TextView)findViewById(R.id.txtLogin);
        nm.setHint("Name");
        usr.setHint("Username");
        em.setHint("E-mail");
        psw.setHint("Password");
        cLayout=(ConstraintLayout)findViewById(R.id.cLayout);
        leftToRight= AnimationUtils.loadAnimation(this,R.anim.lefttoright);
        cLayout.setAnimation(leftToRight);
        nmInput=(TextInputEditText)findViewById(R.id.txtInputName);
        usrInput=(TextInputEditText)findViewById(R.id.txtInputUsername);
        emInput=(TextInputEditText)findViewById(R.id.txtInputEmail);
        pswInput=(TextInputEditText)findViewById(R.id.txtInputPassword);
        btnReg=(Button)findViewById(R.id.btnRegister);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnReg.setEnabled(false);
                userModel usr1=new userModel(nmInput.getText().toString(),usrInput.getText().toString(),pswInput.getText().toString(),emInput.getText().toString());
                if(!(usr1.getName()==null||usr1.getUser()==null||usr1.getEmail()==null)) {
                    new postData().execute(new DBManager().getAddressApi(), usr1.toJson());
                }
            }
        });
    }
    class postData extends AsyncTask<String,String,String> {
        protected ProgressDialog pd = new ProgressDialog(register.this);
        protected userModel usr1;
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pd.setTitle("Please wait..");
            pd.show();
        }
        @Override
        protected String doInBackground(String... params){
            String urlString =params[0];
            HTTPDataHandler hh = new HTTPDataHandler();
            hh.postHttpData(urlString,params[1]);
            return "";
        }
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            pd.setTitle("Success!");
            btnReg.setEnabled(true);
            finish();
        }

    }

}
