package semesterProject;

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
	
	public void setQuestion(String question){
		this.question = question;
	}
	
	public void setAnswers(Answer[] answers){
		this.answers = answers;
	}

}
