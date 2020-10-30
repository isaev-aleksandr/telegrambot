package ru.isaev.telegrambot;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Component
public class Buttons {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    public synchronized void createReminderButtons(SendMessage sendMessage) {

// Создаем клавиуатуру


        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);


// Создаем список строк клавиатуры

        List<KeyboardRow> keyboard = new ArrayList<>();


// Первая строчка клавиатуры

        KeyboardRow keyboardFirstRow = new KeyboardRow();

// Добавляем кнопки в первую строчку клавиатуры

        keyboardFirstRow.add(new KeyboardButton("Создать напоминание"));


// Добавляем все строчки клавиатуры в список

        keyboard.add(keyboardFirstRow);

// и устанваливаем этот список нашей клавиатуре

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public synchronized void createNameButtons(SendMessage sendMessage) {

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Я укажу ID чата друга"));

        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public synchronized void createNoButtons(SendMessage sendMessage) {

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        keyboard.clear();

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
    public synchronized void createDaysButtons(SendMessage sendMessage) {

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Сегодня"));

        KeyboardRow keyboardSecondRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Завтра"));

        KeyboardRow keyboardThirdRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Послезавтра"));

        KeyboardRow keyboardFourthRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Точная дата"));

        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThirdRow);
        keyboard.add(keyboardFourthRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    public synchronized void createDeleteButtons(SendMessage sendMessage) {

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboard = new ArrayList<>();

        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Удалить напоминание"));

        keyboard.add(keyboardFirstRow);

        replyKeyboardMarkup.setKeyboard(keyboard);
    }
}
