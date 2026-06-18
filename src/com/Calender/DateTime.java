/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Calender;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.swing.JLabel;

/**
 *
 * @author manis
 */
public class DateTime {

    public void curDateTime(JLabel lblDate, JLabel lblTime) {

        Thread clock = new Thread() {
            public void run() {
                while (true) {

                    TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
                    Calendar cal = new GregorianCalendar();
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);

                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR);
                    int meridian = cal.get(Calendar.HOUR_OF_DAY);

                    if (hour == 0) {
                        hour = 12;
                    }
                    String currentHour;
                    String currentMinute;
                    String currentSecond;
                    if (hour < 10) {
                        currentHour = String.valueOf("0" + hour);
                    } else {
                        currentHour = String.valueOf(hour);
                    }

                    if (minute < 10) {
                        currentMinute = String.valueOf("0" + minute);
                    } else {
                        currentMinute = String.valueOf(minute);
                    }

                    if (second < 10) {
                        currentSecond = String.valueOf("0" + second);
                    } else {
                        currentSecond = String.valueOf(second);
                    }

                    lblDate.setText(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));

                    if (meridian >= 12) {
                        lblTime.setText(currentHour + ":" + currentMinute + ":" + currentSecond + " " + "PM");
                    } else if (meridian < 12) {
                        lblTime.setText(currentHour + ":" + currentMinute + ":" + currentSecond + " " + "AM");
                    }

                }
            }
        };
        clock.start();
    }

}
