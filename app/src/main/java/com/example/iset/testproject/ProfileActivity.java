package com.example.iset.testproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView   textViewUsername ;
Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        //if the user is not logged in
        //starting the login activity

        textViewUsername=(TextView)findViewById(R.id.user_name);
button=(Button)findViewById(R.id.Nextbtn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this,Menu_App.class);
                startActivity(intent);
            }
        });



        //getting the current user
        User user = SharedPrefManager.getInstance(this).getUser();

        //setting the values to the textviews
        textViewUsername.setText(user.getEmail());

        //when the user presses logout button
        //calling the logout method

    }
}