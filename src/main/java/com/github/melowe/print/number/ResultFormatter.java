package com.github.melowe.print.number;

import java.util.ArrayList;
import java.util.List;

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

    private static final List<String> ZERO_TO_NINETEEN = Stream.concat(
            Stream.of(SingleDigit.values()),
            Stream.of(DoubleDigit.values())
    )
            .map(Enum::name)
            .collect(Collectors.toList());

    protected static String format(Result result) {

        if (result.isZero()) {
            return SingleDigit.ZERO.name();
        }

        List<String> tokens = new ArrayList<>();

        if (result.hasMillions()) {
            tokens.add(format(result.getMillions()));
            tokens.add("MILLION");
        }

        if (result.hasThousands()) {
            tokens.add(format(result.getThousands()));
            tokens.add("THOUSAND");
        }

        int t = result.getHundreds() * 100 + result.getFirstTwoDigits();

        if (!result.hasHundreds() && !tokens.isEmpty()) {
            tokens.add("AND");
        }

        tokens.add(format(t));

        return String.join(" ", tokens);

    }

    private static String format(int n) {
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
                if(i != 0) {
                    tokens.add(SingleDigit.values()[i].name());
                }
            }
        }

        return String.join(" ", tokens);
    }

}
