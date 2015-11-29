
package com.github.melowe.print.number;

import java.util.EnumMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ResultFormatterTest {
    
    public ResultFormatterTest() {
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
    
    
}
