package BuilderDP;

final class Client {

    private int id;
    private String name;
    private int number;


    public Client setId(int id) {
        this.id = id;
        return this;
    }

    public Client setName(String name) {
        this.name = name;
        return this;
    }

    public Client setNumber(int number) {
        this.number = number;
        return this;
    }

    @Override
    public String toString() {
        return "id: " + this.id + ", name: " + this.name + ", phone number: " + this.number;
    }
}