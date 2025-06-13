import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * BookFlightForm
 * --------------
 * Demo Swing form that lets an operator book a flight for a customer.
 * Fields: name, nationality, Aadhaar, address, gender, source, destination,
 *         flight name, flight code, date (YYYY-MM-DD)
 * Buttons:
 *   • Fetch Details   – fills customer fields from a mock customer DB using Aadhaar
 *   • Fetch Flights   – fills flight name & code based on route/date (mock)
 *   • Save Booking    – validates and "saves" (shows confirmation)
 *
 * NOTE: Uses in‑memory HashMaps for demo purposes. Replace with real services/DB.
 */
public class BookFlightForm extends JFrame {

    // === Styling === //
    private static final Color PRIMARY       = new Color(33, 150, 243);
    private static final Color PRIMARY_DARK  = new Color(25, 118, 210);
    private static final Color ERROR_RED     = new Color(211, 47, 47);
    private static final Color SUCCESS_GREEN = new Color(56, 142, 60);
    private static final Font  DEFAULT_FONT  = new Font("Segoe UI", Font.PLAIN, 14);

    // === Mock Databases === //
    private final Map<String, Customer> customerDb = new HashMap<>();
    private final Map<RouteKey, List<Flight>> flightDb = new HashMap<>();

    // === UI Components === //
    private JTextField nameField, nationalityField, aadhaarField, addressField,
                       flightNameField, flightCodeField, dateField;
    private JComboBox<String> genderBox, sourceBox, destinationBox;
    private JButton fetchDetailsBtn, fetchFlightsBtn, saveBtn;
    private JLabel statusLabel;

    public BookFlightForm() {
        super("Book Flight");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 520);
        setLocationRelativeTo(null);
        setResizable(false);

