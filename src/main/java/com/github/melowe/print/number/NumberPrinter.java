package com.github.melowe.print.number;


public interface NumberPrinter {

    default String print(int num) {
        Result result = Result.fromNumber(num);
        return ResultPrinter.print(result);
    }

    public static NumberPrinter create() {
        return new DefaultNumberPrinter();
    }
    
}
