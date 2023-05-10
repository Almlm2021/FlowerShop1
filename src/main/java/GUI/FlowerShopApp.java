package GUI;

import Datenhaltung.ApplicationState;
import Datenhaltung.SerializationUtil;
import Fachlogik.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlowerShopApp {
    static ApplicationState applicationState;
    static  showCustomerMenu showCustomerMenu;
    static   showOwnerMenu showOwnerMenu;
    static  String filePath;
    public static void main(String[] args) {
       filePath = "app_state.ser";

        // Load the application state from a file
       applicationState = SerializationUtil.loadAppStateFromFile(filePath);
       if(applicationState==null){
           applicationState=new ApplicationState();
           SerializationUtil.saveAppStateToFile(applicationState, filePath);
       }
        // ... continue using the loadedAppState object
        showOwnerMenu=new showOwnerMenu(applicationState.getWarehouse());
        showCustomerMenu=new showCustomerMenu(applicationState.getWarehouse(),applicationState.getOwners());
        Scanner scanner = new Scanner(System.in);
        //List<Customer> customers = new ArrayList<Customer>();


        String customerPassword;
        System.out.println("Welcome to the Flower Shop Application!");

        boolean running = true;
        User currentUser = null;

        while (running) {
            if (currentUser == null) {
                System.out.println("\n--- Login Menu ---");
                System.out.println("1. Log in as owner");
                System.out.println("2. Log in as customer");
                System.out.println("3. register new Customer");
                System.out.println("4. Exit");
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
                        //Owner login
                        System.out.println("Please Enter Owner Password");
                        String ownerPassword=scanner.next();
                        if(applicationState.getOwners().getPassword().equals(ownerPassword)) {
                            currentUser = applicationState.getOwners();
                        }else{
                            System.out.println("Unathorized Access");
                        }
                        break;
                    case 2:
                        //Customer Login
                        if(applicationState.getCustomers().isEmpty()){
                            System.out.println("Please register yourself by choosing the Option 3");
                        }else{
                            boolean found=false;
                        System.out.println("Enter Customer Password");
                        customerPassword= scanner.next();

                            for(int i=0;i< applicationState.getCustomers().size();i++){
                                if(applicationState.getCustomers().get(i).getPassword().equals(customerPassword)){
                                    currentUser = applicationState.getCustomers().get(i);
                                    found=true;
                                    break;

                                }
                            }
                            if(!found){
                                System.out.println("Please register yourself by choosing the Option 3");
                            }
                        }
                        break;
                    case 3:
                        //register new Customer
                        System.out.println("Welcome To the FlowerShop Application");
                        System.out.println("Please enter your Name:");
                        String customerName=scanner.next();
                        System.out.println("Please enter your email:......@gmail.com");
                        String customerEmail= scanner.next();
                        customerEmail+="@gmail.com";
                        System.out.println("Please enter your Password:");
                        customerPassword=scanner.next();
                       Customer c=new Customer(customerName,customerEmail,customerPassword);
                       applicationState.getCustomers().add(c);
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a valid option from the list.");
                }
            } else {
                // Display menu options based on the type of the current user
                if (currentUser instanceof Owner) {
                    System.out.println("welcome Alma");
                    showOwnerMenu(scanner, (Owner) currentUser);
                    currentUser = null;

                } else if (currentUser instanceof Customer) {
                    System.out.println("welcome "+currentUser.getName());
                    showCustomerMenu(scanner, (Customer) currentUser);
                    currentUser = null;

                }
            }
            SerializationUtil.saveAppStateToFile(applicationState, filePath);
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
            System.out.println("6. Sort warehouse products by ID");
            System.out.println("7. Log out");
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
                    //add product
                    showOwnerMenu.addProduct(owner);
                    break;
                case 2:
                    //remove product
                   showOwnerMenu.removeProduct(scanner,owner);
                    break;
                case 3:
                    //update the quantity of a product
                   showOwnerMenu.updateQuantity(scanner,owner);
                    break;
                case 4:
                    //view Warehouse
                    System.out.println("Your Warehouse contains the following Products:");
                    owner.viewWarehouse();
                    break;
                case 5:
                    //sort by type
                    owner.sortByType();
                    break;
                case 6:
                    owner.sortByID();
                    break;
                case 7:
                    ownerRunning = false;
                    System.out.println("Tschüss");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option from the list.");
            }
            SerializationUtil.saveAppStateToFile(applicationState, filePath);
        }
    }

    private static void showCustomerMenu(Scanner scanner, Customer customer) {
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
            System.out.println("13. View Orders");
            System.out.println("14. Cancel Order");
            System.out.println("15. Log out");
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
                 showCustomerMenu.viewshop();
                    break;
                case 2:
                    // Sort products by color
                    customer.sortByColor(applicationState.getWarehouse());
                    break;
                case 3:
                    // Sort products by price
                    customer.sortByPrice(applicationState.getWarehouse());
                    break;
                case 4:
                    // Sort products by type
                    customer.sortByType(applicationState.getWarehouse());
                    break;
                case 5:
                    // Add item to cart
                   showCustomerMenu.addItemtoCart(scanner,customer);
                    break;
                case 6:
                    // Update item quantity in cart
                   showCustomerMenu.updateQuantityinCart(scanner,customer);
                    break;
                case 7:
                    // Remove item from cart
                    showCustomerMenu.removefromeCart(scanner,customer);
                    break;
                case 8:
                    // View cart
                   showCustomerMenu.viewCart(customer);
                    break;
                case 9:
                    // Place order
                    showCustomerMenu.placeOrder(scanner,customer);
                    break;
                case 10:
                    // View favorite products
                   showCustomerMenu.viewFavorit(customer);
                    break;
                case 11:
                    // Add product to favorites
                    showCustomerMenu.AddFavortie( scanner, customer);
                    break;
                case 12:
                    showCustomerMenu.removeFavorit(scanner,customer);
                    break;
                case 13:
                    //view Orders
                    customer.getOrders();
                    break;
                case 14:
                    showCustomerMenu.cancelOrder(scanner,customer);
                    break;
                case 15:
                    // Logout
                    System.out.println("Logging out...");
                    customerRunning = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            SerializationUtil.saveAppStateToFile(applicationState, filePath);
        }
    }

    }

