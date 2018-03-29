package com.example.hph1995.multipurposebooking;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class Registration extends AppCompatActivity {

    private EditText txtFullName, txtUserName, txtPassword, txtEmail, txtContactNo, txtCity, txtState, txtCountry;
    private RadioGroup rdgbGender;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle(R.string.registration);

        initializeUI();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initializeUI(){
        txtFullName = (EditText)findViewById(R.id.txtFullName);
        txtUserName = (EditText)findViewById(R.id.txtUserName);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtEmail = (EditText)findViewById(R.id.txtEmail);
        txtContactNo = (EditText)findViewById(R.id.txtContactNo);
        txtCity = (EditText)findViewById(R.id.txtCity);
        txtState = (EditText)findViewById(R.id.txtState);
        txtCountry = (EditText)findViewById(R.id.txtCountry);
        rdgbGender = (RadioGroup)findViewById(R.id.rdgbGender);
        btnRegister = (Button)findViewById(R.id.btnRegister);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            this.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
