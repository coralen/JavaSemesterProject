package semesterProject;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {

		test myTest = null;
		int userChoice = 0;
		Scanner scanInput = new Scanner(System.in);

		do {
			System.out.println("To show all the current questions and corresponding answers, press 1.");
			System.out.println("To add a new answer to a question, press 2");
			System.out.println("TO add a new question,press 3");
			System.out.println("To delete an answer from a question, press 6");
			System.out.println("To delete a question with its answers, press 7");
			System.out.println("To create a test press 8");
			userChoice = scanInput.nextInt();

			switch (userChoice) {
			case 1: {
				myTest.printTest();
			}
			case 3: {
				String questionInput;

				System.out.println("enter your quesion.");
				questionInput = scanInput.next();
				question qst = new question(questionInput);
				myTest.addQuestion(qst);
			}
			}
		} while (userChoice != -1);

	}
}
