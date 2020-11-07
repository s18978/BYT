package MementoDP;

public class Original {

    private int integer;

    public void setInteger(int i) {
        this.integer = i;
    }

    public int getInteger() {
        return integer;
    }

    public Memento saveIntegerToMemento() {
        return new Memento(integer);
    }

    public void getIntegerFromMemento(Memento memento) {
        integer = memento.getInteger();
    }
}