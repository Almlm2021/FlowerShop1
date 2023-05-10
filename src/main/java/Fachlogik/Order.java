package Fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Order {
    static int x;
    int id;
    Customer customer;
   List<CartItem> cartItems;
    Cart cart;
    String status;
    boolean isBouquet;

    public Order(String s){
        this.id = ++x;
        this.cartItems = new ArrayList<CartItem>();
        this.status = s;
        this.isBouquet = false;
    }

        public void placeOrder(Warehouse warehouse,Customer customer1,Order o) {
            if(warehouse.getProducts().isEmpty()){
                System.out.println("No Product in the Shop yet");
            } else {
                if(customer1.getCart().getCartItems().size()>0) {
                    for (CartItem cartItem : cartItems) {
                        Product product = cartItem.getProduct();
                        int quantity = cartItem.getQuantity();
                        quantity *= -1;

                        // Update warehouse quantity
                        warehouse.updateProduct(product, quantity);
                        customer.getCart().cartItems.remove(cartItem);
                    }
                   o.setStatus("Placed");
                    System.out.println("Order placed successfully. Order ID: " + getId());
                    customer1.addOrder(o);
                }else{
                    System.out.println("No Product in the Cart yet");
                }
            }
        }


    void cancelOrder(Warehouse warehouse) {
        setStatus("Canceled");
        //System.out.println("Cancel placed successfully.");

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            // Update warehouse quantity
            warehouse.updateProduct(product, quantity);
        }
        //cartItems.removeAll(cartItems);
    }

    void putAsBouquet() {
        isBouquet=true;
    }

    void viewOrder() {
        System.out.println("Order details:");
        System.out.println("ID: " + getId());
        System.out.println("Customer: " + customer.getName());
        System.out.println("Status: " + getStatus());
        System.out.println("Is bouquet: " + isBouquet);
        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem.getProduct());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isBouquet() {
        return isBouquet;
    }

    public void setBouquet(boolean bouquet) {
        isBouquet = bouquet;
    }
    public String toString(){
        return "The Order with Id "+getId()+" has been"+getStatus();
    }
}
