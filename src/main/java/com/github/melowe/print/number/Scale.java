package com.github.melowe.print.number;

import java.math.BigInteger;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Scale {

    ONE(0),
    TENS(1),
    HUNDRED(2),
    THOUSAND(3),
    MILLION(6),
    BILLION(9),
    TRILLION(12),
    QUADRILLION(15),
    QUINTILLION(18),
    SEXTILLION(21),
    SEPTILLION(24),
    OCTILLION(27),
    NONILLION(30),
    DECILLION(33),
    UNDECILLION(36),
    DUODECILLION(39),
    TREDECILLION(42),
    QUATTUORDECILLION(45),
    QUINDECILLION(48),
    SEXDECILLION(51),
    SEPTENDECILLION(54),
    OCTODECILLION(57),
    NOVEMDECILLION(60),
    VIGINTILLION(63),
    CENTILLION(303);

    private final int value;

    private Scale(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    protected static Map<Scale, Integer> toScaleMap(int number) {
        if (Integer.signum(number) < 0) {
            throw new IllegalArgumentException("Negative values aren't supported : " + number);
        }
        return toScaleMap(BigInteger.valueOf(number));
    }

    protected static Map<Scale, Integer> toScaleMap(BigInteger number) {

        int numberLength = Scale.CENTILLION.getValue();

        String value = String.format("%0" + numberLength + "d", number);

        int first = Character.getNumericValue(value.charAt(numberLength - 1));
        int tens = Character.getNumericValue(value.charAt(numberLength - 2));
        int hundreds = Character.getNumericValue(value.charAt(numberLength - 3));
        int thousands = Integer.parseInt(value.substring(numberLength - 6, numberLength - 3));

        Map<Scale, Integer> multiples = new EnumMap<>(Scale.class);

        multiples.put(Scale.ONE, first);
        multiples.put(Scale.TENS, tens);
        multiples.put(Scale.HUNDRED, hundreds);
        multiples.put(Scale.THOUSAND, thousands);

        Stream.of(Scale.values())
                .filter(s -> !multiples.containsKey(s))
                .filter(s -> Scale.CENTILLION != s)
                .forEach(s -> {

                    int fromIndex = numberLength - (s.getValue() + 3);
                    int toIndex = numberLength - s.getValue();
                    
                    
                    multiples.put(s, Integer.parseInt(value.substring(fromIndex, toIndex)));
                });

        return multiples;

    }

}
