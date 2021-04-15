package Santana;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		SantaShop shop = new SantaShop();
		Thread deers = new RainDeers(shop, 2);
		Thread elves = new Elves(shop, 30);
		Thread santa = new Santa(shop);

		deers.start();
		elves.start();
		santa.start();

		deers.join();
		elves.join();
		santa.join();

	}

}