        seedMockData();
        buildUI();
    }

    // ------------------------------------------------------------ //
    //                       Mock seed data                         //
    // ------------------------------------------------------------ //
    private void seedMockData() {
        customerDb.put("123456789012", new Customer("Alice Rao", "Indian", "123456789012",
                "42 MG Road, Bengaluru", "Female", "+919876543210"));
        customerDb.put("999888777666", new Customer("John Smith", "British", "999888777666",
                "221B Baker St, London", "Male", "+447700900111"));

        // Flights: key = (source,dest,date)
        RouteKey key1 = new RouteKey("DEL", "BOM", "2025-07-10");
        flightDb.put(key1, List.of(new Flight("Air India", "AI202"), new Flight("IndiGo", "6E441")));

        RouteKey key2 = new RouteKey("BLR", "DXB", "2025-07-10");
        flightDb.put(key2, List.of(new Flight("Emirates", "EK569")));
    }

    // ------------------------------------------------------------ //
    //                           UI                                 //
    // ------------------------------------------------------------ //
    private void buildUI() {
        JPanel root = new JPanel(new GridBagLayout());
        root.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        int y = 0;

        // Title
        JLabel title = new JLabel("Book Flight", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(PRIMARY_DARK);
        gbc.gridx = 0; gbc.gridy = y++; gbc.gridwidth = 2;
        root.add(title, gbc);
        gbc.gridwidth = 1;

        // Customer section --------------------------------------
        addField(root, gbc, y++, "Name:", nameField = new JTextField());
        addField(root, gbc, y++, "Nationality:", nationalityField = new JTextField());
        addField(root, gbc, y++, "Aadhaar No:", aadhaarField = new JTextField());
        addField(root, gbc, y++, "Address:", addressField = new JTextField());

        gbc.gridx = 0; gbc.gridy = y;
        root.add(buildLabel("Gender:"), gbc);
        genderBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        styleField(genderBox);
        gbc.gridx = 1;
        root.add(genderBox, gbc);
        y++;

        // Fetch Details button under Aadhaar field
        fetchDetailsBtn = new JButton("Fetch Details");
        stylizeButton(fetchDetailsBtn);
        fetchDetailsBtn.addActionListener(e -> fetchCustomer());
        gbc.gridx = 1; gbc.gridy = y++;
        root.add(fetchDetailsBtn, gbc);

        // Flight search section ---------------------------------
        sourceBox = new JComboBox<>(new String[]{"DEL", "BOM", "BLR", "DXB"});
        destinationBox = new JComboBox<>(new String[]{"DEL", "BOM", "BLR", "DXB"});

        addField(root, gbc, y++, "Source:", sourceBox);
        addField(root, gbc, y++, "Destination:", destinationBox);

        addField(root, gbc, y++, "Date (YYYY-MM-DD):", dateField = new JTextField());

        fetchFlightsBtn = new JButton("Fetch Flights");
        stylizeButton(fetchFlightsBtn);
        fetchFlightsBtn.addActionListener(e -> fetchFlights());
        gbc.gridx = 1; gbc.gridy = y++;
        root.add(fetchFlightsBtn, gbc);

        addField(root, gbc, y++, "Flight Name:", flightNameField = new JTextField());
        addField(root, gbc, y++, "Flight Code:", flightCodeField = new JTextField());

        // Save button
        saveBtn = new JButton("Save Booking");
        stylizeButton(saveBtn);
        saveBtn.addActionListener(e -> saveBooking());
        gbc.gridx = 0; gbc.gridy = y++; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        root.add(saveBtn, gbc);

        // Status label
        statusLabel = new JLabel(" ", SwingConstants.CENTER);
        statusLabel.setFont(DEFAULT_FONT);
        gbc.gridy = y; gbc.fill = GridBagConstraints.HORIZONTAL;
        root.add(statusLabel, gbc);

        setContentPane(root);
    }

    // Utility to add a label + component row
    private void addField(JPanel root, GridBagConstraints gbc, int y, String labelText, JComponent field) {
        gbc.gridx = 0; gbc.gridy = y;
        root.add(buildLabel(labelText), gbc);
        styleField(field);
        gbc.gridx = 1;
        root.add(field, gbc);
    }

    // ------------------------------------------------------------ //
    //                         Actions                              //
    // ------------------------------------------------------------ //
    private void fetchCustomer() {
        String aadhaar = aadhaarField.getText().trim();
        Customer c = customerDb.get(aadhaar);
        if (c == null) {
            showStatus("No customer found for Aadhaar.", ERROR_RED);
            return;
        }
        nameField.setText(c.name);
        nationalityField.setText(c.nationality);
        addressField.setText(c.address);
        genderBox.setSelectedItem(c.gender);
        phoneField.setText(c.phone); // phoneField not defined; optional
        showStatus("Customer details fetched.", SUCCESS_GREEN);
    }

    private void fetchFlights() {
        String src = (String) sourceBox.getSelectedItem();
        String dest = (String) destinationBox.getSelectedItem();
        String date = dateField.getText().trim();
        RouteKey key = new RouteKey(src, dest, date);
        List<Flight> flights = flightDb.getOrDefault(key, Collections.emptyList());
        if (flights.isEmpty()) {
            showStatus("No flights found.", ERROR_RED);
            return;
        }
        Flight f = flights.get(0); // pick first for demo
        flightNameField.setText(f.name);
        flightCodeField.setText(f.code);
        showStatus("Flight details fetched.", SUCCESS_GREEN);
    }

    private void saveBooking() {
        if (nameField.getText().trim().isEmpty() || flightCodeField.getText().trim().isEmpty()) {
            showStatus("Please fill mandatory fields and fetch flight.", ERROR_RED);
            return;
        }
        JOptionPane.showMessageDialog(this,
                "Booking saved for " + nameField.getText().trim() + " (" + flightCodeField.getText().trim() + ")",
                "Success", JOptionPane.INFORMATION_MESSAGE);
        showStatus("Booking saved successfully!", SUCCESS_GREEN);
    }

    // ------------------------------------------------------------ //
    //                        Helpers                               //
    // ------------------------------------------------------------ //
    private JLabel buildLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(DEFAULT_FONT);
        l.setForeground(PRIMARY_DARK);
        return l;
    }

    private void styleField(JComponent comp) {
        comp.setFont(DEFAULT_FONT);
        if (comp instanceof JTextField) {
            ((JTextField) comp).setBorder(BorderFactory.createLineBorder(PRIMARY_DARK));
        }
    }

    private void stylizeButton(JButton btn) {
        btn.setBackground(PRIMARY);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(DEFAULT_FONT.deriveFont(Font.BOLD));
    }

    private void showStatus(String msg, Color c) {
        statusLabel.setText(msg);
        statusLabel.setForeground(c);
    }

    // ------------------------------------------------------------ //
    //                      Data Classes                            //
    // ------------------------------------------------------------ //
    private record Customer(String name, String nationality, String aadhaar,
                             String address, String gender, String phone) {}

    private record Flight(String name, String code) {}

    private record RouteKey(String src, String dest, String date) {
        @Override public int hashCode() { return Objects.hash(src, dest, date); }
        @Override public boolean equals(Object o) {
            return o instanceof RouteKey rk && rk.src.equals(src) && rk.dest.equals(dest) && rk.date.equals(date);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BookFlightForm().setVisible(true));
    }
}
