package joptionpane;

import javax.swing.JOptionPane;
import java.text.DecimalFormat;

public class TempConverterJOptionPane {
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public static double cToF(double c) { return c * 9.0/5.0 + 32.0; }
    public static double fToC(double f) { return (f - 32.0) * 5.0/9.0; }
    public static double cToK(double c) { return c + 273.15; }
    public static double kToC(double k) { return k - 273.15; }
    public static double fToK(double f) { return fToC(f) + 273.15; }
    public static double kToF(double k) { return cToF(kToC(k)); }

    public static void main(String[] args) {
        String[] options = {
            "Celsius → Fahrenheit",
            "Fahrenheit → Celsius",
            "Celsius → Kelvin",
            "Kelvin → Celsius",
            "Fahrenheit → Kelvin",
            "Kelvin → Fahrenheit"
        };

        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose conversion:",
                "Temperature Converter",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice < 0) return; // user closed dialog

        String input = JOptionPane.showInputDialog(null, "Enter temperature value:");
        if (input == null) return; // user canceled

        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double result = 0;
        String message = "";
        switch (choice) {
            case 0: result = cToF(value); message = df.format(value) + " °C = " + df.format(result) + " °F"; break;
            case 1: result = fToC(value); message = df.format(value) + " °F = " + df.format(result) + " °C"; break;
            case 2: result = cToK(value); message = df.format(value) + " °C = " + df.format(result) + " K"; break;
            case 3: result = kToC(value); message = df.format(value) + " K = " + df.format(result) + " °C"; break;
            case 4: result = fToK(value); message = df.format(value) + " °F = " + df.format(result) + " K"; break;
            case 5: result = kToF(value); message = df.format(value) + " K = " + df.format(result) + " °F"; break;
            default: message = "Invalid choice"; break;
        }

        JOptionPane.showMessageDialog(null, message, "Result", JOptionPane.INFORMATION_MESSAGE);
    }
}
