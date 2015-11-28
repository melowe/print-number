package com.github.melowe.print.number;

import java.math.BigInteger;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.Test;

public class ScaleTest {

    public ScaleTest() {
    }

    @Test
    public void toScaleMapFactoryMethod() throws Exception {
        Map<Scale, Integer> result = Scale.toScaleMap(56945781);

        assertThat(result.get(Scale.MILLION)).isEqualTo(56);
        assertThat(result.get(Scale.THOUSAND)).isEqualTo(945);
        assertThat(result.get(Scale.HUNDRED)).isEqualTo(7);
        assertThat(result.get(Scale.ONE)).isEqualTo(1);
        assertThat(result.get(Scale.TENS)).isEqualTo(8);

    }

    @Test
    public void toScaleMapZero() throws Exception {
        Map<Scale, Integer> result = Scale.toScaleMap(0);
        assertThat(result.values().stream().allMatch(i -> i == 0)).isTrue();

    }

    @Test
    public void toScaleMapMaxIntValue() throws Exception {
        Map<Scale, Integer> result = Scale.toScaleMap(Integer.MAX_VALUE);
        assertThat(result.get(Scale.HUNDRED)).isEqualTo(6);
        assertThat(result.get(Scale.ONE)).isEqualTo(7);
        assertThat(result.get(Scale.TENS)).isEqualTo(4);
        assertThat(result.get(Scale.THOUSAND)).isEqualTo(483);
        assertThat(result.get(Scale.MILLION)).isEqualTo(147);
        assertThat(result.get(Scale.BILLION)).isEqualTo(2);

    }

    @Test(expected = IllegalArgumentException.class)
    public void somePunkTryingToProvideANegativeValue() throws Exception {
        Scale.toScaleMap(-1);
    }

    @Test
    public void toScaleMapTen() throws Exception {
        Map<Scale, Integer> result = Scale.toScaleMap(10);

        assertThat(result.get(Scale.TENS)).isEqualTo(1);
        assertThat(result.get(Scale.ONE)).isEqualTo(0);
    }

    @Test
    public void toScaleMapBigIntegerOneThousandTrillion() {
        BigInteger billion = new BigInteger("1000000000000");

        Map<Scale, Integer> result = Scale.toScaleMap(billion.multiply(new BigInteger("1000")));
        
        assertThat(result.get(Scale.QUADRILLION)).isEqualTo(1);
        
        Stream.of(Scale.values())
                .filter(s -> s != Scale.QUADRILLION).forEach(s -> {
            assertThat(result.getOrDefault(s,0)).isEqualTo(0);
        });
        
        
    }

    @Test
    public void toScaleMapOneBillion() {
        BigInteger n = new BigInteger("1000000000");
        Map<Scale, Integer> result = Scale.toScaleMap(n);
        assertThat(result.get(Scale.BILLION)).isEqualTo(1);
        Stream.of(Scale.values()).filter(s -> !Objects.equals(s, Scale.BILLION))
                .forEach(s -> {
                    assertThat(result.get(s)).isEqualTo(0);
                });


    }

}
