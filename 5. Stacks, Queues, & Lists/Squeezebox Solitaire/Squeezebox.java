package org.friscoisd.k12.linkedlist.solitaire;

import org.friscoisd.k12.linkedlist.solitaire.card.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Squeezebox {

    public static void main(String[] args) {
        Squeezebox squeezebox = new Squeezebox();
        squeezebox.setupAndSolve();
    }

    private void setupAndSolve() {
        try (Scanner scanner = new Scanner(new File("squeezebox.dat"))) {
            while (!scanner.hasNext("#")) {
                ArrayDeque<Card> cards = new ArrayDeque<>();
                String[][] rawCards = { scanner.nextLine().split(" "), scanner.nextLine().split(" ") };

                Arrays.stream(rawCards).forEach(cardRow ->
                        Arrays.stream(cardRow).forEach(card -> cards.addLast(new Card(card))));

                solve(cards);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void solve(ArrayDeque<Card> cards) {
        LinkedList<LinkedList<Card>> piles = new LinkedList<>();

        do {
            Card cardDrawn = drawCard(cards);
            makeNewPileOfCards(piles, cardDrawn);

            int newPileIndex = piles.size() - 1;
            doMoveIfPossible(piles, newPileIndex);
        } while (cards.size() != 0);

        printResult(piles);
    }

    private void makeNewPileOfCards(LinkedList<LinkedList<Card>> piles, Card cardDrawn) {
        LinkedList<Card> stackOfCards = new LinkedList<>();
        stackOfCards.addFirst(cardDrawn);

        piles.addLast(stackOfCards);
    }

    private Card drawCard(ArrayDeque<Card> cards) {
        return cards.removeFirst();
    }

    private void printResult(LinkedList<LinkedList<Card>> piles) {
        int pilesRemaining = piles.size();
        StringBuilder pilesWithCount = new StringBuilder();
        for (LinkedList<Card> queueOfStacksOfCard : piles) {
            pilesWithCount.append(queueOfStacksOfCard.size()).append(" ");
        }
        System.out.println(pilesRemaining + " piles remaining: " + pilesWithCount.toString());
    }

    private void doMoveIfPossible(LinkedList<LinkedList<Card>> piles, int originIndex) {
        if (matchesNeighborThreeSpacesDown(piles, originIndex)) {
            moveCardThreeSpacesDown(piles, originIndex);
        } else if (matchesDirectNeighbor(piles, originIndex)) {
            moveCardOneSpaceDown(piles, originIndex);
        }
    }

    private void doMoveAcrossAllPiles(LinkedList<LinkedList<Card>> piles) {
        for (int i = 0; i < piles.size(); i++) {
            doMoveIfPossible(piles, i);
        }
    }

    private boolean matchesNeighborThreeSpacesDown(LinkedList<LinkedList<Card>> piles, int pileIndex) {
        return matchesNeighborXSpacesDown(piles, pileIndex, 3);
    }

    private boolean matchesDirectNeighbor(LinkedList<LinkedList<Card>> piles, int pileIndex) {
        return matchesNeighborXSpacesDown(piles, pileIndex, 1);
    }

    private boolean matchesNeighborXSpacesDown(LinkedList<LinkedList<Card>> piles, int pileIndex, int x) {
        if (piles.size() < x || pileIndex >= piles.size() || pileIndex - x < 0) {
            return false;
        }
        int directNeighborIndex = pileIndex - x;
        Card cardInQuestion = piles.get(pileIndex).getFirst();
        Card neighborThreeSpacesDown = piles.get(directNeighborIndex).getFirst();
        return cardInQuestion.equals(neighborThreeSpacesDown);
    }

    private void moveCardThreeSpacesDown(LinkedList<LinkedList<Card>> piles, int pileIndex) {
        moveCardXSpacesDown(piles, pileIndex, 3);
    }

    private void moveCardOneSpaceDown(LinkedList<LinkedList<Card>> piles, int pileIndex) {
        moveCardXSpacesDown(piles, pileIndex, 1);
    }

    private void moveCardXSpacesDown(LinkedList<LinkedList<Card>> piles, int pileIndex, int x) {
        Card cardBeingMoved = piles.get(pileIndex).getFirst();
        int neighborXSpacesDownIndex = pileIndex - x;

        piles.get(neighborXSpacesDownIndex).addFirst(cardBeingMoved); // adds card to top of new pile
        piles.get(pileIndex).removeFirst(); // removes card from old pile

        postMove(piles);
    }

    private void postMove(LinkedList<LinkedList<Card>> piles) {
        cleanAndShiftEmptyPiles(piles);
        doMoveAcrossAllPiles(piles);
    }

    private void cleanAndShiftEmptyPiles(LinkedList<LinkedList<Card>> piles) {
        ArrayList<Integer> emptyIndices = new ArrayList<>();
        for (int i = 0; i < piles.size(); i++) {
            if (piles.get(i).size() == 0) {
                emptyIndices.add(i);
            }
        }
        for (int emptyIndex : emptyIndices) {
            piles.remove(emptyIndex);
        }
    }
}
