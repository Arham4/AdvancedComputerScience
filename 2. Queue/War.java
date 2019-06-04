import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class War {
    public static WarQueue playerTwo;
    public static WarQueue playerOne;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner temp = new Scanner(new File("war.txt"));
        // i see nowhere in your code where it takes into account 100,000 hands
        do {
            /*String one = temp.nextLine();
            String two = temp.nextLine();*/

            String[] a = temp.nextLine().split(" ");
            playerOne = new WarQueue(a);

            String[] b = temp.nextLine().split(" ");
            playerTwo = new WarQueue(b);
            for (int i = 0; i < 26; i++) {
                playerOne.enqueue(a[i]);
                playerTwo.enqueue(b[i]);

            }

            //System.out.println(playerOne.toString());
            //System.out.println(playerTwo.toString());
            //System.out.println(playerTwo.peek());
            //System.out.println(playerOne.peek());
            // System.out.println(playerOne.peek() + " " + playerTwo.peek());
            for (int i = 0; i < 100000; i++)
            {
                int hand;
                String card1 = playerOne.dequeue();
                String card2 = playerTwo.dequeue();
                hand = hand(card1, card2);

                // no idea whats happening anymore..
                /*playerOne.dequeue();
                playerTwo.dequeue();*/
                if (hand == 1) {
                    playerOne.enqueue(card1);
                    playerOne.enqueue(card2);
                    // System.out.println(playerOne.peek());
                } else if (hand == 2) {
                    playerTwo.enqueue(card2);
                    playerTwo.enqueue(card1);
                    //System.out.println(playerTwo.peek());
                } else {
                    ArrayList<String> warCards = new ArrayList<>();

                    String first = null;
                    String second = null;

                    int win = 0;
                    do {

                        String down1 = playerOne.dequeue();
                        String down2 = playerTwo.dequeue();

                        first = playerOne.dequeue();
                        second = playerTwo.dequeue();

                        warCards.add(down1);
                        warCards.add(down2);

                        warCards.add(first);
                        warCards.add(second);
                        if(!(playerTwo.size == 0 || playerOne.size == 0))
                        {
                            win = hand(first, second);
                        }
                        else
                        {
                            break;
                        }


                    } while (win == 10);

                    if (win == 1) {
                        for (String card : warCards) {
                            playerOne.enqueue(card);
                        }
                    } else if (win == 2) {
                        for (String card : warCards) {
                            playerTwo.enqueue(card);
                        }
                    }


                    warCards.clear();
                }

                if(i == 99999)
                {
                    System.out.println("tie");
                    break;
                }
                if (playerTwo.size == 0)
                {
                    System.out.println("player 1 wins");
                    break;
                } else if (playerOne.size== 0)
                {
                    System.out.println("player 2 wins");
                    break;
                }
            }

        } while (temp.hasNextLine());

    }
    static int hand(String one, String two) {
        String[] card = one.split("");
        String[] card2 = two.split("");
        int cardOne = 0;
        int cardTwo = 0;
        int win = 0;
        switch (card[0]) {
            case "T":
                cardOne = 10;
                break;
            case "J":
                cardOne = 11;
                break;
            case "Q":
                cardOne = 12;
                break;
            case "K":
                cardOne = 13;
                break;
            case "A":
                cardOne = 14;
                break;
            case "2":
                cardOne = 2;
                break;
            case "3":
                cardOne = 3;
                break;
            case "4":
                cardOne = 4;
                break;
            case "5":
                cardOne = 5;
                break;
            case "6":
                cardOne = 6;
                break;
            case "7":
                cardOne = 7;
                break;
            case "8":
                cardOne = 8;
                break;
            case "9":
                cardOne = 9;
                break;

        }
        switch (card2[0]) {
            case "T":
                cardTwo = 10;
                break;
            case "J":
                cardTwo = 11;
                break;
            case "Q":
                cardTwo = 12;
                break;
            case "K":
                cardTwo = 13;
                break;
            case "A":
                cardTwo = 14;
                break;
            case "2":
                cardTwo = 2;
                break;
            case "3":
                cardTwo = 3;
                break;
            case "4":
                cardTwo = 4;
                break;
            case "5":
                cardTwo = 5;
                break;
            case "6":
                cardTwo = 6;
                break;
            case "7":
                cardTwo = 7;
                break;
            case "8":
                cardTwo = 8;
                break;
            case "9":
                cardTwo = 9;
                break;

        }
        if (cardOne > cardTwo)
            win = 1;
        if (cardTwo > cardOne)
            win = 2;
        if (cardOne == cardTwo)
            win = 10;
        return win;
    }
}