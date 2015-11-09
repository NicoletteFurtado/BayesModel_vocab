import java.util.ArrayList;

public class TempLists {
	private ArrayList<String> words;
	private ArrayList<String> sentences;

	TempLists() {
		words = new ArrayList<String>();
		sentences = new ArrayList<String>();
	}

	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

	public ArrayList<String> getSentences() {
		return sentences;
	}

	public void setSentences(ArrayList<String> sentences) {
		this.sentences = sentences;
	}

	public void display() {
		System.out.println("Sentence");
		for (String sentence : this.getSentences()) {
			System.out.println(sentence);
		}
		System.out.println("words");
		for (String word : this.getWords()) {
			System.out.println(word);
		}
	}
}
