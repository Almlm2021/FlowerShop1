package Fachlogik;

class Payment {
    Order order;
    String paymentMethod;
    public Payment(Order order, String paymentMethod) {
        this.order = order;
        this.paymentMethod = paymentMethod;
    }
    void pay() {
        System.out.println("Payment successful using " + paymentMethod + ".");

    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
