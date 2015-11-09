import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Mapper {
	// map sentence to word
	public HashMap<String, ArrayList<String>> map(HashSet<String> sentenceList, HashSet<String> wordList) {
		HashMap<String, ArrayList<String>> result = new HashMap<String, ArrayList<String>>();
		for (String sentence : sentenceList) {
			for (String word : wordList) {
				String wordArray[] = sentence.split(" ");
				for (String wordStr : wordArray) {
					ArrayList<String> tempWordsList = new ArrayList<String>();
					if (wordStr.equalsIgnoreCase(word)) {
						if (result.get(sentence) != null && !result.get(sentence).contains(wordStr)) {
							tempWordsList = result.get(sentence);
						}
						tempWordsList.add(wordStr);
						result.put(sentence, tempWordsList);
						// System.out.println("Words in sentence " + sentence + " are " + result.get(sentence));
					}
				}
			}
		}
		return result;
	}

}
