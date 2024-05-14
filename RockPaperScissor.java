import java.util.Scanner;

public class RockPaperScissor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userChoice = "";
        String computerChoice = "";
        String[] choices = {"rock", "paper", "scissors"};

        while (!userChoice.equals("quit")) {
            System.out.println("Enter your choice (rock, paper, scissors) or type 'quit' to exit:");
            userChoice = scanner.nextLine().toLowerCase();

            if (userChoice.equals("quit")) {
                break;
            }

            computerChoice = choices[(int) (Math.random() * choices.length)];

            System.out.println("Computer chose: " + computerChoice);

            if (userChoice.equals(computerChoice)) {
                System.out.println("It's a tie!");
            } else if ((userChoice.equals("rock") && computerChoice.equals("scissors"))
                    || (userChoice.equals("scissors") && computerChoice.equals("paper"))
                    || (userChoice.equals("paper") && computerChoice.equals("rock"))) {
                System.out.println("You win!");
            } else {
                System.out.println("You lose!");
            }
        }

        System.out.println("Thanks for playing!");
        scanner.close();
    }
}