import java.util.ArrayList;
import java.util.HashMap;

public class Skill {
	private String word = "";
	private String action = "";
	private ArrayList<Double> skillValue;
	HashMap<String, ArrayList<Double>> skillToValue;
	private ArrayList<String> actionList;

	public Skill() {
		skillToValue = new HashMap<String, ArrayList<Double>>();
		skillValue = new ArrayList<Double>();
		actionList = new ArrayList<String>();
		actionList.add(Constants.MOVE_TO_HOTSPOT);
		actionList.add(Constants.MOVE_TO_OBJECT);
		actionList.add(Constants.TAP);
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public ArrayList<Double> getSkillValue() {
		return skillValue;
	}

	public void setSkillValue(ArrayList<Double> skillValue) {
		this.skillValue = skillValue;
	}

	public HashMap<String, ArrayList<Double>> getSkillToValue() {
		return skillToValue;
	}

	public void setSkillToValue(HashMap<String, ArrayList<Double>> skillToValue) {
		this.skillToValue = skillToValue;
	}

	public boolean insertIntoSkillToValue(String word, double skillValue) {
		HashMap<String, ArrayList<Double>> wordSentenceMap = this.getSkillToValue();
		if (wordSentenceMap.containsKey(word)) {
			ArrayList<Double> skillList = wordSentenceMap.get(word);
			if (skillList != null) {
				skillList.add(skillValue);
				return true;
			} else {
				wordSentenceMap.put(word, skillList);
				return true;
			}
		}
		return false;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public ArrayList<String> getActionList() {
		return actionList;
	}

	// private void setActionList(ArrayList<String> actionList) {
	// this.actionList = actionList;
	// }
}
