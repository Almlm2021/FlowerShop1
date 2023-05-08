package Fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    Cart cart;
    List<Order> orders;
    List<FavoriteProduct> favoriteProducts;
    public Customer(int id, String name, String email, String password) {
        super(id, name, email, password);
        this.cart = new Cart();
        this.orders = new ArrayList<Order>();
        this.favoriteProducts = new ArrayList<>();
    }

    public void viewShop(Warehouse wh) {
        System.out.println("This shop has the following products:");
        wh.viewWarehouse();
    }

    public void sortByColor(Warehouse wh) {
        wh.sortByColor();
    }

    public void sortByPrice(Warehouse wh) {
        wh.sortByPrice();
    }

    public void sortByType(Warehouse wh) {
        wh.sortByType();
    }

    public List<FavoriteProduct> viewFavorites() {
        return favoriteProducts;
    }

    public void addFavorite(FavoriteProduct fp) {
        favoriteProducts.add(fp);
    }

    public Product removeFavorite(Product fp) {
         favoriteProducts.remove(fp);
         return fp;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }


    public void addOrder(Order o){
        orders.add(o);
    }

    public List<FavoriteProduct> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }
}