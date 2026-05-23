package utils;

public class FeeCalculator {

    public static double calculateFees(String disease) {

        switch (disease) {

            case "Fever":
                return 2000;

            case "Heart Disease":
                return 15000;

            case "Diabetes":
                return 5000;

            case "Fracture":
                return 8000;

            default:
                return 3000;
        }
    }
}