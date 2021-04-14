
public class Main {

	public static void main(String[] args) throws InterruptedException {
		SharedFiFoQueue queue = new SharedFiFoQueue(5); // Max queue size

		Producer producer = new Producer(queue);
		Barber consumer = new Barber(queue);

		producer.start();
		consumer.start();

		producer.join();
		consumer.join();
		System.out.println(queue.success);
		System.out.println("Terminated");
	}

}
