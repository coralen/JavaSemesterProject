package BarGvili_206419152_CoralEngel_208455659;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		int userChoice, numOfQuestionsSelected, numOfAnswersSelected, questionIndex, answerIndex, questionNumber,
				answerNumber, numOfQuestionsAllowed, numOfAnswersAllowed, finalQuestionIndex = 0, isRightCount = 0,
				questionType, levelOfQuestion;
		String newAns, qstString, ansString;
		boolean ansType, newAnsType;
		Question[] questions, finalQuestions;
		Answer[] answers, finalAnswers;
		Answer ans;
		ExamRepo myTest = new ExamRepo(10), finalTest;
		Scanner scanSelection = new Scanner(System.in);
		Scanner scanAnswer = new Scanner(System.in);
		PrintWriter printerExam, printerSolution;
		myTest.createQuestions();

		do {
			System.out.println("1 - Show all the current questions and following answers\n"
					+ "2 - Add a new answer to a question\n" 
					+ "3 - Add a new question\n" 
					+ "4 - Delete an answer\n"
					+ "5 - Delete a question with its answers\n" 
					+ "6 - Create a test\n" 
					+ "0 - Exit the menu\n");

			userChoice = scanSelection.nextInt();

			switch (userChoice) {
			case 1:
				printPoolOfQuestions(myTest.getQuestions(), answers, questionNumber, answerNumber, qstString);
				break;

			case 2:
				addNewAnswer(scanAnswer, myTest);
				scanAnswer.nextLine();
				break;

			case 3:
				addNewQuestion(scanAnswer, myTest);
				scanAnswer.nextLine();
				break;

			case 4:
				deleteAnswer(scanAnswer, myTest);
				scanAnswer.nextLine();
				break;

			case 5:
				deleteQuestion(scanAnswer, myTest);
				scanAnswer.nextLine();
				break;

			case 6:
				finalQuestionIndex = 0;
				questions = myTest.getQuestions();
				numOfQuestionsAllowed = myTest.getNumberOfQuestions();
				printerExam = getExamPrinter();
				printerSolution = getSolutionPrinter();
				MultiChoiceQuestion multiQuestion;
				OpenQuestion openQuestion;
				Question question;
				
				System.out.println("Please enter how many questions you want in the exam");
				numOfQuestionsSelected = scanAnswer.nextInt();

				while (numOfQuestionsSelected > numOfQuestionsAllowed) {
					System.out.println("You chose too many questions. Please enter a different amount");
					numOfQuestionsSelected = scanAnswer.nextInt();
				}
				finalTest = new ExamRepo(numOfQuestionsSelected);

				for (int i = 0; i < numOfQuestionsSelected; i++) {
					System.out.println("Enter the index of the question you want to add to the exam");
					scanAnswer.nextLine();
					questionIndex = scanAnswer.nextInt();
					question = questions[questionIndex];

					while (questionIndex > numOfQuestionsAllowed || questionIndex < 1) {
						System.out.println("This question doesn't exist. Please enter a different index of question");
						questionIndex = scanAnswer.nextInt()-1;
					}
					
					if (question.getClass() == MultiChoiceQuestion.class) {
						questionType = 1;
					}
					else {
						questionType = 2;
					}
					
					finalTest.addQuestion(question.getQuestion(), questionType, questions[questionIndex].getLevel());
					printerExam.print("  " + (i + 1) + ") " + question.getQuestion() + "\n");
					printerSolution.print("  " + (i + 1) + ") " + question.getQuestion() + "\n");

					if (questionType == 1) {
						multiQuestion = ((MultiChoiceQuestion) question);
						numOfAnswersAllowed = multiQuestion.getNumberOfAnswers();
						answers = multiQuestion.getAnswers();
					}
 
					System.out.println("Enter the indexes of the answers to this question."
							+ "Press any key other than a number to stop");

					numOfAnswersSelected = 0;
					isRightCount = 0;
					while (scanAnswer.hasNextInt()) {
						answerIndex = scanAnswer.nextInt();
						while (answerIndex > numOfAnswersAllowed || answerIndex < 1) {
							System.out.println("Invalid index for an answer");
							answerIndex = scanAnswer.nextInt();
						}
						numOfAnswersSelected++;
						ans = answers[answerIndex - 1];
						if (ans.getIsRightAnswer()) {
							isRightCount++;
						}
						finalTest.addAnswer(i, ans.getAnswer(), ans.getIsRightAnswer());
						System.out.println("Enter another index");
					}

					finalQuestions = finalTest.getQuestions();
					finalAnswers = finalQuestions[finalQuestionIndex].getAnswers();

					int j;
					for (j = 0; j < numOfAnswersSelected; j++) {
						if (isRightCount > 1 || isRightCount == 0) {
							finalAnswers[j].setIsRightAnswer(false);
						}
						finalQuestions[finalQuestionIndex].setAnswers(finalAnswers);
						finalTest.setQuestions(finalQuestions);
						printerExam.print("    " + (j + 1) + ") " + finalAnswers[j].getAnswer() + "\n");
						printerSolution.print("    " + (j + 1) + ") " + finalAnswers[j].getAnswer() + " - "
								+ finalAnswers[j].getIsRightAnswer() + "\n");
					}

					printerExam.print("    " + (j + 1) + ") More than one answer is correct\n" + "    " + (j + 2)
							+ ") No answer is correct\n");
					if (isRightCount > 1) {

						printerSolution.print("    " + (j + 1) + ") More than one answer is correct - true\n" + "    "
								+ (j + 2) + ") No answer is correct - false\n");
					} else if (isRightCount == 0) {
						printerSolution.print("    " + (j + 1) + ") More than one answer is correct - false\n" + "    "
								+ (j + 2) + ") No answer is correct - true\n");
					} else {
						printerSolution.print("    " + (j + 1) + ") More than one answer is correct - false\n" + "    "
								+ (j + 2) + ") No answer is correct - false\n");
					}

					finalQuestionIndex++;
					scanAnswer.nextLine();
				}
				scanAnswer.nextLine();
				printerExam.close();
				printerSolution.close();
				break;

			}

		} while (userChoice != 0);
	}

	public static String getTestDateTime() {
		LocalDateTime myDateTime = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
		String formattedDate = myDateTime.format(myFormatObj);
		return formattedDate;
	}

	public static PrintWriter getExamPrinter() throws FileNotFoundException {

		String formattedDate = getTestDateTime();
		File exam = new File("exam_" + formattedDate + ".txt");
		PrintWriter examPrinter = new PrintWriter(exam);
		return examPrinter;
	}

	public static PrintWriter getSolutionPrinter() throws FileNotFoundException {

		String formattedDate = getTestDateTime();
		File solution = new File("solution_" + formattedDate + ".txt");
		PrintWriter solutionPrinter = new PrintWriter(solution);
		return solutionPrinter;
	}

	public static void addNewAnswer(Scanner scanAnswer, ExamRepo myTest) {
		int questionIndex;
		String newAnswerString;
		Boolean newAnswerType;
		Question[] questions = myTest.getQuestions();

		System.out.println("To add an answer, Please enter the index of the question");
		questionIndex = scanAnswer.nextInt();
		System.out.println("Please enter a new answer");
		scanAnswer.nextLine();
		newAnswerString = scanAnswer.nextLine();
		if (questions[questionIndex].getClass() == MultiChoiceQuestion.class) {
			System.out.println("Please enter true or false for this answer");
			newAnswerType = scanAnswer.nextBoolean();
			myTest.addMultiChoiceAnswer(questionIndex, newAnswerString, newAnswerType);
		} else {
			myTest.addOpenAnswer(questionIndex, newAnswerString);
		}

	}

	public static void addNewQuestion(Scanner scanAnswer, ExamRepo myTest) {
		int questionLevel, questionType;
		Level questionLevelObj = null;
		String questionString;

		System.out.println("Please enter a new question");
		questionString = scanAnswer.nextLine();
		System.out.println("Choose the difficulty of the question: 1-low, 2-medium, 3-high");
		questionLevel = scanAnswer.nextInt();
		System.out.println("Choose the type of the question: 1-multi choice, 2-open");
		questionType = scanAnswer.nextInt();

		switch (questionLevel) {
		case 1:
			questionLevelObj = Level.LOW;
			break;
		case 2:
			questionLevelObj = Level.MEDIUM;
			break;
		case 3:
			questionLevelObj = Level.HIGH;
			break;
		}

		myTest.addQuestion(questionString, questionType, questionLevelObj);

	}

	public static void deleteAnswer(Scanner scanAnswer, ExamRepo myTest) {
		int questionIndex, answerIndex;
		Question question;

		System.out.println("To delete an answer, Please enter the index of the question");
		questionIndex = scanAnswer.nextInt() - 1;
		question = myTest.getQuestions()[questionIndex];

		if (question.getClass() == MultiChoiceQuestion.class) {
			System.out.println("Please enter the index of the answer");
			answerIndex = scanAnswer.nextInt() - 1;

			myTest.deleteMultiChoiceAnswer(questionIndex, answerIndex);
		} else {
			myTest.deleteOpenAnswer(questionIndex);
		}

		System.out.println("Your answer has been deleted");
	}

	public static void deleteQuestion(Scanner scanAnswer, ExamRepo myTest) {
		int questionIndex;

		System.out.println("Please enter the index of the question that you want to delete");
		questionIndex = scanAnswer.nextInt() - 1;
		myTest.deleteQuestion(questionIndex);
		System.out.println("Your question has been deleted");
	}

	public static void printPoolOfQuestions(Question[] questions, Answer[] answers, int questionNumber,
			int answerNumber, String qstString) {

		questionNumber = 1;
		MultiChoiceQuestion multiQuestion;
		OpenQuestion openQuestion;

		for (int i = 0; i < questions.length; i++) {
			answerNumber = 1;
			if (questions[i] != null) {

				System.out.println((questionNumber) + ") " + questions[i].getQuestion());
				questionNumber++;

				if (questions[i].getClass() == MultiChoiceQuestion.class) {
					multiQuestion = (MultiChoiceQuestion) questions[i];
					System.out.println(multiQuestion.getAnswersForDisplay());
				}

				if (questions[i].getClass() == OpenQuestion.class) {
					openQuestion = ((OpenQuestion) questions[i]);
					System.out.println(openQuestion.getAnswer());
				}

			}
		}
	}
}
