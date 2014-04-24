import static org.junit.Assert.assertEquals;
import org.junit.Test;
public class UTestPlayList {

	/* this unit test creates an empty PlayList and tries to retrieve an non-initialized
	 * value as current position
	 * We expect a null value
	 */
	@Test
	public void test_getCurrentAudioFile_01() throws Exception {
		PlayList pl = new PlayList();
		assertEquals("Wrong current AudioFile", null, pl.getCurrentAudioFile());
	}
	
	@Test
	public void test_getCurrentAudioFile_02() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		pl.add(tf0);
		/* We set a wrong index
		 * the setter is not validated
		 * However, getCurrentAudioFile() is
		 */
		pl.setCurrent(10);
		assertEquals("Wrong current AudioFile", null, pl.getCurrentAudioFile());
	}
	
	@Test
	public void test_getCurrentAudioFile_03() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		
		pl.add(tf0);
		pl.setCurrent(0);
		
		assertEquals("Wrong current AudioFile", tf0, pl.getCurrentAudioFile());
	}
	
	@Test
	public void test_getCurrentAudioFile_04() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		TaggedFile tf1 = new TaggedFile("audiofiles/Rock 812.mp3");
		
		pl.add(tf0);
		pl.add(tf1);
		pl.setCurrent(1);
		
		assertEquals("Wrong current AudioFile", tf1, pl.getCurrentAudioFile());
		
		/* We remove the first element, wich invalidates the current index pointing
		 * at position 1. Now the list is too short
		 */
		pl.remove(0);
		assertEquals("Wrong current AudioFile", null, pl.getCurrentAudioFile());
	}
	
	/* This unit test creates a playlist containing three audiofiles and iterates through it
	 * We expect to set the next audiofile until the end of list is reached.
	 * After reaching the end of the list we expect to set the first song in the list
	 */
	@Test
	public void test_changeCurrent_01() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		TaggedFile tf1 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
		TaggedFile tf2 = new TaggedFile("audiofiles/Rock 812.mp3");
		
		pl.add(tf0);
		pl.add(tf1);
		pl.add(tf2);
		pl.setCurrent(0);

		assertEquals("Wrong current index", 0, pl.getCurrent());
		pl.changeCurrent();
		assertEquals("Wrong current index", 1, pl.getCurrent());
		pl.changeCurrent();
		assertEquals("Wrong current index", 2, pl.getCurrent());
		pl.changeCurrent();
		assertEquals("Wrong current index", 0, pl.getCurrent());
	}
	
	/* This unit test creates a playlist containing three songs and iterates until it reaches
	 * the last one. Afterwards we remove the current Audiofile and invoke the changeCurrent()
	 * method.
	 * We expect to get the song id beeing reset to 0
	 */
	@Test
	public void test_changeCurrent_02() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		TaggedFile tf1 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
		TaggedFile tf2 = new TaggedFile("audiofiles/Rock 812.mp3");
		
		pl.add(tf0);
		pl.add(tf1);
		pl.add(tf2);
		pl.setCurrent(0);

		assertEquals("Wrong current index", 0, pl.getCurrent());
		pl.changeCurrent();
		assertEquals("Wrong current index", 1, pl.getCurrent());
		pl.changeCurrent();
		assertEquals("Wrong current index", 2, pl.getCurrent());
		pl.remove(2);
		pl.changeCurrent();
		assertEquals("Wrong current index", 0, pl.getCurrent());
	}
	
	/* This unit test creates a playlist containing three song but this time we do not set the
	 * current index before calling, causing a 0 value to be returned.
	 */
	@Test
	public void test_changeCurrent_03() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile tf0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		TaggedFile tf1 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
		TaggedFile tf2 = new TaggedFile("audiofiles/Rock 812.mp3");
		
		pl.add(tf0);
		pl.add(tf1);
		pl.add(tf2);

		assertEquals("just checking for 0...", 0, pl.getCurrent());
		pl.changeCurrent();
		assertEquals("Wrong current index", 1, pl.getCurrent());
		pl.changeCurrent();
	}
	
	/* This unit creates a playlist containing four songs, shuffles them and echoes the
	 * position and the filename of the current song in playlist while iterating through
	 * it 5 times
	 */
	@Test
	public void test_changeCurrent_04() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile f0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		TaggedFile f1 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
		TaggedFile f2 = new TaggedFile("audiofiles/Rock 812.mp3");
		WavFile f3 = new WavFile("audiofiles/wellenmeister - tranquility.wav");
		
		pl.add(f0);
		pl.add(f1);
		pl.add(f2);
		pl.add(f3);
		pl.setRandomOrder(true);
		
		// only the content of the list is shuffled, not the index!!!
		for (int i = 0; i < 5 * pl.size(); i++) {
			System.out.printf("Pos= %d Filename=%s\n", pl.getCurrent(), pl.getCurrentAudioFile().getFilename());
			assertEquals("Wrong current index", i% pl.size(), pl.getCurrent());
			pl.changeCurrent();
			if (pl.getCurrent() == 0) System.out.println("");
		}
	}
	
	/* This unit test creates a playlist with 3 songs and saves it as m3u
	 */
	@Test
	public void test_saveAsM3U_01() throws Exception {
		PlayList pl = new PlayList();
		TaggedFile f0 = new TaggedFile("audiofiles/Eisbach Deep Snow.ogg");
		TaggedFile f1 = new TaggedFile("audiofiles/tanom p2 journey.mp3");
		TaggedFile f2 = new TaggedFile("audiofiles/Rock 812.mp3");
		WavFile f3 = new WavFile("audiofiles/wellenmeister - tranquility.wav");
		
		pl.add(f0);
		pl.add(f1);
		pl.add(f2);
		pl.add(f3);
		pl.add(f2);
		
		pl.saveAsM3U("playlists/unittest_test_saveAsM3U_01.m3u");
	}
}