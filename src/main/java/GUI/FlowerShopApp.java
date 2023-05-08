package GUI;

import Fachlogik.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlowerShopApp {
    static Shop shop = new Shop();
    static Warehouse warehouse = new Warehouse();
    static Owner owner = new Owner(1, "Bob", "bob@example.com", "password", warehouse);


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Alice", "alice@example.com", "password"));
        customers.add(new Customer(2, "Carol", "carol@example.com", "password"));

        System.out.println("Welcome to the Flower Shop Application!");

        boolean running = true;
        User currentUser = null;

        while (running) {
            if (currentUser == null) {
                System.out.println("\n--- Login Menu ---");
                System.out.println("1. Log in as owner");
                System.out.println("2. Log in as customer");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");

                int loginChoice;
                try {
                    loginChoice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.nextLine();
                    continue;
                }
                scanner.nextLine();

                switch (loginChoice) {
                    case 1:
                        currentUser = owner;
                        break;
                    case 2:
                        System.out.println("Enter customer index (0-" + (customers.size() - 1) + "):");
                        int customerIndex = scanner.nextInt();
                        scanner.nextLine();
                        if (customerIndex >= 0 && customerIndex < customers.size()) {
                            currentUser = customers.get(customerIndex);
                        } else {
                            System.out.println("Invalid customer index. Please try again.");
                        }
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid option from the list.");
                }
            } else {
                // Display menu options based on the type of the current user
                if (currentUser instanceof Owner) {
                    System.out.println("welcome Owner");
                    showOwnerMenu(scanner, (Owner) currentUser);
                    currentUser = null;

                } else if (currentUser instanceof Customer) {
                    System.out.println("welcome Customer");
                    showCustomerMenu(scanner, (Customer) currentUser, shop);
                    currentUser = null;

                }
            }
        }

        scanner.close();
        System.out.println("Thank you for using the Flower Shop Application.");
    }


    private static void showOwnerMenu(Scanner scanner, Owner owner) {
        boolean ownerRunning = true;
        while (ownerRunning) {
            System.out.println("\n--- Owner Menu ---");
            System.out.println("1. Add product");
            System.out.println("2. Remove product");
            System.out.println("3. Update product");
            System.out.println("4. View warehouse");
            System.out.println("5. Sort warehouse products by type");
            System.out.println("6. Log out");
            System.out.print("Enter your choice: ");

            int ownerChoice;
            try {
                ownerChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (ownerChoice) {
                case 1:
                    addProduct(owner);
                    break;
                case 2:
                    Product p = null;
                    owner.viewWarehouse();
                    if (warehouse.getProducts().size() == 0) {
                        break;
                    }
                    System.out.println("Select your to remove Product by choosing the id");
                    int id = scanner.nextInt();
                    for (int i = 0; i < warehouse.getProducts().size(); i++) {
                        if (id == warehouse.getProducts().get(i).getId()) {
                            p = warehouse.getProductById(i);
                        }
                    }
                    owner.removeProduct(p);
                    break;
                case 3:
                    Product p1 = null;
                    owner.viewWarehouse();
                    if (warehouse.getProducts().size() == 0) {
                        break;
                    }
                    System.out.println("Select your to update Product by choosing the id");
                    int id1 = scanner.nextInt();
                    for (int i = 0; i < warehouse.getProducts().size(); i++) {
                        if (id1 == warehouse.getProducts().get(i).getId()) {
                            p1 = warehouse.getProductById(i);
                        }
                    }
                    System.out.println("Give the number of Quantity");
                    int q = scanner.nextInt();
                    owner.updateProduct(p1, q);
                    break;
                case 4:
                    System.out.println("Your Warehouse contains the following Products:");
                    owner.viewWarehouse();
                    break;
                case 5:
                    owner.sortByType();
                    break;
                case 6:
                    ownerRunning = false;
                    System.out.println("Tschüss");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option from the list.");
            }
        }
    }

    private static void addProduct(Owner o) {
        Scanner sc = new Scanner(System.in);
        String name;
        double price;
        String color;
        String type;
        int quantity;
        System.out.println("Enter the name");
        name = sc.nextLine();
        System.out.println("Enter the price");
        price = sc.nextDouble();
        System.out.println("Enter the Color");
        color = sc.next();
        System.out.println("Enter the Type");
        type = sc.next();
        System.out.println("Enter the Quantity");
        quantity = sc.nextInt();
        Product p = new Product(name, price, color, type, quantity);
        o.addProduct(p);

    }

    private static void showCustomerMenu(Scanner scanner, Customer customer, Shop shop) {
        boolean customerRunning = true;
        while (customerRunning) {
            System.out.println("\n--- Customer Menu ---");
            System.out.println("1. View shop");
            System.out.println("2. Sort products by color");
            System.out.println("3. Sort products by price");
            System.out.println("4. Sort products by type");
            System.out.println("5. Add item to cart");
            System.out.println("6. Update item quantity in cart");
            System.out.println("7. Remove item from cart");
            System.out.println("8. View cart");
            System.out.println("9. Place order");
            System.out.println("10. View favorite products");
            System.out.println("11. Add product to favorites");
            System.out.println("12. Remove product from favorites");
            System.out.println("13. Log out");
            System.out.print("Enter your choice: ");

            int customerChoice;
            try {
                customerChoice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (customerChoice) {
                case 1:
                    // View shop
                    customer.viewShop(warehouse);
                    break;
                case 2:
                    // Sort products by color
                    customer.sortByColor(warehouse);
                    break;
                case 3:
                    // Sort products by price
                    customer.sortByPrice(warehouse);
                    break;
                case 4:
                    // Sort products by type
                    customer.sortByType(warehouse);
                    break;
                case 5:
                    // Add item to cart
                    Cart c = new Cart();
                    System.out.println("Enter product ID to add to the cart:");
                    int productIdToAdd = scanner.nextInt();
                    System.out.println("Enter quantity:");
                    int quantityToAdd = scanner.nextInt();
                    Product productToAdd = warehouse.getProductById(productIdToAdd);
                    if (productToAdd != null) {
                        customer.getCart().addItem(warehouse.getProductById(productIdToAdd), quantityToAdd);
                        System.out.println("Item added to the cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 6:
                    // Update item quantity in cart
                    System.out.println("Enter product ID to update quantity:");
                    int productIdToUpdate = scanner.nextInt();
                    System.out.println("Enter new quantity:");
                    int newQuantity = scanner.nextInt();
                    Product productToUpdate = warehouse.getProductById(productIdToUpdate);
                    if (productToUpdate != null) {
                        customer.getCart().updateQuantity(productToUpdate, newQuantity);
                        System.out.println("Item quantity updated.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 7:
                    // Remove item from cart
                    System.out.println("Enter product ID to remove from the cart:");
                    int productIdToRemove = scanner.nextInt();
                    Product productToRemove = warehouse.getProductById(productIdToRemove);
                    if (productToRemove != null) {
                        customer.getCart().deleteItem(productToRemove);
                        System.out.println("Item removed from the cart.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 8:
                    // View cart
                    customer.getCart().viewCart();
                    break;
                case 9:
                    // Place order
                    Order newOrder = new Order("null");

                    if (newOrder != null) {

                        newOrder.placeOrder(warehouse);
                        customer.addOrder(newOrder);
                        System.out.println("Order placed successfully. Order ID: " + newOrder.getId());
                    } else {
                        System.out.println("Failed to place the order.");
                    }
                    break;
                case 10:
                    // View favorite products
                    List<FavoriteProduct> favoriteProducts = customer.viewFavorites();
                    System.out.println("\n--- Favorite Products ---");
                    for (FavoriteProduct favoriteProduct : favoriteProducts) {
                        System.out.println(favoriteProduct.getProduct().toString());
                    }
                    break;
                case 11:
                    // Add product to favorites
                    System.out.println("Enter product ID to add to favorites:");
                    int productIdToFavorite = scanner.nextInt();
                    Product productToFavorite = warehouse.getProductById(productIdToFavorite);
                    if (productToFavorite != null) {
                        FavoriteProduct favoriteProduct = new FavoriteProduct();
                        customer.addFavorite(favoriteProduct);
                        favoriteProduct.setProduct(productToFavorite, customer);

                        System.out.println("Product added to favorites.");
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 12:
                    // Remove product from favorites
                    System.out.println("Enter product ID to remove from favorites:");
                    int productIdToRemoveFromFavorites = scanner.nextInt();
                    Product productToRemoveFromFavorites = warehouse.getProductById(productIdToRemoveFromFavorites);
                    if (productToRemoveFromFavorites != null) {
                        Product favoriteProductToRemove = customer.removeFavorite(productToRemoveFromFavorites);
                        if (favoriteProductToRemove != null) {
                            customer.removeFavorite(favoriteProductToRemove);
                            System.out.println("Product removed from favorites.");
                        } else {
                            System.out.println("Product not found in favorites.");
                        }
                    } else {
                        System.out.println("Product not found.");
                    }
                    break;
                case 13:
                    // Logout
                    System.out.println("Logging out...");
                    customerRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }


    }
}
