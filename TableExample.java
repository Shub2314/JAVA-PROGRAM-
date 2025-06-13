
import javax.swing.*;
public class TableExample extends JFrame
{
    public TableExample() 
    {
        String[] columnNames = {"Name", "Age", "City"};
        Object[][] data = {
                                {"John", 25, "New York"},
                                {"Anna", 30, "London"},
                                {"Mike", 35, "San Francisco"}
                          };

        JTable table = new JTable(data, columnNames);
        add(new JScrollPane(table));
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    public static void main(String[] args) 
    {
        TableExample ob = new  TableExample();
    }
}
