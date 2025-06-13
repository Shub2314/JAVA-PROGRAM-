import javax.swing.*;
public class Jscrollpane_demo extends JFrame
{
    public Jscrollpane_demo()
    {
        JTextArea t=new JTextArea(10,10);
        t.setText("ahjggsgjhgsg .\n".repeat(10));
        JScrollPane s= new JScrollPane(t);
        add(s);
        setSize(100,110);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public static void main(String[] args) 
    {
        new Jscrollpane_demo();
    }
}
