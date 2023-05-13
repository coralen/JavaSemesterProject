package BarGvili_206419152_CoralEngel_208455659;

public class ExamRepo {

	private Question[] questions;

	public ExamRepo(int numberOfQuestions) {
		this.questions = new Question[numberOfQuestions];
	}

	public Question[] getQuestions() {
		return this.questions;
	}

	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}

	public void addQuestion(String question, String type, Level questionLevel) {
		Question questionToAdd;
		if (type.equals("open")) {
			questionToAdd = new OpenQuestion(question, questionLevel);
		}else{
			questionToAdd = new MultiChoiceQuestion(question, questionLevel);
		}
		for (int i = 0; i < this.questions.length; i++) {
			if (this.questions[i] == null) {
				this.questions[i] = questionToAdd;
				i = this.questions.length;
			}
		}
	}

	public void addMultiChoiceAnswer(int questionIndex, String answer, boolean answerType) { //We need the type of question

		Answer newAnswer = new Answer(answer, answerType);
		Answer[] answers = ((MultiChoiceQuestion)this.questions[questionIndex]).getAnswers();
		Question[] questionArray; 
		
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == null) {
				answers[i] = newAnswer;
				questionArray = this.questions;
				((MultiChoiceQuestion)questionArray[questionIndex]).setAnswers(answers);
				this.setQuestions(questionArray);
				i = answers.length;
			}
		}
	}
	
	public void addOpenAnswer(int questionIndex, String newAnswer) {
		Question[] questionArray; 
		
		questionArray = this.questions;
		((OpenQuestion)questionArray[questionIndex]).setAnswer(newAnswer);
		this.setQuestions(questionArray);
	}

	public void deleteMultiChoiceAnswer(int questionIndex, int answerIndex) {
		
		Question question = this.questions[questionIndex];
		Answer[] answers = ((MultiChoiceQuestion)question).getAnswers();
		answers[answerIndex] = null;
		((MultiChoiceQuestion)this.questions[questionIndex]).setAnswers(answers);
	}
	
	public void deleteOpenAnswer(int questionIndex){
		
		((OpenQuestion)this.questions[questionIndex]).setAnswer(null);
	}

	public void deleteQuestion(int questionInput) {

		this.questions[questionInput] = null;
	}

	public void createQuestions() {

		Answer ans1 = new Answer("Uranus", false);
		Answer ans2 = new Answer("Saturn", false);
		Answer ans3 = new Answer("Jupiter", true);
		Answer[] answers = new Answer[10];
		answers[0] = ans1;
		answers[1] = ans2;
		answers[2] = ans3;
		MultiChoiceQuestion qst1 = new MultiChoiceQuestion("Which is the largest planet in the solar system?", answers, Level.HIGH);

		ans1 = new Answer("Pacific ocean", true);
		ans2 = new Answer("Atlantic ocean", false);
		ans3 = new Answer("Indian ocean", false);
		Answer[] answers2 = new Answer[10];
		answers2[0] = ans1;
		answers2[1] = ans2;
		answers2[2] = ans3;
		MultiChoiceQuestion qst2 = new MultiChoiceQuestion("which is the world’s largest ocean?", answers2, Level.MEDIUM);
		
		OpenQuestion qst3 = new OpenQuestion("Who was the first presidente of the USA?", "George Washington", Level.LOW);
		this.questions[0] = qst1;
		this.questions[1] = qst2;
		this.questions[2] = qst3;

	}

	public int getNumberOfQuestions() {
		int numOfQst = 0;
		for (int i = 0; i < this.questions.length; i++) {
			if (this.questions[i] != null) {
				numOfQst++;
			}
		}
		return numOfQst;
	}

}
