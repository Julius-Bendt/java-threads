import java.util.Random;

public class Barber extends Thread { // BUt actually consumer
	SharedFiFoQueue queue = null;
	final int MINTIME = 100;
	final int MAXTIME = 300;
	private int i = 0;


	public Barber(SharedFiFoQueue queue) {
		this.queue = queue;
	}

	public void run() {
		Random rand = new Random();
		try {
			while (true) {
				// Find next customer, and remove them from the queue
				Thread.sleep(rand.nextInt(MAXTIME - MINTIME + 1) + MINTIME); // Assume a customer comes into the shop
																				// every MINTIME and MAXTIME ( in ms)
				Integer customer = queue.remove();
				i++;
			}
		} catch (Exception e) {
			System.out.println("STACKTRACE: ");
			e.printStackTrace();
		}

		System.out.println("Barber done barbering");
	}
}
