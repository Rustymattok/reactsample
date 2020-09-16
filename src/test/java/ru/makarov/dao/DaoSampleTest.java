package ru.makarov.dao;

import org.junit.Assert;
import org.junit.Test;
import ru.makarov.model.Item;


import java.util.List;


public class DaoSampleTest {

    @Test
    public void findAll() {
        DaoSample daoSample = DaoSample.getINSTANCE();
        List<Item> list = daoSample.findAll(DaoSampleTest::testReact);
        Assert.assertEquals(3,list.size());
    }

     public static void testReact(String s) {
        System.out.println(s+ "test react");
    }

}
