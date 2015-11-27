package com.github.melowe.print.number;

public class Result {

    private final int millions;

    private final int thousands;

    private final int hundreds;

    private final int firstTwoDigits;

    private Result(int millions, int thousands, int hundreds, int firstTwoDigits) {
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

    public boolean isZero() {
        return (firstTwoDigits + hundreds + thousands + millions) == 0;
    }
    
    @Override
    public String toString() {
        return "Result{" + "millions=" + millions + ", thousands=" + thousands + ", hundreds=" + hundreds + ", firstTwoDigits=" + firstTwoDigits + '}';
    }

    protected static Result fromNumber(int number) {
        
        if(Math.signum(number) < 0) {
            throw new IllegalArgumentException("Negative values aren't supported : "+ number);
        }
                
        //PadAndParse
        String value = String.format("%012d", number);
        int first = Integer.parseInt(value.substring(10, 12));
        int hundreds = Character.getNumericValue(value.charAt(9));
        int thousands = Integer.parseInt(value.substring(6, 9));
        int millions = Integer.parseInt(value.substring(0, 6));

        Result result = Result.Builder.create()
                .hundreds(hundreds)
                .millions(millions)
                .thousands(thousands)
                .firstTwoDigits(first)
                .build();
        return result;

    }

    protected static class Builder {

        private int millions;

        private int thousands;

        private int hundreds;

        private int firstTwoDigits;

        private Builder() {
        }

        protected static Builder create() {
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
