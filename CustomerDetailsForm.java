import java.awt.*;
import javax.swing.*;

public class CustomerDetailsForm extends JFrame {

    private static final Color PRIMARY_DARK  = new Color(25, 118, 210);
    private static final Color ERROR_RED     = new Color(211, 47, 47);
    private static final Color SUCCESS_GREEN = new Color(56, 142, 60);
    private static final Font  DEFAULT_FONT  = new Font("Segoe UI", Font.PLAIN, 14);

    private JTextField nameField, nationalityField, aadhaarField, addressField, phoneField;
    private JComboBox<String> genderBox;
    private JButton saveButton;
    private JLabel statusLabel;

    public CustomerDetailsForm() {
        super("Add Customer Details");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        buildUI();
    }

    private void buildUI() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // *** NEW TITLE LABEL ***
        JLabel titleLabel = new JLabel("Add Customer Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));  // Bigger and bold
        titleLabel.setForeground(PRIMARY_DARK);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        root.add(titleLabel, gbc);
        gbc.gridwidth = 1;  // reset gridwidth

        // Name
        gbc.gridx = 0; gbc.gridy = 1;
        root.add(buildLabel("Name:"), gbc);
        nameField = new JTextField();
        styleField(nameField);
        gbc.gridx = 1;
        root.add(nameField, gbc);

        // Nationality
        gbc.gridx = 0; gbc.gridy++;
        root.add(buildLabel("Nationality:"), gbc);
        nationalityField = new JTextField();
        styleField(nationalityField);
        gbc.gridx = 1;
        root.add(nationalityField, gbc);

        // Aadhaar No
        gbc.gridx = 0; gbc.gridy++;
        root.add(buildLabel("Aadhaar No:"), gbc);
        aadhaarField = new JTextField();
        styleField(aadhaarField);
        gbc.gridx = 1;
        root.add(aadhaarField, gbc);

        // Address
        gbc.gridx = 0; gbc.gridy++;
        root.add(buildLabel("Address:"), gbc);
        addressField = new JTextField();
        styleField(addressField);
        gbc.gridx = 1;
        root.add(addressField, gbc);

        // Gender
        gbc.gridx = 0; gbc.gridy++;
        root.add(buildLabel("Gender:"), gbc);
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        styleField(genderBox);
        gbc.gridx = 1;
        root.add(genderBox, gbc);

        // Phone No
        gbc.gridx = 0; gbc.gridy++;
        root.add(buildLabel("Phone No:"), gbc);
        phoneField = new JTextField();
        styleField(phoneField);
        gbc.gridx = 1;
        root.add(phoneField, gbc);

        // Save Button
        saveButton = new JButton("Save");
        saveButton.setBackground(PRIMARY_DARK);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorderPainted(false);
        saveButton.setFont(DEFAULT_FONT.deriveFont(Font.BOLD));
        saveButton.addActionListener(e -> saveCustomer());

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2; gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        root.add(saveButton, gbc);

        // Status Label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(DEFAULT_FONT);
        gbc.gridy++; gbc.fill = GridBagConstraints.HORIZONTAL;
        root.add(statusLabel, gbc);

        setContentPane(root);
    }

    private JLabel buildLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(DEFAULT_FONT);
        label.setForeground(PRIMARY_DARK);
        return label;
    }

    private void styleField(JComponent comp) {
        comp.setFont(DEFAULT_FONT);
        if (comp instanceof JTextField) {
            ((JTextField) comp).setBorder(BorderFactory.createLineBorder(PRIMARY_DARK));
        }
    }

    private void saveCustomer() {
        String name = nameField.getText().trim();
        String nationality = nationalityField.getText().trim();
        String aadhaar = aadhaarField.getText().trim();
        String address = addressField.getText().trim();
        String gender = (String) genderBox.getSelectedItem();
        String phone = phoneField.getText().trim();

        if (name.isEmpty() || nationality.isEmpty() || aadhaar.isEmpty() || address.isEmpty() || phone.isEmpty()) {
            showStatus("Please fill in all fields.", ERROR_RED);
            return;
        }

        if (aadhaar.length() != 12 || !aadhaar.matches("\\d+")) {
            showStatus("Aadhaar number must be 12 digits.", ERROR_RED);
            return;
        }

        if (!phone.matches("\\d{10}")) {
            showStatus("Phone number must be 10 digits.", ERROR_RED);
            return;
        }

        showStatus("Customer details saved successfully!", SUCCESS_GREEN);

        JOptionPane.showMessageDialog(this,
                "Customer " + name + " saved successfully!",
                "Success", JOptionPane.INFORMATION_MESSAGE);

        clearFields();
    }

    private void showStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

    private void clearFields() {
        nameField.setText("");
        nationalityField.setText("");
        aadhaarField.setText("");
        addressField.setText("");
        phoneField.setText("");
        genderBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerDetailsForm().setVisible(true));
    }
}
