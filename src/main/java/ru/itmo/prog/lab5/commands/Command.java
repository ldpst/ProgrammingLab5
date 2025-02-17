package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.utils.Permissions;
import ru.itmo.prog.lab5.utils.Runable;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команда
 *
 * @author ldpst
 */
public abstract class Command implements Runable {
    private final String name;
    private final String description;
    protected final StreamHandler stream;
    protected final CommandManager commandManager;
    protected final Permissions permission;

    protected Command(String commandName, String description, StreamHandler stream, CommandManager commandManager, Permissions permission) {
        this.name = commandName;
        this.description = description;
        this.stream = stream;
        this.commandManager = commandManager;
        this.permission = permission;
    }

    public Command(String commandName, String description, StreamHandler stream, CommandManager commandManager) {
        this(commandName, description, stream, commandManager, Permissions.USER);
    }

    /**
     * Метод для запуска команды с правами доступа
     *
     * @param args данные команды
     */
    public void runWithPermission(String[] args) {
        if (permission.compareTo(commandManager.getUserPermission()) < 0) {
            stream.printErr("Недостаточно прав для выполнения команды\n");
            return;
        }
        run(args);
    }

    /**
     * Метод, возвращающий название команды
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Метод, возвращающий описание команды
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Метод, возвращающий необходимые права доступа
     *
     * @return permission
     */
    public Permissions getPermission() {
        return permission;
    }
}
