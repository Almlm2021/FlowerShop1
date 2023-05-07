package Fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    List<Product> products;

    public Warehouse() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public Product getProductById(int id){
        return products.get(id);
    }

    public void removeProduct(Product product) {
       products.remove(product);
    }

    void updateProduct(Product product, int q) {
      int productIndex=products.indexOf(product);
      if(productIndex==-1){
          System.out.println("Sorry this product is not found");
      }
      products.get(productIndex).setQuantity(q);
    }
    void viewWarehouse() {
        System.out.println("Products in the warehouse:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    void sortByType() {
        products.sort((product1, product2) -> product1.getType().compareTo(product2.getType()));
        System.out.println("Warehouse products sorted by type:");
        for (Product product : products) {
            System.out.println(product);
        }

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
