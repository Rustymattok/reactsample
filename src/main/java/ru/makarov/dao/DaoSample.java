package ru.makarov.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.makarov.logic.Observe;
import ru.makarov.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DaoSample implements Store {
    private static  final DaoSample INSTANCE = new DaoSample();
    private static final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    private DaoSample() {
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session =  factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        }finally {
            session.close();
        }
    }

    public static DaoSample getINSTANCE() {
        return INSTANCE;
    }

    @Override
    public void add(Item item) {
        this.tx(session -> {
          session.save(item);
          return null;
        });
    }

    @Override
    public void remove(Integer id) {
        this.tx(session -> {
            session.remove(id);
            return null;
        });
    }

    @Override
    public List<Item> findAll() {
        // HQL (Hibernate Query Language)
        return this.tx(session -> {
            List<Item> list = (List<Item>)session.createQuery("from item").list();
            return list;
        });
    }

    /**
     * React method for work with database.
     * @param itemObserve -  reactiv element.
     * @return - list of all data.
     */
    public List<Item> findAll(Observe<String> itemObserve){
        return this.tx(session -> {
            List<Item> list = (List<Item>)session.createQuery("from item").list();
            List<Item> reactList = new ArrayList<>();
            for (Item item: list) {
                Item reactItem = new Item();
                reactItem.setTask(item.getTask());
                reactItem.setName(item.getName() + " react1 Name");
                reactList.add(reactItem);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                itemObserve.receive(reactItem.getName());
            }
            return reactList;
        });
    }
}
