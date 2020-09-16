package ru.makarov.dao;

import ru.makarov.model.Item;

import java.util.List;

public interface Store {
     void add(Item item);
     void remove(Integer id);
     List<Item> findAll();
}
