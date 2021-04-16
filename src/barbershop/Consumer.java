package barbershop;

import java.util.Random;

public class Consumer extends Thread {
    final int MINTIME = 300;
    final int MAXTIME = 600;
    private Shop shop;
    private Random random = new Random();
    public String name;

    public Consumer(Shop shop, String name) {
	this.shop = shop;
	this.name = name;
    }

    public void run() {
	try {
	    while (true) {
		Integer person = shop.remove(this.name);
		if (person == null && shop.open == false) {
		    System.out.println(name + ": Went home for today");
		    break;
		}
		System.out.println(this.name + " finished cutting: " + person);
		if (person != null) {
		    shop.succes++;
		}
		Thread.sleep(random.nextInt(MAXTIME - MINTIME + 1) + MINTIME);
	    }
	} catch (InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }
}
