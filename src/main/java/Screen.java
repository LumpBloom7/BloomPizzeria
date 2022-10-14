import java.util.Scanner;

public abstract class Screen {
    private static Scanner input = new Scanner(System.in);

    protected final int requestInput() {
        System.out.print("Please make a choice: ");
        String userInput = input.nextLine();

        try {
            int res = Integer.parseInt(userInput);

            return res;
        } catch (NumberFormatException e) {
        }

        return Integer.MAX_VALUE;
    }
}
