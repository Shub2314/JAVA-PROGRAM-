import javax.swing.*;
public class ScrollPaneExample extends JFrame
{
    public ScrollPaneExample() 
    {
        JFrame frame = new JFrame("Scroll Pane Example");
        JTextArea textArea = new JTextArea(20, 30);
        JScrollPane scrollPane = new JScrollPane(textArea);

        textArea.setText("This is some text in the JTextArea. You can scroll here.\n".repeat(10));
        add(scrollPane);

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
      
    public static void main(String[] args) 
    {
        ScrollPaneExample ob = new ScrollPaneExample();
    }
}
