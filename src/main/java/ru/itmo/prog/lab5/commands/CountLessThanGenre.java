package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.object.MovieGenre;
import ru.itmo.prog.lab5.object.builders.GenreBuilder;
import ru.itmo.prog.lab5.utils.StreamHandler;

public class CountLessThanGenre extends Command {
    private final CollectionManager collectionManager;

    public CountLessThanGenre(StreamHandler stream, CommandManager commandManager) {
        super("count_less_than_genre genre", "вывести количество элементов, значение поля genre которых меньше заданного", stream, commandManager);
        collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        MovieGenre genre = new GenreBuilder(stream, commandManager.getScanner(), commandManager.getInputFormat()).build();
        stream.printSuccess("Количество элементов, значение поля genre которых меньше:\n");
        stream.printf("> %s\n", collectionManager.countLessTanGenre(genre));
    }
}
