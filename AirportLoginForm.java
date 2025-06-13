import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

/**
 * Airport Staff Login Form (Enhanced + Forgot‑Password & Register + "User" role)
 * ------------------------------------------------------------------------------
 * • Flexible GridBagLayout for responsive design
 * • Consistent colour palette & typography
 * • Separation of concerns (UI vs. logic)
 * • Mock database (replace with real DB in production)
 * • "Forgot Password?" helper
 * • "Register" dialog to create a new user in the mock DB
 * • Added generic "User" role & demo account (user@airport.com / user123)
 */
public class AirportLoginForm extends JFrame {

    // === Colour Palette === //
    private static final Color PRIMARY       = new Color(33, 150, 243);   // Blue 500
    private static final Color PRIMARY_DARK  = new Color(25, 118, 210);   // Blue 700
    private static final Color ACCENT        = new Color(255, 202, 40);   // Amber 400
    private static final Color ERROR_RED     = new Color(211, 47, 47);    // Red 700
    private static final Color SUCCESS_GREEN = new Color(56, 142, 60);    // Green 700
    private static final Font  DEFAULT_FONT  = new Font("Segoe UI", Font.PLAIN, 14);

    // === UI Components === //
    private JComboBox<String> roleBox;
    private JTextField        emailField;
    private JPasswordField    passwordField;
    private JButton           loginButton;
    private JLabel            statusLabel;
    private JLabel            forgotLabel;
    private JLabel            registerLabel;

    // === Data === //
    private final HashMap<String, String> mockDb = new HashMap<>();

