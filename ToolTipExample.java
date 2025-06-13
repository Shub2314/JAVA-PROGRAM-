import java.awt.HeadlessException;
import javax.swing.*;

public class ToolTipExample extends JFrame
{     

    public ToolTipExample() throws HeadlessException 
    {
        JButton button = new JButton("Click Me");
        button.setToolTipText("This is a button that you can click.");
        
        add(button);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        ToolTipExample ob = new ToolTipExample();
    }
}
