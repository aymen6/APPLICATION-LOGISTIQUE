package com.example.iset.testproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ShowProduct extends AppCompatActivity {
    TextView TxtId,TxtCtActive,TxtCtNom,txtemplac,type,quantite,std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Show Produit");
txtemplac=(TextView)findViewById(R.id.txtemplc);

        TxtId =(TextView)findViewById(R.id.txtid);

        TxtCtNom = (TextView) findViewById(R.id.txtCtNom);
         TxtCtActive = (TextView) findViewById(R.id.txtCtActive);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoppinsRegular.ttf");
         TxtCtNom.setTypeface(myCustomFont);

         Intent Intent = getIntent();
         String code_product = Intent.getStringExtra("code_product");
         String nom_product = Intent.getStringExtra("nom_product");
          String id_produit = Intent.getStringExtra("id_produit");
        String active = Intent.getStringExtra("active");


        TxtId.setText(id_produit);
        TxtCtNom.setText(nom_product);
        txtemplac.setText(code_product);
        TxtCtActive.setText(active);

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}