    public AirportLoginForm() {
        super("Airport Staff Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(460, 380);
        setLocationRelativeTo(null);
        setResizable(false);

        seedMockDb();
        buildUI();
    }

    // ------------------------------------------------------------ //
    //                   Mock Database (demo only)                  //
    // ------------------------------------------------------------ //
    private void seedMockDb() {
        mockDb.put("admin@airport.com",    "admin123");
        mockDb.put("staff@airport.com",    "staff123");
        mockDb.put("security@airport.com", "securepass");
        mockDb.put("user@airport.com",     "user123");   // demo user
    }

    // ------------------------------------------------------------ //
    //                             UI                               //
    // ------------------------------------------------------------ //
    private void buildUI() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Title
        JLabel title = new JLabel("Airport Staff Portal", SwingConstants.CENTER);
        title.setFont(DEFAULT_FONT.deriveFont(Font.BOLD, 20f));
        title.setForeground(PRIMARY_DARK);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        root.add(title, gbc);

        // Role selector
        gbc.gridwidth = 1; gbc.gridy++;
        root.add(buildLabel("Select Role:"), gbc);
        roleBox = new JComboBox<>(new String[]{
                "Admin", "Airline Staff", "Security", "Check-in Agent", "Ground Crew", "User"});
        styleField(roleBox);
        gbc.gridx = 1;
        root.add(roleBox, gbc);

        // Email field
        gbc.gridx = 0; gbc.gridy++;
        root.add(buildLabel("Email:"), gbc);
        emailField = new JTextField();
        styleField(emailField);
        gbc.gridx = 1;
        root.add(emailField, gbc);

        // Password field
        gbc.gridx = 0; gbc.gridy++;
        root.add(buildLabel("Password:"), gbc);
        passwordField = new JPasswordField();
        styleField(passwordField);
        gbc.gridx = 1;
        root.add(passwordField, gbc);

        // Links panel
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
        linkPanel.setOpaque(false);

        forgotLabel = makeLink("Forgot Password?");
        forgotLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { handleForgotPassword(); }
        });
        linkPanel.add(forgotLabel);

        registerLabel = makeLink("Register");
        registerLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) { new RegistrationDialog().setVisible(true); }
        });
        linkPanel.add(registerLabel);

        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 2;
        root.add(linkPanel, gbc);

        // Login button
        loginButton = new JButton("Log In");
        loginButton.setBackground(PRIMARY);
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setBorderPainted(false);
        loginButton.setFont(DEFAULT_FONT.deriveFont(Font.BOLD));
        loginButton.addActionListener(new LoginHandler());

        gbc.gridy++; gbc.fill = GridBagConstraints.NONE;
        root.add(loginButton, gbc);

        // Status label
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

    private JLabel makeLink(String text) {
        JLabel link = new JLabel("<HTML><U>" + text + "</U></HTML>");
        link.setFont(DEFAULT_FONT.deriveFont(Font.PLAIN, 12f));
        link.setForeground(PRIMARY_DARK);
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return link;
    }

    // ------------------------------------------------------------ //
    //                          Logic                               //
    // ------------------------------------------------------------ //
    private class LoginHandler implements ActionListener {
        @Override public void actionPerformed(ActionEvent e) {
            String role = (String) roleBox.getSelectedItem();
            String email = emailField.getText().trim();
            String password = String.valueOf(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                showStatus("Please fill in all fields.", ERROR_RED);
                return;
            }

            if (mockDb.containsKey(email) && mockDb.get(email).equals(password)) {
                showStatus("Login successful! Role: " + role, SUCCESS_GREEN);
                JOptionPane.showMessageDialog(AirportLoginForm.this,
                        "Welcome, " + role + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                showStatus("Invalid email or password.", ERROR_RED);
            }
        }
    }

    private void handleForgotPassword() {
        String email = JOptionPane.showInputDialog(this, "Enter your email:",
                "Password Recovery", JOptionPane.QUESTION_MESSAGE);
        if (email == null) return; // user cancelled

        email = email.trim();
        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email cannot be empty.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (mockDb.containsKey(email)) {
            JOptionPane.showMessageDialog(this,
                    "Your temporary password is: " + mockDb.get(email) +
                            "\nPlease change it after login.",
                    "Password Sent", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Email not found.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void showStatus(String message, Color color) {
        statusLabel.setText(message);
        statusLabel.setForeground(color);
    }

   // ... (All your existing import statements and class definitions remain unchanged)

    // ------------------------------------------------------------ //
    //                    Registration Dialog                       //
    // ------------------------------------------------------------ //
    private class RegistrationDialog extends JDialog {
        private final JTextField     emailReg    = new JTextField(20);
        private final JPasswordField passReg     = new JPasswordField(20);
        private final JPasswordField passConfirm = new JPasswordField(20);

        RegistrationDialog() {
            super(AirportLoginForm.this, "Register New User", true);
            setSize(360, 260);
            setLocationRelativeTo(AirportLoginForm.this);
            buildUI();
        }

        private void buildUI() {
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            GridBagConstraints c = new GridBagConstraints();
            c.insets = new Insets(5, 5, 5, 5);
            c.fill = GridBagConstraints.HORIZONTAL;

            c.gridx = 0; c.gridy = 0; panel.add(new JLabel("Email:"), c);
            c.gridx = 1; panel.add(emailReg, c);
            c.gridx = 0; c.gridy++; panel.add(new JLabel("Password:"), c);
            c.gridx = 1; panel.add(passReg, c);
            c.gridx = 0; c.gridy++; panel.add(new JLabel("Confirm:"), c);
            c.gridx = 1; panel.add(passConfirm, c);

            JButton registerBtn = new JButton("Register");
            registerBtn.addActionListener(e -> register());
            c.gridx = 0; c.gridy++; c.gridwidth = 2; c.anchor = GridBagConstraints.CENTER;
            panel.add(registerBtn, c);

            setContentPane(panel);
        }

        private void register() {
            String email = emailReg.getText().trim();
            String pass  = String.valueOf(passReg.getPassword()).trim();
            String conf  = String.valueOf(passConfirm.getPassword()).trim();

            if (email.isEmpty() || pass.isEmpty() || conf.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!pass.equals(conf)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (mockDb.containsKey(email)) {
                JOptionPane.showMessageDialog(this, "Email is already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            mockDb.put(email, pass);
            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // close the dialog
        }
    }

    // ------------------------------------------------------------ //
    //                        Main Method                           //
    // ------------------------------------------------------------ //
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AirportLoginForm().setVisible(true));
    }
}
