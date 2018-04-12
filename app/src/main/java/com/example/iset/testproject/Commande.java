package com.example.iset.testproject;

/**
 * Created by Aymen on 13/12/2017.
 */

public class Commande {
    String name;
    String   order_line;
    String  partner_id;
    String date_order;
    public String getOrder_line() {
        return order_line;
    }
public Commande(){}
    public void setOrder_line(String order_line) {
        this.order_line = order_line;
    }

    public Commande(String name, String partner_id, String date_order , String order_line) {
        this.name = name;
        this.partner_id = partner_id;
        this.date_order = date_order;
    this.order_line=order_line;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(String partner_id) {
        this.partner_id = partner_id;
    }

    public String getDate_order() {
        return date_order;
    }

    public void setDate_order(String date_order) {
        this.date_order = date_order;
    }



}
