import java.awt.*;
import java.awt.event.*;
public class KeyListenerAWTExample extends Frame implements KeyListener {
    private TextField textField;
    private Label label;
    public KeyListenerAWTExample() {
        label = new Label("Type something:");
        label.setBounds(50, 50, 200, 30);
        textField = new TextField();
        textField.setBounds(50, 100, 200, 30);
        textField.addKeyListener(this);
        add(label);
        add(textField);
        setSize(400, 250);
        setLayout(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
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
        new KeyListenerAWTExample();
    }
}