package com.github.melowe.print.number;

import java.math.BigInteger;
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
 
        assertThat(instance.printBigInteger(new BigInteger("1000000000000")))
                .isEqualToIgnoringCase("one trillion");

    }
    
    @Test
    public void printBigIntegerQUADRILLION() {
        BigInteger billion = new BigInteger("1000000000000");
        assertThat(instance.printBigInteger(billion.multiply(new BigInteger("1000"))))
                .isEqualToIgnoringCase("ONE QUADRILLION");

    }
}
