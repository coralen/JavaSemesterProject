package BarGvili_206419152_CoralEngel_208455659;

public class OpenQuestion extends Question {

	private String answer;

	public OpenQuestion() {
	}

	public OpenQuestion(String question, Level questionLevel) {
		this.question = question;
		this.questionlevel = questionLevel;
		this.serialNumber = Question.counter++;
	}

	public OpenQuestion(String question, String answer, Level questionLevel) {
		this.answer = answer;
		this.question = question;
		this.questionlevel = questionLevel;
		this.serialNumber = Question.counter++;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return this.answer;
	}

	public String getQuestion() {
		return this.question;
	}

	public Level getLevel() {
		return this.questionlevel;
	}

	public void setLevel(Level level) {
		this.questionlevel = level;
	}

	public String getAnswersForDisplay() {
		return this.answer;
	}

}
