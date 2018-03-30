package com.example.hph1995.multipurposebooking;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration extends AppCompatActivity {

    private EditText txtFullName, txtUserName, txtPassword, txtEmail, txtContactNo, txtCity, txtState, txtCountry;
    private RadioGroup rdgbGender;
    private Button btnRegister;
    private String getFullName, getGender, getUserName, getPassword, getEmail, getContactNo, getCity, getState, getCountry;

    String URL = "http://172.17.6.246/php/MultipurposeHall/checkRegister.php";
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setTitle(R.string.registration);

        initializeUI();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFullName = txtFullName.getText().toString().trim();

                //Get Selected Radio Button Value
                int selectedId = rdgbGender.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(selectedId);
                getGender = radioButton.getText().toString().trim();

                getUserName = txtUserName.getText().toString().trim();
                getPassword = txtPassword.getText().toString().trim();
                getEmail = txtEmail.getText().toString().trim();
                getContactNo = txtContactNo.getText().toString().trim();
                getCity = txtCity.getText().toString().trim();
                getState = txtState.getText().toString().trim();
                getCountry = txtCountry.getText().toString().trim();

                if(getFullName.equalsIgnoreCase("")) {
                    txtFullName.setError("Please enter your full name");
                    txtFullName.requestFocus();
                }
                else if(getUserName.equalsIgnoreCase("")){
                    txtUserName.setError("Please enter your username");
                    txtUserName.requestFocus();
                }
                else if(getPassword.equalsIgnoreCase("")){
                    txtPassword.setError("Please enter your password");
                    txtPassword.requestFocus();
                }
                else if(getEmail.equalsIgnoreCase("")){
                    txtEmail.setError("Please enter your email");
                    txtEmail.requestFocus();
                }
                else if(!isValidEmail(getEmail)){
                    txtEmail.setError("Invalid email");
                    txtEmail.setText("");
                }
                else if(getContactNo.equalsIgnoreCase("")){
                    txtContactNo.setError("Please enter your contact number");
                    txtContactNo.requestFocus();
                }
                else if(getCity.equalsIgnoreCase("")){
                    txtCity.setError("Please enter your city");
                    txtCity.requestFocus();
                }
                else if(getState.equalsIgnoreCase("")){
                    txtState.setError("Please enter your state");
                    txtState.requestFocus();
                }
                else if(getCountry.equalsIgnoreCase("")){
                    txtCountry.setError("Please enter your country");
                    txtCountry.requestFocus();
                }
                else
                    checkRegister();
            }
        });
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

        mProgress = new ProgressDialog(Registration.this);
        mProgress.setTitle("Processing...");
        mProgress.setMessage("Please wait...");
        mProgress.setCancelable(false);
        mProgress.setIndeterminate(true);
    }

    public void checkRegister(){
        mProgress.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.contains("Register Success!!!"))
                        {
                            mProgress.dismiss();
                            Toast.makeText(Registration.this,response,Toast.LENGTH_LONG).show();
                            Intent intent = new Intent();
                            intent.setClass(getApplicationContext(), LoginScreen.class);
                            startActivity(intent);
                        }
                        else{
                            mProgress.dismiss();
                            Toast.makeText(Registration.this,"Unable to register!!",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Registration.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("fullname",getFullName);
                params.put("gender",getGender);
                params.put("username",getUserName);
                params.put("password",getPassword);
                params.put("email",getEmail);
                params.put("contactno",getContactNo);
                params.put("city",getCity);
                params.put("state",getState);
                params.put("country",getCountry);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
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
