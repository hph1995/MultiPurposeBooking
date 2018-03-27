package com.example.hph1995.multipurposebooking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener{

    private EditText txtusername, txtpass;
    private Button btnLogin, btnRegister;
    private TextView txtForgotPass;

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
    }

    @Override
    public void onClick(View view) {
        if(view == btnLogin){
            Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
        }
        else if(view == btnRegister){
            Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show();
        }
        else if(view == txtForgotPass){
            Toast.makeText(this, "Forgot Password", Toast.LENGTH_SHORT).show();
        }
    }
}
