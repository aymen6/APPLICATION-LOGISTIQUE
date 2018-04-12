package com.example.iset.testproject;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Aymen on 13/12/2017.
 */

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.CommandeHolder> {
    List<Commande>  Commandelist;

    public CommandeAdapter(List<Commande> Commandelist) {
        this.Commandelist = Commandelist;
    }

    @Override
    public CommandeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row,parent,false);
        CommandeHolder holder= new CommandeHolder(row);
        return holder;    }

    @Override
    public void onBindViewHolder(CommandeHolder holder, int position) {
        Commande Commande=Commandelist.get(position);
        holder.name.setText(Commande.getname());
        holder.order_line.setText(Commande.getOrder_line());
        holder.partner_id.setText(Commande.getPartner_id());
        holder.date_order.setText(Commande.getDate_order());
    }

    @Override
    public int getItemCount() {
        return Commandelist.size();
    }

    public class CommandeHolder extends RecyclerView.ViewHolder {
        TextView name,order_line,partner_id,date_order;
        public CommandeHolder(View itemView) {
            super(itemView);
            order_line=(TextView)itemView.findViewById(R.id.id_prod);
            name=(TextView)itemView.findViewById(R.id.nom_prod);
            partner_id=(TextView)itemView.findViewById(R.id.price_prod);
            date_order=(TextView)itemView.findViewById(R.id.direction) ;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "product"+partner_id.getText().toString(), Toast.LENGTH_SHORT).show();

                }
            });

        }

    }
}