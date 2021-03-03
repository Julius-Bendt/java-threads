import java.util.Random;

public class Producer extends Thread {
	
	SharedFiFoQueue queue;
	
	final int MINTIME = 200;
	final int MAXTIME = 300;
	
	
	public Producer(SharedFiFoQueue queue)
	{
		this.queue = queue;
	}
	
	
	public void run()
	{
		Random rand = new Random();
		int i = 0;
		while(true)
		{
			i++;
			if(rand.nextInt(100) > 50)
			{
				try {
					queue.add(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				
				Thread.sleep(rand.nextInt(MAXTIME - MINTIME + 1) + MINTIME); //Assume a customer comes into the shop every MINTIME and MAXTIME  ( in ms)
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
