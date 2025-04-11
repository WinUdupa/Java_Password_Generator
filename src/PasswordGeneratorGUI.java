import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Deque;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PasswordGeneratorGUI extends JFrame {
    private PasswordGenerator passwordGenerator;
    private Deque<String> passwordHistory;
    private static final int HISTORY_SIZE = 4;
    private JPanel historyPanel;

    public PasswordGeneratorGUI() {
        super("Password Generator");
        setSize(540, 700);  
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        passwordGenerator = new PasswordGenerator();
        passwordHistory = new ArrayDeque<>(HISTORY_SIZE);
        getContentPane().setBackground(Color.WHITE); 
        addGuiComponents();
    }

    private void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
        JOptionPane.showMessageDialog(this, "Password copied to clipboard!", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void addPasswordToHistory(String password) {
        if (passwordHistory.size() >= HISTORY_SIZE) {
            passwordHistory.removeLast();
        }
        passwordHistory.addFirst(password);
        updateHistoryPanel();
    }

    private void updateHistoryPanel() {
        historyPanel.removeAll();
        int yPos = 25;

        for (String password : passwordHistory) {
            JLabel passwordLabel = new JLabel(password);
            passwordLabel.setFont(new Font("Cambria", Font.PLAIN, 14));
            passwordLabel.setBounds(10, yPos, 380, 20);

            JButton copyButton = new JButton("Copy");
            copyButton.setBounds(390, yPos, 70, 20);
            copyButton.setFont(new Font("Cambria", Font.PLAIN, 12));
            copyButton.setBackground(new Color(0, 153, 255)); // Light blue
            copyButton.setForeground(Color.WHITE); // White text
            copyButton.addActionListener(e -> copyToClipboard(password));

            historyPanel.add(passwordLabel);
            historyPanel.add(copyButton);

            yPos += 25;
        }

        historyPanel.revalidate();
        historyPanel.repaint();
    }

    private void addGuiComponents() {
        // Title
        JLabel titleLabel = new JLabel("Password Generator");
        titleLabel.setFont(new Font("Cambria", Font.BOLD, 32));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(0, 10, 540, 39);
        titleLabel.setForeground(new Color(0, 102, 204)); // Deep blue
        add(titleLabel);

        // Password Output Area
        JTextArea passwordOutput = new JTextArea();
        passwordOutput.setEditable(false);
        passwordOutput.setFont(new Font("Cambria", Font.BOLD, 32));

        JScrollPane passwordOutputPane = new JScrollPane(passwordOutput);
        passwordOutputPane.setBounds(25, 60, 479, 70);
        passwordOutputPane.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204))); // Blue border
        add(passwordOutputPane);

        // Copy Current Password Button
        JButton copyCurrentButton = new JButton("Copy");
        copyCurrentButton.setFont(new Font("Cambria", Font.PLAIN, 14));
        copyCurrentButton.setBounds(400, 135, 104, 25);
        copyCurrentButton.setBackground(new Color(0, 153, 255)); // Light blue
        copyCurrentButton.setForeground(Color.WHITE); // White text
        copyCurrentButton.addActionListener(e -> {
            String currentPassword = passwordOutput.getText();
            if (!currentPassword.isEmpty()) {
                copyToClipboard(currentPassword);
            }
        });
        add(copyCurrentButton);

        // Strength Label and Output
        JLabel strengthLabel = new JLabel("Password Strength:");
        strengthLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
        strengthLabel.setBounds(25, 160, 150, 50);
        strengthLabel.setForeground(new Color(0, 102, 204)); // Blue text
        add(strengthLabel);

        JTextField strengthOutput = new JTextField();
        strengthOutput.setEditable(false);
        strengthOutput.setFont(new Font("Cambria", Font.BOLD, 16));
        strengthOutput.setBounds(175, 170, 329, 30);
        strengthOutput.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204))); // Blue border
        add(strengthOutput);

        // Time Label and Output
        JLabel timeLabel = new JLabel("Time to Crack:");
        timeLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
        timeLabel.setBounds(25, 210, 150, 30);
        timeLabel.setForeground(new Color(0, 102, 204)); // Blue text
        add(timeLabel);

        JTextField timeOutput = new JTextField();
        timeOutput.setEditable(false);
        timeOutput.setFont(new Font("Cambria", Font.BOLD, 16));
        timeOutput.setBounds(175, 210, 329, 30);
        timeOutput.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204))); // Blue border
        add(timeOutput);

        // Password Length Section
        JLabel passwordLengthLabel = new JLabel("Password Length");
        passwordLengthLabel.setFont(new Font("Cambria", Font.PLAIN, 32));
        passwordLengthLabel.setBounds(25, 250, 272, 39);
        passwordLengthLabel.setForeground(new Color(0, 102, 204)); // Blue text
        add(passwordLengthLabel);

        JTextArea passwordLengthInputArea = new JTextArea();
        passwordLengthInputArea.setFont(new Font("Cambria", Font.PLAIN, 32));
        passwordLengthInputArea.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204))); // Blue border
        passwordLengthInputArea.setBounds(310, 250, 192, 39);
        add(passwordLengthInputArea);

        // Toggle Buttons
        JToggleButton uppercaseToggle = new JToggleButton("Uppercase");
        uppercaseToggle.setFont(new Font("Cambria", Font.PLAIN, 26));
        uppercaseToggle.setBounds(25, 300, 225, 56);
        uppercaseToggle.setBackground(new Color(0, 153, 255)); // Light blue
        uppercaseToggle.setForeground(Color.WHITE); // White text
        add(uppercaseToggle);

        JToggleButton lowercaseToggle = new JToggleButton("Lowercase");
        lowercaseToggle.setFont(new Font("Cambria", Font.PLAIN, 26));
        lowercaseToggle.setBounds(282, 300, 225, 56);
        lowercaseToggle.setBackground(new Color(0, 153, 255)); // Light blue
        lowercaseToggle.setForeground(Color.WHITE); // White text
        add(lowercaseToggle);

        JToggleButton numbersToggle = new JToggleButton("Numbers");
        numbersToggle.setFont(new Font("Cambria", Font.PLAIN, 26));
        numbersToggle.setBounds(25, 365, 225, 56);
        numbersToggle.setBackground(new Color(0, 153, 255)); // Light blue
        numbersToggle.setForeground(Color.WHITE); // White text
        add(numbersToggle);

        JToggleButton symbolsToggle = new JToggleButton("Symbols");
        symbolsToggle.setFont(new Font("Cambria", Font.PLAIN, 26));
        symbolsToggle.setBounds(282, 365, 225, 56);
        symbolsToggle.setBackground(new Color(0, 153, 255)); // Light blue
        symbolsToggle.setForeground(Color.WHITE); // White text
        add(symbolsToggle);

        // Custom Characters Section
        JLabel customCharsLabel = new JLabel("Custom Characters:");
        customCharsLabel.setFont(new Font("Cambria", Font.PLAIN, 16));
        customCharsLabel.setBounds(25, 430, 150, 30);
        customCharsLabel.setForeground(new Color(0, 102, 204)); // Blue text
        add(customCharsLabel);

        JTextArea customCharsInput = new JTextArea();
        customCharsInput.setFont(new Font("Cambria", Font.PLAIN, 16));
        customCharsInput.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204))); // Blue border
        customCharsInput.setBounds(175, 430, 329, 30);
        customCharsInput.setToolTipText("Enter custom characters to use for password generation");
        add(customCharsInput);

        // History Panel
        historyPanel = new JPanel();
        historyPanel.setLayout(null);
        historyPanel.setBounds(25, 520, 479, 130);
        historyPanel.setBackground(new Color(230, 242, 255)); // Very light blue background
        historyPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 102, 204)),
            "Password History (Click to Copy)",
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Cambria", Font.BOLD, 14),
            new Color(0, 102, 204) // Blue border text
        ));
        add(historyPanel);

        // Generate Button
        JButton generateButton = new JButton("Generate");
        generateButton.setFont(new Font("Cambria", Font.PLAIN, 32));
        generateButton.setBounds(155, 470, 222, 41);
        generateButton.setBackground(new Color(0, 102, 204)); // Deep blue
        generateButton.setForeground(Color.WHITE); // White text
        generateButton.setBorder(BorderFactory.createLineBorder(new Color(0, 153, 255))); // Light blue border
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordLengthInputArea.getText().length() <= 0) return;

                String customChars = customCharsInput.getText().trim();
                boolean useCustomChars = !customChars.isEmpty();
                boolean anyToggleSelected = lowercaseToggle.isSelected() ||
                                          uppercaseToggle.isSelected() ||
                                          numbersToggle.isSelected() ||
                                          symbolsToggle.isSelected();

                try {
                    int passwordLength = Integer.parseInt(passwordLengthInputArea.getText());

                    if (useCustomChars || anyToggleSelected) {
                        String generatedPassword = passwordGenerator.generatePassword(
                            passwordLength,
                            uppercaseToggle.isSelected(),
                            lowercaseToggle.isSelected(),
                            numbersToggle.isSelected(),
                            symbolsToggle.isSelected(),
                            customChars
                        );

                        passwordOutput.setText(generatedPassword);
                        addPasswordToHistory(generatedPassword);
                        strengthOutput.setText(PasswordStrengthAnalyzer.getStrength(generatedPassword));
                        timeOutput.setText(PasswordStrengthAnalyzer.getCrackTime(generatedPassword));

                        String strength = PasswordStrengthAnalyzer.getStrength(generatedPassword);
                        Color strengthColor;
                        switch (strength) {
                            case "Very Weak": strengthColor = new Color(255, 0, 0); break; // Bright red for very weak
                            case "Weak": strengthColor = new Color(255, 69, 0); break; // Bright orange for weak
                            case "Reasonable": strengthColor = new Color(255, 255, 0); break; // Bright yellow for reasonable
                            case "Strong": strengthColor = new Color(0, 255, 0); break; // Bright green for strong
                            case "Very Strong": strengthColor = new Color(0, 0, 255); break; // Bright blue for very strong
                            default: strengthColor = Color.BLACK; // Default black for undefined
                        }
                        
                        strengthOutput.setForeground(strengthColor);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, 
                        "Please enter a valid number for password length", 
                        "Invalid Input", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(generateButton);
    }
}
