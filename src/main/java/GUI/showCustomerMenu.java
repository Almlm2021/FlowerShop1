package GUI;

import Fachlogik.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class showCustomerMenu {
    private Warehouse warehouse;
    private Owner owner;

    public showCustomerMenu(Warehouse warehouse, Owner owner) {
        this.warehouse = warehouse;
        this.owner = owner;

    }

    public void cancelOrder(Scanner sc, Customer customer) {
        if (warehouse.getProducts().isEmpty() || customer.getO().isEmpty()) {
            System.out.println("no orders yet");
            return;
        }
        customer.getOrders();
        System.out.println("Enter to cancel order ID");
        int id = sc.nextInt();
        customer.cancelOrder(warehouse, id);
    }

    public void viewshop() {
        owner.sortByID();
        // customer.viewShop(warehouse);
    }

    public void addItemtoCart(Scanner scanner, Customer customer) {
        if (warehouse.getProducts().isEmpty()) {
            System.out.println("No Product in the Shop yet");
            return;
        } else {
            viewshop();
            Cart c = new Cart();
            System.out.println("Enter product ID to add to the cart:");
            int productIdToAdd;
            while (true) {
                try {
                    productIdToAdd = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for ID.");
                    scanner.next();
                }
            }
            System.out.println("Enter quantity:");
            int quantityToAdd;

            while (true) {
                try {
                    quantityToAdd = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for Quantity.");
                    scanner.next();
                }
            }
            Product productToAdd = warehouse.getProductById(productIdToAdd);

            if (productToAdd != null) {

                customer.getCart().addItem(warehouse.getProductById(productIdToAdd), quantityToAdd);

            } else {
                System.out.println("Product not found.");
            }
        }
    }

    public void updateQuantityinCart(Scanner scanner, Customer customer) {
        if (warehouse.getProducts().isEmpty() || customer.getCart().getCartItems().isEmpty()) {
            System.out.println("No Product yet");
        } else {
            customer.getCart().viewCart();
            System.out.println("Enter product ID to update quantity:");
            int productIdToUpdate;
            while (true) {
                try {
                    productIdToUpdate = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for ID.");
                    scanner.next();
                }
            }
            System.out.println("Enter new quantity:");
            int newQuantity;
            while (true) {
                try {
                    newQuantity = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for ID.");
                    scanner.next();
                }
            }
            Product productToUpdate = warehouse.getProductById(productIdToUpdate);
            //if (productToUpdate != null&&newQuantity>0) {
            if (productToUpdate != null) {
                customer.getCart().updateQuantity(productToUpdate, newQuantity);
            } else {
                System.out.println("can't update quantity");
            }
        }
    }

    public void removefromeCart(Scanner scanner, Customer customer) {
        if (warehouse.getProducts().isEmpty()) {
            System.out.println("No Product in the Shop yet");
        } else {
            customer.getCart().viewCart();
            System.out.println("Enter product ID to remove from the cart:");
            int productIdToRemove;
            while (true) {
                try {
                    productIdToRemove = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for ID.");
                    scanner.next();
                }
            }
            Product productToRemove = warehouse.getProductById(productIdToRemove);
            if (productToRemove != null) {
                customer.getCart().deleteItem(productToRemove);
                System.out.println("Item removed from the cart.");
            } else {
                System.out.println("Item not found.");
            }
        }
    }

    public void viewCart(Customer customer) {
        if (warehouse.getProducts().isEmpty()) {
            System.out.println("No Product in the Shop yet");
            return;
        } else {
            customer.getCart().viewCart();
        }
    }

    public void placeOrder(Scanner sc, Customer customer) {
        System.out.println("as A bouqute?y/n");
        String b = sc.next();
        if (b.equals("y")) {
            customer.placeOrder(warehouse, true);
        } else {
            customer.placeOrder(warehouse, false);
        }
    }

    public void viewFavorit(Customer customer) {
        if (warehouse.getProducts().isEmpty() || customer.viewFavorites().isEmpty()) {
            System.out.println("No Favorite yet");

        } else {
            List<FavoriteProduct> favoriteProducts = customer.viewFavorites();
            System.out.println("\n--- Favorite Products ---");
            for (FavoriteProduct favoriteProduct : favoriteProducts) {
                System.out.println(favoriteProduct.getProduct().toString());
            }
        }
    }

    public void removeFavorit(Scanner scanner, Customer customer) {
        //List<FavoriteProduct> favoriteProducts = customer.viewFavorites();
        if (warehouse.getProducts().isEmpty() || customer.viewFavorites().size() == 0) {
            System.out.println("No Products yet");
        } else {
            // Remove product from favorites
            viewFavorit(customer);
            System.out.println("Enter product ID to remove from favorites:");
            int productIdToRemoveFromFavorites;
            while (true) {
                try {
                    productIdToRemoveFromFavorites = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for ID.");
                    scanner.next();
                }
            }

            for (int i = 0; i < customer.viewFavorites().size(); i++) {
                if (customer.viewFavorites().get(i).getProduct().getId() == productIdToRemoveFromFavorites) {
                    Product productToRemoveFromFavorites = warehouse.getProductById(productIdToRemoveFromFavorites);
                    customer.removeFavorite(productToRemoveFromFavorites);
                    return;
                }
            }
            System.out.println("Product not found in the Favorite");
        }
    }

    public void AddFavortie(Scanner scanner, Customer customer) {
        if (warehouse.getProducts().isEmpty()) {
            System.out.println("No Product in the Shop yet");
        } else {
            System.out.println("Enter product ID to add to favorites:");
            int productIdToFavorite;
            while (true) {
                try {
                    productIdToFavorite = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid number for ID.");
                    scanner.next();
                }
            }
            Product productToFavorite = warehouse.getProductById(productIdToFavorite);
            if (productToFavorite != null) {
                FavoriteProduct favoriteProduct = new FavoriteProduct();
                customer.addFavorite(favoriteProduct);
                favoriteProduct.setProduct(productToFavorite, customer);

                System.out.println("Product added to favorites.");
            } else {
                System.out.println("Product not found.");
            }
        }

    }
}
