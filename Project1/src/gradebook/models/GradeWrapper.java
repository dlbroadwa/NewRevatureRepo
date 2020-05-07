package gradebook.models;

import java.util.ArrayList;
import java.util.List;

public class GradeWrapper {
	public List<GradeDisplay> grades;
	
	public GradeWrapper() {
		grades = new ArrayList<>();
	}

	@Override
	public String toString() {
		return "GradeWrapper [grades=" + grades + "]";
	}
}
