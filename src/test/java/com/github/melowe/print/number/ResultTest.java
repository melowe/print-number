package com.github.melowe.print.number;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class ResultTest {

    public ResultTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createValidAllPowers() throws Exception {

        Result result = Result.Builder.create()
                .firstTwoDigits(9)
                .hundreds(8)
                .millions(7)
                .thousands(12).build();

        assertThat(result.getFirstTwoDigits()).isEqualTo(9);
        assertThat(result.getMillions()).isEqualTo(7);
        assertThat(result.getThousands()).isEqualTo(12);
        assertThat(result.getHundreds()).isEqualTo(8);

        assertThat(result.hasMillions()).isTrue();
        assertThat(result.hasFirstTwoDigits()).isTrue();
        assertThat(result.hasThousands()).isTrue();
        assertThat(result.hasHundreds()).isTrue();
    }

    @Test
    public void createNoAllPowers() throws Exception {

        Result result = Result.Builder.create().build();

        assertThat(result.getFirstTwoDigits()).isEqualTo(0);
        assertThat(result.getMillions()).isEqualTo(0);
        assertThat(result.getThousands()).isEqualTo(0);
        assertThat(result.getHundreds()).isEqualTo(0);
    }

    @Test
    public void createOnlyMillions() throws Exception {

        Result result = Result.Builder.create().millions(3).build();

        assertThat(result.getFirstTwoDigits()).isEqualTo(0);
        assertThat(result.getMillions()).isEqualTo(3);
        assertThat(result.getThousands()).isEqualTo(0);
        assertThat(result.getHundreds()).isEqualTo(0);
        assertThat(result.hasMillions()).isTrue();
        assertThat(result.hasFirstTwoDigits()).isFalse();
        assertThat(result.hasThousands()).isFalse();
        assertThat(result.hasHundreds()).isFalse();
    }
}
