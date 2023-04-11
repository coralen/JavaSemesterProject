package semesterProject;

public class question {

	String question;
	answer[] answers = new answer[10];

	public question(String question) {
		this.question = question;
	}

	public question(answer[] answers) {
		this.answers = answers;
	}

	public question(String question, answer[] answers) {
		this.question = question;
		this.answers = answers;
	}

	public String getQuestion() {
		return this.question;
	}

	public answer[] getAnswers() {
		return this.answers;
	}

	public void printQuestion() {
		System.out.println(question);
	}

	public void printAnswers() {
		for (answer ans : this.answers) {
			ans.printAnswer();
			System.out.print(" - ");
			ans.printIsRightAnswer();
		}
	}

	public void addAnswer(answer answerInput) {
		boolean noRoomLeft = true;
		for (int i = 0; i < this.answers.length; i++) {
			if (this.answers[i] == null) {
				this.answers[i] = answerInput;
				noRoomLeft = false;
			}
		}
		if (noRoomLeft) {
			System.out.println("There are already 10 answers. Limit has been reached.");
		}
	}

	public void removeAnswer(answer answerInput) {
		for (int i = 0; i < this.answers.length; i++) {
			if (this.answers[i] == answerInput) {
				this.answers[i] = null;
			}
		}
	}

}
