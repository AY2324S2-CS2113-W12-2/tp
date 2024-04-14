package activeedge;

import activeedge.ui.CommandUi;
import command.ActiveEdgeException;
import activeedge.parser.Parser;
import activeedge.ui.ByeUi;
import command.Command;

import java.util.Scanner;

public class ActiveEdge {
    /**
     * Main entry-point for the ActiveEdge application.
     */
    public static void run() throws ActiveEdgeException {
        Scanner in = new Scanner(System.in);

        CommandUi.printWelcomeMessage();
        Parser parser = new Parser();

        Storage.fetchData();
        String input = in.nextLine();

        while (!input.toLowerCase().contains("bye")) {
            Command command = parser.handleInput(input);
            if (command != null) {
                command.execute();
                Storage.saveLogsToFile("data/data.txt");
            } else {
                System.out.println("Unknown command.");
            }
            input = in.nextLine();
        }

        ByeUi.printByeMessage();
    }

    public static void main(String[] args) throws ActiveEdgeException {
        new ActiveEdge().run();
    }

}
