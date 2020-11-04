package com.pragmatic.examples;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ReadProperties {

    public static void main(String[] args) {
        try {
            Configuration config = new PropertiesConfiguration("hrm.properties");
            System.out.println("base.url = " +config.getString("base.url"));

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

    }


}
