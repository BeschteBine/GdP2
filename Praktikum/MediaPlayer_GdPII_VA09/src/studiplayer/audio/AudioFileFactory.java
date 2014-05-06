package studiplayer.audio;

public class AudioFileFactory {

	public static AudioFile getInstance(String pathname)
			throws NotPlayableException {
		// input check
		if (pathname == null)
			throw new RuntimeException("pathname can not be null");
		if (pathname.isEmpty() || !pathname.contains(".")) {
			throw new NotPlayableException(pathname,
					"Unknow suffix for AudioFile: \"" + pathname + "\"");
		}

		// do the magic
		AudioFile af = null;
		if (pathname.endsWith(".wav")) {
			WavFile wf = new WavFile(pathname);
			af = (AudioFile) wf;
		} else if (pathname.endsWith(".mp3")
				|| pathname.toLowerCase().endsWith(".ogg")) {
			TaggedFile tf = new TaggedFile(pathname);
			af = (AudioFile) tf;
		}
		return af;
	}
}// end

