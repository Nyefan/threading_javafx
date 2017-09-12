package com.nyefan.demos.threading.model;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Nyefan on 9/12/2017.
 * Contact at nyefancoding@gmail.com
 * or through Github at github.com/nyefan
 */
public class OperationModel {
    private MKSetupModel mkSetupModel;
    private String message = "";

    private int updateInterval = 1000; //milliseconds
    private ScheduledThreadPoolExecutor executor;
    private Thread task;

    public OperationModel(MKSetupModel mkSetupModel) {
        this.mkSetupModel = mkSetupModel;
        executor = new ScheduledThreadPoolExecutor(1);
        task = new Thread(() -> {
            this.mkSetupModel.setMessage(message);
        });
        task.setDaemon(true);
        task.setPriority(Thread.MIN_PRIORITY);
        executor.scheduleAtFixedRate(task, 0, updateInterval, TimeUnit.MILLISECONDS);
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
