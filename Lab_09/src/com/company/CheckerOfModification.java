package com.company;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class CheckerOfModification {

    private List<Memento> mementoList;
    private UrlAddressState[] urlAddresses;
    private HashMap<String, List<Observer>> listHashMap;
    private int counter = 0;

    public CheckerOfModification(UrlAddressState[] urlAddressStates, Observer... observers) {
        mementoList = new ArrayList<>();
        urlAddresses = urlAddressStates;
        fillMap(observers);
    }
    private void fillMap(Observer[] observers) {
        listHashMap = new HashMap<>();
        for (int i = 0; i < observers.length; i++) {
            if (observers[i] == null) {
                continue;
            }
            if(!UrlAddressState.checkUrlPresence(urlAddresses, observers[i].getUrl())) {
                continue;
            }
            if (!listHashMap.containsKey(observers[i].getUrl())) {
                listHashMap.put(observers[i].getUrl(), new ArrayList<>());
            }
            listHashMap.get(observers[i].getUrl()).add(observers[i]);
        }
    }
    public void startCheck() {
        while (counter < 2) {
            counter++;
            for (int i = 0; i < urlAddresses.length; i++) {
                if (urlAddresses[i] == null) {
                    continue;
                }
                long time = urlAddresses[i].getConnection().getLastModified();

                if (urlAddresses[i].getTime() == null) {
                    urlAddresses[i].setTime(time);
                } else {
                    Date modifiedDate = new Date(time);
                    if (modifiedDate.after(urlAddresses[i].getTime())) {
                        notifyingObservers(urlAddresses[i]);
                    }
                }
            }
            try {
                Memento memento = new Memento(urlAddresses,listHashMap,counter);
                mementoList.add(memento);
                memento.write();
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void notifyingObservers(UrlAddressState urlAddress) {
        for (Observer obs: listHashMap.get(urlAddress.urlAsString())) {
            obs.update();
        }
    }
}