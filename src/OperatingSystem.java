import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;



public class OperatingSystem {
	
	public static ArrayList<Thread> ProcessTable = new ArrayList<Thread>();
	public static Queue<Process> readyQueue = new LinkedList<Process>();
	public static Semaphore readFile = new Semaphore();
	public static Semaphore writeFile= new Semaphore();
	public static Semaphore printText= new Semaphore();
	public static Semaphore takeInput= new Semaphore();
//	public static int activeProcess= 0;
	//system calls:
	// 1- Read from File
	@SuppressWarnings("unused")
	public static String readFile(String name) {
		String Data="";
		File file = new File(name);
	 try {
		Scanner scan = new Scanner(file);
		while (scan.hasNextLine())
		{
			Data+= scan.nextLine()+"\n";
		}
		scan.close();
	} catch (FileNotFoundException e) {
		System.out.println(e.getMessage());
	}
		return Data;
	}
	
	// 2- Write into file
	@SuppressWarnings("unused")
	public static void writefile(String name, String data) {
		try
		{
			BufferedWriter BW = new BufferedWriter(new FileWriter(name));
			BW.write(data);
			BW.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}

	}
	//3- print to console
	@SuppressWarnings("unused")
	public static void printText(String text) {

		System.out.println(text);
		
	}
	
	//4- take input
	public static String TakeInput() {
		Scanner in= new Scanner(System.in);
		String data = in.nextLine();
		return data;
		
	}
	
	private static void createProcess(int processID){
		Process p = new Process(processID);
		ProcessTable.add(p);
		Process.setProcessState(p,ProcessState.Ready);
		readyQueue.add(p);
	}
	
	private static void FCFSScheduler(){
		while(readyQueue.size()>0){
			if(Thread.activeCount()==1){
				Process p = readyQueue.peek();
				p.start();
				readyQueue.remove();
			}
		}
	}
		
	public static void main(String[] args) {
		createProcess(5);
		createProcess(2);
		createProcess(3);
		createProcess(4);
		createProcess(1);
		FCFSScheduler();
	}
}



