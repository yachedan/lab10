package com.company;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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

        LocalDate dateStart  = LocalDate.parse(dateStartString);
        LocalDate dateEnd = LocalDate.parse(dateEndString);

        this.logsList = Files.lines(Paths.get(fileName))
                .filter(line -> line.length()>9)
                .filter(line -> Character.isDigit(line.charAt(0)))
                .filter(line -> !LocalDate.parse(line.substring(0,10)).isBefore(dateStart))
                .filter(line -> !LocalDate.parse(line.substring(0,10)).isAfter(dateEnd))
                .collect(Collectors.toList());
        Path path = Paths.get("logs["+dateStartString+"; "+dateEndString+".txt");
        Files.write(path,logsList );

        System.out.println(dateStartString + "--" + dateEndString + " search ended at - " + LocalDateTime.now()
                + " duration " + ChronoUnit.MILLIS.between(start, LocalDateTime.now()));
    }
}
