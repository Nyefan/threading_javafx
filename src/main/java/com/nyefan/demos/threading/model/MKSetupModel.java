package com.nyefan.demos.threading.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalTime;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nyefan on 9/12/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */
public class MKSetupModel {

    private IntegerProperty hour = new SimpleIntegerProperty(LocalTime.now().getHour());
    private IntegerProperty minute = new SimpleIntegerProperty(LocalTime.now().getMinute());
    private StringProperty message = new SimpleStringProperty("");

    private LocalTime referenceTime = LocalTime.MIN;
    private int updateInterval = 250; // milliseconds
    private ScheduledThreadPoolExecutor executor;
    private Thread task;

    public MKSetupModel() {
        executor = new ScheduledThreadPoolExecutor(1);
        task = new Thread(() -> {
            LocalTime displayTime = LocalTime.now()
                    .minusMinutes(referenceTime.getMinute())
                    .minusHours(referenceTime.getHour());
            hour.setValue(Math.floorMod(displayTime.getHour(), 24));
            minute.setValue(Math.floorMod(displayTime.getMinute(), 60));
        });
        task.setDaemon(true);
        task.setPriority(Thread.MAX_PRIORITY);
        executor.scheduleAtFixedRate(task, 0, updateInterval, TimeUnit.MILLISECONDS);
    }

    public StringProperty getMessageProperty() {
        return message;
    }

    void setMessage(String message) {
        this.message.setValue(message);
    }

    public void setReferenceTime(int hour, int minute) {
        if (hour >= 0 && hour < 24 && minute >= 0 && minute < 60) {
            referenceTime = LocalTime.now().minusMinutes(minute).minusHours(hour);
        }
    }

    public int getHour() {
        return hour.get();
    }

    public int getMinute() {
        return minute.get();
    }

    public IntegerProperty getHourProperty() {
        return hour;
    }

    public IntegerProperty getMinuteProperty() {
        return minute;
    }
}
