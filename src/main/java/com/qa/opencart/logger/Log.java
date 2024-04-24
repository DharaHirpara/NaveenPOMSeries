package com.qa.opencart.logger;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Log {//customemethod
    private static final Logger logger= LogManager.getLogger(Log.class);

    public static void info(String message){
        logger.info(message);
    }
    public static void error(String message){
        logger.info(message);
    }
    public static void error(String message,Exception e){//in driverfactory switch case
        logger.info(message, e);
    }
    public static void debug(String message){
        logger.debug(message);
    }
    public static void warn(String message){
        logger.warn(message);
    }
}
