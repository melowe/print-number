package com.github.melowe.print.number;

import static org.fest.assertions.Assertions.assertThat;
import org.junit.Test;

public class ResultTest {

    public ResultTest() {
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
        assertThat(result.isZero()).isFalse();
    }

    @Test
    public void createNoAllPowers() throws Exception {

        Result result = Result.Builder.create().build();

        assertThat(result.getFirstTwoDigits()).isEqualTo(0);
        assertThat(result.getMillions()).isEqualTo(0);
        assertThat(result.getThousands()).isEqualTo(0);
        assertThat(result.getHundreds()).isEqualTo(0);
        assertThat(result.isZero()).isTrue();
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
        assertThat(result.isZero()).isFalse();
    }
    
    @Test
    public void fromNumberFactoryMethod() throws Exception {
        Result result = Result.fromNumber(56945781);
        
        assertThat(result.getMillions()).isEqualTo(56);
        assertThat(result.getThousands()).isEqualTo(945);
        assertThat(result.getHundreds()).isEqualTo(7);
        assertThat(result.getFirstTwoDigits()).isEqualTo(81);

        assertThat(result.hasMillions()).isTrue();
        assertThat(result.hasFirstTwoDigits()).isTrue();
        assertThat(result.hasThousands()).isTrue();
        assertThat(result.hasHundreds()).isTrue();
        assertThat(result.isZero()).isFalse();
        

    }
    
    
    
    @Test(expected = IllegalArgumentException.class)
    public void somePunkTryingToProvideANegativeValue() throws Exception {
        Result.fromNumber(-1);
    } 
    
}