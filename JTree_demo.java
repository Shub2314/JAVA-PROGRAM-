
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
public class JTree_demo extends JFrame
{
    public JTree_demo() 
    {
        setSize(300,300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode r= new DefaultMutableTreeNode("root");
        DefaultMutableTreeNode ch1= new DefaultMutableTreeNode("child1");
        DefaultMutableTreeNode ch2= new DefaultMutableTreeNode("child2");

        r.add(ch1);
        r.add(ch2);

        JTree t=new JTree(r);
        add(t);
    }
    public static void main(String[] args) {
        JTree_demo ob =new JTree_demo();
    }
    
}
