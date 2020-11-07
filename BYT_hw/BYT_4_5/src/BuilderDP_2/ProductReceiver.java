package BuilderDP_2;

class ProductReceiver extends Builder<Product> {

    private volatile Product product;

    public ProductReceiver() {

        int i = (int) (Math.random() * 2);
        if (i == 0)
            product = (Product) setId(1).setName("P1").build();
        else
            product = (Product) setId(2).setName("P2").build();
    }

    public Product getProduct() {
        return product;
    }

    @Override
    public Product build() {
        return new Product(this);
    }
}