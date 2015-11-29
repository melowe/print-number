package com.github.melowe.print.number;

import static com.github.melowe.print.number.Scale.HUNDRED;
import static com.github.melowe.print.number.Scale.ONE;
import static com.github.melowe.print.number.Scale.TENS;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ResultFormatter {

    private enum Tens {

        NONE, TEN, TWENTY, THIRTY, FORTY, FIFTY, SIXTY, SEVENTY, EIGHTY, NINETY;
    }

    private enum DoubleDigit {

        TEN, ELEVEN, TWELVE, THIREEN, FOURTEEN, FIFTEEN, SIXTEEN, SEVENTEEN, EIGHTEEN, NINETEEN;
    }

    private enum SingleDigit {

        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE;
    }

    private static final Map<Integer, String> EXECUTION_CACHE = new HashMap<>();

    private static final List<String> ZERO_TO_NINETEEN = Stream.concat(
            Stream.of(SingleDigit.values()),
            Stream.of(DoubleDigit.values())
    )
            .map(Enum::name)
            .collect(Collectors.toList());

    protected static String format(Map<Scale, Integer> result) {

        if (result.values().stream().allMatch(v -> v == 0)) {
            return SingleDigit.ZERO.name();
        }

        List<String> tokens = result.entrySet().stream()
                .filter(e -> e.getKey().ordinal() > TENS.ordinal())
                .filter(e -> Objects.nonNull(e.getKey()))
                .filter(e -> Objects.nonNull(e.getValue()))
                .filter(e -> !Objects.equals(e.getValue(), 0))
                .sorted((o1,o2) -> o2.getKey().compareTo(o1.getKey()))
                .flatMap(e -> Stream.of(format(e.getValue()),e.getKey().name()))
                .collect(Collectors.toList());

        List<String> lastTokens = new ArrayList();
        
        
        
        int singleScale = result.get(Scale.ONE);
        int tens = result.get(Scale.TENS);
        int t = singleScale + (tens * 10);
        
        if (t != 0 && (tens != 0 || singleScale != 0) && !tokens.isEmpty()) {
            lastTokens.add("AND");
        }
        
        lastTokens.add(format(t));
        
        List<String> allTokens = new ArrayList<>(tokens);
        allTokens.addAll(lastTokens);
        return String.join(" ", allTokens).trim();
    }


    private static String format(int n) {
        return  EXECUTION_CACHE.getOrDefault(n, doFormat(n));
    }

    private static String doFormat(int n) {

        String str = String.format("%03d", n);
        List<String> tokens = new ArrayList<>();

        int hundreds = Character.getNumericValue(str.charAt(0));

        int o = Integer.parseInt(str.substring(1));

        if (hundreds != 0) {
            tokens.add(SingleDigit.values()[hundreds].name());
            tokens.add("HUNDRED");
        }

        if (o != 0) {
            if (!tokens.isEmpty()) {
                tokens.add("AND");
            }

            if (o < 20) {
                tokens.add(ZERO_TO_NINETEEN.get(o));
            } else {

                int t = Character.getNumericValue(str.charAt(1));
                tokens.add(Tens.values()[t].name());

                int i = Character.getNumericValue(str.charAt(2));
                if (i != 0) {
                    tokens.add(SingleDigit.values()[i].name());
                }
            }
        }

        String result = String.join(" ", tokens);
        EXECUTION_CACHE.put(n,result);

        return result;
    }

}
