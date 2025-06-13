import java.awt.*;
public class Button_Demo extends Frame
{
    public Button_Demo()  
    {      
        Button b= new Button("click me");
        Label l=new Label("shubham");
        l.setBounds(20,90 ,150,10);
        add(l);
        add(b);
        setSize(300,300);
        setVisible(true);

    }
    public static void main(String[] args) {
        Button_Demo ob=new Button_Demo();
    }
    
}
