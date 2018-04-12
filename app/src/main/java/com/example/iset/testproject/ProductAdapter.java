package com.example.iset.testproject;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Aymen on 17/11/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    List<Product>Productlist;
    public ProductAdapter(List<Product> Productlist){
        this.Productlist=Productlist;
    }

    @Override
    public ProductHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        ProductHolder holder= new ProductHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(ProductHolder holder, int position) {
        Product product=Productlist.get(position);
        holder.id.setText(product.getId());
        holder.nom_produit.setText(product.getProductname());
        holder.price_produit.setText(product.getProductprice());
        holder.etat.setText(product.getEtat());

    }

    @Override
    public int getItemCount() {
        return Productlist.size();
    }
    class ProductHolder extends RecyclerView.ViewHolder{
        TextView id,nom_produit,price_produit,etat;
        public ProductHolder(View itemView) {
            super(itemView);
            id=(TextView)itemView.findViewById(R.id.id_prod);
            nom_produit=(TextView)itemView.findViewById(R.id.nom_prod);
            price_produit=(TextView)itemView.findViewById(R.id.price_prod);
            etat=(TextView)itemView.findViewById(R.id.direction) ;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "product"+id.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
}
