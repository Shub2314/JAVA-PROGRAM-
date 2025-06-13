import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class Demo extends JFrame implements ActionListener {
    JLabel l1, l2;
    JTextField T;
    JTextArea T2;
    public Demo() 
    {
        l1 = new JLabel("Name");
        l2 = new JLabel("Comments");

        l1.setBounds(50, 50, 100, 30);
        l2.setBounds(200, 50, 100, 30);

        T = new JTextField();
        T.setBounds(50, 100, 120, 30);

        T2 = new JTextArea();
        JScrollPane S = new JScrollPane(T2);
        S.setBounds(200, 100, 150, 100);

        JButton b = new JButton("SUBMIT");
        b.setBounds(150, 250, 100, 30);
        b.addActionListener(this);

        setLayout(null);
        add(l1);
        add(l2);
        add(T);
        add(S);
        add(b);
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        @Override
         public void actionPerformed(ActionEvent e) 
         {
              String name = T.getText();
              String comments = T2.getText();
              JOptionPane.showMessageDialog(this, "Name: " + name + "\nComments: " + comments);
          }
    public static void main(String[] args) 
    {
        new Demo();
    }
}
