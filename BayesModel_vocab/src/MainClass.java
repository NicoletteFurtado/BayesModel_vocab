import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MainClass {
	public static void main(String args[]) {
		ProcessLogData reader = new ProcessLogData();
		HashSet<String> wordList = reader
				.readSentenceAndWords("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\Bestfarm_words.csv");
		HashSet<String> sentenceList = reader
				.readSentenceAndWords("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\Bestfarm_sentences.csv");
		Students students = reader
				.readStudentDetails("C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\participantid_actor_sentence_verification_action.csv");
		// check for verification of each student
		// for (String studentid : students.getIdToStudentModel().keySet()) {
		// // System.out.println("student id; " + studentid);
		// for (String sentence : students.getIdToStudentModel().get(studentid).getSentenceAnswers().keySet()) {
		// System.out.println("sentence: " + sentence);
		// System.out.println("answer: "
		// + students.getIdToStudentModel().get(studentid).getSentenceAnswers().get(sentence));
		// }
		// }

		// map sentences to words
		StorySentenceWord storySentenceWord = new StorySentenceWord();
		Mapper mapper = new Mapper();
		HashMap<String, ArrayList<String>> sentenceToWord = mapper.map(sentenceList, wordList);
		// for (String sentence : sentenceToWord.keySet()) {
		// String key = sentence.toString();
		// String value = sentenceToWord.get(sentence).toString();
		// System.out.println("Sentence: " + key + " " + "words: " + value);
		// }

		// everything set in StorySentenceWord
		storySentenceWord.setSentenceToWord(sentenceToWord);
		HashMap<String, ArrayList<String>> storyToSentence = new HashMap<String, ArrayList<String>>();
		ArrayList<String> sentenceArrayList = new ArrayList<String>(sentenceList);
		storyToSentence.put(Constants.STORY_BEST_FARM, sentenceArrayList);
		storySentenceWord.setStoryToSentence(storyToSentence);
		HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>> storyToSentenceToWord = new HashMap<HashMap<String, ArrayList<String>>, HashMap<String, ArrayList<String>>>();
		storyToSentenceToWord.put(storyToSentence, sentenceToWord);
		storySentenceWord.setStoryToSentenceToWord(storyToSentenceToWord);

		// map words to skills for every student
		// System.out.println("students skill list");
		// Iterator<String> idIter = students.getStudentIds().iterator();
		// Iterator<String> wordIter = wordList.iterator();
		// HashMap<String, StudentModel> idToStudentModel = students.getIdToStudentModel();
		// StudentModel studentModel = new StudentModel();
		// ArrayList<Double> skillList = new ArrayList<Double>();
		// boolean added = false;

		Iterator<String> idIter = students.getStudentIds().iterator();
		Iterator<String> wordIter = wordList.iterator();
		HashMap<String, StudentModel> idToStudentModel = students.getIdToStudentModel();
		StudentModel studentModel = new StudentModel();
		ArrayList<Double> skillList = new ArrayList<Double>();
		ArrayList<String> actionList = new ArrayList<String>();
		boolean added = false;
		String actualSkill = "";
		while (idIter.hasNext()) {
			// System.out.println("///////////////////// " + studentModel.getParticipantId());
			String id = idIter.next();
			if (id != null && !id.isEmpty()) {
				wordIter = wordList.iterator();
				studentModel = idToStudentModel.get(id);
				skillList = studentModel.getSkill().getSkillValue();
				actionList = studentModel.getSkill().getActionList();
				// for every word
				while (wordIter.hasNext()) {
					String word = wordIter.next();
					// for every action
					for (String action : actionList) {
						skillList = new ArrayList<Double>();
						added = skillList.add(studentModel.getInitialSkillValue());
						actualSkill = word + "_" + action;
						studentModel.getSkill().getSkillToValue().put(actualSkill, skillList);
						// System.out.println(actualSkill + " " + Arrays.toString(skillList.toArray()));
					}
				}
				// studentModel.display();
			}
		}

		// call the model for each student
		// KnowledgeTracer knowledgeTracer = new KnowledgeTracer();
		// while (idIter.hasNext()) {
		// String id = idIter.next();
		// studentModel = idToStudentModel.get(id);
		// studentModel = knowledgeTracer.bayes(studentModel, storySentenceWord);
		// studentModel.display();
		// }

		KnowledgeTracer knowledgeTracer = new KnowledgeTracer();
		studentModel = idToStudentModel.get("L0221BE");
		System.out.println(":::::::::::" + studentModel.getParticipantId());
		// for (String sentence : studentModel.getSentenceAnswers().keySet()) {
		// System.out.println(sentence + " " + studentModel.getSentenceAnswers().get(sentence));
		// System.out.println(sentence + " " + studentModel.getSentenceActions().get(sentence));
		// }
		StudentModel studentModel2 = new StudentModel();
		studentModel = knowledgeTracer.bayes(studentModel, storySentenceWord);
		System.out.println("''''''" + studentModel.getParticipantId());
		WriteCSV write = new WriteCSV();
		write.writeOutput(studentModel2, storySentenceWord);
		// studentModel.display();
	}
}
