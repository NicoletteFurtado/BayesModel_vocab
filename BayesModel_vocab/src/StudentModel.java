import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class StudentModel {
	// private double prior = 0.35; // 0.1
	// private double skill = 0.35;
	private double guess = 0.1; // 0.25 //0.6
	private double transition = 0.1; // 0.1 //0.7 //0.4
	private double slip = 0.1; // 0.4 //0.1
	private final double initialSkillValue = 0.35;

	private String actor = "";
	private String participantId = "";

	private Skill skill;
	private HashMap<String, String> sentenceAnswers; // answer_action
	private HashMap<String, String> sentenceActions;
	private ArrayList<String> actions;

	// private HashMap<String, String>

	public StudentModel() {
		this.actor = "";
		this.participantId = "";
	}

	public StudentModel(String actor, String participantId) {
		this.actor = actor;
		this.participantId = participantId;
		this.skill = new Skill();
		this.sentenceAnswers = new HashMap<String, String>();
		this.sentenceActions = new HashMap<String, String>();
		actions = new ArrayList<String>();
		actions.add(Constants.MOVE_TO_HOTSPOT);
		actions.add(Constants.MOVE_TO_OBJECT);
		actions.add(Constants.TAP);
	}

	public void display() {
		Set<String> wordSet = this.getSkill().skillToValue.keySet();
		Iterator<String> wordIter = wordSet.iterator();
		String id = this.getParticipantId();
		System.out.println(id.toString());
		while (wordIter.hasNext()) {
			System.out.println(wordIter.next());
		}
		System.out.println("sentenceanwers");
		for (String sentence : this.sentenceAnswers.keySet()) {
			System.out.println("////////// " + this.sentenceAnswers.get(sentence));
		}
	}

	public double getGuess() {
		return guess;
	}

	public void setGuess(double guess) {
		this.guess = guess;
	}

	public double getTransition() {
		return transition;
	}

	public void setTransition(double transition) {
		this.transition = transition;
	}

	public double getSlip() {
		return slip;
	}

	public void setSlip(double slip) {
		this.slip = slip;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public double getInitialSkillValue() {
		return initialSkillValue;
	}

	public HashMap<String, String> getSentenceAnswers() {
		return sentenceAnswers;
	}

	public void setSentenceAnswers(HashMap<String, String> sentenceAnswers) {
		this.sentenceAnswers = sentenceAnswers;
	}

	public HashMap<String, String> getSentenceActions() {
		return sentenceActions;
	}

	public void setSentenceActions(HashMap<String, String> sentenceActions) {
		this.sentenceActions = sentenceActions;
	}

	public ArrayList<String> getActions() {
		return actions;
	}

	public void setActions(ArrayList<String> actions) {
		this.actions = actions;
	}

}
