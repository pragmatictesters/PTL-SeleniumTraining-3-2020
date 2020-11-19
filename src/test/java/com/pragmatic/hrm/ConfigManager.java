package com.pragmatic.hrm;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class ConfigManager {

    private static String BROWSER_TYPE;



    public static void initProperties() {
        Logger logger = LogManager.getLogger();

        try {
            Configuration config = new PropertiesConfiguration("conf/hrm.properties");
            Constants.BASE_URL = config.getString("base.url");
            Constants.BROWSER_TYPE = config.getString("browser.type");
            Constants.ADMIN_USERNAME = config.getString("admin.username");
            Constants.ADMIN_PASSWORD = config.getString("admin.password");
            logger.info("Properties are initialized ");


        } catch (ConfigurationException e) {
            logger.fatal("Property Initialization Failed", e);
            throw new ConfigManagerException(" Property file initialization failed \n " + e.getMessage());
        }
    }
}
