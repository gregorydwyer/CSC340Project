/**
 * this class uses a scanner to read a text file line by line display the contents
 * @author anthony cardenas barrera
 * last updated: 2/24/20
 */
import java.io.*;
import java.util.Scanner;

public class FileReader {

    private Scanner scores;

    public void openFile() throws FileNotFoundException {

        try {
            scores = new Scanner(new File("src/GameScores.txt"));
        } catch (Exception e) {
            System.out.println("could not find file");
        }
    }

    public void readFile() {
        while (scores.hasNext()) {
            String rank = scores.next();
            String player = scores.next();
            String score = scores.next();

            System.out.printf("%s %s %s\n", rank, player, score);
        }
    }

    public void closeFile() {
        scores.close();
    }
}