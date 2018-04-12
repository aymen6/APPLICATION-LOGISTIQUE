package com.example.iset.testproject;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetAllProducts extends AppCompatActivity {

    List<Product>products;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_products);

        recyclerView=(RecyclerView)findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("All Product");

                getDataWithvolley();





    }

    private void getDataWithvolley() {
        String url = "http://192.168.43.175/test/get_product.php";
        StringRequest productRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);//TODO
                    JSONArray productsArray = object.getJSONArray("products");
                    products= new ArrayList<>();
                    for (int i = 0; i < productsArray.length(); i++) {
                        JSONObject currentobject = productsArray.getJSONObject(i);
                        String id = currentobject.getString("id");
                        String etat = currentobject.getString("code");

                        String productname = currentobject.getString("name");
                        String productprice = currentobject.getString("active");
                        String productpicture = currentobject.getString("weight");
                             Product product = new Product(id, etat , productname, productprice, productpicture);
                            products.add(product);






                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                //TODO adapter of list or recyclier view
                ProductAdapter adapterprod =new ProductAdapter(products);
                recyclerView.setAdapter(adapterprod);


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(GetAllProducts.this, "error", Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();
            }
        });
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        productRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(this).add(productRequest);
    }

}

