package ru.itmo.prog.lab5.commands;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс команды execute_script
 *
 * @author ldpst
 */
public class ExecuteScript extends Command {

    public ExecuteScript(StreamHandler stream, CommandManager commandManager) {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме", stream, commandManager);
    }

    @Override
    public void run(String[] args) {
        if (args.length != 2) {
            stream.printErr("Неверный формат команды\n");
            if (commandManager.getInputFormat() == InputFormat.FILE) {
                commandManager.getRunner().setRunMode(RunMode.ERROR);
            }
            return;
        }
        if (Runner.usedScripts.contains(args[1])) {
            stream.printErr("Запуск скрипта " + args[1] + " вызывает рекурсию\n");
            if (commandManager.getInputFormat() == InputFormat.FILE) {
                commandManager.getRunner().setRunMode(RunMode.ERROR);
            }
            return;
        }
        Runner.usedScripts.add(args[1]);
        try (BufferedReader br = new BufferedReader(new FileReader("config/" + args[1] + ".txt"))) {
            stream.printSuccess("Выполнение скрипта:\n");
            new Runner(new ScannerHandler(br), stream, commandManager).runScriptMode();
            Runner.usedScripts.remove(args[1]);
        } catch (FileNotFoundException e) {
            stream.printErr("Файл со скриптом не найден\n");
            if (commandManager.getInputFormat() == InputFormat.FILE) {
                commandManager.getRunner().setRunMode(RunMode.ERROR);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
