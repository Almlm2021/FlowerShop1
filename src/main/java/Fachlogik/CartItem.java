package Fachlogik;

class CartItem {
    Product product;
    int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    double getSubtotal() {
        return product.getPrice() * quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        product.setQuantity(quantity);
    }
    @Override
    public String toString() {
        return "CartItem{" +
                "productID="+product.getId()+" name:" + product.getName()+" costs: "+product.getPrice()+
                ", quantity=" + quantity +" costs: "+getSubtotal()+
                '}';
    }
}
