package studiplayer.audio;

import studiplayer.basic.WavParamReader;

public class WavFile extends SampledFile {

	// constructors
	public WavFile() throws NotPlayableException {
		super();
	}

	public WavFile(String s) throws NotPlayableException {
		super(s);
		readAndSetDurationFromFile(this.getPathname());
	}

	public void readAndSetDurationFromFile(String cleanname)
			throws NotPlayableException {

		try {
			WavParamReader.readParams(cleanname);
		} catch (RuntimeException e) {
			throw new NotPlayableException(cleanname,
					"Failed to read data from Wavefile");
		}
		this.setDuration(WavFile.computeDuration(
				WavParamReader.getNumberOfFrames(),
				WavParamReader.getFrameRate()));
	}

	public String toString() {
		String woutput = new String();

		woutput = super.toString() + " - " + timeFormatter(this.duration);
		return woutput;
	}

	public String[] fields() {
		String[] fields = { author, title, "", this.getFormattedDuration() };
		return fields;
	}

	public static long computeDuration(long numberOfFrames, float frameRate) {

		// float mt = Float.parseFloat(numberOfFrames);
		// Gesamtanzahl Frames / Frame Rate = Gesamtspieldauer in microseconds
		return (long) (numberOfFrames / frameRate * 1000000);
	}
}
