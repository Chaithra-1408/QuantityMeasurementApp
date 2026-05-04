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

        public QuantityLength convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null");
            }
            double baseValue = this.toBaseUnit();
            double convertedValue =
                    baseValue / targetUnit.getConversionFactor();
            return new QuantityLength(convertedValue, targetUnit);
        }

        public static double convert(double value,
                                     LengthUnit sourceUnit, LengthUnit targetUnit) {
            if (sourceUnit == null || targetUnit == null) {
                throw new IllegalArgumentException(
                        "Units cannot be null");
            }
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be a finite number");
            }
            double baseValue =
                    value * sourceUnit.getConversionFactor();
            return baseValue / targetUnit.getConversionFactor();
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

        @Override
        public String toString() {
            return value + " " + unit.name();
        }
    }

    static void demonstrateLengthConversion(double value,
                                            LengthUnit fromUnit, LengthUnit toUnit) {
        double result = QuantityLength.convert(
                value, fromUnit, toUnit);
        System.out.printf("convert(%.1f, %s, %s) = %.4f%n",
                value, fromUnit, toUnit, result);
    }

    static void demonstrateLengthConversion(
            QuantityLength length, LengthUnit toUnit) {
        QuantityLength converted = length.convertTo(toUnit);
        System.out.printf("convert(%s, %s) = %s%n",
                length, toUnit, converted);
    }

    static void demonstrateLengthEquality(
            QuantityLength length1, QuantityLength length2) {
        System.out.printf("Equal(%s, %s) = %b%n",
                length1, length2, length1.equals(length2));
    }

    public static void main(String[] args) {

        System.out.println("=== Quantity Measurement App ===");
        System.out.println("\n--- UC5: Unit Conversion ---");

        demonstrateLengthConversion(1.0,
                LengthUnit.FEET, LengthUnit.INCH);

        demonstrateLengthConversion(3.0,
                LengthUnit.YARD, LengthUnit.FEET);

        demonstrateLengthConversion(36.0,
                LengthUnit.INCH, LengthUnit.YARD);

        demonstrateLengthConversion(1.0,
                LengthUnit.CENTIMETER, LengthUnit.INCH);

        demonstrateLengthConversion(0.0,
                LengthUnit.FEET, LengthUnit.INCH);

        demonstrateLengthConversion(6.0,
                LengthUnit.FEET, LengthUnit.YARD);

        demonstrateLengthConversion(24.0,
                LengthUnit.INCH, LengthUnit.FEET);

        System.out.println("\n--- Method Overloading Demo ---");

        QuantityLength lengthInYards =
                new QuantityLength(1.0, LengthUnit.YARD);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCH);

        System.out.println("\n--- Equality Checks ---");

        demonstrateLengthEquality(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH));

        demonstrateLengthEquality(
                new QuantityLength(1.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET));

        System.out.println("================================");
    }
}