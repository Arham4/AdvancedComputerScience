package org.friscoisd.k12.linkedlist.solitaire.card;

/**
 * Created by 142817 on 9/12/2018.
 */
public class Card {
    private final String[][] numberToRankName = {
            {"2", "TWO"},
            {"3", "THREE"},
            {"4", "FOUR"},
            {"5", "FIVE"},
            {"6", "SIX"},
            {"7", "SEVEN"},
            {"8", "EIGHT"},
            {"9", "NINE"},
            {"T", "TEN"}
    };
    private final String[][] abbreviationToFullName = {
            {"D", "DIAMONDS"},
            {"H", "HEARTS"},
            {"C", "CLUBS"},
            {"S", "SPADES"}
    };

    private Rank rank;
    private Type type;

    public Card(String cardAsString) {
        String rankAsString = cardAsString.split("")[0];
        for (String[] pair : numberToRankName) {
            if (pair[0].equals(rankAsString)) {
                rankAsString = pair[1];
                break;
            }
        }
        String fullTypeName = cardAsString.split("")[1];
        for (String[] pair : abbreviationToFullName) {
            if (pair[0].equals(fullTypeName)) {
                fullTypeName = pair[1];
                break;
            }
        }
        rank = Rank.valueOf(rankAsString);
        type = Type.valueOf(fullTypeName);
    }

    @Override
    public String toString() {
        return "Card " + rank.toString() + " " + type.toString();
    }

    public Rank getRank() {
        return rank;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Card) {
            Card otherCard = (Card) obj;
            return otherCard.getRank() == rank || otherCard.getType() == type;
        }
        return super.equals(obj);
    }
}