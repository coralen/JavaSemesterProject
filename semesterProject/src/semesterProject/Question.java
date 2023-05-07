package BarGvili_206419152_CoralEngel_208455659;

public class Question {

	private String question;
	private Answer[] answers = new Answer[10];

	public Question(String question) {
		this.question = question;
	}

	public Question(Answer[] answers) {
		this.answers = answers;
	}

	public Question(String question, Answer[] answers) {
		this.question = question;
		this.answers = answers;
	}

	public String getQuestion() {
		return this.question;
	}

	public Answer[] getAnswers() {
		return this.answers;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}

	public int getNumberOfAnswers() {
		int numOfQst = 0;
		for (int i = 0; i < this.answers.length; i++) {
			if (this.answers[i] != null) {
				numOfQst++;
			}
		}
		return numOfQst;
	}

}
