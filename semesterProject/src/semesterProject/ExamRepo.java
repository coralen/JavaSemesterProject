package semesterProject;

public class ExamRepo {
	
	private Question[] questions;
	
	public ExamRepo(int numOfQuestions) {
		this.questions = new Question[numOfQuestions];
	}
	
	public Question[] getQuestions(){
		return this.questions;
	}
	
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
	
	public void addQuestion(String qst) {
		boolean noRoomLeft = true;
		
		for (int i = 0; i < this.questions.length; i++) {
			if (this.questions[i] == null) {
				this.questions[i] = new Question(qst);
				noRoomLeft = false;
				i = this.questions.length;
				System.out.println("Your question has been added");
			}
		}
		if (noRoomLeft) {
			System.out.println("Limit has been reached.");
		}
	}
	
	public void addAnswer(int qstIdx, String ans, boolean ansType) {
		
		boolean noRoomLeft = true;
		Answer newAns = new Answer(ans, ansType);
		Answer[] answers = this.questions[qstIdx].getAnswers();
		Question[] qstArr;
		
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == null) {
				answers[i] = newAns;				
				qstArr = this.questions;
				qstArr[qstIdx].setAnswers(answers);
				this.setQuestions(qstArr);
				noRoomLeft = false;
				i = answers.length;
				System.out.println("Your answer has been added");
			}
		}
		if (noRoomLeft) {
			System.out.println("There are already 10 answers. Limit has been reached.");
		}
	}
		
	
	public void deleteAnswer(int qstIdx,int ansIdx) {
		
		Question qst = this.questions[qstIdx];
		qst.getAnswers()[ansIdx] = null;
		this.questions[qstIdx] = qst;
	}
	
	public void deleteQuestion(int questionInput){
		
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
		Question qst1 = new Question("Which is the largest planet in the solar system?", answers);
		
		ans1 = new Answer("Pacific ocean", true);
		ans2 = new Answer("Atlantic ocean", false);
		ans3 = new Answer("Indian ocean", false);
		Answer[] answers2 = new Answer[10];
		answers2[0] = ans1;
		answers2[1] = ans2;
		answers2[2] = ans3;
		Question qst2 = new Question("which is the world’s largest ocean?", answers2);
		
		this.questions[0] = qst1;
		this.questions[1] = qst2;
		
	}
	
}
