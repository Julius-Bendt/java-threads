import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {
	private Integer[] queue = null;

	public int success = 0;
	public int fails = 0;

	private int posW = 0;
	private int posR = 0;

	private Lock lock = new ReentrantLock();
	private Condition isSleeping = lock.newCondition();
	private Condition isQueueFullCondition = lock.newCondition();

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
			fails++;
			isQueueFullCondition.await();
		}
		printQueue("add");
		lock.unlock();

	}

//TODO write counter for succesrate
	public Integer remove() throws InterruptedException {
		lock.lock();
		// System.out.println("posW: " + posW + " posR: " + posR );

		Integer customer = queue[posR];
		if (customer == null) {
			System.out.println("Barber is now sleeping 1");
			isSleeping.await();

		} else {
			System.out.println("Barber finished cutting " + customer);
			queue[posR] = null;
			posR = (posR + 1) % queue.length;

			isQueueFullCondition.signal();
			if (posW == posR) {
				System.out.println("Barber is now sleeping 2");
				isSleeping.await();
			}

			printQueue("remove");
			success++;
			lock.unlock();

		}
		return customer;
	}

	private void printQueue(String from) {
		String print = "{ ";
		for (Integer i : queue) {
			print += i;
			print += ", ";
		}
		System.out.println(from + ": " + print + " }");
	}
}
