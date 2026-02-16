package com.incident.platform.loggenerator.model;

/**
 * @author mednj
 **/
public class LogEvent {

    private String service;
    private String level;
    private String message;
    private long timestamp;


    public LogEvent() {}

    public LogEvent(String service, String level, String message, long timestamp) {
        this.service = service;
        this.level = level;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
