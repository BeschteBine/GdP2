import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class UTestAudioFile {
	


	@Test
	public void test_parsePathname_01() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("");
		//OnUnix we expect ""
		assertEquals("Pathname stored incorrectly",
				"" ,
				af.getPathname());
		assertEquals("Returned filename is incorrect",
				"",
				af.getFilename());
	}
	@Test
	public void test_parsePathname_02() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("     ");
		// OnUnix we expect "     "
		assertEquals("Pathname stored incorrectly",
				"     ",
				af.getPathname());
		assertEquals("Returned filename is incorrect",
				"     ",
				af.getFilename());
	}

	@Test
	public void test_parsePathname_03() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("file.mp3");
		// OnUnix we expect file.mp3"
		assertEquals("Pathname stored incorrectly",
				"file.mp3" ,
				af.getPathname());
		assertEquals("Returned filename is incorrect",
				"file.mp3",
				af.getFilename());
	}

	@Test
	public void test_parsePathname_04() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("/my-tmp/file.mp3");
		char sepchar = java.io.File.separatorChar;
		// OnUnix we expect " /my-tmp/file.mp3"
		assertEquals("Pathname stored incorrectly",
				sepchar + "my-tmp" + sepchar + "file.mp3" ,
				af.getPathname());
		assertEquals("Returned filename is incorrect",
				"file.mp3",
				af.getFilename());
	}
	
	@Test
	public void test_parsePathname_05() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("//my-tmp////part1//file.mp3/");
		// OnUnix we expect " /my-tmp/file.mp3"
		assertEquals("Pathname stored incorrectly",
				"/my-tmp/part1/file.mp3/" ,
				af.getPathname());
		assertEquals("Returned filename is incorrect",
				"",
				af.getFilename());
	}
	
	
	@Test
	public void test_parsePathname_06() throws Exception {
		AudioFile af = new AudioFile();
		af.parsePathname("d:\\\\part1///file.mp3");
		// OnUnix we expect "/my-tmp/file.mp3"
		assertEquals("Pathname stored incorrectly",
				"/d/part1/file.mp3" ,
				af.getPathname());
		assertEquals("Returned filename is incorrect",
				"file.mp3",
				af.getFilename());
	}
	// filename tests
		@Test
		public void test_AudioFile_parseFilename_01() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = " Falco  -  Rock me    Amadeus .mp3  ";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", " Falco  -  Rock me    Amadeus .mp3  ", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","Falco", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "Rock me    Amadeus", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_02() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "/dir/ Falco  -  Rock me    Amadeus .mp3  ";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", " Falco  -  Rock me    Amadeus .mp3  ", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","Falco", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "Rock me    Amadeus", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_03() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "Frankie Goes To Hollywood - The Power Of Love.ogg";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "Frankie Goes To Hollywood - The Power Of Love.ogg", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","Frankie Goes To Hollywood", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "The Power Of Love", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_04() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "/dir/Frankie Goes To Hollywood - The Power Of Love.ogg";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "Frankie Goes To Hollywood - The Power Of Love.ogg", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","Frankie Goes To Hollywood", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "The Power Of Love", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_05() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "audiofile.aux";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "audiofile.aux", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "audiofile", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_06() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "/dir/audiofile.aux";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "audiofile.aux", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "audiofile", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_07() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "   A.U.T.O.R   -  T.I.T.E.L  .EXTENSION";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "   A.U.T.O.R   -  T.I.T.E.L  .EXTENSION", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","A.U.T.O.R", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "T.I.T.E.L", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_08() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "/dir/   A.U.T.O.R   -  T.I.T.E.L  .EXTENSION";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "   A.U.T.O.R   -  T.I.T.E.L  .EXTENSION", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","A.U.T.O.R", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "T.I.T.E.L", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_09() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","Hans-Georg Sonstwas", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "Blue-eyed boy-friend", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_10() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "/dir/Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "Hans-Georg Sonstwas - Blue-eyed boy-friend.mp3", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","Hans-Georg Sonstwas", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "Blue-eyed boy-friend", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_11() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "-";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "-", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "-", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_12() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "/dir/-";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", "-", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "-", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_13() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = " - ";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", " - ", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "", af.getTitle());
		}

		@Test
		public void test_AudioFile_parseFilename_14() throws Exception {
			AudioFile af = new AudioFile();
			String pathName = "/dir/ - ";

			af.parsePathname(pathName);
			af.parseFilename(af.getFilename());

			assertEquals("failed to save filename for '" + pathName + "'", " - ", af.getFilename());
			assertEquals("failed to parse author for '" + pathName + "'","", af.getAuthor());
			assertEquals("failed to parse title for '" + pathName + "'", "", af.getTitle());
		}
		

			
	}	
} // unit tests end