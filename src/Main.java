
public class Main {
	public static void main(String[] args) throws InterruptedException {
		int runs = 10;
		Shop shop = new Shop();
		Consumer barber = new Consumer(shop, "Karsten");
//		Consumer barber2 = new Consumer(shop, "Istvan");
//		Consumer barber3 = new Consumer(shop, "Istvan2");
//		Consumer barber4 = new Consumer(shop, "Istvan3");
		Producer costumer = new Producer(shop, runs);

		barber.start();
//		barber2.start();
//		barber3.start();
//		barber4.start();
		costumer.start();

		barber.join();
//		barber2.join();
//		barber3.join();
//		barber4.join();
		costumer.join();
		int fails = runs - shop.succes;
		System.out.println("Succes/Fails: " + shop.succes + "/" + fails);
	}
}
