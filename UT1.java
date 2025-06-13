import  java.lang.*;
import java.util.*;

class NoMatchException extends Exception
{
    public NoMatchException(String s) 
    {
        super(s);
    }
}

public class UT1 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        String pass="@shub";
        System.out.println("enter the pass :");
        String str=sc.nextLine();

        try 
        {
            if(str!=pass)
            {
                throw new NoMatchException("Error");
            }
            else
            {
                System.out.println("done..");
            }
        } 
        catch (NoMatchException e) 
        {
                System.out.println(e.getMessage());
        }
    }
}
