import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    private static final int MAX_ATTEMPTS = 10;
    private static final int RANGE_START = 1;
    private static final int RANGE_END = 100;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int roundsWon = 0;
        boolean playAgain = true;

        while (playAgain) {
            int randomNumber = random.nextInt(RANGE_END - RANGE_START + 1) + RANGE_START;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println("A new round has started! Try to guess the number between " + RANGE_START + " and " + RANGE_END + ".");
            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number.");
                    guessedCorrectly = true;
                    roundsWon++;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low!");
                } else {
                    System.out.println("Too high!");
                }

                if (!guessedCorrectly) {
                    System.out.println("You have " + (MAX_ATTEMPTS - attempts) + " attempts left.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry! You've used all your attempts. The correct number was: " + randomNumber);
            }

            System.out.print("Do you want to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equalsIgnoreCase("yes");
        }

        System.out.println("You won " + roundsWon + " round(s).");
        System.out.println("Thank you for playing!");

        scanner.close();
    }
}
