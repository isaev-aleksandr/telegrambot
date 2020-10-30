package ru.isaev.telegrambot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс-обработчик поступающих к боту сообщений.
 */

public class EchoBot extends TelegramLongPollingBot {

    private final Buttons buttons = new Buttons();
    private final Pattern patternNumbers = Pattern.compile("[0-9]{9}");
    private final Pattern patternDate = Pattern.compile("[0-3][0-9][.][0-1][0-9]");
    private final Pattern patternTime = Pattern.compile("[0-2][0-9][:][0-6][0-9]");
    private final Service service = new Service();
    private final String finger = "\ud83d\udc47\ud83c\udffb";
    private final String keyboard = "\u2328\ufe0f";
    /**
     * Метод, который возвращает токен, выданный нам ботом @BotFather.
     * @return токен
     */
    @Override
    public String getBotToken() {

        return "1389157501:AAHFApdxCM45xnwzGpW6bk9H-IGrMb8h2ZA";
    }

    /**
     * Метод-обработчик поступающих сообщений.
     * @param update объект, содержащий информацию о входящем сообщении
     */
    @Override
    public void onUpdateReceived(Update update) {
        try {
            if (update.hasMessage() && update.getMessage().hasText()) {
                String textMessage = update.getMessage().getText();
                Long chatId = update.getMessage().getChatId();
                Matcher matcherNumbers = patternNumbers.matcher(textMessage);
                Matcher matcherDate = patternDate.matcher(textMessage);
                Matcher matcherTime = patternTime.matcher(textMessage);
                SendMessage outMessage = new SendMessage();
                outMessage.setChatId(chatId);
                Reminders rem = service.getOrCreateChatForChatId(chatId);
                if (matcherNumbers.matches() &&
                        rem.getSender() != 0 &&
                        rem.getGiver() == 0 &&
                        rem.getSenderName() == null &&
                        rem.getGiverName() == null &&
                        rem.getRemind() == null &&
                        !rem.targetDate &&
                        rem.getRemindDate() == null &&
                        rem.getRemindTime() == null) {

                    Reminders reminders = service.getOrCreateChatForChatId(chatId);
                    reminders.setGiver(Long.parseLong(textMessage));
                    outMessage.setText(
                            "Отлично! Как зовут твоего друга?");
                    execute(outMessage);
                } else {
                    if (rem.getSender() != 0 &&
                            rem.getGiver() != 0 &&
                            rem.getSenderName() == null &&
                            rem.getGiverName() == null &&
                            rem.getRemind() == null &&
                            !rem.targetDate &&
                            rem.getRemindDate() == null &&
                            rem.getRemindTime() == null) {
                        Reminders reminders = service.getOrCreateChatForChatId(chatId);
                        reminders.setGiverName(textMessage);
                        outMessage.setText(
                                "Теперь назови свое имя ");
                        execute(outMessage);
                    } else {
                        if (rem.getSender() != 0 &&
                                rem.getGiver() != 0 &&
                                rem.getSenderName() == null &&
                                rem.getGiverName() != null &&
                                rem.getRemind() == null &&
                                !rem.targetDate &&
                                rem.getRemindDate() == null &&
                                rem.getRemindTime() == null) {
                            Reminders reminders = service.getOrCreateChatForChatId(chatId);
                            reminders.setSenderName(textMessage);
                            outMessage.setText(
                                    "Теперь укажи текст напоминания для " +
                                            reminders.getGiverName());
                            execute(outMessage);
                        } else {
                            if (rem.getSender() != 0 &&
                                    rem.getGiver() != 0 &&
                                    rem.getSenderName() != null &&
                                    rem.getGiverName() != null &&
                                    rem.getRemind() == null &&
                                    !rem.targetDate &&
                                    rem.getRemindDate() == null &&
                                    rem.getRemindTime() == null) {
                                Reminders reminder = service.getOrCreateChatForChatId(chatId);
                                reminder.setRemind(textMessage);
                                outMessage.setText(
                                        "Укажи дату ");
                                buttons.createDaysButtons(outMessage);
                                execute(outMessage);
                            } else {
                                if(matcherDate.matches() &&
                                        rem.getSender() != 0 &&
                                        rem.getGiver() != 0 &&
                                        rem.getSenderName() != null &&
                                        rem.getGiverName() != null &&
                                        rem.getRemind() != null &&
                                        rem.targetDate &&
                                        rem.getRemindDate() == null &&
                                        rem.getRemindTime() == null){
                                    Reminders reminders = service.getOrCreateChatForChatId(chatId);
                                    service.setDateAfterParse(reminders.getSender(), reminders.getGiver(), textMessage);
                                    outMessage.setText(
                                            "Укажите время в формате ЧЧ:ММ Пример: 16:15");
                                    execute(outMessage);

                                }else{
                                    if(!matcherDate.matches() &&
                                            rem.getSender() != 0 &&
                                            rem.getGiver() != 0 &&
                                            rem.getSenderName() != null &&
                                            rem.getGiverName() != null &&
                                            rem.getRemind() != null &&
                                            rem.targetDate &&
                                            rem.getRemindDate() == null &&
                                            rem.getRemindTime() == null) {
                                        outMessage.setText(
                                                "Ошибка формата даты. Укажите дату в формате ДД.ММ Пример: Для 21 Февраля следует написать 21.03");
                                        execute(outMessage);
                                    } else {
                                        if((rem.getSender() != 0 &&
                                                rem.getGiver() != 0 &&
                                                rem.getSenderName() != null &&
                                                rem.getGiverName() != null &&
                                                rem.getRemind() != null &&
                                                !rem.targetDate &&
                                                rem.getRemindDate() == null &&
                                                rem.getRemindTime() == null) &&
                                                (textMessage.equals("Сегодня") ||
                                                textMessage.equals("Завтра") ||
                                                textMessage.equals("Послезавтра") ||
                                                textMessage.equals("Точная дата"))){
                                            Reminders reminders = service.getOrCreateChatForChatId(chatId);
                                            switch (textMessage){
                                                case ("Сегодня"):
                                                    service.setToday(reminders.getSender(), reminders.getGiver());
                                                    outMessage.setText(
                                                            "Укажите время в формате ЧЧ:ММ Пример: 16:15");
                                                    execute(outMessage);
                                                    break;
                                                case ("Завтра"):
                                                    service.setTomorrow(reminders.getSender(), reminders.getGiver());
                                                    outMessage.setText(
                                                            "Укажите время в формате ЧЧ:ММ Пример: 16:15");
                                                    execute(outMessage);
                                                    break;
                                                case ("Послезавтра"):
                                                    service.setDayAfterTomorrow(reminders.getSender(), reminders.getGiver());
                                                    outMessage.setText(
                                                            "Укажите время в формате ЧЧ:ММ Пример: 16:15");
                                                    execute(outMessage);
                                                    break;
                                                case ("Точная дата"):
                                                    reminders.setTargetDate(true);
                                                    outMessage.setText(
                                                            "Укажите точную дату в формате ДД.ММ Пример: 21.03 ");
                                                    execute(outMessage);
                                                    break;
                                            }
                                        }else {
                                            if (matcherTime.matches() &&
                                                    rem.getSender() != 0 &&
                                                    rem.getGiver() != 0 &&
                                                    rem.getSenderName() != null &&
                                                    rem.getGiverName() != null &&
                                                    rem.getRemind() != null &&
                                                    rem.getRemindDate() != null &&
                                                    rem.getRemindTime() == null) {
                                                service.setDataTime(rem.getSender(), rem.getGiver(), textMessage);
                                                rem.setCompleted(true);
                                                outMessage.setText(
                                                        "Напоминание \"" + rem.getRemind() +
                                                                "\" для " + rem.getGiverName() +
                                                                " на " + rem.getRemindDate().getDayOfMonth() +
                                                                "." + rem.getRemindDate().getMonthValue() +
                                                                " в " + rem.getRemindTime().getHour() + ":" +
                                                                rem.getRemindTime().getMinute() + " установлено. " + rem.toString()
                                                );
                                                buttons.createDeleteButtons(outMessage);
                                                execute(outMessage);
                                                service.go(rem.getSender());

                                            }else {
                                                if(!matcherTime.matches() &&
                                                        rem.getSender() != 0 &&
                                                        rem.getGiver() != 0 &&
                                                        rem.getSenderName() != null &&
                                                        rem.getGiverName() != null &&
                                                        rem.getRemind() != null &&
                                                        rem.getRemindDate() != null &&
                                                        rem.getRemindTime() == null){
                                                    outMessage.setText(
                                                            "Ошибка формата Времени. Укажите время в формате ЧЧ.ММ Пример: 16:15");
                                                    execute(outMessage);
                                                }
                                                else{
                                                    if (textMessage.equals("Удалить напоминание") &&
                                                            rem.getSender() != 0 &&
                                                            rem.getGiver() != 0 &&
                                                            rem.getSenderName() != null &&
                                                            rem.getGiverName() != null &&
                                                            rem.getRemind() != null &&
                                                            rem.getRemindDate() != null &&
                                                            rem.getRemindTime() != null) {
                                                        rem.setDeleted(true);
                                                        outMessage.setText("Напоминание удалено");
                                                        execute(outMessage);
                                                    } else {
                                                        if ((textMessage.equals("Создать напоминание") &&
                                                                rem.getSender() != 0 &&
                                                                rem.getGiver() == 0 &&
                                                                rem.getSenderName() == null &&
                                                                rem.getGiverName() == null &&
                                                                rem.getRemind() == null &&
                                                                !rem.targetDate &&
                                                                rem.getRemindDate() == null &&
                                                                rem.getRemindTime() == null)) {
                                                            service.getOrCreateChatForChatId(chatId);
                                                            outMessage.setText("Кому хотите установить напоминание?");
                                                            buttons.createNameButtons(outMessage);
                                                            execute(outMessage);
                                                        } else {
                                                            if (textMessage.equals("Я укажу ID чата друга") &&
                                                                    rem.getSender() != 0 &&
                                                                    rem.getGiver() == 0 &&
                                                                    rem.getSenderName() == null &&
                                                                    rem.getGiverName() == null &&
                                                                    rem.getRemind() == null &&
                                                                    !rem.targetDate &&
                                                                    rem.getRemindDate() == null &&
                                                                    rem.getRemindTime() == null) {
                                                                outMessage.setText("Укажите ID чата друга (9 цифр)");
                                                                buttons.createNoButtons(outMessage);
                                                                execute(outMessage);
                                                            } else {
                                                                if (!matcherNumbers.matches() &&
                                                                        (rem.getSender() != 0 &&
                                                                        rem.isVisit &&
                                                                        rem.getGiver() == 0 &&
                                                                        rem.getSenderName() == null &&
                                                                        rem.getGiverName() == null &&
                                                                        rem.getRemind() == null &&
                                                                        !rem.targetDate &&
                                                                        rem.getRemindDate() == null &&
                                                                        rem.getRemindTime() == null)){
                                                                    outMessage.setText("ID чата друга должен состоять из 9 цифр, " +
                                                                            "друг получает его при первом сообщении от бота");
                                                                    buttons.createNoButtons(outMessage);
                                                                    execute(outMessage);
                                                                }else {
                                                                    if (rem.completed){
                                                                        outMessage.setText("Напоминание уже установлено, " +
                                                                                "чтобы создать новое необходимо удалить текущее напоминание " +
                                                                                "или дождаться когда выполнится текущее");
                                                                        buttons.createDeleteButtons(outMessage);
                                                                        execute(outMessage);
                                                                    }else {
                                                                        outMessage.setText(
                                                                                "Привет! это ID твоего чата: " +
                                                                                        chatId +
                                                                                        ", отправь его другу, чтобы он установил тебе напоминание, " +
                                                                                        "или сам установи ему напоминание! " + "Используй дополнительную клавиатуру " +
                                                                                keyboard + finger);
                                                                        buttons.createReminderButtons(outMessage);
                                                                        rem.isVisit = true;
                                                                        execute(outMessage);
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод, который возвращает имя пользователя бота.
     * @return имя пользователя
     */
    @Override
    public String getBotUsername() {
        return "RemindersForYourLovedOnesBot";
    }
}