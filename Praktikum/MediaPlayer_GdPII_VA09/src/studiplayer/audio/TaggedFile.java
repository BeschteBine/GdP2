package studiplayer.audio;

import java.util.Map;

import studiplayer.basic.TagReader;

public class TaggedFile extends SampledFile {

	// attributes
	protected String album;

	// constuctor
	public TaggedFile() throws NotPlayableException {
		super();
		album = "";
	}

	public TaggedFile(String s) throws NotPlayableException {
		super(s);
		album = "";
		readAndStoreTags(getPathname());
	}

	public String getAlbum() {
		return album;
	}

	public void readAndStoreTags(String pathname) throws NotPlayableException {
		Map<String, Object> tag_map = null;

		try {
			tag_map = TagReader.readTags(pathname);
		} catch (RuntimeException e) {
			throw new NotPlayableException(pathname,
					"Failed to read tags from file");
		}

		for (String key : tag_map.keySet()) {

			if (tag_map.get(key) == null)
				continue;

			if (key.trim().isEmpty())
				continue;

			if (tag_map.get(key).toString().trim().isEmpty())
				continue;

			if (key == "title") {
				title = tag_map.get(key).toString().trim();
			}

			else if (key == "author") {
				author = tag_map.get(key).toString().trim();
			}

			else if (key == "album") {
				album = tag_map.get(key).toString().trim();
			}

			else if (key == "duration") {
				duration = Long.parseLong(tag_map.get(key).toString());
			}

		}
	}

	public String toString() {
		String toutput = new String();

		if (this.getAlbum().isEmpty()) {

			toutput = super.toString() + " - " + getFormattedDuration();
		} else {

			toutput = super.toString() + " - " + album + " - "
					+ getFormattedDuration();
		}

		return toutput;
	}

	@Override
	public String[] fields() {
		String[] fields = { author, title, album, this.getFormattedDuration() };
		// TODO Auto-generated method stub
		return fields;
	}

}
