package com.company;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Memento {

    private UrlAddressState[] addressStates;
    private HashMap<String,List<Observer>> observerHashMap;
    private int counter;

    Memento(UrlAddressState[] addressStates, HashMap<String, List<Observer>> observerHashMap, int counter) {
        this.counter = counter;
        this.addressStates = addressStates;
        this.observerHashMap = observerHashMap;
    }
    public void write() {
        try {
            File file = new File("output");
            if(!file.isDirectory() || !file.exists()) {
                file.mkdir();
            }
            File outFile = new File(file.getPath() + "/output" + counter);
            if(!outFile.exists() && !outFile.isDirectory()) try {
                outFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            PrintWriter printWriter = new PrintWriter(new FileOutputStream(outFile));
            printWriter.write("Iteration: " + counter +
            "\nObservers: " + observerHashMap.toString() +
            "\nThe checked Url addresses: " + Arrays.toString(addressStates));
            printWriter.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}