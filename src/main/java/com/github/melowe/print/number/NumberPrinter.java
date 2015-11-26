
package com.github.melowe.print.number;

import java.util.ServiceLoader;


public interface NumberPrinter {
    
    String print(int num);
    
    
    public static NumberPrinter create() {
         ServiceLoader<NumberPrinter> loader = ServiceLoader.load(NumberPrinter.class);
         return loader.iterator().next();
    }
    
}
