package studiplayer.audio;

import java.util.Comparator;

public class AuthorComparator implements Comparator<AudioFile> {

	@Override
	public int compare(AudioFile o1, AudioFile o2) {
		// input check
		if (o1 == null || o2 == null)
			throw new NullPointerException("o? == null");

		if (o1.getAuthor() == null || o2.getAuthor() == null)
			throw new NullPointerException("o? == null");

		// compare & return

		return o1.getAuthor().compareTo(o2.getAuthor());

	}

}
