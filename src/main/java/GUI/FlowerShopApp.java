package GUI;

import Fachlogik.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlowerShopApp {
    static Shop shop = new Shop();
    static Warehouse warehouse = new Warehouse();
    static Owner owner = new Owner(1, "Bob", "bob@example.com", "password", shop);


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
                    showOwnerMenu(scanner,(Owner) currentUser);
                    running = false;
                } else if (currentUser instanceof Customer) {
                    System.out.println("welcome Customer");
                    running = false;
                }

                // Perform operations based on the current user
                // ...
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
                    owner.viewWarehouse();
                    System.out.println("Select your to remove Product by choosing the id");
                    int id=scanner.nextInt();
                    Product p=warehouse.getProductById(id);
                    owner.removeProduct(p);
                    break;
                case 3:
                    owner.viewWarehouse();
                    System.out.println("Select your to update Product by choosing the id");
                    int id1=scanner.nextInt();
                    System.out.println("Give the number of Quantity");
                    int q=scanner.nextInt();
                    Product p1=warehouse.getProductById(id1);
                    owner.updateProduct(p1,q);
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
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option from the list.");
            }
        }
    }
    private static void addProduct(Owner o){
        Scanner sc=new Scanner(System.in);
        String name;
        double price;
        String color;
        String type;
        int quantity;
        System.out.println("Enter the name");
        name=sc.nextLine();
        System.out.println("Enter the price");
        price=sc.nextDouble();
        System.out.println("Enter the Color");
        color=sc.next();
        System.out.println("Enter the Type");
        type=sc.next();
        System.out.println("Enter the Quantity");
        quantity=sc.nextInt();
        Product p=new Product(name,price,color,type,quantity);
     warehouse.addProduct(p);
     o.addProduct(p);
    }
}
