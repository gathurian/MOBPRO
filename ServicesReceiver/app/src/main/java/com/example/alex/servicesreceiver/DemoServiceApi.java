package com.example.alex.servicesreceiver;

public interface DemoServiceApi {
    int getNumberOfJobsRunning();
    int getNumberOfJobsCompleted();
    void resetCounters();
}
