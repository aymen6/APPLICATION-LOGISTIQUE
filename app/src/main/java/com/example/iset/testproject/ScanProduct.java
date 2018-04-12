package com.example.iset.testproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dd.CircularProgressButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ScanProduct extends AppCompatActivity {
    CircularProgressButton BtnSearch;
    Button scancode;
    EditText TxtSearch;
    ImageView img_scan;
    String res;
    String url = "http://192.168.43.175/test/get_product_by_code.php";
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_product);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Scanner BarCode");
        getSupportActionBar().setLogo(R.drawable.azz);


scancode=(Button)findViewById(R.id.scanbtn);
        scancode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInfoProduct();
            }
        });
        TxtSearch = (EditText) findViewById(R.id.txtSearch);
       // BtnSearch = (CircularProgressButton) findViewById(R.id.btnsch);
      /*  BtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TxtSearch.getText().toString()==""){
                    Toast.makeText(ScanProduct.this, "you must enter a code ", Toast.LENGTH_SHORT).show();

                }else {
                    Intent intent = new Intent(ScanProduct.this, ShowProduct.class);
                    intent.putExtra("KEY", TxtSearch.getText().toString());
                }
            }
        });*/
        img_scan=(ImageView)findViewById(R.id.img_scan);
        img_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScanProduct.this, "scanned", Toast.LENGTH_SHORT).show();
                scan();

            }
        });

    }

    private void scan(){


        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(false);
        integrator.setBarcodeImageEnabled(false);
        integrator.initiateScan();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Log.d("ScanProduct", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("ScanProduct", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                TxtSearch.setText(result.getContents());



            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void search(View v) {
        if(BtnSearch.getProgress()== 0){
            BtnSearch.setProgress(40);


        } else if(BtnSearch.getProgress() == -1 ){
            BtnSearch.setProgress(0);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(TxtSearch.getText().toString()!=""){
                    BtnSearch.setProgress(100);
                    Intent myintent = new Intent(ScanProduct.this,ShowProduct.class);
                    startActivity(myintent);

                }else {
                    BtnSearch.setProgress(-1);
                }

            }
        }, 3000);
    }
    @Override
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

    private void getInfoProduct()
    {
        if (TextUtils.isEmpty(TxtSearch.getText().toString())) {
            TxtSearch.setError("Please enter code barre");
            TxtSearch.requestFocus();
            return;
        }
        StringRequest productRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("existe");
                    String code_product=jsonResponse.getString("code_produit");
                    String nom_product=jsonResponse.getString("nom_produit");
                    String type_produit=jsonResponse.getString("type");
                    String id_produit=jsonResponse.getString("id");
                    String active=jsonResponse.getString("active");


                    if (success) {
                        Toast.makeText(ScanProduct.this, "existe"+success, Toast.LENGTH_SHORT).show();

                        Intent intent=new Intent(ScanProduct.this,ShowProduct.class);
                        intent.putExtra("code_product",code_product);
                        intent.putExtra("nom_product",nom_product);
                        intent.putExtra("type_produit",type_produit);
                        intent.putExtra("id_produit",id_produit);
                        intent.putExtra("active",active);


                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(ScanProduct.this, "access dinded", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(ScanProduct.this, "error maybe cnx", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String>parame=new HashMap<>();
                parame.put("barcode",TxtSearch.getText().toString().trim());
                parame.put("email", SharedPrefManager.getUser().getEmail());
                parame.put("password", SharedPrefManager.getUser().getPwd());

                return parame;
            }
        };
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        productRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(this).add(productRequest);
    }


    }

