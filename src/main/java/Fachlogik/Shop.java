package Fachlogik;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Shop {
    List<Product> products;

    public Shop(){
        this.products=new ArrayList<Product>();
    }
    void addProduct(Product product) {
            products.add(product);
    }

    void removeProduct(Product product) {
        products.remove(product);
    }

    public Product getProductById(int id){
        return products.get(id);
    }

    void updateProduct(Product product, int q) {
        int productIndex=products.indexOf(product);
        if(productIndex==-1){
            System.out.println("Sorry this product is not found");
        }
        products.get(productIndex).setQuantity(q);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public void viewProducts() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
    public void sortByColor() {
        products.sort(Comparator.comparing(Product::getColor));
        viewProducts();
    }

    public void sortByPrice() {
        products.sort(Comparator.comparingDouble(Product::getPrice));
        viewProducts();
    }

    public void sortByType() {
        products.sort(Comparator.comparing(Product::getType));
        viewProducts();
    }
}