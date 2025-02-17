package ru.itmo.prog.lab5.managers;

import ru.itmo.prog.lab5.object.Movie;
import ru.itmo.prog.lab5.object.MovieGenre;
import ru.itmo.prog.lab5.object.Person;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Класс для хранения и управления коллекцией
 *
 * @author ldpst
 */
public class CollectionManager {
    private Deque<Movie> movies = new ArrayDeque<>();
    private final java.time.ZonedDateTime creationTime;

    public CollectionManager() {
//        loadFromFile();
        creationTime = java.time.ZonedDateTime.now();
    }

    /**
     * Метод для добавления movie в файл
     *
     * @param movie элемент для добавления
     */
    public void add(Movie movie) {
        movies.addLast(movie);
        Movie.increaseNextId();
    }

    /**
     * Метод для получения типа коллекции
     *
     * @return тип коллекции
     */
    public Class<?> getType() {
        return movies.getClass();
    }

    /**
     * Метод для очищения коллекции
     */
    public void clear() {
        movies = new ArrayDeque<>();
        Movie.setNextId(1);
    }

    /**
     * Метод для поиска элемента по id
     *
     * @param id айди
     * @return Movie
     */
    public Movie findElemById(long id) {
        for (Movie movie : movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    /**
     * Возвращает первый элемент в коллекции
     *
     * @return первый элемент
     */
    public Movie getHead() {
        if (isEmpty()) {
            return null;
        }
        return movies.getFirst();
    }

    /**
     * Возвращает последний элемент в коллекции
     *
     * @return первый элемент
     */
    public Movie getTail() {
        if (isEmpty()) {
            return null;
        }
        return movies.getLast();
    }

    /**
     * Возвращает максимальный элемент коллекции
     *
     * @return максимальный элемент
     */
    public Movie getMax() {
        Movie max = movies.getFirst();
        for (Movie movie : movies) {
            if (max.compareTo(movie) < 0) {
                max = movie;
            }
        }
        return max;
    }

    /**
     * Возвращает минимальный элемент коллекции
     *
     * @return максимальный элемент
     */
    public Movie getMin() {
        Movie min = movies.getFirst();
        for (Movie movie : movies) {
            if (min.compareTo(movie) > 0) {
                min = movie;
            }
        }
        return min;
    }

    /**
     * Возвращает максимальный элемент по полю оператор
     *
     * @return максимальный элемент по полю оператор
     */
    public Movie getMaxByOperator() {
        Movie max = movies.getFirst();
        for (Movie movie : movies) {
            if (max.getOperator() == null || (movie.getOperator() != null && max.getOperator().compareTo(movie.getOperator()) > 0)) {
                max = movie;
            }
        }
        return max;
    }

    /**
     * Удалить все элементы превышающие данный и вернуть их количество
     *
     * @return количество удаленных элементов
     */
    public int removeGreater(Movie greater) {
        int count = 0;
        for (Movie movie : movies) {
            if (greater.compareTo(movie) < 0) {
                movies.remove(movie);
                count++;
            }
        }
        return count;
    }

    /**
     * Возвращает количество элементов с полем operator равным данному
     *
     * @param operator значение для проверки
     * @return количество
     */
    public int countByOperator(Person operator) {
        int count = 0;
        for (Movie movie : movies) {
            if (movie.getOperator() != null && movie.getOperator().equals(operator) || operator == null && movie.getOperator() == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Возвращает количество элементов с полем genre меньшим данного
     *
     * @param genre значение для проверки
     * @return количество
     */
    public int countLessTanGenre(MovieGenre genre) {
        int count = 0;
        for (Movie movie : movies) {
            if (movie.getGenre() != null && genre != null && movie.getGenre().compareTo(genre) > 0 || movie.getGenre() == null && genre == null) {
                count++;
            }
        }
        return count;
    }

    /**
     * Метод, проверяющий пуста ли коллекция
     *
     * @return результат проверки
     */
    public boolean isEmpty() {
        return movies.isEmpty();
    }

    /**
     * Метод для удаления элемента
     *
     * @param movie элемент для удаления
     */
    public void remove(Movie movie) {
        movies.remove(movie);
    }

    /**
     * Метод, возвращающий размер коллекции
     *
     * @return int
     */
    public int getSize() {
        return movies.size();
    }

    /**
     * Метод, возвращающий значение поля creationTime
     *
     * @return ZonedDateTime
     */
    public java.time.ZonedDateTime getCreationTime() {
        return creationTime;
    }

    /**
     * Метод, возвращающий содержимое коллекции
     *
     * @return Deque[Movie]
     */
    public Deque<Movie> getMovies() {
        return movies;
    }
}
