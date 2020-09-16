package ru.makarov.logic;

import ru.makarov.dao.DaoSample;
import ru.makarov.model.Item;

public class Main {
    public static void main(String[] args) {
        StoreObserve storeObserve = new StoreObserve();
        try {
            storeObserve.getByReact(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
