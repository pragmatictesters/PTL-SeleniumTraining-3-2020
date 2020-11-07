package com.pragmatic.examples.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class MapExample {


    public static void main(String[] args) {
        Map<String, String> simpleMap = new HashMap<>();
        simpleMap.put("name", "Janesh");
        simpleMap.put("address", "Colombo, Sri Lanka ");
        simpleMap.put("phone", "071873202723");

        Set<String> keys = simpleMap.keySet();

        for (String key : keys) {
            System.out.printf("%1$s is %2$s  %n", key, simpleMap.get(key));
        }


    }

}
