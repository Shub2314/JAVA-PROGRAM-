import java.awt.*;
import java.awt.event.*;
public class MouseListenerAWTExample extends Frame implements MouseListener {
    private Label label;
    public MouseListenerAWTExample() {
        label = new Label("Click anywhere inside the window");
        label.setBounds(50, 50, 300, 30);
        addMouseListener(this);
        add(label);
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
    public void mouseClicked(MouseEvent e) {
        label.setText("Mouse Clicked ");
    }
    @Override
    public void mousePressed(MouseEvent e) {
        label.setText("Mouse Pressed");
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        label.setText("Mouse Released");
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        label.setText("Mouse Entered Window");
    }
    @Override
    public void mouseExited(MouseEvent e) {
        label.setText("Mouse Exited Window");
    }
    public static void main(String[] args) {
        new MouseListenerAWTExample();
    }
}