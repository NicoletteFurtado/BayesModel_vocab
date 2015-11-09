import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

class ProcessLogData {
	private String filename;

	public ProcessLogData() {

	}

	public ProcessLogData(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public HashSet<String> readSentenceAndWords(String fileName) {
		String line = "";
		// ArrayList<String> result = new ArrayList<String>();
		HashSet<String> result = new HashSet<String>();
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
				String tokens[] = line.split(Constants.CSV_FILE_SEPARATOR);
				result.add(tokens[0].trim().toLowerCase());
			}
			fileReader.close();
			return result;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Returning empty arraylist");
		return result;
	}

	public Students readStudentDetails(String fileName) {

		String line = "";
		Students students = new Students();
		boolean inserted = false;
		String participantId = "";
		String actor = "";
		StudentModel studentModel = new StudentModel();
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			fileReader.readLine();
			while ((line = fileReader.readLine()) != null) {
				String tokens[] = line.split(Constants.CSV_FILE_SEPARATOR);
				for (String token : tokens) {
					token = token.trim();
				}
				// System.out.println(Arrays.toString(tokens));
				if (tokens[0].equalsIgnoreCase("User") && tokens[2].equalsIgnoreCase("PM")) {
					if (!tokens[1].contains("Pilot") && !tokens[1].contains("S00")) {
						if (!students.getStudentIds().contains(tokens[1])) {
							students.getStudentIds().add(tokens[1].trim());
							studentModel = new StudentModel(actor, participantId);
							students.getIdToStudentModel().put(tokens[1], studentModel);
							// inserted = true;
						} else {
							participantId = tokens[1];
							actor = tokens[0];
							if (tokens.length >= 4) {
								if (!tokens[3].equalsIgnoreCase("#NAME?") && !tokens[3].equalsIgnoreCase("NULL")
										&& !tokens[3].equalsIgnoreCase("#N/A")) {
									tokens[3] = tokens[3].toLowerCase(); // sentence text to lower case
									tokens[4] = tokens[4].toLowerCase(); // verification to lower case
									if (studentModel.getSentenceAnswers().containsKey(tokens[3])
											&& !tokens[4].isEmpty() && !tokens[5].isEmpty()) {
										// System.out.println("rrrrrrrrr " + tokens[3]);
										String ans = studentModel.getSentenceAnswers().get(tokens[3]) + " " + tokens[4];
										String action = studentModel.getSentenceActions().get(tokens[3]) + " /// "
												+ tokens[5];
										studentModel.getSentenceAnswers().put(tokens[3], ans);
										studentModel.getSentenceActions().put(tokens[3], action);
										// System.out.println(ans);
									} else if (!tokens[4].isEmpty() && !tokens[5].isEmpty()) {
										// System.out.println("/////////// " + tokens[3]);
										String newAns = "" + tokens[4];
										String action = "" + tokens[5];
										studentModel.getSentenceAnswers().put(tokens[3], newAns);
										studentModel.getSentenceActions().put(tokens[3], action);
										// System.out.println(val);
									}
								}
							}
							students.getIdToStudentModel().put(tokens[1], studentModel);
							// System.out.println(studentModel.getParticipantId());
							// for (String sentence : studentModel.getSentenceAnswers().keySet()) {
							// System.out.println(sentence + " " + studentModel.getSentenceAnswers().get(sentence));
							// }

						}
					}
				}

			}

			fileReader.close();
			return students;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Returning empty arraylist");
		return students;

	}
}
