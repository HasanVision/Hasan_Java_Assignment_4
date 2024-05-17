package utility;

import java.util.ArrayList;

import objects.*;

public class Ink {
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_PURPLE = "\u001B[35m";

  public Ink() {
    // do nothing
  }

  public void printWelcome() {
    String welcomeArt = ANSI_GREEN +
        "   _____ _             _    _    _         ____        _    \n" +
        "  / ____| |           | |  | |  (_)       |  _ \\      | |   \n" +
        " | (___ | |_ __ _ _ __| | _| | ___  __ _  | |_) |_   _| | __\n" +
        "  \\___ \\| __/ _` | '__| |/ / |/ / |/ _` | |  _ <| | | | |/ /\n" +
        "  ____) | || (_| | |  |   <|   <| | (_| | | |_) | |_| |   < \n" +
        " |_____/ \\__\\__,_|_|  |_|\\_\\_|\\_\\_|\\__,_| |____/ \\__,_|_|\\_\\\n" + ANSI_RESET;
    System.out.println(welcomeArt);
    System.out.println(ANSI_GREEN + "*** Welcome to StockUP beta ***" + ANSI_RESET + "\n");
  }

  public void printGoodday() {
    String goodbyeArt = ANSI_BLUE +
        "  ____                 _ _                _____          _       \n" +
        " / __ \\               | | |              |  __ \\        | |      \n" +
        "| |  | |_   _____ _ __| | |_   _ _ __ ___| |  | |_   _  | |_   _ \n" +
        "| |  | \\ \\ / / _ \\ '__| | | | | | '__/ _ \\ |  | | | | | | | | | |\n" +
        "| |__| |\\ V /  __/ |  | | | |_| | | |  __/ |__| | |_| |_| | |_| |\n" +
        " \\____/  \\_/ \\___|_|  |_|_|\\__,_|_|  \\___|_____/ \\__,_|_|\\__, |\n" +
        "                                                        __/ |\n" +
        "                                                       |___/ \n" + ANSI_RESET;
    System.out.println(goodbyeArt);
    System.out.println(ANSI_BLUE + "*** Richer Every Day with stockUP ***" + ANSI_RESET + "\n");
  }

  public void printStock(Stock stock) {
    System.out.printf(ANSI_YELLOW + "Name: %s\nSymbol: %s\nPrice: $%.2f" + ANSI_RESET,
        stock.getName(), stock.getSymbol(), stock.getPrice());
    System.out.println("\nHow many units of this stock would you like?? ");
  }

  public void printPortfolio(ArrayList<Stock> stocks, double networth,
      double balance) {
    for (int i = 0; i < stocks.size(); i++) {
      System.out.printf("(%d) Name: %s Symbol: %s Price: $%.2f Qty: %d\n",
          i + 1,
          stocks.get(i).getName(),
          stocks.get(i).getSymbol(),
          stocks.get(i).getPrice(),
          stocks.get(i).getQty());
    } // for
    System.out.printf("Networth: $%.2f\n", networth);
    System.out.printf("Balance: $%.2f\n", balance);
  } // printPortfolio()

  public void printMarket(ArrayList<Stock> stocks) {
    for (int i = 0; i < stocks.size(); i++) {
      System.out.printf("(%d) Name: %s Symbol: %s Price: $%.2f\n",
          i + 1,
          stocks.get(i).getName(),
          stocks.get(i).getSymbol(),
          stocks.get(i).getPrice());
    } // for
    System.out.println("Which stock would you like to buy?: ");
  } // printMarket()

  public void printMenu() {
    System.out.println(ANSI_YELLOW + "(1) Show Portfolio" + ANSI_RESET);
    System.out.println(ANSI_BLUE + "(2) Show Stocks" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "(3) Add Funds" + ANSI_RESET);
    System.out.println(ANSI_RED + "(4) Withdraw Funds" + ANSI_RESET);
    System.out.println("(5) Show NetWorth");
    System.out.println("(6) Sell Stock");
    System.out.println(ANSI_RED + "(7) Close account" + ANSI_RESET);
    System.out.println("(8) Exit");
  } // printMenu()

  public void printCloseAccount() {
    System.out.println("Are you sure you want to close your account? (y/n)");
  }

  public void printInvalidInput() {
    System.out.println("Invalid input. Please try again.");
  }

  public void printStockDetail(Stock stock) {
    System.out.printf("Name: %s Symbol: %s Price: %d Qty: %d",
        stock.getName(), stock.getSymbol(),
        stock.getPrice(), stock.getQty());
  }

  public void printAddFunds(double balance) {
    System.out.printf("Current balance: $%.2f\nAmount to add?: ",
        balance);
  }

  public void printNetWorth(double netWorth, ArrayList<Stock> stocks) {
    System.out.println("Net Worth: $" + netWorth);
    System.out.println("Stocks owned:");
    // Optionally print details of each stock
    for (Stock stock : stocks) {
      System.out.println(stock.getName() + " - " + stock.getQty() + " shares at $" + stock.getPrice());
    }
  }

  public void printSellStock(ArrayList<Stock> stocks) {
    System.out.println("Available stocks to sell:");
    for (int i = 0; i < stocks.size(); i++) {
      Stock stock = stocks.get(i);
      System.out.printf("%d. %s (%s) - %d shares at $%.2f each\n",
          i + 1, stock.getName(), stock.getSymbol(), stock.getQty(), stock.getPrice());
    }
  }

  public void printWithdrawFunds(double balance) {
    System.out.printf("Current balance: $%.2f\nAmount to withdraw?: ", balance);
  }

} // class
