package studiplayer.audio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("serial")
public class PlayList extends LinkedList<AudioFile> {
	// attributes
	private int current;
	private boolean randomOrder;

	// constructor
	public PlayList() {
		current = 0;
		randomOrder = false;
	}

	public PlayList(String pathname) {

		this();
		loadFromM3U(pathname);

	}

	// setters
	public void setCurrent(int current) {
		this.current = current;
	}

	public void setRandomOrder(boolean randomOrder) {
		this.randomOrder = randomOrder;
		if (randomOrder == true) {
			Collections.shuffle(this);
		}
	}

	// getters
	public int getCurrent() {
		return current;
	}

	public AudioFile getCurrentAudioFile() {
		if (this.size() == 0) {
			return null;
		}

		if (getCurrent() < 0 || getCurrent() >= this.size()) {
			return null;
		} else {
			return this.get(getCurrent());

		}

	}

	public void changeCurrent() {
		// index -> letztes Lied (0)
		if (getCurrent() >= this.size() - 1) {
			setCurrent(0);
			if (randomOrder == true) {
				Collections.shuffle(this);
			}
		}
		// index ++
		else {
			setCurrent(getCurrent() + 1);
		}

	}

	public void saveAsM3U(String pathname) {
		FileWriter writer = null;
		String fname = pathname;
		String linesep = System.getProperty("line.separator");

		try {
			// Create a FileWriter
			writer = new FileWriter(fname);
			writer.write("# My best songs" + linesep);
			writer.write(linesep);

			for (int i = 0; i < this.size(); i++) {
				writer.write(this.get(i).getPathname() + linesep);
			}

		} catch (IOException e) {
			throw new RuntimeException("Unable to write to file " + fname + ":"
					+ e.getMessage());
		} finally {
			try {
				writer.close();
			} catch (Exception e) {

			}
		}

	}

	public void loadFromM3U(String pathname) {
		Scanner scanner = null;
		String line;
		this.clear();

		try {
			scanner = new Scanner(new File(pathname));

			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (!line.trim().isEmpty() && !line.startsWith("#")) {
					try {
						this.add(AudioFileFactory.getInstance(line));
					} catch (NotPlayableException e) {
						e.printStackTrace();
					}

				}

			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				scanner.close();
			} catch (Exception e) {

			}
		}
	}

	public void sort(SortCriterion order) {

		if (order == SortCriterion.TITLE) {
			Collections.sort(this, new TitleComparator());
		}
		if (order == SortCriterion.AUTHOR) {
			Collections.sort(this, new AuthorComparator());
		}
		if (order == SortCriterion.ALBUM) {
			Collections.sort(this, new AlbumComparator());
		}
		if (order == SortCriterion.DURATION) {
			Collections.sort(this, new DurationComparator());
		}
	}
}// end
