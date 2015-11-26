package com.github.melowe.print.number;

public class Result {

    private final int millions;

    private final int thousands;

    private final int hundreds;

    private final int firstTwoDigits;

    public Result(int millions, int thousands, int hundreds, int firstTwoDigits) {
        this.millions = millions;
        this.thousands = thousands;
        this.hundreds = hundreds;
        this.firstTwoDigits = firstTwoDigits;
    }

    public int getMillions() {
        return millions;
    }

    public int getThousands() {
        return thousands;
    }

    public int getHundreds() {
        return hundreds;
    }

    public int getFirstTwoDigits() {
        return firstTwoDigits;
    }

    
    public boolean hasThousands() {
        return thousands != 0;
    }
    
    public boolean hasFirstTwoDigits() {
        return firstTwoDigits != 0;
    } 
    
    public boolean hasHundreds() {
        return hundreds != 0;
    }
    
    public boolean hasMillions() {
        return millions != 0;
    }
    
    public static class Builder {

        private int millions;

        private int thousands;

        private int hundreds;

        private int firstTwoDigits;

        private Builder() {
        }

        public static Builder create() {
            return new Builder();
        }

        public Builder millions(int millions) {
            this.millions = millions;
            return this;
        }

        public Builder thousands(int thousands) {
            this.thousands = thousands;
            return this;
        }

        public Builder hundreds(int hundreds) {
            this.hundreds = hundreds;
            return this;
        }

        public Builder firstTwoDigits(int firstTwoDigits) {
            this.firstTwoDigits = firstTwoDigits;
            return this;
        }
        
        public Result build() {
            return new Result(millions, thousands, hundreds, firstTwoDigits);
        }

    }

}
