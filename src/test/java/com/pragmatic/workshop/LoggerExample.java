package com.pragmatic.workshop;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Pragmatic Test Labs (Private) Limited
 *
 * @Author Janesh Kodikara
 */
public class LoggerExample {


    /**
     * This example demonstrates the use of log levels and setting a custom log level
     *
     * Reference : https://logging.apache.org/log4j/2.x/manual/customloglevels.html
     *
     */
    public static void main(String[] args) {

        //Getting an instance of a logger from LogManager
        Logger logger = LogManager.getLogger(LoggerExample.class.getName());

        //Log messages
        logger.error("This is an error ");
        logger.info("This is an info message ");
        logger.warn("this is a warn");
        logger.fatal("This is a fatal message");
        logger.debug("This is a debug");
        logger.trace("This is trace");

        // Creating a custom log level
        final Level PTL = Level.forName("PTL", 550);

        logger.log(PTL, "This is PTL Custom log level ");


    }

}
