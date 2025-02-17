package ru.itmo.prog.lab5.object;

/**
 * Класс жанра фильма
 * @author ldpst
 */
public enum MovieGenre {
    DRAMA,
    MUSICAL,
    HORROR;

    /**
     * Метод, переводящий строку в верхний регистр и проверяющий на принадлежность к данному enum'у
     *
     * @param s строка
     * @return результат проверки
     */
    public static MovieGenre checkOf(String s) {
        return MovieGenre.valueOf(s.toUpperCase());
    }
}