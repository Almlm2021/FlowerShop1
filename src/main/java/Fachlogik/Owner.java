package Fachlogik;

import java.util.List;

public class Owner extends User {
    private Shop shop;

    public Owner(int id, String name, String email, String password, Shop shop) {
        super(id,name,email,password);
        this.shop = shop;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void addProduct(Product product) {
        shop.addProduct(product);
    }

    public void removeProduct(Product product) {
        shop.removeProduct(product);
    }

    public void updateProduct(Product product,int quan) {
        shop.updateProduct(product,quan);
    }

    public List<Product> viewWarehouse() {
        return shop.getProducts();
    }

    public void sortByType() {
        shop.getProducts().sort((Product p1, Product p2) -> p1.getType().compareTo(p2.getType()));
    }
}
