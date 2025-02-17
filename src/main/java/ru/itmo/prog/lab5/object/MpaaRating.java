package ru.itmo.prog.lab5.object;

/**
 * Класс Мпа рейтинга
 *
 * @author ldpst
 */
public enum MpaaRating {
    G,
    PG,
    PG_13,
    R,
    NC_17;

    /**
     * Метод, переводящий строку в верхний регистр и проверяющий на принадлежность к данному enum'у
     *
     * @param s строка
     * @return результат проверки
     */
    public static MpaaRating checkOf(String s) {
        return MpaaRating.valueOf(s.toUpperCase());
    }
}