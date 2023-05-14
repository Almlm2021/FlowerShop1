package Fachlogik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Warehouse implements Serializable {
    List<Product> products;

    public Warehouse() {
        this.products = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public Product getProductById(int id){
        Product p=null;
        for(int i=0;i< products.size();i++){
            if(products.get(i).getId()==id){
               p=products.get(i);
            }
        }

        return p;
    }

    public void removeProduct(Product product) {
       products.remove(product);
    }

    void updateProduct(Product product, int q) {
      int productIndex=products.indexOf(product);
      if(productIndex==-1){
          System.out.println("Sorry this product is not found");
          return;
      }
      products.get(productIndex).setQuantity(q);
    }
    void viewWarehouse() {
        System.out.println("Products in the warehouse:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void sortByType() {
        products.sort((product1, product2) -> product1.getType().compareTo(product2.getType()));
        System.out.println("products sorted by type:");
        for (Product product : products) {
            System.out.println(product);
        }
    }
    public void sortByColor() {
        products.sort(Comparator.comparing(Product::getColor));
        System.out.println("products sorted by Color:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void sortByPrice() {
        if(products.size()==0){
            System.out.println("No products to sort");
            return;
        }else {
            products.sort(Comparator.comparingDouble(Product::getPrice));
            System.out.println("products sorted by Price:");
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
    public void sortByID() {
        if(products.size()==0){
            System.out.println("No products to sort");
            return;
        }else {
            getProducts().sort(Comparator.comparingInt(Product::getId));
            for (Product product : products) {
                System.out.println(product);
            }
        }

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
