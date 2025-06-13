import javax.swing.*;
import javax.swing.event.*;

public class TextListenerSwingExample extends JFrame {
    private JTextField textField;
    private JLabel label;

    public TextListenerSwingExample() {
        // Create components
        label = new JLabel("Type something:");
        label.setBounds(50, 20, 200, 30);
        
        textField = new JTextField();
        textField.setBounds(50, 50, 200, 30);
        
        JLabel outputLabel = new JLabel("Text will appear here");
        outputLabel.setBounds(50, 90, 300, 30);
        
        // Add DocumentListener to JTextField
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                outputLabel.setText("You typed: " + textField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                outputLabel.setText("You typed: " + textField.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not needed for plain text components
            }
        });

        // Set up JFrame properties
        add(label);
        add(textField);
        add(outputLabel);
        setSize(400, 200);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TextListenerSwingExample();
    }
}