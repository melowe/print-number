package com.github.melowe.print.number;

import java.math.BigInteger;
import java.util.Map;


public interface NumberPrinter {

    default String printBigInteger(BigInteger num) {
        Map<Scale,Integer> map = Scale.toScaleMap(num);
        return ResultFormatter.format(map);
    }
    
              
    default String print(int num) {
        Map<Scale,Integer> map = Scale.toScaleMap(BigInteger.valueOf(num));
        return ResultFormatter.format(map);

    }
      
    public static NumberPrinter create() {
        return new NumberPrinter() {};
    }
    
    
    
}
