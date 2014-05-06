package studiplayer.audio;

public abstract class SampledFile extends AudioFile {

	// attributes
	protected long duration;

	// constructors
	public SampledFile() throws NotPlayableException {
		super();
		duration = 0L;
	}

	public SampledFile(String s) throws NotPlayableException {
		super(s);
		duration = 0L;
	}

	// public methods
	@Override
	public void play() throws NotPlayableException {
		studiplayer.basic.BasicPlayer.play(getPathname());

	}

	@Override
	public void togglePause() {
		studiplayer.basic.BasicPlayer.togglePause();
	}

	@Override
	public void stop() {
		studiplayer.basic.BasicPlayer.stop();
	}

	// setters
	protected void setDuration(long l) {
		duration = l;
	}

	// getters
	public String getFormattedDuration() {
		return TaggedFile.timeFormatter(this.duration);
	}

	public String getFormattedPosition() {
		return TaggedFile.timeFormatter(studiplayer.basic.BasicPlayer
				.getPosition());
	}

	public static String timeFormatter(long microtime) {
		if (microtime < 0) {
			throw new RuntimeException("Negative time value provided");
		}
		if (microtime > 5999999999L) {
			throw new RuntimeException("Time value exceeds allowed format");
		}
		// Umrechnung microsekunden in mm:ss

		int minutes = (int) (microtime / 1000000) / 60;
		int seconds = (int) (microtime / 1000000) % 60;

		String duration = String.format("%02d:%02d", minutes, seconds);

		return duration;
	}

}// end
