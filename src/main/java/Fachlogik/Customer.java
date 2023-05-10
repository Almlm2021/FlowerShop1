package Fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    Cart cart;
    List<Order> orders;
    List<FavoriteProduct> favoriteProducts;
    public Customer( String name, String email, String password) {
        super( name, email, password);
        this.cart = new Cart();
        this.orders = new ArrayList<Order>();
        this.favoriteProducts = new ArrayList<>();
    }

    public void viewShop(Warehouse wh) {
        System.out.println("This shop has the following products:");
        wh.viewWarehouse();
    }

    public void sortByColor(Warehouse wh) {
        if(wh.products.isEmpty()){
            System.out.println("The Shop is Empty!");
        }else {
            wh.sortByColor();
        }
    }

    public void sortByPrice(Warehouse wh) {
        if(wh.products.isEmpty()){
            System.out.println("The Shop is Empty!");
        }else {
            wh.sortByPrice();
        }
    }

    public void sortByType(Warehouse wh) {
        if(wh.products.isEmpty()){
            System.out.println("The Shop is Empty!");
        }else {
            wh.sortByType();
        }
    }

    public List<FavoriteProduct> viewFavorites() {

            return favoriteProducts;

    }

    public void addFavorite(FavoriteProduct fp) {
        favoriteProducts.add(fp);
    }

    public void removeFavorite(Product fp) {
        for(FavoriteProduct a:favoriteProducts){
            if(a.getProduct().getId()==fp.getId())
                favoriteProducts.remove(a);
            System.out.println("Product is removed from the favorite");
            return;
        }
        System.out.println("Product not found in the favorite");

    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public void getOrders() {
        if(orders.isEmpty()){
            System.out.println("No orders yet");
         return ;
        }else {
           for(Order a:orders){
               System.out.println(a);
           }
        }
    }


    public void addOrder(Order o){
        orders.add(o);
    }

    /*public List<FavoriteProduct> getFavoriteProducts() {
        return favoriteProducts;
    }*/

    public void setFavoriteProducts(List<FavoriteProduct> favoriteProducts) {
        this.favoriteProducts = favoriteProducts;
    }
}