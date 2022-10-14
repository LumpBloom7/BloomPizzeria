import java.util.Scanner;

public abstract class Screen {
    private static Scanner input = new Scanner(System.in);

    protected final static int requestInput() {
        System.out.print("Please make a choice: ");
        String userInput = input.nextLine();

        try {
            int res = Integer.parseInt(userInput);

            return res;
        } catch (NumberFormatException e) {
        }

        return Integer.MAX_VALUE;
    }

    protected final static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }
}
