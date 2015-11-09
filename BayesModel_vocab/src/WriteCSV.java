import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteCSV {

	public void writeOutput(StudentModel student, StorySentenceWord storySentenceWord) {
		BufferedWriter writer;
		System.out.println(student.getParticipantId());
		System.out.println("//////////" + student.getParticipantId());
		String outputFile = "C:\\Users\\Nicolette\\OneDrive\\Documents\\EMBRACE\\Analysis\\output_"
				+ student.getParticipantId() + ".csv";
		String appender = "";
		ArrayList<Double> skillValues;
		String answerArray[];
		String actionArray[];
		String actualSkill = "";
		String answers = "";
		String actions = "";
		try {
			FileWriter fw = new FileWriter(outputFile);
			writer = new BufferedWriter(fw);
			// headers
			writer.write("Student id \n");
			writer.write(student.getParticipantId() + "\n");

			for (String sentence : storySentenceWord.getSentenceToWord().keySet()) {
				appender = "";
				writer.write(appender + sentence + "\n");
				if (student.getSentenceAnswers() != null) {
					answers = student.getSentenceAnswers().get(sentence);
					actions = student.getSentenceActions().get(sentence);
					if (answers != null && actions != null) {
						answerArray = answers.split(" ");
						actionArray = actions.split(" /// ");

						for (String word : storySentenceWord.getSentenceToWord().get(sentence)) {
							for (int i = 0; i < answerArray.length; i++) {
								appender = "";
								writer.append(appender + word);
								writer.newLine();
								// for everyskill
								actualSkill = word + "_" + actionArray[i];
								skillValues = student.getSkill().getSkillToValue().get(actualSkill);

								appender = "" + actualSkill + ",\n,";

								appender = "";
								for (double skillVal : skillValues) {
									writer.write(skillVal + ",\n,");
								}
								writer.flush();
							}
						}
					}
				}

			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
