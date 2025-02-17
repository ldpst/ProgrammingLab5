package ru.itmo.prog.lab5.utils;

/**
 * Интерфейс для команд с возможностью запуска
 * @author ldpst
 */
public interface Runable {
    /**
     * Метод запуска команды
     *
     * @param args дополнительные входные данные
     */
    void run(String[] args);
}
