package GUI;

import Fachlogik.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FlowerShopApp {
    static Shop shop = new Shop();
    static Warehouse warehouse = new Warehouse();
    static Owner owner = new Owner( "Alma", "bob@example.com", "password", warehouse);


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Customer> customers = new ArrayList<Customer>();
        customers.add(new Customer("Alice", "alice@example.com", "password"));
        owner.addProduct(new Product("khra",10,"red","re",20));
        //customers.add(new Customer(2, "Carol", "carol@example.com", "password"));
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
                        if(owner.getPassword().equals(ownerPassword)) {
                            currentUser = owner;
                        }else{
                            System.out.println("Unathorized Access");
                        }
                        break;
                    case 2:
                        //Customer Login
                        if(customers.isEmpty()){
                            System.out.println("Please register yourself by choosing the Option 3");
                        }else{
                            boolean found=false;
                        System.out.println("Enter Customer Password");
                        customerPassword= scanner.next();

                            for(int i=0;i< customers.size();i++){
                                if(customers.get(i).getPassword().equals(customerPassword)){
                                    currentUser = customers.get(i);
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
                       customers.add(c);
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
                    addProduct(owner);
                    break;
                case 2:
                    //remove product
                   removeProduct(scanner,owner);
                    break;
                case 3:
                    //update the quantity of a product
                   updateQuantity(scanner,owner);
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
        }
    }
    private static void updateQuantity(Scanner scanner,Owner owner){
        Product p2 = null;
        owner.viewWarehouse();
        if (warehouse.getProducts().size() == 0) {
            return;
        }
        System.out.println("Select your to update Product by choosing the id");
        int id1 = scanner.nextInt();
        p2 = warehouse.getProductById(id1);
        int q;
        if(p2!=null) {
            System.out.println("Give the number of Quantity");

            while (true) {
                try {
                    q = scanner.nextInt();
                    owner.updateProduct(p2, q);
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid ID.");
                    scanner.next();
                }

            }
        }else{
            System.out.println("Product ID not found");
        }
    }
    private static void removeProduct(Scanner scanner,Owner owner){
        Product p = null;
        owner.viewWarehouse();
        if (warehouse.getProducts().size() == 0) {
         return;
        }
        System.out.println("Select your to remove Product by choosing the id");

        int id;
        while (true) {
            try {
                id = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid ID.");
                scanner.next();
            }
        }
        p=warehouse.getProductById(id);
        if(p!=null) {
            owner.removeProduct(p);
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
        while (true) {
            try {
                price = sc.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for price.");
                sc.next();
            }
        }

        System.out.println("Enter the Color");
        color = sc.next();

        System.out.println("Enter the Type");
        type = sc.next();

        System.out.println("Enter the Quantity");
        while (true) {
            try {
                quantity = sc.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number for quantity.");
                sc.next();
            }
        }
        for(int i=0;i<warehouse.getProducts().size();i++){
            if(warehouse.getProducts().get(i).getPrice()==price&&warehouse.getProducts().get(i).getType().equals(type)&&warehouse.getProducts().get(i).getName().equals(name)&&warehouse.getProducts().get(i).getColor().equals(color)){
                System.out.println("This product is already added to the warehouse");
                return;
            }
        }

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
            System.out.println("13. View Orders");
            System.out.println("14. Log out");
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
                 viewshop(customer);
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
                   addItemtoCart(scanner,customer);
                    break;
                case 6:
                    // Update item quantity in cart
                   updateQuantityinCart(scanner,customer);
                    break;
                case 7:
                    // Remove item from cart
                    removefromeCart(scanner,customer);
                    break;
                case 8:
                    // View cart
                   viewCart(customer);
                    break;
                case 9:
                    // Place order
                    placeOrder(scanner,customer);
                    break;
                case 10:
                    // View favorite products
                   viewFavorit(customer);
                    break;
                case 11:
                    // Add product to favorites
                    AddFavortie( scanner, customer);
                    break;
                case 12:
                    removeFavorit(scanner,customer);
                    break;
                case 13:
                    //view Orders
                    customer.getOrders();
                    break;
                case 14:
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
    public static void viewshop(Customer customer){
        owner.sortByID();
       // customer.viewShop(warehouse);
    }
    public static void removeFavorit(Scanner scanner,Customer customer) {
        //List<FavoriteProduct> favoriteProducts = customer.viewFavorites();
        if (warehouse.getProducts().isEmpty()||customer.viewFavorites().size()==0) {
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
            //Product productToRemoveFromFavorites = warehouse.getProductById(productIdToRemoveFromFavorites);
            for(int i=0;i<customer.viewFavorites().size();i++){
                if(customer.viewFavorites().get(i).getProduct().getId()==productIdToRemoveFromFavorites){
                    Product productToRemoveFromFavorites = warehouse.getProductById(productIdToRemoveFromFavorites);
                    customer.removeFavorite(productToRemoveFromFavorites);
                    return;
                }
            }
            System.out.println("Product not found in the Favorite");
        }
    }
    public static void AddFavortie(Scanner scanner,Customer customer){
        if(warehouse.getProducts().isEmpty()){
            System.out.println("No Product in the Shop yet");
        }else {
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

    public static void viewFavorit(Customer customer){
        if(warehouse.getProducts().isEmpty()||customer.viewFavorites().isEmpty()){
            System.out.println("No Favorite yet");

        }else {
            List<FavoriteProduct> favoriteProducts = customer.viewFavorites();
            System.out.println("\n--- Favorite Products ---");
            for (FavoriteProduct favoriteProduct : favoriteProducts) {
                System.out.println(favoriteProduct.getProduct().toString());
            }
        }
    }
    public static void placeOrder(Scanner sc,Customer customer){
        /*if(warehouse.getProducts().isEmpty()){
            System.out.println("No Product in the Shop yet");
        }else {
            if(customer.getCart().getCartItems().size()>0) {
                Order newOrder = new Order("null");
                System.out.println("before");
                newOrder.placeOrder(warehouse,customer);
                System.out.println("after");
                customer.addOrder(newOrder);
            }else{
                System.out.println("No Cart Item was yet added");
            }

        }*/
        //Order newOrder = new Order("null",customer.getCart());
        System.out.println("as A bouqute?y/n");
        String b=sc.next();
        if(b.equals("y")){
            customer.placeOrder(warehouse,true);
        }else{
            customer.placeOrder(warehouse,false);
        }


    }
    //blablabla
    public static void viewCart(Customer customer){
        if(warehouse.getProducts().isEmpty()){
            System.out.println("No Product in the Shop yet");
            return;
        }else {
            customer.getCart().viewCart();
        }
    }
    public static void removefromeCart(Scanner scanner,Customer customer){
        if(warehouse.getProducts().isEmpty()){
            System.out.println("No Product in the Shop yet");
        }else {
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
                System.out.println("Product not found.");
            }
        }
    }
    public static void updateQuantityinCart(Scanner scanner,Customer customer){
        if(warehouse.getProducts().isEmpty()||customer.getCart().getCartItems().isEmpty()){
            System.out.println("No Product yet");
        }else {
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
            if (productToUpdate != null||newQuantity>0) {
                customer.getCart().updateQuantity(productToUpdate, newQuantity);
            } else {
                System.out.println("Product not found.");
            }
        }
    }
    public static void addItemtoCart(Scanner scanner,Customer customer){
        if(warehouse.getProducts().isEmpty()){
            System.out.println("No Product in the Shop yet");
            return;
        }else {
            viewshop(customer);
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
}
