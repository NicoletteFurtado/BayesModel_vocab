import java.util.ArrayList;
import java.util.HashMap;

class StorySentenceWord {
	private String story = "";
	private String sentence = "";
	private ArrayList<String> wordList;
	private HashMap<String, ArrayList<String>> sentenceToWord;
	private HashMap<String, ArrayList<String>> storyToSentence;
	private HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>> storyToSentenceToWord;

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public ArrayList<String> getWordList() {
		return wordList;
	}

	public void setWordList(ArrayList<String> wordList) {
		this.wordList = wordList;
	}

	public HashMap<String, ArrayList<String>> getSentenceToWord() {
		return sentenceToWord;
	}

	public void setSentenceToWord(HashMap<String, ArrayList<String>> sentenceToWord) {
		this.sentenceToWord = sentenceToWord;
	}

	public String getStory() {
		return story;
	}

	public void setStory(String story) {
		this.story = story;
	}

	public HashMap<String, ArrayList<String>> getStoryToSentence() {
		return storyToSentence;
	}

	public void setStoryToSentence(HashMap<String, ArrayList<String>> storyToSentence) {
		this.storyToSentence = storyToSentence;
	}

	public HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>> getStoryToSentenceToWord() {
		return storyToSentenceToWord;
	}

	public void setStoryToSentenceToWord(HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>> storyToSentenceToWord) {
		this.storyToSentenceToWord = storyToSentenceToWord;
	}

}
