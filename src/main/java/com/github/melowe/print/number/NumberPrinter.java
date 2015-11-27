package com.github.melowe.print.number;


public interface NumberPrinter {

    default String print(int num) {
        Result result = Result.fromNumber(num);
        return ResultFormatter.format(result);
    }

    public static NumberPrinter create() {
        return new NumberPrinter() {};
    }
    
    
    
}
