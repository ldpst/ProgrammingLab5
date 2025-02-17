package ru.itmo.prog.lab5.commands;

import ru.itmo.prog.lab5.managers.CollectionManager;
import ru.itmo.prog.lab5.managers.CommandManager;
import ru.itmo.prog.lab5.object.Movie;
import ru.itmo.prog.lab5.utils.StreamHandler;

import java.io.PrintStream;
import java.util.Deque;

/**
 * Класс команды show
 *
 * @author ldpst
 */
public class Show extends Command {
    private final CollectionManager collectionManager;

    public Show(StreamHandler stream, CommandManager commandManager) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении", stream, commandManager);
        this.collectionManager = commandManager.getCollectionManager();
    }

    @Override
    public void run(String[] args) {
        if (collectionManager.isEmpty()) {
            stream.printSuccess("Коллекция пуста\n");
            return;
        }
        Deque<Movie> movies = collectionManager.getMovies();
        stream.printSuccess("Элементы коллекции:\n");
        int cnt = 1;
        for (Movie movie : movies) {
            stream.printSuccessf("Элемент №%s:\n", cnt++);
            stream.printf("%s\n", movie.toString());
        }
    }
}
