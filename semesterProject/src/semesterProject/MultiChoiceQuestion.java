package BarGvili_206419152_CoralEngel_208455659;

public class MultiChoiceQuestion extends Question  {
	
	private Answer[] answers = new Answer[10];
	private Level questionlevel;
	
	public MultiChoiceQuestion(){
		this.serialNumber = counter++;
	}
	
	public MultiChoiceQuestion(String question,Level questionLevel) {
		this.question = question;
		this.questionlevel=questionLevel;
		this.serialNumber = counter++;
	}
	
	public MultiChoiceQuestion(String question, Answer[] answers, Level questionlevel) {
		this.question = question;
		this.answers = answers;
		this.questionlevel = questionlevel;
		this.serialNumber = counter++;
	}
	
	public String getQuestion() {
		return this.question;
	}
	
	public Answer[] getAnswers() {
		return this.answers;
	}
	
	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}
	
	public Level getLevel(){
		return this.questionlevel;
	}
	
	public void setLevel(Level level){
		this.questionlevel = level;
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
