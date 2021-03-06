import java.io.File;

public abstract class AudioFile {

	// attribute
	protected String pathname;
	protected String filename;
	protected String author;
	protected String title;

	// constructor
	public AudioFile() {
		pathname = "";
		filename = "";
		author = "";
		title = "";

	}

	public AudioFile(String path) {
		this();

		parsePathname(path);
		parseFilename(getFilename());

		File ok = new File(this.getPathname());
		if (!ok.canRead()) {
			throw new RuntimeException("kann file in path nicht lesen'"
					+ this.getPathname() + "'");
		}

	}

	// abstact Methods
	public abstract void play();

	public abstract void togglePause();

	public abstract void stop();

	public abstract String getFormattedDuration();

	public abstract String getFormattedPosition();

	public abstract String[] fields();

	// getter
	public String getPathname() {
		return pathname + filename;
	}

	public String getFilename() {
		return filename;
	}

	public String getAuthor() {
		return author;
	}

	public String getTitle() {
		return title;
	}

	// public methods
	public String toString() {
		String aoutput = new String();
		// falls getAuthor() leerer String, nur Ausgabe Titel
		if (author.isEmpty()) {
			aoutput = title;
		} else {
			aoutput = author + " - " + title;
		}
		return aoutput;
	}

	public void parsePathname(String pathName) {
		char sepchar = java.io.File.separatorChar;
		String sepString = java.io.File.separator;
		StringBuffer verkurzt = new StringBuffer(" ");

		// replace slashes with system slash
		pathName = pathName.replace('/', sepchar);
		pathName = pathName.replace('\\', sepchar);

		// : -> sepchar
		if (pathName.contains(":")) {
			pathName = pathName.replace(':', sepchar);
			pathName = sepchar + pathName;
		}

		// copy contents of string if not or first system slash
		for (int i = 0; i < pathName.length(); i++) {
			if (pathName.charAt(i) != sepchar
					|| verkurzt.charAt(verkurzt.length() - 1) != sepchar) {
				verkurzt.append(pathName.charAt(i));
			}
		}

		// remove leading blank
		verkurzt.deleteCharAt(0);

		// convert buffer to String
		String cleanName = verkurzt.toString();

		if (cleanName.endsWith(sepString)) {
			// no audiofile
			pathname = cleanName;
			filename = "";
		} else if (cleanName.contains(sepString)) {
			// both
			pathname = cleanName.substring(0,
					cleanName.lastIndexOf(sepString) + 1);
			filename = cleanName
					.substring(cleanName.lastIndexOf(sepString) + 1);
		} else {
			// only audiofile
			filename = cleanName;
			pathname = "";
		}
	}

	public void parseFilename(String fileName) {

		// remove filename
		if (fileName.contains(".")) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}

		if (fileName.contains(" - ")) {
			// author + title
			author = fileName.substring(0, fileName.indexOf(" - ")).trim();
			title = fileName.substring(fileName.indexOf(" - ") + 3).trim();
		} else {
			//
			author = "";
			title = fileName.trim();
		}

	}

}// end