package com.github.melowe.print.number;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.fest.assertions.Assertions.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NumberPrinterTest {

    NumberPrinter instance;

    public NumberPrinterTest() {
    }

    @Before
    public void setUp() {
        instance = NumberPrinter.create();
    }

    @After
    public void tearDown() {
        instance = null;
    }

    @Test
    public void printZero() {
        assertThat(instance.print(0))
                .isEqualToIgnoringCase("zero");
    }

    @Test
    public void printNineteen() {
        assertThat(instance.print(19))
                .isEqualToIgnoringCase("nineteen");
    }

    @Test
    public void print15() {
        assertThat(instance.print(15))
                .isEqualToIgnoringCase("fifteen");
    }

    @Test
    public void print920() {
        assertThat(instance.print(920))
                .isEqualToIgnoringCase("nine hundred and twenty");
    }

    @Test
    public void print910() {
        assertThat(instance.print(910))
                .isEqualToIgnoringCase("nine hundred and ten");
    }

    @Test
    public void print9910() {
        assertThat(instance.print(9910))
                .isEqualToIgnoringCase("nine thousand nine hundred and ten");
    }

    @Test
    public void printOne() {

        assertThat(instance.print(1))
                .isEqualToIgnoringCase("one");

    }

    @Test
    public void print88() {

        assertThat(instance.print(88))
                .isEqualToIgnoringCase("eighty eight");

    }

    @Test
    public void print900() {

        assertThat(instance.print(900))
                .isEqualToIgnoringCase("nine hundred");

    }

    @Test
    public void printNintyNine() {

        assertThat(instance.print(99))
                .isEqualToIgnoringCase("ninety nine");

    }

    @Test
    public void print56945781() {

        assertThat(instance.print(56945781))
                .isEqualToIgnoringCase("fifty six million nine hundred and forty five thousand seven hundred and eighty one");

    }

    @Test
    public void print100() {

        assertThat(instance.print(100))
                .isEqualToIgnoringCase("one hundred");

    }

    @Test
    public void print1000() {

        assertThat(instance.print(1000))
                .isEqualToIgnoringCase("one thousand");

    }

    @Test
    public void print1001() {

        assertThat(instance.print(1001))
                .isEqualToIgnoringCase("one thousand and one");

    }

    @Test
    public void print11() {

        assertThat(instance.print(11))
                .isEqualToIgnoringCase("eleven");

    }

    @Test
    public void print1019() {

        assertThat(instance.print(1019))
                .isEqualToIgnoringCase("one thousand and nineteen");

    }

    @Test
    public void print101() {

        assertThat(instance.print(101))
                .isEqualToIgnoringCase("one hundred and one");

    }

    @Test
    public void print119() {

        assertThat(instance.print(119))
                .isEqualToIgnoringCase("one hundred and nineteen");

    }

    @Test
    public void print999999999() {
        assertThat(instance.print(999999999))
                .isEqualToIgnoringCase("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine");

    }

    @Test
    public void print7782() {
        assertThat(instance.print(7782))
                .isEqualToIgnoringCase("seven thousand seven hundred and eighty two");

    }

    @Test
    public void print1999999999() {
        assertThat(instance.print(1999999999))
                .isEqualToIgnoringCase("one billion nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine");

    }

    @Test
    public void print1999999900() {
        assertThat(instance.print(1999999900))
                .isEqualToIgnoringCase("one billion nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred");

    }

    @Test
    public void printOneBillion() {
        assertThat(instance.print(1000000000))
                .isEqualToIgnoringCase("one billion");

    }

    @Test
    public void printOneBillionAndThree() {
        assertThat(instance.print(1000000003))
                .isEqualToIgnoringCase("one billion and three");

    }

    @Test
    public void printOneBillionNineMillion() {
        assertThat(instance.print(1009000000))
                .isEqualToIgnoringCase("one billion nine million");

    }

    @Test
    public void printMaxValue() {
        assertThat(instance.print(Integer.MAX_VALUE))
                .isEqualToIgnoringCase("two billion one hundred and forty seven million four hundred and eighty three thousand six hundred and forty seven");

    }

    @Test
    public void printBigIntegerOneThousandBillion() {

        assertThat(instance.printBigInteger(BigInteger.TEN.pow(Scale.TRILLION.getValue())))
                .isEqualToIgnoringCase("one trillion");

    }

    @Test
    public void printBigIntegerQUADRILLION() {

        assertThat(instance.printBigInteger(BigInteger.TEN.pow(Scale.QUADRILLION.getValue())))
                .isEqualToIgnoringCase("ONE QUADRILLION");

    }

    @Test
    public void printBigIntegerOneHundredQUADRILLION() {
        assertThat(instance.printBigInteger(BigInteger.TEN.pow(17)))
                .isEqualToIgnoringCase("ONE HUNDRED QUADRILLION");

    }

    @Test
    public void printGoogolplex() {
        assertThat(instance.printBigInteger(BigInteger.TEN.pow(Scale.valueOf("CENTILLION").getValue())))
                .isEqualToIgnoringCase("ONE Centillion");

    }

    @Test
    public void print999Googolplex() {
        assertThat(instance.printBigInteger(BigInteger.TEN.pow(Scale.valueOf("CENTILLION").getValue()).multiply(BigInteger.valueOf(999))))
                .isEqualToIgnoringCase("NINE HUNDRED AND NINETY NINE CENTILLION");

    }

    @Test
    public void printAllTHe9sGoogolplex() {
        
        BigInteger bigNumber = Stream.of(Scale.values())
                .map(s -> BigInteger.TEN.pow(s.getValue())
                        .multiply(BigInteger.valueOf(999)))
                .reduce((n1, n2) -> n1.add(n2)).get();

        assertThat(instance.printBigInteger(bigNumber))
                .isEqualToIgnoringCase("NINE HUNDRED AND NINETY NINE NOVEMCENTILLION NINE HUNDRED AND NINETY NINE OCTOCENTILLION NINE HUNDRED AND NINETY NINE SEPTENCENTILLION NINE HUNDRED AND NINETY NINE SEXCENTILLION NINE HUNDRED AND NINETY NINE QUINCENTILLION NINE HUNDRED AND NINETY NINE QUATCENTILLION NINE HUNDRED AND NINETY NINE TRECENTILLION NINE HUNDRED AND NINETY NINE DOCENTILLION NINE HUNDRED AND NINETY NINE UNCENTILLION NINE HUNDRED AND NINETY NINE CENTILLION NINE HUNDRED AND NINETY NINE NOVEMNOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE OCTONOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEPTENNOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEXNOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUINNOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUATNOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE TRENOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE DONOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE UNNOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE NOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE NOVEMOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE OCTOOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEPTENOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEXOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUINOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUATOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE TREOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE DOOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE UNOCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE OCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE NOVEMSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE OCTOSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEPTENSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEXSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUINSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUATSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE TRESEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE DOSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE UNSEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE NOVEMSEXAGINTILLION NINE HUNDRED AND NINETY NINE OCTOSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEPTENSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEXSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUINSEXAGINTILLION NINE HUNDRED AND NINETY NINE QUATSEXAGINTILLION NINE HUNDRED AND NINETY NINE TRESEXAGINTILLION NINE HUNDRED AND NINETY NINE DOSEXAGINTILLION NINE HUNDRED AND NINETY NINE UNSEXAGINTILLION NINE HUNDRED AND NINETY NINE SEXAGINTILLION NINE HUNDRED AND NINETY NINE NOVEMQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE OCTOQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE SEPTENQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE SEXQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE QUINQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE QUATQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE TREQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE DOQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE UNQUINQUAGINTILLION NINE HUNDRED AND NINETY NINE QUINQUAGINTILLION NINE HUNDRED AND NINETY NINE NOVEMQUADRAGINTILLION NINE HUNDRED AND NINETY NINE OCTOQUADRAGINTILLION NINE HUNDRED AND NINETY NINE SEPTENQUADRAGINTILLION NINE HUNDRED AND NINETY NINE SEXQUADRAGINTILLION NINE HUNDRED AND NINETY NINE QUINQUADRAGINTILLION NINE HUNDRED AND NINETY NINE QUATQUADRAGINTILLION NINE HUNDRED AND NINETY NINE TREQUADRAGINTILLION NINE HUNDRED AND NINETY NINE DOQUADRAGINTILLION NINE HUNDRED AND NINETY NINE UNQUADRAGINTILLION NINE HUNDRED AND NINETY NINE QUADRAGINTILLION NINE HUNDRED AND NINETY NINE NOVEMTRIGINTILLION NINE HUNDRED AND NINETY NINE OCTOTRIGINTILLION NINE HUNDRED AND NINETY NINE SEPTENTRIGINTILLION NINE HUNDRED AND NINETY NINE SEXTRIGINTILLION NINE HUNDRED AND NINETY NINE QUINTRIGINTILLION NINE HUNDRED AND NINETY NINE QUATTRIGINTILLION NINE HUNDRED AND NINETY NINE TRETRIGINTILLION NINE HUNDRED AND NINETY NINE DOTRIGINTILLION NINE HUNDRED AND NINETY NINE UNTRIGINTILLION NINE HUNDRED AND NINETY NINE TRIGINTILLION NINE HUNDRED AND NINETY NINE NOVEMVIGINTILLION NINE HUNDRED AND NINETY NINE OCTOVIGINTILLION NINE HUNDRED AND NINETY NINE SEPTENVIGINTILLION NINE HUNDRED AND NINETY NINE SEXVIGINTILLION NINE HUNDRED AND NINETY NINE QUINVIGINTILLION NINE HUNDRED AND NINETY NINE QUATVIGINTILLION NINE HUNDRED AND NINETY NINE TREVIGINTILLION NINE HUNDRED AND NINETY NINE DOVIGINTILLION NINE HUNDRED AND NINETY NINE UNVIGINTILLION NINE HUNDRED AND NINETY NINE VIGINTILLION NINE HUNDRED AND NINETY NINE NOVEMDECILLION NINE HUNDRED AND NINETY NINE OCTODECILLION NINE HUNDRED AND NINETY NINE SEPTENDECILLION NINE HUNDRED AND NINETY NINE SEXDECILLION NINE HUNDRED AND NINETY NINE QUINDECILLION NINE HUNDRED AND NINETY NINE QUATDECILLION NINE HUNDRED AND NINETY NINE TREDECILLION NINE HUNDRED AND NINETY NINE DODECILLION NINE HUNDRED AND NINETY NINE UNDECILLION NINE HUNDRED AND NINETY NINE DECILLION NINE HUNDRED AND NINETY NINE NONILLION ONE HUNDRED AND NINE THOUSAND EIGHT HUNDRED AND EIGHTY NINE");

    }

}
