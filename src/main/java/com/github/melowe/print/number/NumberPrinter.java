package com.github.melowe.print.number;

import java.util.ServiceLoader;

public interface NumberPrinter {

    default String print(int num) {
        Result result = Result.fromNumber(num);
        return ResultPrinter.print(result);
    }

    public static NumberPrinter create() {
        ServiceLoader<NumberPrinter> loader = ServiceLoader.load(NumberPrinter.class);
        return loader.iterator().next();
    }

}
