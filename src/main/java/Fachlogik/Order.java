package Fachlogik;

import java.util.ArrayList;
import java.util.List;

class Order {
    int id;
    Customer customer;
    List<CartItem> cartItems;
    String status;
    boolean isBouquet;

    public Order(int id,String s){
        this.id = id;
        this.cartItems = new ArrayList<>(cartItems);
        this.status = status;
        this.isBouquet = false;
    }

        public void placeOrder(Warehouse warehouse) {
            setStatus("Placed");
            System.out.println("Order placed successfully.");

            for (CartItem cartItem : cartItems) {
                Product product = cartItem.getProduct();
                int quantity = cartItem.getQuantity();

                // Update warehouse quantity
                warehouse.updateProduct(product, --quantity);
            }
        }


    void cancelOrder(Warehouse warehouse) {
        setStatus("Canceled");
        System.out.println("Cancel placed successfully.");

        for (CartItem cartItem : cartItems) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();

            // Update warehouse quantity
            warehouse.updateProduct(product, ++quantity);
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
}
