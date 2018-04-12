package com.example.iset.testproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Menu_App extends Activity {
    TextView txtTitle;
    Button BtnLogout,BtnView,BtnSearch,BtnScan;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_menu__app);

        txtTitle = (TextView) findViewById(R.id.txtTitle);
        BtnLogout = (Button) findViewById(R.id.btnlogout);
        BtnScan = (Button) findViewById(R.id.btnscan);
        BtnSearch = (Button) findViewById(R.id.btnsearch);
        BtnView = (Button) findViewById(R.id.btnview);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoppinsRegular.ttf");
        txtTitle.setTypeface(myCustomFont);
        BtnLogout.setTypeface(myCustomFont);
        BtnScan.setTypeface(myCustomFont);
        BtnSearch.setTypeface(myCustomFont);
        BtnView.setTypeface(myCustomFont);
        BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_App.this,GetAllProducts.class);
                startActivity(intent);
            }
        });
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu_App.this,Login.class);
                startActivity(intent);
            }
        });
        BtnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu_App.this,AllCommande.class);
                startActivity(myIntent);
            }
        });
        BtnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Menu_App.this,ScanProduct.class);
                 startActivity(myIntent);
            }
        });
        BtnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //Logout();

            }
        });
    }
    /*void btnScaner(View v){
        Intent myIntent = new Intent(this,ScanProduct.class);
        startActivity(myIntent);
    }*/

    void btnsearch(View v){
        Intent myIntent = new Intent(this,Login.class);
        startActivity(myIntent);
    }
}
