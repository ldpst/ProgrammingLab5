package ru.itmo.prog.lab5.object.builders;

import ru.itmo.prog.lab5.utils.InputFormat;
import ru.itmo.prog.lab5.utils.ScannerHandler;
import ru.itmo.prog.lab5.utils.StreamHandler;

public class IDBuilder extends Builder {
    private final String[] args;
    private final int pos;

    public IDBuilder(StreamHandler stream, ScannerHandler scanner, String[] args, InputFormat inputFormat, int pos) {
        super(stream, scanner, inputFormat);
        this.args = args;
        this.pos = pos;
    }

    public IDBuilder(StreamHandler stream, ScannerHandler scanner, String[] args, InputFormat inputFormat) {
        this(stream, scanner, args, inputFormat, 1);
    }

    @Override
    public Long build() {
        if (args.length >= pos + 1) {
            long aimId;
            try {
                aimId = Long.parseLong(args[pos]);
            } catch (NumberFormatException e) {
                stream.printErr("Введенный id не является целым числом\n");
                return null;
            }
            return aimId;
        }
        stream.printErr("Неверный формат ввода команды\n");
        return null;
    }
}
