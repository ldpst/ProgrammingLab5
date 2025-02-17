package ru.itmo.prog.lab5;

import ru.itmo.prog.lab5.utils.Runner;
import ru.itmo.prog.lab5.utils.ScannerHandler;
import ru.itmo.prog.lab5.utils.StreamHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ScannerHandler scanner = new ScannerHandler(new Scanner(System.in));
        StreamHandler stream = new StreamHandler(System.out);
        Runner runner = new Runner(scanner, stream);
        runner.runInteractiveMode();
    }
}
