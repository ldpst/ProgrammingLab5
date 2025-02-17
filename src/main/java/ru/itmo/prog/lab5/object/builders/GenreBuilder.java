package ru.itmo.prog.lab5.object.builders;

import ru.itmo.prog.lab5.object.MovieGenre;
import ru.itmo.prog.lab5.utils.InputFormat;
import ru.itmo.prog.lab5.utils.ScannerHandler;
import ru.itmo.prog.lab5.utils.StreamHandler;

import java.util.Arrays;

public class GenreBuilder extends Builder {
    public GenreBuilder(StreamHandler stream, ScannerHandler scanner, InputFormat inputFormat) {
        super(stream, scanner, inputFormat);
    }

    @Override
    public MovieGenre build() {
        return readGenre();
    }

    /**
     * Метод для чтения жанра
     *
     * @return Найденный жанр
     */
    private MovieGenre readGenre() {
        stream.print("> Введите жанр " + Arrays.toString(MovieGenre.values()) + ":\n$ ");
        String res = scanner.nextLine().trim();
        printIfFileMode(res);
        if (res.isEmpty()) {
            return null;
        }
        MovieGenre genre;
        try {
            genre = MovieGenre.checkOf(res);
        } catch (IllegalArgumentException e) {
            stream.printErr("Введенный жанр не является одним из предложенных\n");
            return (MovieGenre) tryAgain(this::readGenre);
//            throw new InputCantBeNullException();
        }
        return genre;
    }
}
