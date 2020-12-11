package com.company;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {

    static class MyThread extends Thread
    {
        private  String date;
        MyThread(String date) {this.date=date;}

        @Override
        public void run()
        {
            LogsService serviceThread = new LogsService();
            try {
                serviceThread.getLogsByTime(date);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws IOException, InterruptedException {
	LogsService service = new LogsService();
        LocalDateTime start = LocalDateTime.now();
	service.getLogsByTime("2020-01-03");
	service.getLogsByTime("2020-01-04");
	service.getLogsByTime("2020-01-05");
	service.getLogsByTime("2020-01-06");
    System.out.println("CONSECUTIVE DURATION: "+ ChronoUnit.MILLIS.between(start, LocalDateTime.now())+"\n");
        start = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
    service.getLogsByPeriod("2020-01-03","2020-01-06");
    System.out.println("BUNCH DURATION: "+ ChronoUnit.MILLIS.between(start, LocalDateTime.now())+"\n");
        start = LocalDateTime.now();
    new MyThread("2020-01-03").start();
	new MyThread("2020-01-04").start();
	new MyThread("2020-01-05").start();
	new MyThread("2020-01-06").start();
    }
}
