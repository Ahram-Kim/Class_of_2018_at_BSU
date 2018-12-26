
import java.util.*;

public class FileAccess
{
    private int max;
    private int sum;

    public FileAccess(int mas)
    {
        this.max = max;
        this.sum = 0;
    }

    public synchronized void startAccess(long id)
    {
        while(sum + id >= max)
        {
            try 
            {
                wait();
            } 
            catch (InterruptedException e) 
            {
                System.err.println(e);
            }
        }
        sum += id;
    }

    public synchronized void endAccess(long id)
    {
        sum -= id;
        notifyAll();
    }

}