public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    static class QuantityLength {
        private final double value;
        private final LengthUnit unit;

        QuantityLength(double value, LengthUnit unit) {
            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            QuantityLength other = (QuantityLength) obj;
            return Double.compare(
                    this.toBaseUnit(), other.toBaseUnit()) == 0;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Quantity Measurement App ===");

        QuantityLength oneFeet = new QuantityLength(
                1.0, LengthUnit.FEET);
        QuantityLength twelveInches = new QuantityLength(
                12.0, LengthUnit.INCH);
        QuantityLength twoFeet = new QuantityLength(
                2.0, LengthUnit.FEET);
        QuantityLength oneInch = new QuantityLength(
                1.0, LengthUnit.INCH);
        QuantityLength anotherOneInch = new QuantityLength(
                1.0, LengthUnit.INCH);

        System.out.println(
                "Input: 1.0 feet and 12.0 inches");
        System.out.println(
                "Equal: " + oneFeet.equals(twelveInches));

        System.out.println(
                "Input: 1.0 feet and 2.0 feet");
        System.out.println(
                "Equal: " + oneFeet.equals(twoFeet));

        System.out.println(
                "Input: 1.0 inch and 1.0 inch");
        System.out.println(
                "Equal: " + oneInch.equals(anotherOneInch));

        System.out.println(
                "Input: 12.0 inches and 1.0 feet");
        System.out.println(
                "Equal: " + twelveInches.equals(oneFeet));

        System.out.println("================================");
    }
}