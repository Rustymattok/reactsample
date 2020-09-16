package ru.makarov.logic;

import ru.makarov.dao.DaoSample;
import ru.makarov.model.Item;

import java.util.ArrayList;
import java.util.List;

public class StoreObserve {
    private final DaoSample daoSample = DaoSample.getINSTANCE();

    public void getByReact(Observe<Item> observe) throws InterruptedException {
        List<Item> items = daoSample.findAll();
        for (Item datum : items) {
            Thread.sleep(1000);
            observe.receive(datum);
        }
    }

}
