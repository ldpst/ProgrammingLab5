package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.object.Movie;
import ru.itmo.prog.lab5.object.builders.MovieBuilder;
import ru.itmo.prog.lab5.utils.StreamHandler;

/**
 * Класс команды remove_greater
 *
 * @author ldpst
 */
public class RemoveGreater extends Command {
    private final CollectionManager collectionManager;

    public RemoveGreater(StreamHandler stream, CommandManager commandManager) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный", stream, commandManager);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        Movie movie = new MovieBuilder(stream, commandManager.getScanner(), commandManager.getInputFormat()).build();
        stream.printSuccessf("Удалено %s элементов\n", collectionManager.removeGreater(movie));
    }
}
