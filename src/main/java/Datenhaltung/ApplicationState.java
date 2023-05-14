package Datenhaltung;

import Fachlogik.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationState implements Serializable {
    private Warehouse warehouse;
    private List<Customer> customers;
    private Owner owners;
    //private List<Order> orders;
    private int c;
    public ApplicationState() {
        warehouse = new Warehouse();
        customers = new ArrayList<>();
        owners = new Owner("Alma", "bob@example.com", "password", warehouse);
        //orders=new ArrayList<Order>();
    }

    // Getter and setter methods for warehouse, customers, and owners

    //Customers
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /*public List<Order> getOrder() {
        return orders;
    }

    public void setOrder(List<Order> order) {
        this.orders = order;
    }*/

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }
    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    //Owner
    public Owner getOwners() {
        return owners;
    }

    public void setOwners(Owner owners) {
        this.owners = owners;
    }


    // Warehouse
    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public void addProduct(Product product) {
        warehouse.addProduct(product);
    }

    public void removeProduct(Product product) {
        warehouse.removeProduct(product);
    }

    public void updateNextIds() {
        int maxProductId = warehouse.getProducts().stream().mapToInt(Product::getId).max().orElse(0);
        Product.setNextId(maxProductId);

        int maxUserId = customers.stream().mapToInt(User::getId).max().orElse(0);
        User.setNextId(maxUserId);

    }



}

