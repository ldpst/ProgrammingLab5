package ru.itmo.prog.lab5.object.builders;

import ru.itmo.prog.lab5.object.Person;
import ru.itmo.prog.lab5.utils.InputFormat;
import ru.itmo.prog.lab5.utils.ScannerHandler;
import ru.itmo.prog.lab5.utils.StreamHandler;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PersonBuilder extends Builder {
    public PersonBuilder(StreamHandler stream, ScannerHandler scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public Person build() {
        return new Person(
                readName(),
                readBirthday(),
                readWeight(),
                readPassportID()
        );
    }

    /**
     * Метод для чтения имени
     *
     * @return Найденная строка
     */
    private String readName() {
        stream.print("> Введите имя человека:\n$ ");
        String name = scanner.nextLine().trim();
        printIfFileMode(name);
        if (name.isEmpty()) {
            stream.printErr("Имя не должно быть пустым\n");
            return (String) tryAgain(this::readName);
//            throw new InputCantBeNullException();
        }
        return name;
    }

    /**
     * Метод для чтения даты рождения по формату
     *
     * @return Найденная дата
     */
    private Date readBirthday() {
        String format = "dd:MM:yyyy";
        stream.printf("> Введите дату рождения человека (формата %s):\n$ ", format);
        String res = scanner.nextLine().trim();
        printIfFileMode(res);
        if (res.isEmpty()) {
            return null;
        }
        String[] split = res.split(":");
        if (split.length != 3) {
            stream.printErr("Введенные данные неверного формата\n");
            return (Date) tryAgain(this::readBirthday);
        }
        int day, month, year;
        try {
            day = Integer.parseInt(split[0]);
            month = Integer.parseInt(split[1]);
            year = Integer.parseInt(split[2]);
        } catch (NumberFormatException e) {
            stream.printErr("В дате допустимо использование только цифр и символа \":\"\n");
            return (Date) tryAgain(this::readBirthday);
        }
        if (day < 1 || day > 31 || month < 1 || month > 12 || year < 1) {
            stream.printErr("Введена невозможная дата\n");
            return (Date) tryAgain(this::readBirthday);
        }
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date;
        try {
            date = dateFormat.parse(res);
        } catch (ParseException e) {
            stream.printErr("Введенные данные неверного формата\n");
            return (Date) tryAgain(this::readBirthday);
        }
        return date;
    }

    /**
     * Метод для чтения веса
     *
     * @return Найденный вес
     */
    private long readWeight() {
        stream.print("> Введите вес человека:\n$ ");
        String res = scanner.nextLine().trim();
        printIfFileMode(res);
        if (res.isEmpty()) {
            stream.printErr("Вес не должен быть пустым\n");
            return (long) tryAgain(this::readWeight);
        }
        long weight;
        try {
            weight = Long.parseLong(res);
        } catch (NumberFormatException e) {
            stream.printErr("Вес должен быть целым числом\n");
            return (long) tryAgain(this::readWeight);
        }
        if (weight <= 0) {
            stream.printErr("Вес должен быть больше 0\n");
            return (long) tryAgain(this::readWeight);
        }
        return weight;
    }

    /**
     * Метод для паспорт айди
     *
     * @return Найденный паспорт айди
     */
    private String readPassportID() {
        stream.print("> Введите паспорт айди:\n$ ");
        String passportID = scanner.nextLine().trim();
        printIfFileMode(passportID);
        if (passportID.isEmpty()) {
            stream.printErr("Паспорт айди не может быть пустым\n");
            return (String) tryAgain(this::readPassportID);
        }
        if (!passportID.matches("\\d+")) {
            stream.printErr("Паспорт айди должен состоять только из цифр\n");
            return (String) tryAgain(this::readPassportID);
        }
        if (passportID.length() > 25) {
            stream.printErr("Паспорт айди не должен быть больше 25 символов\n");
            return (String) tryAgain(this::readPassportID);
        }
        return passportID;
    }
}
