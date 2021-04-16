package barbershop;

import java.util.Random;

public class Producer extends Thread {
    final int MINTIME = 100;
    final int MAXTIME = 150;
    private Shop shop;
    private Random random = new Random();
    private int runs;

    public Producer(Shop shop, int runs) {
	this.shop = shop;
	this.runs = runs;
    }

    public void run() {
	int i = 0;
	while (i < runs) {
	    shop.add(i);
	    i++;
	    try {
		Thread.sleep(random.nextInt(MAXTIME - MINTIME + 1) + MINTIME);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	shop.open = false;

    }
}
