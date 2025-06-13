import javax.swing.*;
public class TabbedPaneExample extends JFrame
{    
    public TabbedPaneExample() 
    {
        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Content of Tab 1"));
        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Content of Tab 2"));

        tabbedPane.addTab("Tab 1", panel1);
        tabbedPane.addTab("Tab 2", panel2);

        add(tabbedPane);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) 
    {
        TabbedPaneExample ob =new  TabbedPaneExample();
    }
}
