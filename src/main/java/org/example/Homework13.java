package org.example;

import java.util.Scanner;

public class Homework13 {
    public static void main(String[] args) {
        DeckOfCards myDeckOfCards = new DeckOfCards();
        myDeckOfCards.shuffle();

        Scanner scanner = new Scanner(System.in);

        // 遊戲開始，玩家和莊家各發兩張牌
        Card playerCard1 = myDeckOfCards.dealCard();
        Card playerCard2 = myDeckOfCards.dealCard();
        Card dealerCard1 = myDeckOfCards.dealCard();
        Card dealerCard2 = myDeckOfCards.dealCard();

        int playerScore = calculateScore(playerCard1, playerCard2);
        int dealerScore = calculateScore(dealerCard1, dealerCard2);

        // 顯示初始牌面
        System.out.println("玩家的牌: " + playerCard1 + ", " + playerCard2);
        System.out.println("莊家的牌: " + dealerCard1 + ", " + dealerCard2);

        // 玩家回合
        while (playerScore < 21) {
            // 可以選擇是否再拿一張牌
            System.out.println("玩家當前分數: " + playerScore);
            System.out.print("要再拿一張牌嗎？(Y/N): ");
            char choice = scanner.next().charAt(0);

            if (choice == 'Y' || choice == 'y') {
                // 玩家選擇再拿一張牌
                Card newCard = myDeckOfCards.dealCard();
                System.out.println("玩家抽到的牌: " + newCard);
                playerScore += getCardValue(newCard, playerScore);
            } else {
                // 玩家選擇停止拿牌
                break;
            }
        }

        // 莊家回合
        while (dealerScore < 17) {
            // 莊家在分數小於17時必須繼續拿牌
            Card newCard = myDeckOfCards.dealCard();
            System.out.println("莊家抽到的牌: " + newCard);
            dealerScore += getCardValue(newCard, dealerScore);
        }

        // 顯示最終結果
        System.out.println("\n最終結果:");
        System.out.println("玩家的最終分數: " + playerScore);
        System.out.println("莊家的最終分數: " + dealerScore);

        // 判斷勝負
        if (playerScore == 21 || (playerScore < 21 && (dealerScore > 21 || playerScore > dealerScore))) {
            System.out.println("玩家勝利！");
        } else if (dealerScore == 21 || (dealerScore <= 21 && dealerScore >= playerScore)) {
            System.out.println("莊家勝利！");
        } else {
            System.out.println("遊戲平局！");
        }

        scanner.close();
    }

    // 計算牌的點數（A的計算可能有兩種情況）
    private static int getCardValue(Card card, int currentScore) {
        if (card.getNumber() == 1) {
            // 如果是A，根據當前分數判斷是否將A視為11點
            return currentScore + 11 <= 21 ? 11 : 1;
        } else if (card.getNumber() >= 10) {
            // J、Q、K 每張為10點
            return 10;
        } else {
            // 2點至10點的牌以牌面的點數計算
            return card.getNumber();
        }
    }

    // 計算牌的點數（A的計算可能有兩種情況）
    private static int calculateScore(Card card1, Card card2) {
        return getCardValue(card1, 0) + getCardValue(card2, 0);
    }
}
