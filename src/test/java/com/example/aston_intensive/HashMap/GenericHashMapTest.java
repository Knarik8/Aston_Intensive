package com.example.aston_intensive.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GenericHashMapTest {

    private GenericHashMap <String, Integer> genericHashMap;

    @BeforeEach
    void setUp(){
        genericHashMap = new GenericHashMap<>();
    }


    @Test
    public void getTest() {
        genericHashMap.put("Petrov", 60);
        genericHashMap.put("Ivanov", 100);
        genericHashMap.put("Sidorov", 70);

        Integer result1 = genericHashMap.get("Ivanov");
        Integer result2 = genericHashMap.get("Petrov");
        Integer result3 = genericHashMap.get("Sidorov");
        Integer result4 = genericHashMap.get("none");

        Assertions.assertEquals(100, result1);
        Assertions.assertEquals(60, result2);
        Assertions.assertEquals(70, result3);
        Assertions.assertNull(result4);

        for (int i = 0; i< 1000000; i++){
            genericHashMap.put("String"+ i, i);
        }
        Integer result5 = genericHashMap.get("String0");
        Integer result6 = genericHashMap.get("String1300");
        Assertions.assertEquals(0, result5);
        Assertions.assertEquals(1300, result6);



    }

    @Test
    public void putTest() {

        for (int i = 0; i< 1000000; i++){
            genericHashMap.put("String"+ i, i);
        }
        Assertions.assertEquals(7, genericHashMap.get("String7"));
        Assertions.assertEquals(70, genericHashMap.get("String70"));
        Assertions.assertEquals(70000, genericHashMap.get("String70000"));

        genericHashMap.put("String2", 2222); //update value
        Assertions.assertEquals(2222, genericHashMap.get("String2"));

    }

    @Test
    public void extendTest(){

        genericHashMap.put("0ne", 1);
        genericHashMap.put("Two", 2);
        genericHashMap.put("Three", 3);
        genericHashMap.put("Four", 4);
        genericHashMap.put("Five", 5);
        genericHashMap.put("Six", 6);
        genericHashMap.put("Seven", 7);
        genericHashMap.put("Eight", 8);
        genericHashMap.put("Nine", 9);
        genericHashMap.put("Ten", 10);
        genericHashMap.put("Eleven", 11);
        genericHashMap.put("Twelve", 12);
        genericHashMap.put("Thirteen", 13);
        genericHashMap.put("Fourteen", 14);
        genericHashMap.extend();
        Integer result = genericHashMap.getArray().length;
        Assertions.assertEquals(32, result);


        for (int i = 0; i< 100; i++){
            genericHashMap.put("String"+ i, i);
        }

        genericHashMap.extend();
        Integer result1 = genericHashMap.getArray().length;
        Assertions.assertEquals(128, result1);
    }


    @Test
    public void getBucketTest(){
        String key = "thisIsMyKey";
        int hash = key.hashCode();
        boolean result = (hash % genericHashMap.getArray().length)>0;
        Assertions.assertTrue(result);

    }
}
