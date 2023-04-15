package semesterProject;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		int userChoice, numOfQst, qstIdx, ansIdx, rightAnsCount, qstNumber, ansNumber, ansCounterForFinal,
				finalQstIdx = 0, isRightCount = 0;
		String newAns, qstString, ansString;
		boolean ansType, newAnsType;
		Question[] questions, finalQuestions;
		Answer[] answers, finalAnswers;
		Answer ans;
		ExamRepo myTest = new ExamRepo(10), finalTest;
		Scanner scanSelection = new Scanner(System.in);
		Scanner scanAnswer = new Scanner(System.in);
		File exam, solution;
		PrintWriter printerExam, printerSolution;
		myTest.createQuestions();

		do {
			System.out.println("1 - Show all the current questions and following answers\n"
					+ "2 - Add a new answer to a question\n" + "3 - Add a new question\n" + "4 - Delete an answer\n"
					+ "5 - Delete a question with its answers\n" + "6 - Create a test\n" + "0 - Exit the menu\n");
			userChoice = scanSelection.nextInt();

			switch (userChoice) {
			case 1:
				qstNumber = 1;
				ansNumber = 1;
				questions = myTest.getQuestions();
				for (int i = 0; i < questions.length; i++) {
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
				break;

			case 3:
				System.out.println("Please enter a new question");
				String newQst = scanAnswer.next();
				myTest.addQuestion(newQst);
				break;

			case 4:
				System.out.println("To delete an answer, Please enter the index of the question");
				qstIdx = scanAnswer.nextInt();
				System.out.println("Please enter the index of the answer");
				ansIdx = scanAnswer.nextInt();
				myTest.deleteAnswer(qstIdx - 1, ansIdx - 1);
				System.out.println("Your answer has been deleted");
				break;

			case 5:
				System.out.println("Please enter the index of the question that you want to delete");
				qstIdx = scanAnswer.nextInt();
				myTest.deleteQuestion(qstIdx - 1);
				System.out.println("Your question has been deleted");
				break;

			case 6:
				LocalDateTime myDateTime = LocalDateTime.now();
				finalQstIdx = 0;
				DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm");
				String formattedDate = myDateTime.format(myFormatObj);
				exam = new File("exam_" + formattedDate);
				solution = new File("solution_" + formattedDate);
				questions = myTest.getQuestions();

				printerExam = new PrintWriter(exam);
				printerSolution = new PrintWriter(solution);

				System.out.println("Please enter how many questions you want in the exam");
				numOfQst = scanAnswer.nextInt();
				finalTest = new ExamRepo(numOfQst);
				for (int i = 0; i < numOfQst; i++) {
					System.out.println("Enter an index of the question you want to add to the exam");
					qstIdx = scanAnswer.nextInt();
					qstIdx--;
					finalTest.addQuestion(questions[qstIdx].getQuestion());
					printerExam.print("  " + (i + 1) + ") " + questions[qstIdx].getQuestion() + "\n");
					printerSolution.print("  " + (i + 1) + ") " + questions[qstIdx].getQuestion() + "\n");

					answers = questions[qstIdx].getAnswers();
					System.out.println("Enter the indexes of the answers to this question."
							+ "Press any key other than a number to stop");

					ansCounterForFinal = 0;
					while (scanAnswer.hasNextInt()) {
						ansIdx = scanAnswer.nextInt();
						ansIdx--;
						ansCounterForFinal++;
						ans = answers[ansIdx];
						if (ans.getIsRightAnswer()) {
							isRightCount++;
						}
						finalTest.addAnswer(i, ans.getAnswer(), ans.getIsRightAnswer());
					}

					finalQuestions = finalTest.getQuestions();
					finalAnswers = finalQuestions[finalQstIdx].getAnswers();

					int j;
					for (j = 0; j < ansCounterForFinal; j++) {
						if (isRightCount > 1 || isRightCount == 0) {
							finalAnswers[j].setIsRightAnswer(false);
						}
						finalQuestions[finalQstIdx].setAnswers(finalAnswers);
						finalTest.setQuestions(finalQuestions);
						printerExam.print((j + 1) + ") " + finalAnswers[j].getAnswer() + "\n");
						printerSolution.print((j + 1) + ") " + finalAnswers[j].getAnswer() + " - "
								+ finalAnswers[j].getIsRightAnswer() + "\n");
					}

					printerExam.print("  " + (j + 1) + ") More than one answer is correct\n" + (j + 2)
							+ ") No answer is correct");
					if (isRightCount > 1) {

						printerSolution.print("  " + (j + 1) + ") More than one answer is correct - true\n" + (j + 2)
								+ ") No answer is correct - false");
					} else if (isRightCount == 0) {
						printerSolution.print("  " + (j + 1) + ") More than one answer is correct - false\n" + (j + 2)
								+ ") No answer is correct - true");
					}
					finalQstIdx++;

				}
				printerExam.close();
				printerSolution.close();
				break;

			}

		} while (userChoice != 0);
	}

}
