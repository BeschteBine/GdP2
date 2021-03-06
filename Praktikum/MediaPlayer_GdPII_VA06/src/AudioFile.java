
public class AudioFile {

	//attribute
	private String pathname;
	private String filename;
	private String author;
	private String title;
	private String ausgabe;
	
	// constructor
	public AudioFile(){
		pathname = "";
		filename = "";
		author = "";
		title = "";
		
	}
	
	public AudioFile(String path){
		pathname = "";
		filename = "";
		author = "";
		title = "";
		
		
		parsePathname(path);
		parseFilename(getFilename());
	}
	
	// getter
	public String getPathname() {
		return pathname + filename;
	}
	
	public String getFilename() {
		return filename;
	}
	
	public String getAuthor(){
		return author;
	}
	
	public String getTitle(){
		return title;
	}
	
		
	
	
	public String toString(){
		//falls getAuthor() leerer String, nur Ausgabe Titel
		if(author.isEmpty()){
			ausgabe = title ;
		}else {
			ausgabe  = author + " - " + title;
		} return ausgabe;
		}
		
	// public methods
	public void parsePathname(String pathName){
		char sepchar = java.io.File.separatorChar;
		String sepString = java.io.File.separator;
		StringBuffer verkurzt = new StringBuffer(" ");
		
		// replace slashes with system slash
		pathName = pathName.replace('/', sepchar);
		pathName = pathName.replace('\\', sepchar);	
	
		
		// : -> sepchar
		if(pathName.contains(":")) {
			pathName = pathName.replace(':', sepchar);
			pathName = sepchar + pathName;
		}
		
		
		// copy contents of string if not or first system slash
		for(int i = 0; i < pathName.length(); i++ ) {
			if(pathName.charAt(i) != sepchar || verkurzt.charAt(verkurzt.length()-1) != sepchar ) {
				verkurzt.append(pathName.charAt(i));
			}
		}
		
		
		// remove leading blank
		verkurzt.deleteCharAt(0);
		
		// convert buffer to String
		String cleanName = verkurzt.toString();
		
		
		if (cleanName.endsWith(sepString)){
			//kein audiofile
			pathname = cleanName;
			filename = "";
		} else if (cleanName.contains(sepString)){
			// beides
			pathname = cleanName.substring(0, cleanName.lastIndexOf(sepString)+1);
			filename = cleanName.substring(cleanName.lastIndexOf(sepString) + 1);
		} else {
			// nur audiofile
			filename = cleanName;
			pathname = "";
		}
	}
	
	public void parseFilename(String fileName){
		
		// remove filetype
		if (fileName.contains(".")){
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
		}
		
		if (fileName.contains(" - ")){
			// author + titel
			author = fileName.substring(0, fileName.indexOf(" - ")).trim();
			title = fileName.substring(fileName.indexOf(" - ") + 3).trim();
		} else {
			//
			author = "";
			title = fileName.trim();
			}
		
		}
	
		
	
	
	
	
}//end