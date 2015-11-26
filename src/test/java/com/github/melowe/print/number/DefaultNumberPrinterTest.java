package com.github.melowe.print.number;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DefaultNumberPrinterTest {

    NumberPrinter instance;

    public DefaultNumberPrinterTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        instance = NumberPrinter.create();
    }

    @After
    public void tearDown() {
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
   //
   // 56945781
    
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
    public void print999999999() {
        assertThat(instance.print(999999999))
                .isEqualToIgnoringCase("nine hundred and ninety nine million nine hundred and ninety nine thousand nine hundred and ninety nine");

    }
    
    @Test
    public void print7782() {
        assertThat(instance.print(7782))
                .isEqualToIgnoringCase("seven thousand seven hundred and eighty two");

    }

}
