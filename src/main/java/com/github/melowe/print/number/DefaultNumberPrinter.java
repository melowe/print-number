package com.github.melowe.print.number;

public class DefaultNumberPrinter implements NumberPrinter {

    public DefaultNumberPrinter() {
    }

    @Override
    public String print(int num) {
        Result result = Result.fromNumber(num);
        return ResultPrinter.print(result);
    }

  

}
