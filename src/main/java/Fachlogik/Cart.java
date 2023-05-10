package Fachlogik;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    List<CartItem> cartItems;

    public Cart(){
        this.cartItems=new ArrayList<CartItem>();
    }

    public void addItem(Product product, int quantity) {
        if(product.getQuantity()>=quantity&&quantity>0) {
            cartItems.add(new CartItem(product, quantity));
            System.out.println("Item added to the cart.");
        }else{
            System.out.println("given Quantity is not available in the shop");
        }
    }

    public void updateQuantity(Product product, int quantity) {
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)&&cartItem.getProduct().getQuantity()>=quantity) {
                cartItem.setQuantity(quantity);
                //System.out.println("Quantity updated");
                return;
            }
        }
       // System.out.println("Quantity couldn't be updated");
    }

    public void deleteItem(Product product) {
        cartItems.removeIf(cartItem -> cartItem.getProduct().equals(product));
    }

    double getTotal() {
        double total = 0;
        for (CartItem cartItem : cartItems) {
            total += cartItem.getSubtotal();
        }
        return total;
    }

    public void viewCart() {
        System.out.println("Items in the cart:");
        for (CartItem cartItem : cartItems) {
            System.out.println(cartItem);
        }
        System.out.println("The total price = "+getTotal());
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}


