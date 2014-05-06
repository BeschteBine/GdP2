package studiplayer.audio;

import java.util.Comparator;

public class DurationComparator implements Comparator<AudioFile> {

	int vergleich;

	@Override
	public int compare(AudioFile af1, AudioFile af2) {

		if (af1 == null || af2 == null) {
			throw new NullPointerException("af? == null");
		}

		SampledFile sf1 = null;
		SampledFile sf2 = null;

		if (af1 instanceof SampledFile) {
			sf1 = (SampledFile) af1;
		}
		if (af2 instanceof SampledFile) {
			sf2 = (SampledFile) af2;
		}

		if (sf1 == null && sf2 != null) {
			vergleich = -1;
		}
		if (sf1 != null && sf2 == null) {
			vergleich = 1;
		}
		if (sf1 != null && sf2 != null) {
			vergleich = sf1.getFormattedDuration().compareTo(
					sf2.getFormattedDuration());
		}

		return vergleich;
	}

}
