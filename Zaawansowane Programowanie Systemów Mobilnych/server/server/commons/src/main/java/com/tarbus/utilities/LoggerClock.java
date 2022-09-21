package com.tarbus.utilities;

import java.time.LocalDateTime;

public class LoggerClock {
  private final LocalDateTime startTime;
  private LocalDateTime lastOperationTime;

  public LoggerClock() {
    this.startTime = LocalDateTime.now();
    this.lastOperationTime = this.startTime;
  }

  public int getSecondsFromLastCheck() {
    LocalDateTime timeNow = LocalDateTime.now();
    int seconds = (timeNow.getHour() * 60 + timeNow.getMinute()) * 60 + timeNow.getSecond() - (lastOperationTime.getHour() * 60 + lastOperationTime.getMinute()) * 60 + lastOperationTime.getSecond();
    this.lastOperationTime = timeNow;
    return seconds;
  }

  public int getTimeFromStart() {
    LocalDateTime timeNow = LocalDateTime.now();
    int seconds = (timeNow.getHour() * 60 + timeNow.getMinute()) * 60 + timeNow.getSecond() - (startTime.getHour() * 60 + startTime.getMinute()) * 60 + startTime.getSecond();
    this.lastOperationTime = timeNow;
    return seconds;
  }
}
