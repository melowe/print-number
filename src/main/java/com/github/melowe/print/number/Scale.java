
package com.github.melowe.print.number;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;


public final class Scale implements Comparable<Scale>{
    
    public static final Scale ONE = new Scale(0, "ONE",0);
    
    public static final Scale TENS = new Scale(1, "TENS",1);
    
    public static final Scale HUNDRED = new Scale(2, "HUNDRED",2);
    
    public static final Scale THOUSAND = new Scale(3, "THOUSAND",3);
    
    public static final Scale MILLION = new Scale(6, "MILLION",4);
    
    public static final Scale BILLION = new Scale(9, "BILLION",5);
    
    public static final Scale TRILLION = new Scale(12, "TRILLION",6);
    
    public static final Scale QUADRILLION = new Scale(15, "QUADRILLION",7);
    
    public static final Scale QUINTILLION = new Scale(18, "QUINTILLION",8);

    public static final Scale SEXTILLION = new Scale(21, "SEXTILLION",9);
    
    public static final Scale SEPTILLION = new Scale(24, "SEPTILLION",10);
    
    public static final Scale OCTILLION = new Scale(27, "OCTILLION",11);
    
    public static final Scale NONILLION = new Scale(30, "NONILLION",12);
    
    @Override
    public int compareTo(Scale o) {
        return Integer.compare(ordinal, o.ordinal);
    }
    
    
    private enum Prefix {
        UN,DO,TRE,QUAT,QUIN,SEX,SEPTEN,OCTO,NOVEM;
    }
    
    //TODO: What is is class of numbers called?
    private enum Illion {
        DECILLION,
        VIGINTILLION,
        TRIGINTILLION,
        QUADRAGINTILLION,
        QUINQUAGINTILLION,
        SEXAGINTILLION,
        SEPTENSEXAGINTILLION,
        OCTOSEXAGINTILLION,
        NOVEMSEXAGINTILLION,
        CENTILLION;
    }
    

    private final int ordinal;
    
    private final int value;
    
    private final String name;

    private static final  Scale[] VALUES = generate();
    
    private Scale(int value, String name,int ordinal) {
        this.value = value;
        this.name = name;
        this.ordinal = ordinal;
    }

    public String name() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int ordinal() {
        return ordinal;
    }
    
    public static Scale valueOf(String name) {
        Objects.requireNonNull(name);
        return Stream.of(values()).filter(s -> Objects.equals(name, s.name))
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
    
    
    public static Scale[] values() {
        return VALUES;
    }
    
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.ordinal;
        hash = 59 * hash + this.value;
        hash = 59 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Scale other = (Scale) obj;
        if (this.ordinal != other.ordinal) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    



    
    private static  Scale[] generate() {
        

        List<Scale> list = new ArrayList<>();
        list.addAll(
            Arrays.asList(  ONE,TENS,HUNDRED,THOUSAND,MILLION,
                            BILLION,TRILLION,QUADRILLION,
                            QUINTILLION,SEXTILLION,SEPTILLION,NONILLION)
        );
        int scaleValue = 33;
       
        for(Illion i : Illion.values()) {
            list.add(new Scale(scaleValue, i.name(),list.size()));
            scaleValue = scaleValue + 3;
            for(Prefix p : Prefix.values()) {
                list.add(new Scale(scaleValue, p.name() + i.name(),list.size()));
                scaleValue = scaleValue + 3;
            }
        }
        
        return list.toArray(new Scale[0]);
    }
    
    
    
    private static final int MAX_NUMBER_LENGTH = 333;
    
    private static final String FORMAT = "%0"+ MAX_NUMBER_LENGTH +"d";
    
    protected static Map<Scale, Integer> toScaleMap(int number) {
        if(Integer.signum(number) < 0) {
            throw new IllegalArgumentException("NO Negative number support");
        }
        return toScaleMap(BigInteger.valueOf(number));
    }
    
    protected static Map<Scale, Integer> toScaleMap(BigInteger number) {

        //Highest big number allowing for hundreds if it
        final int numberLength = MAX_NUMBER_LENGTH;
        if (number.toString().length() > numberLength) {
            throw new UnsupportedOperationException("Scale exceeds "+ numberLength +" " + number);
        }

        final String value = String.format(FORMAT, number);

        final int first = Character.getNumericValue(value.charAt(numberLength - 1));
        final int tens = Character.getNumericValue(value.charAt(numberLength - 2));
        final int hundreds = Character.getNumericValue(value.charAt(numberLength - 3));

        final Map<Scale, Integer> multiples = new HashMap<>();

        multiples.put(Scale.ONE, first);
        multiples.put(Scale.TENS, tens);
        multiples.put(Scale.HUNDRED, hundreds);

        Stream.of(Scale.values())
                .filter(s -> s.ordinal() > HUNDRED.ordinal())
                .forEach(s -> {
                    int fromIndex = numberLength - (s.getValue() + 3);
                    int toIndex = numberLength - s.getValue();
                    multiples.put(s, Integer.parseInt(value.substring(fromIndex, toIndex)));
                });


        return Collections.unmodifiableMap(multiples);

    }

}
