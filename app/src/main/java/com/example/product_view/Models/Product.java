package com.example.product_view.Models;

public class Product {
    private int id;
    private String productLabel;
    private int productStock;
    private Float productPrice;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
    public void setProductLabel(String productLabel){
        this.productLabel = productLabel;
    }
    public String getProductLabel(){
        return this.productLabel;
    }
    public void setProductStock(int productStock){
        this.productStock = productStock;
    }
    public int getProductStock(){
        return this.productStock;
    }
    public void setProductPrice(Float price){
        this.productPrice = productPrice;
    }
    public Float getProductPrice(){
        return this.productPrice;
    }
    public Product(String productLabel, int productStock, Float productPrice){
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productLabel = productLabel;
    }
}
