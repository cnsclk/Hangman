import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HangMan {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Do you want to play as having  1 or 2 player");
        Scanner keyBoard = new Scanner(System.in);
        String players = keyBoard.nextLine();
        String word;
        if(players.equals("1")){
            Scanner scanner = new Scanner(new File(("C:/Users/90535/Desktop/hang_man.txt")));


            ArrayList<String> words = new ArrayList<>();
            while (scanner.hasNextLine()) {
                words.add(scanner.nextLine());
            }
            Random rand = new Random();
            word = words.get(rand.nextInt(words.size()));
        }
        else{

            System.out.println("player 1 please enter your word");
            word = keyBoard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("ready for 2! good luck !");
        }

        System.out.println(word);


        List<Character> PlayerGuesses = new ArrayList<>();

        printWordState(word, PlayerGuesses);
        int wrongCount = 0;
        while (true) {
            printHangman(wrongCount);
            if(wrongCount>=6){
                System.out.println("You loose");
                System.out.println("the word was " + word);
                break;
            }

            printWordState(word,PlayerGuesses);

            if(!getPlayerGuesses(keyBoard,word,PlayerGuesses)){
                wrongCount++;
            }

            if (printWordState(word, PlayerGuesses)) {
                break;
            }
            System.out.println("please enter your guess for the word");
            if(keyBoard.nextLine().equals(word)){
                System.out.println("You win");
                break;
            }
            else{
                System.out.println("Nope!Try again :)");
            }

        }

    }


    private static void printHangman(int wrongCount) {
        System.out.println(" -------");
        System.out.println(" |       |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }
        if (wrongCount >= 2) {
            System.out.print("\\");
        }
        if (wrongCount >= 3) {
            System.out.println(" /");
        }
        if (wrongCount >= 4) {
            System.out.println(" |");
        }
        if (wrongCount >= 5) {
            System.out.print("/");
        }
        if (wrongCount >= 6) {
            System.out.println(" \\");
        } else {
            System.out.println(" ");
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuesses(Scanner keyBoard, String word, List<Character> PlayerGuesses) {
        System.out.println("please enter a letter");
        String letterGuess = keyBoard.nextLine();
        PlayerGuesses.add(letterGuess.charAt(0));
        return word.contains(letterGuess);


    }


    public static boolean printWordState(String word, List<Character> PlayerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (PlayerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
                correctCount++;
            } else {
                System.out.print("-");
            }

        }
        System.out.println();

        return (correctCount == word.length());
    }
}