package com.github.melowe.print.number;

import java.math.BigInteger;


public interface NumberPrinter {

    default String printBigInteger(BigInteger num) {
        Result result = Result.fromNumber(num);
        return ResultFormatter.format(result);
    }
    
    
    default String print(int num) {

        
        Result result = Result.fromNumber(num);
        return ResultFormatter.format(result);

    }

    public static NumberPrinter create() {
        return new NumberPrinter() {};
    }
    
    
    
}
