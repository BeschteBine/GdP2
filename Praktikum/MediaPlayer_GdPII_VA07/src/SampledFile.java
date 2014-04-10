
abstract  class SampledFile extends AudioFile {

	// attributes
	protected String duration;
	
	// constructors
	public SampledFile() {
		super();
	}
	
	public SampledFile(String s){
		super(s);
	}
	
	//public methods
	@Override
	public void play() {
		// TODO Auto-generated method stub

	}

	@Override
	public void togglePause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub

	}
	

	public String getFormattedDuration() {
		long time = Long.parseLong(duration);
		return timeFormatter(time);
	}

	public String getFormattedPosition() {
		return timeFormatter(studiplayer.basic.BasicPlayer.getPosition()) ;
	}
	
	public static String timeFormatter(long microtime){
		if (microtime < 0) {
			throw new RuntimeException("Negative time value provided");
		}
		if (microtime > 5999999999L) {
			throw new RuntimeException("Time value exceeds allowed format");
		}
		// Umrechnung microsekunden in mm:ss

		int minutes = (int) (microtime / 10000000) / 60;
		int seconds = (int) (microtime / 10000000) % 60;

		// if (){

		// }

		String time = String.format("02%d:02%d", minutes, seconds);

		return time;
	}
	
}//end
