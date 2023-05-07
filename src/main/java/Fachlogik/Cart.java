package Fachlogik;

import java.util.ArrayList;
import java.util.List;

class Cart {
    List<CartItem> cartItems;

    public Cart(){
        this.cartItems=new ArrayList<CartItem>();
    }
    void addItem(Product product, int quantity) {

       cartItems.add(new CartItem(product, quantity));
    }

    void updateQuantity(Product product, int quantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                cartItem.setQuantity(quantity);
                return;
            }
        }
        System.out.println("Product not found in the cart.");
    }

    void deleteItem(Product product) {
        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(product));
    }

    double getTotal() {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getSubtotal();
        }
        return total;
    }

    void viewCart() {
        System.out.println("Items in the cart:");
        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem);
        }
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}


