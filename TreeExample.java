import javax.swing.*;
import javax.swing.tree.*;
public class TreeExample extends JFrame
{
    public TreeExample() 
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
        DefaultMutableTreeNode child2 = new DefaultMutableTreeNode("Child 2");
        root.add(child1);
        root.add(child2);
        JTree tree = new JTree(root);
        add(new JScrollPane(tree));
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) 
    {
        TreeExample ob = new TreeExample();
    }
}
