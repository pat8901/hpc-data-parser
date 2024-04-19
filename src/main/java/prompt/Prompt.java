package prompt;

import java.io.IOException;
import java.util.Scanner;

public class Prompt {

    public String startPrompt() throws IOException {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        if (isWindows) {
            System.out.println("This program is not compatible with Windows.");
            System.out.println("Shutting down...");
            System.exit(-1);
        }
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            System.out.print("Enter input: ");
            input = scanner.nextLine();

            if (input.equals("--help")) {
                helpMenu();
            } else {
                break;
            }
        }

        scanner.close();
        return input;
    }

    public void helpMenu() {
        System.out.println("This is the help menu");
    }
}
