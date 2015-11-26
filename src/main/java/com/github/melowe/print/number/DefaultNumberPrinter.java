package com.github.melowe.print.number;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultNumberPrinter implements NumberPrinter {

    List<String> ZERO_TO_NINETEEN = Stream.concat(
            Stream.of(SingleDigit.values()),
            Stream.of(DoubleDigit.values())
    )
            .map(Enum::name)
            .collect(Collectors.toList());

    public DefaultNumberPrinter() {
    }

    @Override
    public String print(int num) {

        String value = String.format("%012d", num);

        if (num == 0) {
            return SingleDigit.ZERO.name();
        }

        int first = Integer.parseInt(value.substring(10, 12));
        int hundreds = Character.getNumericValue(value.charAt(9));
        int thousands = Integer.parseInt(value.substring(6, 9));
        int millions = Integer.parseInt(value.substring(0, 6));

        
        Result result = Result.Builder.create()
                .hundreds(hundreds)
                .millions(millions)
                .thousands(thousands)
                .firstTwoDigits(first)
                .build();
        
        return ResultPrinter.print(result);
        
     

    }

  

}
