package Fachlogik;

import java.util.List;

public class Product {
    static int x;
    int id;
    String name;
    double price;
    String color;
    String type;
    int quantity;

    public Product(String n,double p,String c,String t,int q){
        id=++x;
        this.name=n;
        this.price=p;
        this.color=c;
        this.type=t;
        this.quantity=q;

    }

    @Override
    public String toString() {

        return "the Product withe the ID: "+getId()+" is: "+getName()+" and has the type : "+getType()+" of flowers. The shop has "+getQuantity()+" of it. \n It has the color of : "+getColor()+" .\n this Product costs : "+getPrice()+" €";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

