package Santana;

import java.util.Random;

public class Elves extends Thread {
	final int MINTIME = 50;
	final int MAXTIME = 70;
	private Random random = new Random();
	private SantaShop shop;
	private Integer elves;

	public Elves(SantaShop shop, Integer elves) {
		this.shop = shop;
		this.elves = elves;
	}

	public void run() {
		int i = 1;
		while (i < elves + 1) {
			System.out.println("Elf: " + i);
			shop.addElf(i);
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
