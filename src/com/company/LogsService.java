package com.company;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogsService {
    private List<String> logsList;

    public void getLogsByTime(String date) throws IOException {
        String fileName = "C:/Users/user/IdeaProjects/lab9/src/com/company/logs.txt";
        LocalDateTime start = LocalDateTime.now();
        System.out.println((date + " search started at - " + start));
        this.logsList =
                Files.lines(Paths.get(fileName))
                        .filter(line->line.contains(date))
                        .collect(Collectors.toList());
        Path path = Paths.get("logs"+date+".txt");
        Files.write(path,logsList );
        System.out.println(date+" search over at " + LocalDateTime.now()
                + " duration " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
    }
    public void getLogsByPeriod(String dateStartString, String dateEndString) throws IOException {
        String fileName = "C:/Users/user/IdeaProjects/lab9/src/com/company/logs.txt";
        LocalDateTime start = LocalDateTime.now();
        System.out.println((dateStartString + "--" + dateEndString + " search started at - " + start));
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate dateStart = LocalDate.parse(dateStartString);
        LocalDate dateEnd = LocalDate.parse(dateEndString);
        List<String> date = new ArrayList<String>();
        for (int i = 0; i<= ChronoUnit.DAYS.between(dateStart,dateEnd);i++)
        {
            date.add(i,dateStart.plusDays(i).format(formatter));
        }
        for(int i = 0; i< date.size();i++)
        {
            int finalI = i;
            this.logsList =
                    Files.lines(Paths.get(fileName))
                            .filter(line->line.contains(date.get(finalI)))
                            .collect(Collectors.toList());
            Path path = Paths.get("logs"+date.get(i)+".txt");
            Files.write(path,logsList );
        }
        System.out.println(date+" search over at " + LocalDateTime.now()
                + " duration " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
    }
}
