
package com.github.melowe.print.number;

import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import static org.junit.Assert.*;


public class ResultFormatterTest {
    
    public ResultFormatterTest() {
    }

    
    @Test
    public void formatEleven() throws Exception {
        
        Map<Scale,Integer> scaleMap = new EnumMap<>(Scale.class);
        scaleMap.put(Scale.ONE,1);
        scaleMap.put(Scale.TENS,1);
        
        String result = ResultFormatter.format(scaleMap);
        
        assertEquals("ELEVEN", result); 
    }
    
    @Test
    public void formatElevenMillion() throws Exception {
        
        Map<Scale,Integer> scaleMap = new EnumMap<>(Scale.class);
        scaleMap.put(Scale.MILLION,11);
        
        String result = ResultFormatter.format(scaleMap);
        
        assertEquals("ELEVEN MILLION", result); 
    }
    
    @Test
    public void formatAllEmptyShouldBeZero() throws Exception {
        
        Map<Scale,Integer> scaleMap = new EnumMap<>(Scale.class);

        
        String result = ResultFormatter.format(scaleMap);
        
        assertEquals("ZERO", result); 
    }
    
    @Test
    public void formatAllZerosShouldBeZero() throws Exception {
        
        Map<Scale,Integer> scaleMap = Stream.of(Scale.values())
                .collect(Collectors.toMap(s -> s,s -> 0));
        
        
        String result = ResultFormatter.format(scaleMap);
        
        assertEquals("ZERO", result); 
    }
    
    
    @Test(expected = IllegalArgumentException.class)
    public void formatTooManyHundreds() throws Exception {
        
        Map<Scale,Integer> scaleMap = new EnumMap<>(Scale.class);
        scaleMap.put(Scale.HUNDRED, 1000);
        
        ResultFormatter.format(scaleMap);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void formatTooManyTens() throws Exception {
        
        Map<Scale,Integer> scaleMap = new EnumMap<>(Scale.class);
        scaleMap.put(Scale.TENS, 1000);
        
        ResultFormatter.format(scaleMap);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void formatTooManyOnes() throws Exception {
        
        Map<Scale,Integer> scaleMap = new EnumMap<>(Scale.class);
        scaleMap.put(Scale.ONE, 1000);
        
        ResultFormatter.format(scaleMap);

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void formatTooManyOnes999() throws Exception {
        
        Map<Scale,Integer> scaleMap = new EnumMap<>(Scale.class);
        scaleMap.put(Scale.ONE, 999);
        
        ResultFormatter.format(scaleMap);

    }
    
}
