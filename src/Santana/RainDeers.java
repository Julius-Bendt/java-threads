package Santana;

import java.util.Random;

public class RainDeers extends Thread {
	final int MINTIME = 100;
	final int MAXTIME = 150;
	private SantaShop shop;
	private int deers;
	private Random random = new Random();

	public RainDeers(SantaShop shop, int deers) {
		this.shop = shop;
		this.deers = deers;
	}

	public void run() {
		int i = 0;
		while (i < deers) {
			System.out.println("deer: " + i + " arrived ");
			shop.arrive(i);
			i++;
			try {
				Thread.sleep(random.nextInt(MAXTIME - MINTIME + 1) + MINTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
