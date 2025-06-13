import javax.swing.*;
import java.awt.event.*;

public class KeyListenerSwingExample extends JFrame implements KeyListener {
    private JTextField textField;
    private JLabel label;

    public KeyListenerSwingExample() {
        // Create components
        label = new JLabel("Type something:");
        label.setBounds(50, 50, 200, 30);

        textField = new JTextField();
        textField.setBounds(50, 100, 200, 30);

        // Add KeyListener to TextField
        textField.addKeyListener(this);

        // Set up JFrame properties
        add(label);
        add(textField);
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        label.setText("Key Typed: " + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {
        label.setText("Key Pressed: " + e.getKeyChar());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        label.setText("Key Released: " + e.getKeyChar());
    }

    public static void main(String[] args) {
        new KeyListenerSwingExample();
    }
}