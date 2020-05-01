public class Process extends Thread {
	
	public int processID;
    ProcessState status=ProcessState.New;	
	
	public Process(int m) {
		processID = m;
	}
	@Override
	public void run() {
		
		switch(processID)
		{
		case 1:process1();break;
		case 2:process2();break;
		case 3:process3();break;
		case 4:process4();break;
		case 5:process5();break;
		}

	}
	
	private void process1() {
		OperatingSystem.printText.semWait(this);
		OperatingSystem.takeInput.semWait(this);
		OperatingSystem.printText("Enter File Name: ");		
		String s = OperatingSystem.TakeInput();
		OperatingSystem.takeInput.semPost(this);
		OperatingSystem.printText.semPost(this);
		
		OperatingSystem.readFile.semWait(this);
		String r = OperatingSystem.readFile(s);
		OperatingSystem.readFile.semPost(this);
		
		OperatingSystem.printText.semWait(this);
		OperatingSystem.printText(r);
		OperatingSystem.printText.semPost(this);
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process2() {
		OperatingSystem.printText.semWait(this);
		OperatingSystem.takeInput.semWait(this);
		OperatingSystem.printText("Enter File Name: ");		
		String filename= OperatingSystem.TakeInput();
		OperatingSystem.takeInput.semPost(this);
		OperatingSystem.printText.semPost(this);
		
		OperatingSystem.printText.semWait(this);
		OperatingSystem.takeInput.semWait(this);
		OperatingSystem.printText("Enter Data: ");		
		String data= OperatingSystem.TakeInput();
		OperatingSystem.takeInput.semPost(this);
		OperatingSystem.printText.semPost(this);
		
		OperatingSystem.writeFile.semWait(this);
		OperatingSystem.writefile(filename,data);
		OperatingSystem.writeFile.semPost(this);
		
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process3() {
		int x=0;
		OperatingSystem.printText.semWait(this);
		while (x<301)
		{ 
			OperatingSystem.printText(x+"\n");
			x++;
		}
		OperatingSystem.printText.semPost(this);
		setProcessState(this,ProcessState.Terminated);
		}
	
	private void process4() {
	
		int x=500;
		OperatingSystem.printText.semWait(this);
		while (x<1001)
		{
			OperatingSystem.printText(x+"\n");
			x++;
		}	
		OperatingSystem.printText.semPost(this);
		setProcessState(this,ProcessState.Terminated);
		}
	private void process5() {
		OperatingSystem.printText.semWait(this);
		OperatingSystem.takeInput.semWait(this);
		OperatingSystem.printText("Enter LowerBound: ");
		String lower= OperatingSystem.TakeInput();
		OperatingSystem.takeInput.semPost(this);
		OperatingSystem.printText.semPost(this);
		
		
		OperatingSystem.printText.semWait(this);
		OperatingSystem.takeInput.semWait(this);
		OperatingSystem.printText("Enter UpperBound: ");
		String upper= OperatingSystem.TakeInput();
		OperatingSystem.takeInput.semPost(this);
		OperatingSystem.printText.semPost(this);
		
		int lowernbr=Integer.parseInt(lower);
		int uppernbr=Integer.parseInt(upper);
		String data="";
		
		while (lowernbr<=uppernbr)
		{
			data+=lowernbr++ +"\n";
		}	
		
		OperatingSystem.writeFile.semWait(this);
		OperatingSystem.writefile("P5.txt", data);
		OperatingSystem.writeFile.semPost(this);
		
		setProcessState(this,ProcessState.Terminated);
	}
	
	 public static void setProcessState(Process p, ProcessState s) {
		 p.status=s;
		 if (s == ProcessState.Terminated)
		 {
			 OperatingSystem.ProcessTable.remove(OperatingSystem.ProcessTable.indexOf(p));
		 }
	}
	 
	 public static ProcessState getProcessState(Process p) {
		 return p.status;
	}
}
