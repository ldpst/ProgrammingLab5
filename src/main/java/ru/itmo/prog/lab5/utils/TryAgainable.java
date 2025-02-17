package ru.itmo.prog.lab5.utils;

import java.util.function.Supplier;

/**
 * Интерфейс, дающий возможность запускать методы заново
 *
 * @author ldpst
 */
public interface TryAgainable {
    Object tryAgain(Supplier<Object> action);
}
