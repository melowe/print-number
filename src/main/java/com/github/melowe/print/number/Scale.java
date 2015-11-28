package com.github.melowe.print.number;

import java.math.BigInteger;
import java.util.Collections;
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
    UNVIGINTILLION(66),
    DOVIGINTILLION(69),
    TREVIGINTILLION(72),
    QUATTUORVIGINTILLION(75),
    QUINVIGINTILLION(78),
    SEXVIGINTILLION(81),
    SEPTENVIGINTILLION(84),
    OCTOVIGINTILLION(87),
    NOVEMVIGINTILLION(90),
    TRIGINTILLION(93),
    UNTRIGINTILLION(96),
    DOTRIGINTILLION(99),
    TRETRIGINTILLION(102),
    QUATTUORTRIGINTILLION(105),
    QUINTRIGINTILLION(108),
    SEXTRIGINTILLION(111),
    SEPTENTRIGINTILLION(114),
    OCTOTRIGINTILLION(117),
    NOVEMTRIGINTILLION(120);
    // CENTILLION(303);

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

    private static final int MAX_NUMBER_LENGTH = NOVEMTRIGINTILLION.getValue() + 3;
    
    private static final String FORMAT = "%0"+ MAX_NUMBER_LENGTH +"d";
    
    protected static Map<Scale, Integer> toScaleMap(BigInteger number) {

        //Highest big number allowing for hundreds if it
        final int numberLength = MAX_NUMBER_LENGTH;
        if (number.toString().length() > numberLength) {
            throw new UnsupportedOperationException("Scale exceeds "+ numberLength +" " + number);
        }

        final String value = String.format(FORMAT, number);

        final int first = Character.getNumericValue(value.charAt(numberLength - 1));
        final int tens = Character.getNumericValue(value.charAt(numberLength - 2));
        final int hundreds = Character.getNumericValue(value.charAt(numberLength - 3));

        final Map<Scale, Integer> multiples = new EnumMap<>(Scale.class);

        multiples.put(Scale.ONE, first);
        multiples.put(Scale.TENS, tens);
        multiples.put(Scale.HUNDRED, hundreds);

        Stream.of(Scale.values())
                .filter(s -> s.ordinal() > HUNDRED.ordinal())
                .forEach(s -> {
                    int fromIndex = numberLength - (s.getValue() + 3);
                    int toIndex = numberLength - s.getValue();
                    multiples.put(s, Integer.parseInt(value.substring(fromIndex, toIndex)));
                });


        return Collections.unmodifiableMap(multiples);

    }

}
