
public class WavFile  extends SampledFile{

	// constructors
	public WavFile(){
		super();
	}
	
	public WavFile(String s) {
		super(s);
	}
	
	public void readAndSetDurationFromFile(String filename){
		
	}
	
	public String toString() {
		return "";
	}
	
	public String[] fields() {
		return null;
	}
	
	public static long computeDuration(long numberOfFrames, float frameRate){
		
		return 0L;
	}
	
	
}
