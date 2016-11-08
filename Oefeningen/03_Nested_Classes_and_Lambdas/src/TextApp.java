

public class TextApp {
	public static void main(String[] args){
		String s = "hello";
		Text text = new Text("Hello this is an exemple of a sentence containing words");
		System.out.println("*** Words containing 'e' ***");
		text.printFilteredWords(new WorldFilter(){
			@Override
			public boolean isValid(String word) {
				return s.contains("e");
			}
		});
		// No implementation (?)
		//text.printFilteredWords("Hello how are you doing");
	}
}
