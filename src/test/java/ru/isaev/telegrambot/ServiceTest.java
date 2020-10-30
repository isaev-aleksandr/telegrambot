package ru.isaev.telegrambot;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceTest {
    String textMessage = "11.01";
    Service service = new Service();
    String s = null;
    @Test
    public void parseDateTest(){
        String[] parseString = service.parseDate(textMessage);
        System.out.println(parseString.length);
        System.out.println(parseString[0]);
        System.out.println(parseString[1]);
        int day = Integer.parseInt(parseString[0]);
        int month = Integer.parseInt(parseString[1]);
        System.out.println(day);
        System.out.println(month);
    }


}