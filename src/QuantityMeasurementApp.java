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

        private static QuantityLength addInBaseUnit(
                QuantityLength l1, QuantityLength l2,
                LengthUnit targetUnit) {
            if (l1 == null || l2 == null) {
                throw new IllegalArgumentException(
                        "Lengths cannot be null");
            }
            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null");
            }
            double baseSum = l1.toBaseUnit() + l2.toBaseUnit();
            double result =
                    baseSum / targetUnit.getConversionFactor();
            double rounded =
                    Math.round(result * 100.0) / 100.0;
            return new QuantityLength(rounded, targetUnit);
        }

        public static QuantityLength add(
                QuantityLength l1, QuantityLength l2) {
            return addInBaseUnit(l1, l2, l1.unit);
        }

        public static QuantityLength add(
                QuantityLength l1, QuantityLength l2,
                LengthUnit targetUnit) {
            return addInBaseUnit(l1, l2, targetUnit);
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
            QuantityLength l1, QuantityLength l2,
            LengthUnit targetUnit) {
        QuantityLength result =
                QuantityLength.add(l1, l2, targetUnit);
        System.out.println("add(" + l1 + ", " + l2
                + ", " + targetUnit + ") = " + result);
    }

    public static void main(String[] args) {

        System.out.println("=== Quantity Measurement App ===");
        System.out.println(
                "\n--- UC7: Addition with Target Unit ---");

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.FEET);

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.INCH);

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCH),
                LengthUnit.YARD);

        demonstrateAddition(
                new QuantityLength(1.0, LengthUnit.YARD),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARD);

        demonstrateAddition(
                new QuantityLength(36.0, LengthUnit.INCH),
                new QuantityLength(1.0, LengthUnit.YARD),
                LengthUnit.FEET);

        demonstrateAddition(
                new QuantityLength(2.54, LengthUnit.CENTIMETER),
                new QuantityLength(1.0, LengthUnit.INCH),
                LengthUnit.CENTIMETER);

        demonstrateAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCH),
                LengthUnit.YARD);

        demonstrateAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.INCH);

        System.out.println("================================");
    }
}