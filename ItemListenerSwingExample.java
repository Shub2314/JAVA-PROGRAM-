import javax.swing.*;
import java.awt.event.*;
public class ItemListenerSwingExample extends JFrame implements ItemListener {
    private JCheckBox checkBox;
    private JLabel label;
    public ItemListenerSwingExample() {
        label = new JLabel("Check the box:");
        label.setBounds(50, 50, 200, 30);
        checkBox = new JCheckBox("Accept Terms");
        checkBox.setBounds(50, 100, 150, 30);
        checkBox.addItemListener(this);
        add(label);
        add(checkBox);
        setSize(400, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (checkBox.isSelected()) {
            label.setText("Checkbox is Checked");
        } else {
            label.setText("Checkbox is Unchecked");
        }
    }
    public static void main(String[] args) {
        new ItemListenerSwingExample();
    }
}