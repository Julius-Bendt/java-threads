import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SharedFiFoQueue {
	private Integer[] queue = null;
	private Integer chair = null;

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
		
		

		
		if(queue[posW] == null)
		{
			System.out.println("Adding " + person);
			queue[posW] = person;
			posW = (posW + 1) % queue.length;
			isSleeping.signal();
		}
		else
		{
			System.out.println("Full - customer leaving (" + person + ")");
		}
			



		
		lock.unlock();
		
	}

	public Integer remove() throws InterruptedException {
		lock.lock();
		

		
		
		Integer customer = queue[posR];

		System.out.println("Barber finished cutting " + customer);
		
		posR = (posR+1) % queue.length;
		
		isQueueFullCondition.signal();
		
		if (posW == posR)
		{
			System.out.println("Barber is now sleeping");
			isSleeping.await();
		}
		
		
		
		queue[posR] = null;
		
		lock.unlock();
		
		return customer;
	}

}
