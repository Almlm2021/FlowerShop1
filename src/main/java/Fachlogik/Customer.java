package Fachlogik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User implements Serializable {
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
        }else {
           for(Order a:orders){
               System.out.println(a);
               a.viewOrder();
           }
        }
    }
    public List<Order>getO(){
        return orders;
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
    public void placeOrder(Warehouse warehouse,boolean b) {
        if(warehouse.getProducts().isEmpty()){
            System.out.println("No Product in the Shop yet");
        } else {
            if(getCart().getCartItems().size()>0) {
                for (CartItem cartItem :getCart().cartItems) {
                    Product product = cartItem.getProduct();
                    int quantity = cartItem.getQuantity();
                    quantity *= -1;

                    // Update warehouse quantity
                    warehouse.updateProduct(product, quantity);

                }
                //getCart().cartItems.clear();
                Order o=null;
                if(b) {
                   o = new Order("placed", cart,true);
                    orders.add(o);
                }else{
                     o = new Order("placed", cart,false);
                    orders.add(o);
                }
                this.cart=new Cart();
                System.out.println("Order placed successfully. ");
            }else{
                System.out.println("No Product in the Cart yet");
            }
        }
    }
    public void cancelOrder(Warehouse warehouse,int id) {

      for(Order o:orders){
          if(o.getId()==id&&o.getStatus().equals("Canceled")){
              System.out.println("This order is already canceled");

              return;
          }
      }
        Order a1;
        for(Order a:orders){
           if(a.getId()==id){
                a1=a;
                a1.setStatus("Canceled");
                System.out.println("The Order with the ID= "+a1.getId()+" has been canceled");
               for (CartItem cartItem : cart.cartItems) {
                   Product product = cartItem.getProduct();
                   int quantity = cartItem.getQuantity();
                   // Update warehouse quantity
                   warehouse.updateProduct(product, quantity);
               }
               System.out.println("Cancel placed successfully.");
            return;
           }
        }
        System.out.println("to cancel Order not found");


        /*for (CartItem cartItem : cart.cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            // Update warehouse quantity
            warehouse.updateProduct(product, quantity);
        }*/

    }

}