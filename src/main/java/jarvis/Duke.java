package jarvis;

import jarvis.exception.EmptyListException;
import jarvis.exception.HandleException;
import jarvis.exception.InvalidCommandException;
import jarvis.exception.InvalidTaskException;
import jarvis.storage.TextManager;
import jarvis.ui.JarvisUi;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Entry point of the JARVIS program.
 * Initialises the program and starts the interaction with the user.
 */
public class Duke {

    public static final JarvisUi jarvis = new JarvisUi();

    public static void main(String[] args) throws InterruptedException {

        jarvis.startJarvis();

        // Reads jarvis.txt file if it exists in the folder
        try {
            TextManager.printFileContents();
            jarvis.printDivider();
        } catch (FileNotFoundException exception) {
            HandleException.handleFileNotFoundException();
        }

        /*
          JARVIS program gets commands from user and executes it.
          jarvis.txt file is written whenever the list is updated.
         */
        while (true) {
            try {
                TextManager.writeToFile(jarvis.performTask());
            } catch (InvalidCommandException exception) {
                HandleException.handleInvalidCommandException();
            } catch (IOException exception) {
                System.out.println("\tSomething went wrong: " + exception.getMessage());
                jarvis.printDivider();
            } catch (EmptyListException exception) {
                HandleException.handleEmptyListException();
            } catch (InvalidTaskException exception) {
                HandleException.handleInvalidTaskException();
            }
        }
    }
}