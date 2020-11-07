package MementoDP;

import java.util.ArrayList;
import java.util.List;

public class CareTaker {
    public static void main(String[] args) {

        Original original = new Original();
        List<Memento> mList = new ArrayList<Memento>();

        original.setInteger(1);
        original.setInteger(2);
        mList.add(original.saveIntegerToMemento());

        original.setInteger(3);
        mList.add(original.saveIntegerToMemento());

        original.setInteger(4);
        System.out.println("current integer - " + original.getInteger());

        original.getIntegerFromMemento(mList.get(0));
        System.out.println("1st saved integer - " + original.getInteger());
        original.getIntegerFromMemento(mList.get(1));
        System.out.println("2nd saved integer - " + original.getInteger());
    }
}