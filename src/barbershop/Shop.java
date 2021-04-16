package barbershop;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Shop {
    private Queue<Integer> line;
    private Lock lock = new ReentrantLock();
    private Condition sleep = lock.newCondition();
    public boolean open = true;
    public int succes = 0;

    public Shop() {
	this.line = new ArrayDeque<>();
    }

    public void add(Integer person) {
	lock.lock();
	if (line.size() < 5) {
	    System.out.println("added: " + person);
	    line.add(person);
	    sleep.signal();
	} else {
	    System.out.println(person + " left");
	}
	printQueue("add");
	lock.unlock();
    }

    public Integer remove(String name) throws InterruptedException {
	Integer person = null;
	lock.lock();
	if (line.isEmpty() && open) {
	    System.out.println(name + " is sleeping ......");
	    sleep.await();
	} else if (line.isEmpty() && open == false) {
	    sleep.signalAll();
	}
	person = line.poll();
	lock.unlock();
	return person;
    }

    private void printQueue(String from) {
	String print = "{ ";
	for (Integer i : line) {
	    print += i + ", ";
	}
	System.out.println(from + ": " + print + " }");
    }

}
