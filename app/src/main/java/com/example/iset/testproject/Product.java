package com.example.iset.testproject;

/**
 * Created by Aymen on 13/11/2017.
 */

class Product {
   private  String  id, productname, productprice, productpicture,etat;

    public Product(String id,String etat, String productname, String productprice, String productpicture) {
        this.id = id;
        this.productname = productname;
        this.productprice = productprice;
        this.productpicture = productpicture;
        this.etat=etat;
    }

    public Product() {
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductpicture() {
        return productpicture;
    }

    public void setProductpicture(String productpicture) {
        this.productpicture = productpicture;
    }

    public String getId() {
        return id;
    }
}
