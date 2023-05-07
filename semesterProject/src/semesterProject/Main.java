package BarGvili_206419152_CoralEngel_208455659;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		int userChoice, numOfQuestionsSelected, numOfAnswersSelected, qstIdx, ansIdx, qstNumber, ansNumber,
				numOfQuestionsAllowed, numOfAnswersAllowed, finalQstIdx = 0, isRightCount = 0;
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
					+ "6 - Create a test\n" + "0 - Exit the menu\n");
			
			userChoice = scanSelection.nextInt();

			switch (userChoice) {
			case 1:
				qstNumber = 1;
				questions = myTest.getQuestions();
				for (int i = 0; i < questions.length; i++) {
					ansNumber = 1;

					if (questions[i] != null) {
						qstString = questions[i].getQuestion();
						System.out.println((qstNumber) + ") " + qstString);
						qstNumber++;
						if (questions[i] != null) {
							answers = questions[i].getAnswers();
							for (int j = 0; j < answers.length; j++) {
								if (answers[j] != null) {
									ansString = answers[j].getAnswer();
									ansType = answers[j].getIsRightAnswer();
									System.out.println("	" + ansNumber + ") " + ansString + " - " + ansType);
									ansNumber++;
								}
							}
						}
					}
				}
				break;

			case 2:
				System.out.println("To add an answer, Please enter the index of the question");
				qstIdx = scanAnswer.nextInt();
				System.out.println("Please enter a new answer");
				scanAnswer.nextLine();
				newAns = scanAnswer.nextLine();
				System.out.println("Please enter true or false for this answer");
				newAnsType = scanAnswer.nextBoolean();
				myTest.addAnswer(qstIdx - 1, newAns, newAnsType);
				scanAnswer.nextLine();
				break;

			case 3:
				System.out.println("Please enter a new question");
				String newQst = scanAnswer.nextLine();
				myTest.addQuestion(newQst);
				break;

			case 4:
				System.out.println("To delete an answer, Please enter the index of the question");
				qstIdx = scanAnswer.nextInt();
				System.out.println("Please enter the index of the answer");
				ansIdx = scanAnswer.nextInt();
				myTest.deleteAnswer(qstIdx - 1, ansIdx - 1);
				System.out.println("Your answer has been deleted");
				scanAnswer.nextLine();
				break;

			case 5:
				System.out.println("Please enter the index of the question that you want to delete");
				qstIdx = scanAnswer.nextInt();
				myTest.deleteQuestion(qstIdx - 1);
				System.out.println("Your question has been deleted");
				scanAnswer.nextLine();
				break;

			case 6:
				finalQstIdx = 0;
				questions = myTest.getQuestions();
				numOfQuestionsAllowed = myTest.getNumberOfQuestions();
				printerExam = getExamPrinter();
				printerSolution = getSolutionPrinter();

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
					qstIdx = scanAnswer.nextInt();

					while (qstIdx > numOfQuestionsAllowed || qstIdx < 1) {
						System.out.println("This question doesn't exist. Please enter a different index of question");
						qstIdx = scanAnswer.nextInt();
					}
					numOfAnswersAllowed = questions[qstIdx - 1].getNumberOfAnswers();
					finalTest.addQuestion(questions[qstIdx - 1].getQuestion());
					printerExam.print("  " + (i + 1) + ") " + questions[qstIdx - 1].getQuestion() + "\n");
					printerSolution.print("  " + (i + 1) + ") " + questions[qstIdx - 1].getQuestion() + "\n");

					numOfAnswersAllowed = questions[qstIdx - 1].getNumberOfAnswers();
					answers = questions[qstIdx - 1].getAnswers();
					System.out.println("Enter the indexes of the answers to this question."
							+ "Press any key other than a number to stop");

					numOfAnswersSelected = 0;
					isRightCount = 0;
					while (scanAnswer.hasNextInt()) {
						ansIdx = scanAnswer.nextInt();
						while (ansIdx > numOfAnswersAllowed || ansIdx < 1) {
							System.out.println("Invalid index for an answer");
							ansIdx = scanAnswer.nextInt();
						}
						numOfAnswersSelected++;
						ans = answers[ansIdx - 1];
						if (ans.getIsRightAnswer()) {
							isRightCount++;
						}
						finalTest.addAnswer(i, ans.getAnswer(), ans.getIsRightAnswer());
						System.out.println("Enter another index");
					}

					finalQuestions = finalTest.getQuestions();
					finalAnswers = finalQuestions[finalQstIdx].getAnswers();

					int j;
					for (j = 0; j < numOfAnswersSelected; j++) {
						if (isRightCount > 1 || isRightCount == 0) {
							finalAnswers[j].setIsRightAnswer(false);
						}
						finalQuestions[finalQstIdx].setAnswers(finalAnswers);
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

					finalQstIdx++;
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
		File exam = new File("exam_" + formattedDate);
		PrintWriter examPrinter = new PrintWriter(exam);
		return examPrinter;
	}

	public static PrintWriter getSolutionPrinter() throws FileNotFoundException {

		String formattedDate = getTestDateTime();
		File solution = new File("solution_" + formattedDate);
		PrintWriter solutionPrinter = new PrintWriter(solution);
		return solutionPrinter;
	}

}
