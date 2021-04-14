
public class Main {
	final static int RUNFOR = 30; //How many "cycles" the program should run
	public static void main(String[] args) throws InterruptedException {
		SharedFiFoQueue queue = new SharedFiFoQueue(5, RUNFOR); // Max queue size
		
		

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
