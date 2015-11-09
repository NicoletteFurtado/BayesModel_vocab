import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class KnowledgeTracer {
	private StudentModel student;
	private Skill skill;
	private StorySentenceWord storySentenceWord;

	public StudentModel bayes(StudentModel student, StorySentenceWord storySentenceWord) {
		HashMap<String, ArrayList<String>> sentenceToWord = storySentenceWord.getSentenceToWord();
		StudentModel studentUpdated = new StudentModel();
		String prevWord = "";
		String prevAction = "";
		double prevSkillValue = 0.0;
		double prevSkillSameWord = 0.0;
		String actualSkill = "";
		double skillEvaluated = 0.0;
		double newSkill = 0.0;
		ArrayList<Double> skillValues;
		String[] answerArray;
		String[] actionArray;

		HashMap<String, Double> wordSkill = new HashMap<String, Double>();
		for (String sentence : storySentenceWord.getSentenceToWord().keySet()) {
			for (String word : storySentenceWord.getSentenceToWord().get(sentence)) {
				wordSkill.put(word, student.getInitialSkillValue());
			}
		}

		Skill skill = student.getSkill();

		for (String sentence : storySentenceWord.getSentenceToWord().keySet()) {
			String answers = student.getSentenceAnswers().get(sentence);
			String actions = student.getSentenceActions().get(sentence);
			if (answers != null && actions != null) {
				answerArray = answers.split(" ");
				actionArray = actions.split(" /// ");
				for (String word : storySentenceWord.getSentenceToWord().get(sentence)) {
					for (int i = 0; i < answerArray.length; i++) {
						// //////////
						System.out.println(Arrays.toString(answerArray));
						System.out.println(Arrays.toString(actionArray));
						System.out.println("word=" + word);
						// ////////////
						actualSkill = word + "_" + actionArray[i];
						System.out.println("actualskill=" + actualSkill);
						skillValues = student.getSkill().getSkillToValue().get(actualSkill);
						prevSkillValue = wordSkill.get(word);
						System.out.println("prevSkill=" + prevSkillValue);
						if (answerArray[i].equalsIgnoreCase(Constants.CORRECT)) {
							// calculate skill for correct
							skillEvaluated = calcCorrect(student, prevSkillValue);

						} else if (answerArray[i].equalsIgnoreCase(Constants.INCORRECT)) {
							// calculate skill for incorrect
							skillEvaluated = calcIncorrect(student, prevSkillValue);

						}
						newSkill = calcNewSkillValue(student, skillEvaluated);
						skillValues.add(newSkill);
						student.getSkill().getSkillToValue().put(word, skillValues);
						wordSkill.put(word, newSkill);
						// ///////////
						System.out.println("new skill=" + newSkill);
						System.out.println(Arrays.toString(skillValues.toArray()));
						// /////////

					}
				}
			}
		}
		System.out.println("[[[[[[[[[[[[" + student.getParticipantId());
		return student;
	}

	private double calcCorrect(StudentModel student, double prevSkillValue) {

		return (prevSkillValue * (1 - student.getSlip()))
				/ (prevSkillValue * (1 - student.getSlip()) + (1 - prevSkillValue) * student.getGuess());

	}

	private double calcIncorrect(StudentModel student, double prevSkillValue) {

		return ((student.getSlip() * prevSkillValue) + ((1 - student.getGuess()) * (1 - prevSkillValue)));

	}

	private double calcNewSkillValue(StudentModel student, double skillEvaluated) {
		return skillEvaluated + ((1 - skillEvaluated) * student.getTransition());
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
		this.student = student;
	}

	private String capitalize(String line) {
		return Character.toUpperCase(line.charAt(0)) + line.substring(1);
	}
}
