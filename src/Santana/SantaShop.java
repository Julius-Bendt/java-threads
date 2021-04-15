package Santana;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SantaShop {
	private ArrayList<String> deers;
	private Queue<Integer> elves;
	private Lock lock = new ReentrantLock();
	private Condition sleep = lock.newCondition();

	public SantaShop() {
		this.elves = new ArrayDeque<>();
		this.deers = new ArrayList<>();
	}

	public void addElf(Integer elf) {
		lock.lock();
		elves.add(elf);
		if (elves.size() >= 3)
			sleep.signal();
		lock.unlock();
	}

	public void arrive(String deer) {
		lock.lock();
		deers.add(deer);
		if (deers.size() > 8)
			sleep.signal();
		lock.unlock();
	}

	public void santa() throws InterruptedException {
		lock.lock();
		if (elves.size() < 3 && deers.size() < 9) {
			System.out.println("Santana: Zzz");
			sleep.await();
		}
		if (deers.size() >= 9) {
			deers.removeAll(deers);
			System.out.println("Christ is here");
		}
		if (elves.size() >= 3) {
			System.out.println("helping elves..");
			Integer elf1 = elves.poll();
			Integer elf2 = elves.poll();
			Integer elf3 = elves.poll();
			System.out.println("Helped: " + elf1.toString() + ", " + elf2.toString() + " and " + elf3.toString());

		}
		lock.unlock();
	}
}
