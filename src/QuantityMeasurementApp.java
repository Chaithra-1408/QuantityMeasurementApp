public class QuantityMeasurementApp {

    static class Feet {
        private final double value;

        Feet(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    static class Inches {
        private final double value;

        Inches(double value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null) return false;
            if (getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    static boolean checkFeetEquality(double val1, double val2) {
        Feet feet1 = new Feet(val1);
        Feet feet2 = new Feet(val2);
        return feet1.equals(feet2);
    }

    static boolean checkInchesEquality(double val1, double val2) {
        Inches inches1 = new Inches(val1);
        Inches inches2 = new Inches(val2);
        return inches1.equals(inches2);
    }

    public static void main(String[] args) {

        System.out.println("=== Quantity Measurement App ===");

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Equal: " + checkFeetEquality(1.0, 1.0));

        System.out.println("Input: 1.0 ft and 2.0 ft");
        System.out.println("Equal: " + checkFeetEquality(1.0, 2.0));

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Equal: " + checkInchesEquality(1.0, 1.0));

        System.out.println("Input: 1.0 inch and 2.0 inch");
        System.out.println("Equal: " + checkInchesEquality(1.0, 2.0));

        System.out.println("================================");
    }
}