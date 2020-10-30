package ru.isaev.telegrambot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Service {

    private final AllReminders allReminders = new AllReminders();

    public Reminders getOrCreateChatForChatId (long chatId){
            for (Reminders remind : allReminders.getReminders()){
                if (remind.getSender() == chatId){
                    return remind;
                }
        }
            allReminders.getReminders().add(new Reminders(chatId));
            for (Reminders remind : allReminders.getReminders()){
                if(remind.getSender() == chatId){
                    return remind;
                }
            }
            return null;
    }
    public void setToday(Long sender, Long giver){
        LocalDate date = LocalDate.now();
        for (Reminders remind : allReminders.getReminders()){
            if (remind.getSender() == sender && remind.getGiver() == giver && remind.getRemindDate() == null){
                        remind.setRemindDate(date);
            }
        }

    }
    public void setTomorrow(Long sender, Long giver){
        LocalDate date = LocalDate.now();
        for (Reminders remind : allReminders.getReminders()){
            if (remind.getSender() == sender && remind.getGiver() == giver && remind.getRemindDate() == null){
                remind.setRemindDate(date.plusDays(1));
            }
        }
    }

    public void setDayAfterTomorrow(Long sender, Long giver){
        LocalDate date = LocalDate.now();
        for (Reminders remind : allReminders.getReminders()){
            if (remind.getSender() == sender && remind.getGiver() == giver && remind.getRemindDate() == null){
                remind.setRemindDate(date.plusDays(2));
            }
        }
    }

    public void setDateAfterParse (Long sender, Long giver ,String textMessage){
        String[] parseText = parseDate(textMessage);
        int day = Integer.parseInt(parseText[0]);
        int month = Integer.parseInt(parseText[1]);
        LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, day);
        for (Reminders remind : allReminders.getReminders()){
            if (remind.getSender() == sender && remind.getGiver() == giver){
                    remind.setRemindDate(date);
            }
        }
    }

    public String[] parseDate(String stringDate){
        String[] subStr = stringDate.split("\\.");
        return subStr;
    }
    public void setDataTime (Long sender, Long giver ,String textMessage){
        String[] parseText = parseTime(textMessage);
        int hour = Integer.parseInt(parseText[0]);
        int min = Integer.parseInt(parseText[1]);
        for (Reminders remind : allReminders.getReminders()){
            if (remind.getSender() == sender && remind.getGiver() == giver){
                remind.setRemindTime(LocalDateTime.of(LocalDate.now().getYear(),
                        remind.remindDate.getMonth(),
                        remind.remindDate.getDayOfMonth(),
                        hour,
                        min));
            }
        }

    }
    public String[] parseTime(String stringTime){
        return stringTime.split(":");
    }
    public synchronized void deleteRemind(Long sender){
        allReminders.getReminders().removeIf(remind -> remind.getSender() == sender);
    }

    public void go(Long sender){
        Thread t = new Thread(new Reader(sender));
        t.start();

    }
    public class Reader implements Runnable{
        Long s;
        Reader(Long sender){
            this.s = sender;
        }
        public void run(){
            SendMessage outMessage = new SendMessage();
            boolean isActive = true;
                while (isActive) {
                    for (Reminders remind : allReminders.getReminders()) {
                        LocalDateTime now = LocalDateTime.now();
                        if (remind.completed && remind.getSender() == s &&
                                remind.getRemindTime().getDayOfYear() == now.getDayOfYear() &&
                                remind.getRemindTime().getMonth() == now.getMonth() &&
                                remind.getRemindTime().getDayOfMonth() == now.getDayOfMonth() &&
                                remind.getRemindTime().getHour() == now.getHour() &&
                                remind.getRemindTime().getMinute() == now.getMinute() &&
                                remind.getRemindTime().getSecond() == now.getSecond()) {
                            try {
                                EchoBot echoBot= new EchoBot();
                                outMessage.setChatId(remind.getGiver()).setText("Напоминание от" + " " + remind.getSenderName() + ": "+
                                        remind.getRemind());
                                echoBot.execute(outMessage);
                                deleteRemind(remind.getSender());
                                isActive = false;
                            }catch (TelegramApiException e) {
                                e.printStackTrace();
                            }
                        } if (remind.deleted){
                            isActive = false;
                        }
                    }
                }

        }
    }
}
