package Fachlogik;

import java.util.List;

public class FavoriteProduct {
    List<Customer> customer;
    Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product, Customer customer) {
        this.product = product;
    }


    List<Customer> getCustomers() {
        return customer;
    }

    void setCustomer(Customer customer) {
        this.customer.add(customer);
    }

    List<Customer> viewCustomers() {
        return customer;
    }

    public List<Customer> getCustomer() {
        return customer;
    }

    public void setCustomer(List<Customer> customer) {
        this.customer = customer;
    }
}
