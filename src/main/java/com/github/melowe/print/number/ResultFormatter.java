package com.github.melowe.print.number;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import java.util.stream.Collectors;
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

    private static Map<Integer, String> executionCache = new HashMap<>();

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

        List<String> tokens = new ArrayList<>();

        List<Scale> ignoreScales = Arrays.asList(Scale.ONE, Scale.TENS,Scale.CENTILLION);

        result.entrySet().stream()
                .filter(e -> !ignoreScales.contains(e.getKey()))
                .sorted((Entry<Scale, Integer> o1, Entry<Scale, Integer> o2) -> o2.getKey().compareTo(o1.getKey()))
                .forEach((entry) -> {

                    Scale key = entry.getKey();
                    Integer value = entry.getValue();
                    if (Objects.nonNull(value) && !Objects.equals(value, 0)) {
                        tokens.add(format(value));
                        tokens.add(key.name());
                    }
                });

        int hundreds = result.get(Scale.HUNDRED);
        int singleScale = result.get(Scale.ONE);
        int tens = result.get(Scale.TENS);
        int t = tens * 10 + singleScale;
        
        if (t != 0 && (tens != 0 || singleScale != 0) && !tokens.isEmpty()) {
            tokens.add("AND");
        }
        
        tokens.add(format(t));
        
        return String.join(" ", tokens).trim();
    }


    private static String format(int n) {
//        String str = executionCache.get(n);
//        if(Objects.nonNull(str)) {
//            return str;
//        }

        return doFormat(n);
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
        //executionCache.put(n,result);

        return result;
    }

}
