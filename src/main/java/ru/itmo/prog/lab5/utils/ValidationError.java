package ru.itmo.prog.lab5.utils;

/**
 * Исключение, вызываемое при необработанном нарушении условий полей.
 * Создано в целях тестирования и отладки
 *
 * @author ldpst
 */
public class ValidationError extends RuntimeException {
    String place;

    public ValidationError(String place) {
        super();
        this.place = place;
    }

    @Override
    public String getMessage() {
        return "Ошибка при загрузке данных из файла. Объект класса " + place + " введен неверно.";
    }
}
