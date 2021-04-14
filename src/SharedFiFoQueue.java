import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {
	private Integer[] queue = null;

	private int posW = 0; //Write position
	private int posR = 0; //Read position

	private Lock lock = new ReentrantLock();
	private Condition isSleeping = lock.newCondition();


	public int getQueueLength() {
		return queue.length;
	}

	public SharedFiFoQueue(int size) {
		queue = new Integer[size];
	}

	public void add(Integer person) throws InterruptedException {
		lock.lock();
		if (queue[posW] == null) {
			System.out.println("Adding " + person);
			queue[posW] = person;
			posW = (posW + 1) % queue.length;
			isSleeping.signal();
		} else {
			System.out.println("Full - customer leaving (" + person + ")");

		}
		printQueue("add");
		lock.unlock();
	}

	public Integer remove() throws InterruptedException {
		Integer customer = queue[posR];
		
			lock.lock();
			if (customer == null) {
				System.out.println("Barber is now sleeping 1");
				isSleeping.await();
			} else {

				System.out.println("Barber finished cutting " + customer);
				queue[posR] = null;
				posR = (posR + 1) % queue.length;
				if (posW == posR) {
					System.out.println("Barber is now sleeping 2");
					isSleeping.await();

				}
				printQueue("remove");
				lock.unlock();
			}

		

		return customer;
	}

	private void printQueue(String from) {
		String print = "{ ";
		for (Integer i : queue) {
			print += i + ", ";
		}
		System.out.println(from + ": " + print + " }");
	}
}
