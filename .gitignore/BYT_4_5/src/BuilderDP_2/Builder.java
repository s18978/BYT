package BuilderDP_2;

public abstract class Builder<T> {

    public int id;
    public String name;

    public Builder setId(int id) {

        this.id = id;
        return this;
    }

    public Builder setName(String name)
    {
        this.name = name;
        return this;
    }

    public abstract T build();


    public String getResult(T obj) {
        return obj.toString();
    }
}