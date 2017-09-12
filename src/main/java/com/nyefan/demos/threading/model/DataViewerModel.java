package com.nyefan.demos.threading.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nyefan on 9/12/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */
public class DataViewerModel {

    private IntegerProperty hour;
    private IntegerProperty minute;

    private MKSetupModel mkSetupModel;
    private int updateInterval = 500; //milliseconds
    private ScheduledExecutorService executor;
    private ScheduledFuture<?> scheduledTask;
    private Thread task;

    public DataViewerModel(MKSetupModel mkSetupModel) {
        this.mkSetupModel = mkSetupModel;
        hour = new SimpleIntegerProperty(this.mkSetupModel.getHour());
        minute = new SimpleIntegerProperty(this.mkSetupModel.getMinute());

        executor = new ScheduledThreadPoolExecutor(1);
        task = new Thread(() -> {
            hour.setValue(mkSetupModel.getHour());
            minute.setValue(mkSetupModel.getMinute());
        });
        task.setDaemon(true);
        task.setPriority(Thread.NORM_PRIORITY);
        initThread();
    }

    private void initThread() {
        if (scheduledTask != null) {
            scheduledTask.cancel(true);
        }

        scheduledTask = executor.scheduleAtFixedRate(task, 0, updateInterval, TimeUnit.MILLISECONDS);
    }

    public IntegerProperty getHourProperty() {
        return hour;
    }

    public IntegerProperty getMinuteProperty() {
        return minute;
    }

    public void setUpdateInterval(Double newInterval) { //newInterval should be in seconds
        if (newInterval != null
                && newInterval >= 1d / 1000d
                && newInterval <= 0.5d) {
            this.updateInterval = ((Double) (newInterval * 1000)).intValue();
            initThread();
        }
    }
}
