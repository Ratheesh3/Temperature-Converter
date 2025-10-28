package console;

import java.util.Scanner;
import java.text.DecimalFormat;

public class TempConverterConsole {
    private static final DecimalFormat df = new DecimalFormat("#.##");

    // Conversion helpers
    public static double cToF(double c) { return c * 9.0/5.0 + 32.0; }
    public static double fToC(double f) { return (f - 32.0) * 5.0/9.0; }
    public static double cToK(double c) { return c + 273.15; }
    public static double kToC(double k) { return k - 273.15; }
    public static double fToK(double f) { return fToC(f) + 273.15; }
    public static double kToF(double k) { return cToF(kToC(k)); }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Temperature Converter (Celsius <-> Fahrenheit <-> Kelvin)");
        System.out.println("Choose conversion:");
        System.out.println("1. Celsius -> Fahrenheit");
        System.out.println("2. Fahrenheit -> Celsius");
        System.out.println("3. Celsius -> Kelvin");
        System.out.println("4. Kelvin -> Celsius");
        System.out.println("5. Fahrenheit -> Kelvin");
        System.out.println("6. Kelvin -> Fahrenheit");
        System.out.print("Enter choice (1-6): ");

        int choice = sc.nextInt();
        System.out.print("Enter temperature value: ");
        double value = sc.nextDouble();

        double result;
        String out = "";

        switch (choice) {
            case 1:
                result = cToF(value);
                out = df.format(value) + " °C = " + df.format(result) + " °F";
                break;
            case 2:
                result = fToC(value);
                out = df.format(value) + " °F = " + df.format(result) + " °C";
                break;
            case 3:
                result = cToK(value);
                out = df.format(value) + " °C = " + df.format(result) + " K";
                break;
            case 4:
                result = kToC(value);
                out = df.format(value) + " K = " + df.format(result) + " °C";
                break;
            case 5:
                result = fToK(value);
                out = df.format(value) + " °F = " + df.format(result) + " K";
                break;
            case 6:
                result = kToF(value);
                out = df.format(value) + " K = " + df.format(result) + " °F";
                break;
            default:
                out = "Invalid choice.";
        }

        System.out.println(out);
        sc.close();
    }
}
