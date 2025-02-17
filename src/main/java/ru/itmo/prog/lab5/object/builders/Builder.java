package ru.itmo.prog.lab5.object.builders;

import ru.itmo.prog.lab5.utils.InputFormat;
import ru.itmo.prog.lab5.utils.ScannerHandler;
import ru.itmo.prog.lab5.utils.StreamHandler;
import ru.itmo.prog.lab5.utils.TryAgainable;

import java.util.function.Supplier;

import static java.lang.System.exit;

public abstract class Builder implements TryAgainable {
    protected StreamHandler stream;
    protected ScannerHandler scanner;
    protected InputFormat inputFormat;

    public Builder(StreamHandler stream, ScannerHandler scanner, InputFormat inputFormat) {
        this.stream = stream;
        this.scanner = scanner;
        this.inputFormat = inputFormat;
    }


    /**
     * Метод для заполнения объекта данными, используя стандартный ввод
     *
     * @return Заполненный объект
     */
    abstract public Object build();

    /**
     * Метод для запроса повторного ввода
     *
     * @param action метод, который запустится повторно
     * @return Объект-результат переданной функции
     */
    public Object tryAgain(Supplier<Object> action) {
        if (inputFormat.equals(InputFormat.FILE)) {
            exit(0);
        }
        stream.print("* Повторная попытка ввода\n");
        return action.get();
    }

    /**
     * Метод для вывода сообщения при вводе из файла
     *
     * @param msg сообщение
     */
    protected void printIfFileMode(String msg) {
        if (inputFormat == InputFormat.FILE) {
            stream.printScriptLine(msg + "\n");
        }
    }
}
