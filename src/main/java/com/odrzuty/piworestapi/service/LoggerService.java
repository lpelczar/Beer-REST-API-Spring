package com.odrzuty.piworestapi.service;

import org.springframework.stereotype.Component;

@Component
public interface LoggerService {
    void logError(String message);
    void logInfo(String message);
}
