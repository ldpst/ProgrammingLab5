package ru.itmo.prog.lab5.utils;

import ru.itmo.prog.lab5.commands.Command;
import ru.itmo.prog.lab5.managers.CSVManager;
import ru.itmo.prog.lab5.managers.CommandManager;

import java.util.ArrayList;
import java.util.Map;

public class Runner {
    private final ScannerHandler scanner;
    private final StreamHandler stream;
    private final CommandManager commandManager;
    private final Map<String, Command> commands;
    private ScannerHandler oldScanner;

    private static Runner mainRunner;
    private Runner oldRunner;
    private InputFormat oldInputFormat;

    private RunMode runMode = RunMode.RUN;

    public static ArrayList<String> usedScripts = new ArrayList<>();

    public Runner(ScannerHandler scanner, StreamHandler stream) {
        this.scanner = scanner;
        this.stream = stream;
        commandManager = new CommandManager(stream, scanner, this, Permissions.USER, scanner.getInputFormat());
        commands = commandManager.getCommands();
        if (mainRunner == null) {
            mainRunner = this;
            CSVManager csvManager = new CSVManager(stream, commandManager);
            csvManager.loadFromCSV();
        }
    }

    public Runner(ScannerHandler scanner, StreamHandler stream, CommandManager commandManager) {
        this.scanner = scanner;
        this.stream = stream;
        this.commandManager = commandManager;
        oldRunner = this.commandManager.getRunner();
        this.commandManager.setRunner(this);
        oldInputFormat = this.commandManager.getInputFormat();
        oldScanner = commandManager.getScanner();
        this.commandManager.setScanner(scanner);
        commands = this.commandManager.getCommands();
        if (mainRunner == null) {
            mainRunner = this;
        }
    }

    /**
     * Запуск в интерактивном режиме
     */
    public void runInteractiveMode() {
        while (runMode == RunMode.RUN) {
            stream.print("$ ");
            String nextCommand = scanner.nextLine().trim();
            if (nextCommand.isEmpty()) {
                continue;
            }
            String[] splitCommand = nextCommand.split(" ");
            try {
                commands.get(splitCommand[0]).runWithPermission(splitCommand);
            } catch (Exception e) {
                stream.printErr("Команда не распознана\n");
            }
        }
        exit();
    }

    /**
     * Запуск в режиме скрипта
     */
    public void runScriptMode() {
        commandManager.setInputFormat(InputFormat.FILE);
        while (runMode == RunMode.RUN) {
            String nextCommand;
            try {
                nextCommand = scanner.nextLine().trim();
            } catch (NullPointerException e) {
                stream.printErr("В скрипте отсутствует команда exit\n");
                setRunMode(RunMode.ERROR);
                mainRunner.setRunMode(RunMode.ERROR);
                return;
            }
            stream.print("$ ");
            stream.printScriptLine(nextCommand + "\n");
            if (nextCommand.isEmpty()) {
                continue;
            }
            String[] splitCommand = nextCommand.split(" ");
            try {
                commands.get(splitCommand[0]).runWithPermission(splitCommand);
            } catch (Exception e) {
                stream.printErr("Команда не распознана\n");
                setRunMode(RunMode.ERROR);
            }
            if (runMode == RunMode.ERROR) {
                oldRunner.setRunMode(RunMode.ERROR);
                mainRunner.setRunMode(RunMode.ERROR);
                return;
            }
        }
        commandManager.setRunner(oldRunner);
        commandManager.setInputFormat(oldInputFormat);
        exit();
    }

    /**
     * Устанавливает режим запуска
     *
     * @param runMode режим
     */
    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }

    /**
     * Метод по завершению раннера
     */
    private void exit() {
        if (oldScanner != null) {
            commandManager.setScanner(oldScanner);
        }
    }
}
