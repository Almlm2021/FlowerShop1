package Datenhaltung;

import Fachlogik.Customer;
import Fachlogik.Owner;
import Fachlogik.Product;
import Fachlogik.Warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ApplicationState implements Serializable {
    private Warehouse warehouse;
    private List<Customer> customers;
    private Owner owners;

    public ApplicationState() {
        warehouse = new Warehouse();
        customers = new ArrayList<>();
        owners = new Owner("Alma", "bob@example.com", "password", warehouse);
    }

    // Getter and setter methods for warehouse, customers, and owners

    //Customers
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

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




}

