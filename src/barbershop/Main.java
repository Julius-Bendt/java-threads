package barbershop;

public class Main {
    public static void main(String[] args) throws InterruptedException {
	int runs = 100;
	Shop shop = new Shop();
	Consumer barber1 = new Consumer(shop, "Karsten");
	Consumer barber2 = new Consumer(shop, "Istvan2");
	Consumer barber3 = new Consumer(shop, "Istvan3");
	Consumer barber4 = new Consumer(shop, "Istvan4");
	Consumer barber5 = new Consumer(shop, "Istvan5");
	Consumer barber6 = new Consumer(shop, "Istvan6");
	Consumer barber7 = new Consumer(shop, "Istvan7");
	Consumer barber8 = new Consumer(shop, "Istvan8");
	Consumer barber9 = new Consumer(shop, "Istvan9");
	Producer costumer = new Producer(shop, runs);

	barber1.start();
	barber2.start();
	barber3.start();
	barber4.start();
	barber5.start();
	barber6.start();
	barber7.start();
	barber8.start();
	barber9.start();
	costumer.start();

	barber1.join();
	barber2.join();
	barber3.join();
	barber4.join();
	barber5.join();
	barber6.join();
	barber7.join();
	barber8.join();
	barber9.join();
	costumer.join();
	int fails = runs - shop.succes;
	System.out.println("Succes/Fails: " + shop.succes + "/" + fails);
	System.out.println("Main is complete.");
    }
}
