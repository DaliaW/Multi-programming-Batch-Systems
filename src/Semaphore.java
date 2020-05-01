import java.util.LinkedList;
import java.util.Queue;

public class Semaphore {
	Queue<Thread> blocked;
	BinarySem available;
	
	public Semaphore(){
		this.blocked = new LinkedList<Thread>();
		this.available = BinarySem.ONE;
	}
	
	@SuppressWarnings("deprecation")
	public void semWait(Process p){
		 if(this.available==BinarySem.ONE){
				this.available = BinarySem.ZERO;
		}else if(this.available==BinarySem.ZERO){
			p.status = ProcessState.Waiting;
			this.blocked.add(p);
			p.suspend();
		}
	}
	
	public void semPost(Process p){
			if(blocked.size()>0){
				Process tp = (Process) this.blocked.remove();
				tp.status = ProcessState.Running;
				this.available = BinarySem.ZERO;
				tp.resume();
			}else {
				this.available = BinarySem.ONE;
			}
	}
}
