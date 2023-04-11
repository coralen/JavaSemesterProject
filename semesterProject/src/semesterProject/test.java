package semesterProject;

public class test {

	question[] questions;

	public test(question[] questions) {
		this.questions = questions;
	}

	public void printTest() {
		for (question qst : questions) {
			qst.printQuestion();
			qst.printAnswers();
		}
	}

	public void addQuestion(question qst) {
		boolean noRoomLeft = true;
		for (int i = 0; i < this.questions.length; i++) {
			if (this.questions[i] == null) {
				this.questions[i] = qst;
				noRoomLeft = false;
			}
		}
		if (noRoomLeft) {
			System.out.println("Limit has been reached.");
		}
	}
}
