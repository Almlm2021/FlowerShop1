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
        this.orders = new ArrayList<>();
        this.favoriteProducts = new ArrayList<>();
    }

    void viewShop(Shop shop) {
        System.out.println("This shop has the following products:");
        shop.viewProducts();
    }

    void sortByColor(Shop shop) {
        shop.sortByColor();
    }

    void sortByPrice(Shop shop) {
        shop.sortByPrice();
    }

    void sortByType(Shop shop) {
        shop.sortByType();
    }

    List<FavoriteProduct> viewFavorites() {
        return favoriteProducts;
    }

    void addFavorite(FavoriteProduct fp) {
        favoriteProducts.add(fp);
    }

    void removeFavorite(FavoriteProduct fp) {
        favoriteProducts.remove(fp);
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

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<FavoriteProduct> getFavoriteProducts() {
        return favoriteProducts;
    }

    public void setFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }
}