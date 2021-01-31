import java.util.Scanner;

/**
 * The ProcessScheduler class represents the data type for the main scheduler
 * for our processes.
 */
public class ProcessScheduler {

	private int currentTime; // stores the current time after the last run
	private int numProcessesRun; // stores the number of processes run so far
	private CustomProcessQueue queue; // this processing unit's custom process queue

	/**
	 * set up the CustomProcessQueue queue, initialize both currentTime and
	 * numProcessRun to zero
	 */
	public ProcessScheduler() {
		queue = new CustomProcessQueue();
		currentTime = 0;
		numProcessesRun = 0;
	}

	/**
	 * enqueue the given process in the CustomProcessQueue queue.
	 * 
	 * @param process: the process that is being added.
	 */
	public void scheduleProcess(CustomProcess process) {
		queue.enqueue(process);
	}

	/**
	 * When it is called, the run() method starts running the ready processes
	 * already scheduled in the queue according to the SJF scheduling policy. This
	 * method returns when all the scheduled processes are run and the queue is
	 * empty. It returns a String that represents the log of one run operation.
	 * 
	 * @return res: a String that represents the log of one run operation
	 */
	public String run() {
		String res = "";
		if (queue.size() == 0) { // check size first
			return "No task in the queue.";
		}
		while (queue.size() > 0) {
			numProcessesRun++;
			int id = queue.peek().getProcessId();
			int time = queue.dequeue().getBurstTime();
			res += "Time " + currentTime + " : Process ID " + id + " Starting.\n";
			currentTime += time;
			res += "Time " + currentTime + " : Process ID " + id + " Completed.\n";
		}
		res += "\nTime " + currentTime + " : All scheduled processes completed.";
		return res;
	}

	/**
	 * helper method, print the menu
	 */
	private void printMenu() {
		System.out.println("\n" + "Enter command:\n" + "[schedule <burstTime>] or [s <burstTime>]\n" + "[run] or [r]\n"
				+ "[quit] or [q]\n");
	}

	/**
	 * helper method, print the exit message once application is ended.
	 */
	private void printExitMessage() {
		System.out.println(numProcessesRun + " processes run in " + currentTime + " units of time!\n"
				+ "Thank you for using our scheduler!\n" + "Goodbye!");
	}

	/**
	 * helper method to process user input
	 * 
	 * @param command: a line of command that we read from the use input
	 * @return true if application not end after the execution of the command, false
	 *         otherwise
	 */
	private boolean processCommand(String command) {
		if (command.equals("quit") || command.equals("q")) // case 1: quit
			return false;
		String[] cmd = command.split(" ");
		if (cmd[0].equals("schedule") || cmd[0].equals("s")) { // case 2: add new task
			int burstTime = 0;
			try {
				burstTime = Integer.parseInt(cmd[1]);
			} catch (NumberFormatException e) {
				System.out.println("WARNING: burst time MUST be an integer!");
				return true;
			}
			if (burstTime <= 0) {
				System.out.println("WARNING: burst time MUST be greater than 0!");
				return true;
			}
			CustomProcess process = new CustomProcess(burstTime);
			scheduleProcess(process);
			System.out.println(
					"Process ID " + process.getProcessId() + " scheduled. Burst Time = " + process.getBurstTime());
		} else if (cmd[0].equals("run") || cmd[0].equals("r")) { // case 3: run all tasks
			int num = queue.size();
			if (num <= 1)
				System.out.println("Starting 1 process\n");
			if (num > 1)
				System.out.println("Starting " + num + " processes\n");
			System.out.println(run());
		} else {
			System.out.println("WARNING: Please enter a valid command!");
		}
		return true;
	}

	/**
	 * This method serves as the driver of the application. This method creates and
	 * uses only one instance of the Scanner class, and also only one instance of
	 * the ProcessScheduler class
	 * 
	 * @param String[] args
	 */
	public static void main(String[] args) {
		ProcessScheduler pS = new ProcessScheduler();
		Scanner scnr = new Scanner(System.in);
		System.out.println("==========   Welcome to the SJF Process Scheduler App   ========");
		while (true) {
			pS.printMenu();
			String command = scnr.nextLine();
			if (!pS.processCommand(command)) {
				pS.printExitMessage();
				return;
			}
			// scnr.close();
		}
	}
}
