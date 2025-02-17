package ru.itmo.prog.lab5.object.builders;

import ru.itmo.prog.lab5.object.*;
import ru.itmo.prog.lab5.utils.InputFormat;
import ru.itmo.prog.lab5.utils.ScannerHandler;
import ru.itmo.prog.lab5.utils.StreamHandler;

import java.util.Arrays;

/**
 * Класс для создания объекта Movie
 *
 * @author ldpst
 */
public class MovieBuilder extends Builder {
    public MovieBuilder(StreamHandler stream, ScannerHandler scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public Movie build() {
        return new Movie(
                readName(),
                readCoordinates(),
                readOscarCount(),
                readGenre(),
                readMpaaRating(),
                readPerson());
    }

    /**
     * Метод для чтения имени
     *
     * @return Найденная строка
     */
    private String readName() {
        stream.print("> Введите название фильма:\n$ ");
        String name = scanner.nextLine().trim();
        printIfFileMode(name);
        if (name.isEmpty()) {
            stream.printErr("Название не должно быть пустым\n");
            return (String) tryAgain(this::readName);
//            throw new InputCantBeNullException();
        }
        return name;
    }

    /**
     * Метод для чтения координат
     *
     * @return Найденные координаты
     */
    private Coordinates readCoordinates() {
        stream.print("* Ввод координат\n");
        return new CoordinatesBuilder(stream, scanner, inputFormat).build();
    }

    /**
     * Метод для чтения количества оскаров
     *
     * @return Найденное количество
     */
    private Long readOscarCount() {
        stream.print("> Введите количество оскаров:\n$ ");
        String res = scanner.nextLine().trim();
        printIfFileMode(res);
        long count;
        try {
            count = Long.parseLong(res);
        } catch (NumberFormatException e) {
            stream.printErr("Количество оскаров должно быть целым числом\n");
            return (Long) tryAgain(this::readOscarCount);
        }
        if (count <= 0) {
            stream.printErr("Количество оскаров должно быть больше нуля\n");
            return (Long) tryAgain(this::readOscarCount);
        }
        return count;
    }

    /**
     * Метод для чтения жанра
     *
     * @return Найденный жанр
     */
    private MovieGenre readGenre() {
        return new GenreBuilder(stream, scanner, inputFormat).build();
    }

    /**
     * Метод для чтения Мпаа Рейтинга
     *
     * @return Найденный MpaaRating
     */
    private MpaaRating readMpaaRating() {
        stream.print("> Введите Мпаа Рейтинг " + Arrays.toString(MpaaRating.values()) + ":\n$ ");
        String res = scanner.nextLine().trim();
        printIfFileMode(res);
        if (res.isEmpty()) {
            return null;
        }
        MpaaRating rating;
        try {
            rating = MpaaRating.checkOf(res);
        } catch (IllegalArgumentException e) {
            stream.printErr("Введенный Мпаа рейтинг не является одним из предложенных\n");
            return (MpaaRating) tryAgain(this::readMpaaRating);
        }
        return rating;
    }

    /**
     * Метод для чтения человека
     *
     * @return Найденный Person
     */
    private Person readPerson() {
        stream.print("> Оператор != null? y/n ");
        String res = scanner.nextLine().trim().toLowerCase();
        printIfFileMode(res);
        if (res.equals("y")) {
            stream.print("* Ввод оператора\n");
            return new PersonBuilder(stream, scanner, inputFormat).build();
        }
        if (res.equals("n")) {
            return null;
        }
        return (Person) tryAgain(this::readPerson);
    }
}
