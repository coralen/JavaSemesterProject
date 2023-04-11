package semesterProject;
import semesterProject.answer;
import semesterProject.question;

public class test {
	
	String type;
	question[] questions;
	
	public void setQuestions(question questionInput) {
		int index = getNextIndex();
		this.questions[index] = questionInput;
	}
	
	public question[] getQuestions() {
		return this.questions;
	}
	
	public int getNextIndex() {
		for (int i=0; i<this.questions.length; i++) {
			if (this.questions[i] == null) {
				return i;
			}
		}
		return -1;	
	}
}
