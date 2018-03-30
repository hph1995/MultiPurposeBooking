package com.example.hph1995.multipurposebooking;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{

    private EditText txtusername, txtpass;
    private Button btnLogin, btnRegister;
    private TextView txtForgotPass;
    String getUserName, getPassWord;
    String URL = "http://172.17.6.246/php/MultipurposeHall/checkLogin.php";

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        initializeUI();

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        txtForgotPass.setOnClickListener(this);

    }

    public void initializeUI()
    {
        txtusername = (EditText)findViewById(R.id.txtusername);
        txtpass = (EditText)findViewById(R.id.txtpass);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnRegister = (Button)findViewById(R.id.btnRegister);
        txtForgotPass = (TextView)findViewById(R.id.txtForgetPass);

        mProgress = new ProgressDialog(LoginScreen.this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            getUserName = txtusername.getText().toString().trim();
            getPassWord = txtpass.getText().toString().trim();
            if(getUserName.equalsIgnoreCase("")) {
                txtusername.setError("Please enter username");
            }
            else if(getPassWord.equalsIgnoreCase("")){
                txtpass.setError("Please enter password");
            }
            else
                checkLogin();
        }
        else if(view == btnRegister){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), Registration.class);
            startActivity(intent);
        }
        else if(view == txtForgotPass){
            Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void checkLogin(){
        mProgress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("Login Success!!!"))
                        {
                            mProgress.dismiss();
                            Toast.makeText(LoginScreen.this,response,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), HomePage.class);
                            startActivity(intent);
                        }
                        else{
                            mProgress.dismiss();
                            Toast.makeText(LoginScreen.this,"Invalid Username and Password!!",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginScreen.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("username",getUserName);
                params.put("password",getPassWord);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
