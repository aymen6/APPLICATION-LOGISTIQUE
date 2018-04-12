package com.example.iset.testproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AllCommande extends AppCompatActivity {
    List<Commande> Commandes;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_commande);
        recyclerView=(RecyclerView)findViewById(R.id.rec_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Liste Demande de prix");
        getSupportActionBar().setLogo(R.drawable.azz);

        getDataWithvolley();

    }
    private void getDataWithvolley() {
        String url = "http://192.168.43.175/test/allcommande.php";
        StringRequest productRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);//TODO
                    JSONArray productsArray = object.getJSONArray("products");
                    Commandes= new ArrayList<>();
                    for (int i = 0; i < productsArray.length(); i++) {
                        JSONObject currentobject = productsArray.getJSONObject(i);
                        String name = currentobject.getString("name");
                        String order_line = currentobject.getString("order_line");

                        String partner_id = currentobject.getString("partner_id");
                        String date_order = currentobject.getString("date_order");
                         Commande commande = new Commande(name, order_line , partner_id, date_order);
                        Commandes.add(commande);






                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
                //TODO adapter of list or recyclier view
                CommandeAdapter adapterprod =new CommandeAdapter(Commandes);
                recyclerView.setAdapter(adapterprod);


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(AllCommande.this, "error", Toast.LENGTH_SHORT).show();
                volleyError.printStackTrace();
            }
        });
        int socketTimeout = 30000;//30 seconds - change to what you want
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        productRequest.setRetryPolicy(policy);
        Volley.newRequestQueue(this).add(productRequest);
    }

}
