import java.util.Map;

import studiplayer.basic.TagReader;

public class TaggedFile extends AudioFile {
	
	// attributes
	protected String album;
	protected String duration;

	// constuctor
	public TaggedFile() {
		super();
	}

	public TaggedFile(String s) {
		super(s);
	}

	public readAndStoreTags(){
		super();
	}
	
	public String getAlbum(){
		return album;
	}

	
	public String toString(){
		String output = new String();
		
		if(this.getAlbum().isEmpty()){
			
			output = super.toString() + "author" + "title" + "time" ;
		}
		else{
			
			output = super.toString() + "author" + "title" + "album" + "time";
		}
		
		return output;
	}
	
	
	public void readAndStoreTags(String pathname){
		
		Map<String, Object> tag_map = TagReader.readTags(pathname);
		for (String key : tag_map.keySet()){
			
			if(tag_map.get(key) == null)continue;
			
			if(key.trim().isEmpty())continue;
			
			if(key == "title"){
				title = tag_map.get(key).toString();
				}
			
			else if (key == "author"){
				author = tag_map.get(key).toString();
			}
			
			else if (key == "album"){
				album = tag_map.get(key).toString();
			}
			
			else if (key == "duration"){
				duration = tag_map.get(key).toString();
			}
				
			}
		}
		
	}
