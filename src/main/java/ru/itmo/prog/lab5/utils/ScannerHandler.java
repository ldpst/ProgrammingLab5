package ru.itmo.prog.lab5.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс для единообразного использования Scanner и BufferReader
 *
 * @author ldpst
 */
public class ScannerHandler {
    private final Scanner scanner;
    private final BufferedReader reader;
    private final InputFormat inputFormat;

    public ScannerHandler(Scanner scanner) {
        this.scanner = scanner;
        inputFormat = InputFormat.CONSOLE;
        this.reader = null;
    }

    public ScannerHandler(BufferedReader reader) {
        this.reader = reader;
        inputFormat = InputFormat.FILE;
        this.scanner = null;
    }

    /**
     * Метод для считывания новой строки
     *
     * @return считанная строка
     */
    public String nextLine() {
        if (scanner != null) {
            return scanner.nextLine();
        }
        if (reader != null) {
            try {
                return reader.readLine();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Возвращает формат ввода
     *
     * @return формат ввода
     */
    public InputFormat getInputFormat() {
        return inputFormat;
    }
}
