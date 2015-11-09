import java.util.HashMap;
import java.util.LinkedHashSet;

public class Students {
	private LinkedHashSet<String> studentIds;
	private HashMap<String, StudentModel> idToStudentModel;

	public Students() {
		studentIds = new LinkedHashSet<String>();
		idToStudentModel = new HashMap<String, StudentModel>();
	}

	public Students(LinkedHashSet<String> studentIds, HashMap<String, StudentModel> idToStudentModel) {
		studentIds = new LinkedHashSet<String>();
		this.studentIds = studentIds;
		this.idToStudentModel = idToStudentModel;
	}

	public HashMap<String, StudentModel> getIdToStudentModel() {
		return idToStudentModel;
	}

	public void setIdToStudentModel(HashMap<String, StudentModel> idToStudentModel) {
		this.idToStudentModel = idToStudentModel;
	}

	public LinkedHashSet<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(LinkedHashSet<String> studentIds) {
		this.studentIds = studentIds;
	}

	public void display() {
		System.out.println("Student ids:");
		for (String id : this.getStudentIds()) {
			System.out.println(id);
		}
	}
}
