package Santana;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class RainDeers extends Thread {
	final int MINTIME = 100;
	final int MAXTIME = 150;
	private SantaShop shop;
	private int deers;
	private Random random = new Random();
	private Queue<String> deer;

	public RainDeers(SantaShop shop, int rounds) {
		this.shop = shop;
		this.deers = rounds * 9;
		deer = new ArrayDeque<>();
		makeDeer();
	}

	public void run() {
		int i = 1;
		while (i < deers + 1) {
			String rd = deer.poll();
			System.out.println("deer: " + rd + " arrived ");
			shop.arrive(rd);
			i++;
			if (i % 9 == 0)
				makeDeer();
			try {
				Thread.sleep(random.nextInt(MAXTIME - MINTIME + 1) + MINTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void makeDeer() {
		deer.add("Karsten");
		deer.add("Rudolf");
		deer.add("Rensdyr");
		deer.add("Thorbjørn");
		deer.add("Bjørn");
		deer.add("Katrine");
		deer.add("Kirstine");
		deer.add("Phillipo");
		deer.add("Navn");
	}

}
