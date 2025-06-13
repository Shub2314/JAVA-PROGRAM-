import javax.swing.*;
import java.awt.event.*;

public class MouseMotionListenerSwingExample extends JFrame implements MouseMotionListener {
    private JLabel label;

    public MouseMotionListenerSwingExample() {
        // Create a JLabel
        label = new JLabel("Move the mouse inside the window", SwingConstants.CENTER);
        label.setBounds(50, 50, 300, 30);
        
        // Add MouseMotionListener to the JFrame
        addMouseMotionListener(this);
        
        // Set up JFrame properties
        add(label);
        setSize(400, 300);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        label.setText("Mouse Dragged at");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        label.setText("Mouse Moved at: ");
    }

    public static void main(String[] args) {
        new MouseMotionListenerSwingExample();
    }
}
