package studiplayer.audio;

import java.util.Comparator;

import studiplayer.audio.AudioFile;

public class TitleComparator implements Comparator<AudioFile> {
	@Override
	public int compare(AudioFile o1, AudioFile o2) {
		if (o1 == null || o2 == null)
			throw new NullPointerException("o? == null");

		if (o1.getTitle() == null || o2.getTitle() == null)
			throw new NullPointerException("o?.getTitle == null");

		return o1.getTitle().compareTo(o2.getTitle());
	}
}// end
