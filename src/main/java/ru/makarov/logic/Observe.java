package ru.makarov.logic;

public interface Observe<T> {
    void receive(T model);
}