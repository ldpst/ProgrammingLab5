package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.object.Movie;
import ru.itmo.prog.lab5.object.builders.IDBuilder;
import ru.itmo.prog.lab5.object.builders.MovieBuilder;
import ru.itmo.prog.lab5.utils.InputFormat;
import ru.itmo.prog.lab5.utils.RunMode;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды update
 *
 * @author ldpst
 */
public class Update extends Command {
    private final CollectionManager collectionManager;

    public Update(StreamHandler stream, CommandManager commandManager) {
        super("update id {Movie}", "обновить значение элемента коллекции, id которого равен заданному", stream, commandManager);
        this.collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        Long aimId = new IDBuilder(stream, commandManager.getScanner(), args, commandManager.getInputFormat()).build();
        if (aimId == null) {
            if (commandManager.getInputFormat() == InputFormat.FILE) {
                commandManager.getRunner().setRunMode(RunMode.ERROR);
            }
            return;
        }
        Movie aim = collectionManager.findElemById(aimId);
        if (aim == null) {
            stream.printErr("Объекта под данным id не существует\n");
            if (commandManager.getInputFormat() == InputFormat.FILE) {
                commandManager.getRunner().setRunMode(RunMode.ERROR);
            }
        } else {
            aim.update(new MovieBuilder(stream, commandManager.getScanner(), commandManager.getInputFormat()).build());
            stream.printSuccess("Замена прошла успешно\n");
        }
    }
}
