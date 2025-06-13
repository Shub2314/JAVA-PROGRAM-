
import java.awt.*;
public class Checkbox_Demo extends Frame
{
    public Checkbox_Demo()  
    {      
        Checkbox c1=new Checkbox("java",true);
        Checkbox c2=new Checkbox("c++",false);
        c1.setBounds(50, 50,200,30);
        c2.setBounds(50,100,150,30);
        add(c1);
        add(c2);
        setSize(300,300);
        setVisible(true);
    }
    public static void main(String[] args) 
    {
        Checkbox_Demo ob=new Checkbox_Demo();
    }
}
