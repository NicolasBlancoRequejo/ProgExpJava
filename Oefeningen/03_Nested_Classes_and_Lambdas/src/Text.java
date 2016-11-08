
public class Text {
	private String sentence;
	
	public Text(String sentence){
		this.sentence = sentence;
	}
	
	public void printFilteredWords(WorldFilter filter){
		for(String word : sentence.split("")){
			if(filter.isValid(word)){
				System.out.println(word);
			}
		}
	}
}
