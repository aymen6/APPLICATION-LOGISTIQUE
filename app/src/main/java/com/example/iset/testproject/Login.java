package com.example.iset.testproject;

import android.content.Context;
 import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
 import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    private EditText etUseremail;
    private EditText etPassword;
    private TextView text;
    CircularProgressButton bLogin;
    ConnectionDetector connectionDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);
        etUseremail = (EditText) findViewById(R.id.txtEmail);
        etPassword = (EditText) findViewById(R.id.Password);
        bLogin = (CircularProgressButton) findViewById(R.id.btnLogin);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoppinsRegular.ttf");
        etUseremail.setTypeface(myCustomFont);
        etPassword.setTypeface(myCustomFont);
        bLogin.setTypeface(myCustomFont);

        initView();
        /* Intent Intent = getIntent();
         String message = Intent.getStringExtra("KEY");
         text.setText(message);
      */   bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MethodLoginuser();
            }
        });
    }

    private void initView() {

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bLogin.getProgress()== 0){
                    bLogin.setProgress(40);
                } else if(bLogin.getProgress() == -1 ){
                    bLogin.setProgress(0);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(etUseremail.getText().toString().equals("")){
                            bLogin.setProgress(100);
                            Intent myintent = new Intent(Login.this,SplashScreen.class);
                            startActivity(myintent);
                        }else {
                            bLogin.setProgress(-1);
                        }

                    }
                }, 3000);
            }
        });
    }
    private  void MethodLoginuser()
    {
        final String useremail = etUseremail.getText().toString();
        final String userpassword = etPassword.getText().toString();

        //validating inputs
        /*if (TextUtils.isEmpty(useremail)) {
            etUseremail.setError("Please enter your Email");
            etUseremail.requestFocus();
            return;
        }*/
if(verif()){
    etUseremail.setError("Your Email must have @");
    etUseremail.requestFocus();
}
        if (TextUtils.isEmpty(userpassword)) {
            etPassword.setError("Please enter your password");
            etPassword.requestFocus();
            return;
        }
         String url ="http://192.168.43.175/test/login.php";
        StringRequest productRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                         String name=jsonResponse.getString("name");
                        String email=jsonResponse.getString("email");
                        String password=jsonResponse.getString("pwd");
                        User user=new User(name,email,password);
                         SharedPrefManager.getInstance(getApplicationContext()).userLogin(user);

                        //starting the profile activity
                          finish();
                         startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    }else{
                        Toast.makeText(Login.this, "acce", Toast.LENGTH_LONG).show();

                    }

                 } catch (JSONException e1) {
                    e1.printStackTrace();

                }
            }
            // ici fin du stringrequest


        }



                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Login.this, "error", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parame=new HashMap<>();
                parame.put("email",etUseremail.getText().toString().trim());
                parame.put("password",etPassword.getText().toString().trim());

                return parame;
            }
        };
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        productRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(this).add(productRequest);
    }


    public static boolean isValidEmailAddress(String emailAddress) {
        String emailRegEx;
        Pattern pattern;
        // Regex for a valid email address
        emailRegEx = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$";
        // Compare the regex with the email address
        pattern = Pattern.compile(emailRegEx);
        Matcher matcher = pattern.matcher(emailAddress);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }
    private Boolean verif(){
    if(!(isValidEmailAddress(etUseremail.getText().toString()))){
         etUseremail.setError("Please enter your Email");
        etUseremail.requestFocus();
    }
return false;
     }
    void verifCnx(View v){
        if(connectionDetector.isConnected()){
            Toast.makeText(this,"Connected",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"Not Connected",Toast.LENGTH_SHORT).show();

        }
    }
}
