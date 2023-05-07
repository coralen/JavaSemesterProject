package BarGvili_206419152_CoralEngel_208455659;

public class Answer {

	private String answer;
	private boolean isRightAnswer;

	public Answer(String answer) {
		this.answer = answer;
	}

	public Answer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}

	public Answer(String answer, boolean isRightAnswer) {
		this.answer = answer;
		this.isRightAnswer = isRightAnswer;
	}

	public String getAnswer() {
		return this.answer;
	}

	public boolean getIsRightAnswer() {
		return this.isRightAnswer;
	}

	public void setIsRightAnswer(boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}

}
