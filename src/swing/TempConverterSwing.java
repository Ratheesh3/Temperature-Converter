package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class TempConverterSwing extends JFrame {
    private JComboBox<String> fromBox, toBox;
    private JTextField inputField;
    private JLabel resultLabel, titleLabel;
    private JButton convertBtn, clearBtn;
    private static final DecimalFormat df = new DecimalFormat("#.##");

    public TempConverterSwing() {
        setTitle("🌡️ Temperature Converter");
        setSize(480, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // --- MAIN PANEL ---
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                    0, 0, new Color(173, 216, 230),
                    0, getHeight(), new Color(240, 248, 255)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(null);

        // --- TITLE ---
        titleLabel = new JLabel("🌡️ Temperature Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Poppins", Font.BOLD, 20));
        titleLabel.setBounds(40, 20, 400, 30);
        mainPanel.add(titleLabel);

        // --- INPUT SECTION ---
        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        fromLabel.setBounds(70, 70, 60, 25);
        mainPanel.add(fromLabel);

        String[] units = {"Celsius (°C)", "Fahrenheit (°F)", "Kelvin (K)"};
        fromBox = new JComboBox<>(units);
        fromBox.setBounds(130, 70, 130, 25);
        fromBox.setToolTipText("Select the unit you are converting from");
        mainPanel.add(fromBox);

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        toLabel.setBounds(280, 70, 40, 25);
        mainPanel.add(toLabel);

        toBox = new JComboBox<>(units);
        toBox.setBounds(320, 70, 130, 25);
        toBox.setToolTipText("Select the unit you are converting to");
        mainPanel.add(toBox);

        JLabel valueLabel = new JLabel("Value:");
        valueLabel.setFont(new Font("SansSerif", Font.PLAIN, 15));
        valueLabel.setBounds(70, 115, 60, 25);
        mainPanel.add(valueLabel);

        inputField = new JTextField();
        inputField.setBounds(130, 115, 150, 25);
        inputField.setToolTipText("Enter the temperature value");
        mainPanel.add(inputField);

        // --- BUTTONS ---
        convertBtn = new JButton("Convert 🔄");
        convertBtn.setBounds(300, 115, 150, 25);
        convertBtn.setBackground(new Color(100, 149, 237));
        convertBtn.setForeground(Color.WHITE);
        convertBtn.setFocusPainted(false);
        convertBtn.setBorder(BorderFactory.createEmptyBorder());
        convertBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        convertBtn.setToolTipText("Click to convert the temperature");
        mainPanel.add(convertBtn);

        clearBtn = new JButton("Clear 🧹");
        clearBtn.setBounds(190, 155, 100, 25);
        clearBtn.setBackground(new Color(240, 128, 128));
        clearBtn.setForeground(Color.WHITE);
        clearBtn.setFocusPainted(false);
        clearBtn.setBorder(BorderFactory.createEmptyBorder());
        clearBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        clearBtn.setToolTipText("Clear all input fields");
        mainPanel.add(clearBtn);

        // --- RESULT LABEL ---
        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultLabel.setForeground(new Color(25, 25, 112));
        resultLabel.setBounds(40, 200, 400, 30);
        mainPanel.add(resultLabel);

        // --- ACTIONS ---
        convertBtn.addActionListener(e -> convertTemperature());
        clearBtn.addActionListener(e -> {
            inputField.setText("");
            resultLabel.setText("Result: ");
        });
        inputField.addActionListener(e -> convertTemperature());

        add(mainPanel);
    }

    private void convertTemperature() {
        String from = (String) fromBox.getSelectedItem();
        String to = (String) toBox.getSelectedItem();
        String input = inputField.getText().trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a temperature value.", "Input Missing", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double value;
        try {
            value = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prevent impossible Kelvin values
        if (from.contains("Kelvin") && value < 0) {
            JOptionPane.showMessageDialog(this, "Kelvin value cannot be negative!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double result = convertValue(value, from, to);
        String formatted = df.format(result) + " " + getUnitSymbol(to);
        resultLabel.setText("Result: " + formatted);
    }

    private double convertValue(double value, String from, String to) {
        double celsius;
        if (from.startsWith("Celsius")) {
            celsius = value;
        } else if (from.startsWith("Fahrenheit")) {
            celsius = (value - 32) * 5.0 / 9.0;
        } else { // Kelvin
            celsius = value - 273.15;
        }

        if (to.startsWith("Celsius")) return celsius;
        if (to.startsWith("Fahrenheit")) return (celsius * 9.0 / 5.0) + 32;
        return celsius + 273.15; // Kelvin
    }

    private String getUnitSymbol(String unit) {
        if (unit.startsWith("Celsius")) return "°C";
        if (unit.startsWith("Fahrenheit")) return "°F";
        return "K";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TempConverterSwing().setVisible(true);
        });
    }
}
