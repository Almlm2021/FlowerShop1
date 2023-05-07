package Fachlogik;

import java.util.List;

class FavoriteProduct {
    List<Customer> customer;
    Product product;

    Product getProduct() {
        return product;
    }

    void setProduct(Product product) {
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
