package com.example.demo1.my_utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestConverter {

    /**
     * 將
     * @return Timestamp
     */
    public static Timestamp recordedAtStrToTimestamp(String recordedAt) {
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return Timestamp.valueOf(LocalDateTime.parse(recordedAt, DTF));
    }
}
