import java.awt.*;
import java.awt.event.*;

public class MouseMotionListenerAWTExample extends Frame implements MouseMotionListener {
    private Label label;

    public MouseMotionListenerAWTExample() {
        // Create a Label
        label = new Label("Move the mouse inside the window", Label.CENTER);
        label.setBounds(50, 50, 300, 30);
        
        // Add MouseMotionListener to the Frame
        addMouseMotionListener(this);
        
        // Set up Frame properties
        add(label);
        setSize(400, 300);
        setLayout(null);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        label.setText("Mouse Dragged ");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        label.setText("Mouse Moved ");
    }

    public static void main(String[] args) {
        new MouseMotionListenerAWTExample();
    }
}