package studiplayer.audio;

@SuppressWarnings("serial")
public class NotPlayableException extends Exception {
	// attributes
	String pathname;
	String output;

	public NotPlayableException(String pathname, String msg) {
		super(msg);
		this.pathname = pathname;
	}

	public NotPlayableException(String pathname, Throwable t) {
		super(t);
		this.pathname = pathname;
	}

	public NotPlayableException(String pathname, String msg, Throwable t) {
		super(msg, t);
		this.pathname = pathname;
	}

	public String toString() {
		output = new String();
		output = pathname + ":" + super.toString();
		return output;

	}
}// end
