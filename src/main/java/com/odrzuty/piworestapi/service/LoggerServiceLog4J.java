package com.odrzuty.piworestapi.service;

import com.odrzuty.piworestapi.Application;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.stereotype.Component;

@Component
public class LoggerServiceLog4J implements LoggerService {

    private static final Logger logger = LogManager.getLogger(Application.class);
    private Marker marker  = MarkerManager.getMarker("RUNTIME");
    @Override
    public void logError(String message) {
        logger.error(marker, message);
    }

    @Override
    public void logInfo(String message) {
        logger.info(marker, message);
    }
}
