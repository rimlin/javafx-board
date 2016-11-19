package client.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Created by ilmir on 2016-11-08.
 */
public class DateUtil {

    private static final String DATE_PATTERN = "dd.MM.yyyy";

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);


    public static String format(LocalDate date) {
        if (date == null) {
            return null;
        }

        return DATE_FORMATTER.format(date);
    }

    /**
     * Преобразует строку, которая отформатирована по правилам
     * шаблона {@link DateUtil#DATE_PATTERN} в объект {@link LocalDate}.
     *
     * Возвращает null, если строка не может быть преобразована.
     *
     * @param dateString - дата в виде String
     * @return объект даты или null, если строка не может быть преобразована
     */
    public static LocalDate parse(String dateString) {
        try {
            return DATE_FORMATTER.parse(dateString, LocalDate::from);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    /**
     * Проверяет, является ли строка корректной датой.
     *
     * @param dateString
     * @return true, если строка является корректной датой
     */
    public static boolean validDate(String dateString) {
        // Пытаемся разобрать строку.
        return DateUtil.parse(dateString) != null;
    }

}
