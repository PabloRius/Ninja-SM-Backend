package com.sm_ninja.sm_ninja.model;

import com.opencsv.bean.CsvBindByName;

public class Product {

    @CsvBindByName(column = "Name")
    private String name;

    @CsvBindByName(column = "Price")
    private double price;

    @CsvBindByName(column = "Relative Price")
    private double relative_price;

    @CsvBindByName(column = "Units")
    private String units;

    @CsvBindByName(column = "Supermarket")
    private String supermarket;

    @CsvBindByName(column = "Link")
    private String link;

    @CsvBindByName(column = "Image")
    private String image;

    public Product(){}
    
    public Product(String name, double price, double relative_price, String units, String supermarket, String link, String image){
        this.name = name;
        this.price = price;
        this.relative_price = relative_price;
        this.units = units;
        this.supermarket = supermarket;
        this.link = link;
        this.image = image;
    }

    public String getName(){
        return this.name;
    }

    public double getPrice(){
        return this.price;
    }

    public double getRelativePrice(){
        return this.relative_price;
    }

    public String getUnits(){
        return this.units;
    }

    public String getSupermarket(){
        return this.supermarket;
    }

    public String getLink(){
        return this.link;
    }

    public String getImage(){
        return this.image;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", supermarket='" + supermarket + '\'' +
                ", relative price='" + relative_price + '\'' +
                ", units='" + units + '\'' +
                '}';
    }

}
