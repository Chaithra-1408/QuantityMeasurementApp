public class QuantityMeasurementApp {

    enum LengthUnit {
        FEET(1.0),
        INCH(1.0 / 12.0),
        YARD(3.0),
        CENTIMETER(0.393701 / 12.0);

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

        QuantityLength oneYard = new QuantityLength(
                1.0, LengthUnit.YARD);
        QuantityLength threeFeet = new QuantityLength(
                3.0, LengthUnit.FEET);
        QuantityLength thirtySevenInches = new QuantityLength(
                36.0, LengthUnit.INCH);
        QuantityLength twoYards = new QuantityLength(
                2.0, LengthUnit.YARD);
        QuantityLength anotherTwoYards = new QuantityLength(
                2.0, LengthUnit.YARD);
        QuantityLength oneCm = new QuantityLength(
                1.0, LengthUnit.CENTIMETER);
        QuantityLength pointThreeInch = new QuantityLength(
                0.393701, LengthUnit.INCH);
        QuantityLength twoCm = new QuantityLength(
                2.0, LengthUnit.CENTIMETER);
        QuantityLength anotherTwoCm = new QuantityLength(
                2.0, LengthUnit.CENTIMETER);

        System.out.println("Input: 1.0 YARDS and 3.0 FEET");
        System.out.println("Equal: " + oneYard.equals(threeFeet));

        System.out.println("Input: 1.0 YARDS and 36.0 INCHES");
        System.out.println(
                "Equal: " + oneYard.equals(thirtySevenInches));

        System.out.println("Input: 2.0 YARDS and 2.0 YARDS");
        System.out.println("Equal: " + twoYards.equals(anotherTwoYards));

        System.out.println(
                "Input: 2.0 CENTIMETERS and 2.0 CENTIMETERS");
        System.out.println("Equal: " + twoCm.equals(anotherTwoCm));

        System.out.println(
                "Input: 1.0 CENTIMETERS and 0.393701 INCHES");
        System.out.println(
                "Equal: " + oneCm.equals(pointThreeInch));

        System.out.println("================================");
    }
}