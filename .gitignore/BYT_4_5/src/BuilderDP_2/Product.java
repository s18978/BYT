package BuilderDP_2;

final class Product {

    final private int id;
    final private String name;

    public Product(Builder builder) {

        this.id = builder.id;
        this.name = builder.name;
    }

    @Override
    public String toString() {
        return "built by" + id + " " + name;
    }
}