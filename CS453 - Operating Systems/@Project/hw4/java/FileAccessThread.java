
import java.util.*;
import java.text.*;

public class FileAccessThread extends Thread
{
    private final int RANGE = 5;
    
    private int id;
    private int iterations; 

    private FileAccess monitor;
    private Random generator = new Random();

    public FileAccessThread(int id, int iterations, FileAccess monitor)
    {
        this.id = id;
        this.iterations = iterations;
        this.monitor = monitor;
        generator = new Random();
    }

    public void run()
    {
        for(int i = 0; i < iterations; i++)
        {
            try
            {
                monitor.startAccess(id);
                Thread.sleep(sleepTime());
                monitor.endAccess(id);
                Thread.sleep(sleepTime());
            }
            catch(InterruptedException e)
            {
                System.err.println(e);
            }
        }
    }

    private int sleepTime()
    {
        return (generator.nextInt(RANGE) + 1) * 100;
    }
}