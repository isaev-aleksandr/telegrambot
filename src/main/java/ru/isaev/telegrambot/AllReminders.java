package ru.isaev.telegrambot;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AllReminders {
    public List<Reminders> reminders = new ArrayList<>();
}
