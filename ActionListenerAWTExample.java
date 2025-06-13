import java.awt.*;
import java.awt.event.*;
public class ActionListenerAWTExample extends Frame implements ActionListener {
     TextField textField;
     Button button;
     Label label;
    public ActionListenerAWTExample() {
        // Create components
        label = new Label("Enter text and click the button:");
        label.setBounds(50, 50, 250, 30);
        textField = new TextField();
        textField.setBounds(50, 100, 200, 30);
        button = new Button("Submit");
        button.setBounds(50, 150, 80, 30);
        // Add ActionListener to Button
        button.addActionListener(this);
        // Set up Frame properties
        add(label);
        add(textField);
        add(button);
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
    public void actionPerformed(ActionEvent e) {
        label.setText("You entered: " + textField.getText());
    }
    public static void main(String[] args) {
        new ActionListenerAWTExample();
    }
}