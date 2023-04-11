package semesterProject;

public class answer {

	String answer;
	boolean isRightAnswer;

	public answer(String answer) {
		this.answer = answer;
	}

	public answer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}

	public answer(String answer, boolean isRightAnswer) {
		this.answer = answer;
		this.isRightAnswer = isRightAnswer;
	}

	public String getAnswer() {
		return answer;
	}

	public boolean getIsRightAnswer() {
		return isRightAnswer;
	}

	public void printAnswer() {
		System.out.println(this.answer);
	}

	public void printIsRightAnswer() {
		if (this.isRightAnswer) {
			System.out.print("This is the right answer");
		} else {
			System.out.print("This is not the right answer");
		}
	}

}
