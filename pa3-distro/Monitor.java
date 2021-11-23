/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */
	int talking;
	int numberOfChopsticks;

	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
		numberOfChopsticks = piNumberOfPhilosophers;
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{
		try{
		while(numberOfChopsticks < 2){
			this.wait(numberOfChopsticks);
			}
		System.out.println("Philosopher " + piTID + " is picking up the chopsticks");
		numberOfChopsticks -= 2;
		}
		catch(Exception e){
			System.out.println(e);
		}
		// ...
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		try{
		System.out.println("Philosopher " + piTID + " is putting down the chopsticks");
		numberOfChopsticks += 2;
		notifyAll();
		}
		catch(Exception e){
			System.out.println(e);
		}
		// ...
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		try{
		while(talking > 0){
			wait(talking);
		}
		talking++;
	}
	catch(Exception e){
		System.out.println(e);
	}
		// ...
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		try{
			talking--;
			notifyAll();
		}
		catch(Exception e){
			System.out.println(e);
		}
		// ...

	}
}

// EOF
