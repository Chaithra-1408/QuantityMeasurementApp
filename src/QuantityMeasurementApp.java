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
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be a finite number");
            }
            if (unit == null) {
                throw new IllegalArgumentException(
                        "Unit cannot be null");
            }
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

        public static QuantityLength add(
                QuantityLength length1,
                QuantityLength length2) {
            if (length1 == null || length2 == null) {
                throw new IllegalArgumentException(
                        "Lengths cannot be null");
            }
            double baseSum = length1.toBaseUnit()
                    + length2.toBaseUnit();
            double resultValue = baseSum
                    / length1.unit.getConversionFactor();
            return new QuantityLength(resultValue, length1.unit);
        }

        public static QuantityLength add(
                double value1, LengthUnit unit1,
                double value2, LengthUnit unit2,
                LengthUnit targetUnit) {
            QuantityLength l1 = new QuantityLength(value1, unit1);
            QuantityLength l2 = new QuantityLength(value2, unit2);
            double baseSum = l1.toBaseUnit() + l2.toBaseUnit();
            double resultValue =
                    baseSum / targetUnit.getConversionFactor();
            return new QuantityLength(resultValue, targetUnit);
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
            return String.format("Quantity(%.4f, %s)",
                    value, unit.name());
        }
    }

    static void demonstrateAddition(
            QuantityLength l1, QuantityLength l2) {
        QuantityLength result = QuantityLength.add(l1, l2);
        System.out.println("add(" + l1 + ", " + l2
                + ") = " + result);
    }

    public static void main(String[] args) {

        System.out.println("=== Quantity Measurement App ===");
        System.out.println("\n--- UC6: Addition of Two Lengths ---");

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(2.0, LengthUnit.FEET));

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH));

        demonstrateAddition(
                new QuantityLength(12.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.FEET));

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET));

        demonstrateAddition(
                new QuantityLength(36.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.YARD));

        demonstrateAddition(
                new QuantityLength(2.54, LengthUnit.CENTIMETER),
                new QuantityLength(1.0, LengthUnit.INCH));

        demonstrateAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCH));

        demonstrateAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET));

        System.out.println("================================");
    }
}