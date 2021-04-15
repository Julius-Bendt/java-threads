package Santana;

import java.util.Random;

public class Santa extends Thread {
	final int MINTIME = 100;
	final int MAXTIME = 150;
	private SantaShop shop;
	private Random random = new Random();

	public Santa(SantaShop shop) {
		this.shop = shop;
	}

	public void run() {
		while (true) {
			try {
				shop.santa();
				Thread.sleep(random.nextInt(MAXTIME - MINTIME + 1) + MINTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
