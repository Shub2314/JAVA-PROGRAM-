import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MouseListenerSwingExample extends JFrame implements MouseListener {
    private JLabel label;

    public MouseListenerSwingExample() {
        setTitle("MouseListener Example");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        
        label = new JLabel("Click anywhere in the window");
        add(label);
        
        addMouseListener(this);
        
        setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        label.setText("Mouse Clicked at X: " + e.getX() + " Y: " + e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        label.setText("Mouse Pressed at X: " + e.getX() + " Y: " + e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        label.setText("Mouse Released at X: " + e.getX() + " Y: " + e.getY());
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label.setText("Mouse Entered the Window");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        label.setText("Mouse Exited the Window");
    }

    public static void main(String[] args) {
        new MouseListenerSwingExample();
    }
}
