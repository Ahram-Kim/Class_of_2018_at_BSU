/**
 *
 *
 */
public class TestFileAccess 
{ 
    public static void main(String[] args)
    {
        if(args.length < 3)
        {
            System.err.println("Usage: java TestFileAccess <number of threads> <max sum> <iterations>\n");
            System.exit(1);
        }

        int numOfThreads = Integer.parseInt(args[0]);
        int maxSum = Integer.parseInt(args[1]);
        int iterations = Integer.parseInt(args[2]);

        FileAccess monitor = new FileAccess(maxSum); 
        FileAccessThread threads[] = new FileAccessThread[numOfThreads];

        for(int i = 0; i < numOfThreads; i++)
        {
            threads[i] = new FileAccessThread(i + 1, iterations, monitor);
            threads[i].start();
            System.out.println("Thread: " + (i + 1) + " starting file access.");
        }

        for(int i = 0; i < numOfThreads; i++)
        {
            try 
            {
                System.out.println("Thread: " + (i + 1) + " ending file access.");
                threads[i].join(30000);
            }
            catch(InterruptedException e)
            {
                System.err.println(e);
            }
        }

        for(int i = 0; i < numOfThreads; i++)
        {
            if(threads[i].isAlive())
            {
                System.out.println("Aieeeee...received an alarm. Timeout!");
                System.exit(0);
                // threads[i].interrupt();
            }
        }
    }
}