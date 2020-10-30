package ru.isaev.telegrambot;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Reminders {
    static long chatsCount;
    public long Id;
    public boolean isVisit;
    public long sender;
    public long giver;
    public String senderName;
    public String giverName;
    public boolean targetDate;
    public String remind;
    public LocalDate remindDate;
    public LocalDateTime remindTime;
    public boolean completed;
    public boolean deleted;
    public Reminders(long sender){
        chatsCount++;
        this.Id =chatsCount;
        this.sender = sender;
    }
}
