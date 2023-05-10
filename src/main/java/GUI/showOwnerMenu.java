package GUI;

import Fachlogik.Owner;
import Fachlogik.Product;
import Fachlogik.Warehouse;

import java.util.InputMismatchException;
import java.util.Scanner;

public class showOwnerMenu {
    private Warehouse warehouse;
    public showOwnerMenu(Warehouse w){
        this.warehouse=w;
    }

    public void updateQuantity(Scanner scanner, Owner owner){
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
    public void removeProduct(Scanner scanner,Owner owner) {
        Product p = null;
        owner.viewWarehouse();
        if (warehouse.getProducts().isEmpty()) {
            System.out.println("No products yet");
            return;
        } else {
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
            p = warehouse.getProductById(id);
            if (p != null) {
                owner.removeProduct(p);
            } else {
                System.out.println("wrong ID");
            }
        }
    }
    public void addProduct(Owner o) {
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
        if(quantity>0) {
            for (int i = 0; i < warehouse.getProducts().size(); i++) {
                if (warehouse.getProducts().get(i).getPrice() == price && warehouse.getProducts().get(i).getType().equals(type) && warehouse.getProducts().get(i).getName().equals(name) && warehouse.getProducts().get(i).getColor().equals(color)) {
                    System.out.println("This product is already added to the warehouse");
                    return;
                }
            }

            Product p = new Product(name, price, color, type, quantity);

            o.addProduct(p);
        }else{
            System.out.println("wrong quantity");
        }
    }
}
