import java.util.Random;

public class Producer extends Thread {
	SharedFiFoQueue queue;
	final int MINTIME = 250;
	final int MAXTIME = 500;
	final int CHANCEFORCUSTOMER = 75;
	int runFor;


	public Producer(SharedFiFoQueue queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		Random rand = new Random();
		int i = 0;
		while (true) { 
			
			if (rand.nextInt(100) < CHANCEFORCUSTOMER) {
				try {
					i++;
					queue.add(i);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				Thread.sleep(rand.nextInt(MAXTIME - MINTIME + 1) + MINTIME); // Assume a customer comes into the shop
																				// every MINTIME and MAXTIME ( in ms)
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
