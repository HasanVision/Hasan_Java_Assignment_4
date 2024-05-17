import objects.*;
import utility.*;
import java.util.ArrayList;
import java.util.Scanner;

public class StockUp {
  private static Scanner input = new Scanner(System.in);
  private static Ink ink = new Ink();
  private static Validator validator = new Validator();
  private static Market market = new Market();
  private static Portfolio portfolio;

  private static int min = 1; // used for menu selections
  private static int max = 8; // we need a way to set that based on menu items!!
  private static boolean isDone = false;
  private static boolean goBack = false;

  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";

  public static void main(String[] args) {

    ink.printWelcome();

    // sets the starting balance for our portfolio
    System.out.println("How much money are you starting with?");
    double deposit = input.nextDouble();
    portfolio = new Portfolio(deposit);

    seedStocks(); // seed out stocks with some pretend stocks
    seedMarket(); // creates our test Market stocks

    while (!isDone) {
      int choice = validator.selValidation(ink, input, min, max);

      switch (choice) {
        case 1: // print portfolio
          ink.printPortfolio(portfolio.getStocks(), portfolio.getNetWorth(),
              portfolio.getBalance());
          break;
        case 2: // print market
          while (!goBack) {
            ink.printMarket(market.getStocks());
            int idx = input.nextInt();
            Stock stock = market.getStock(idx - 1);
            ink.printStock(stock);
            int qty = input.nextInt();
            boolean isSuccess = market.buyStocks(stock, qty, portfolio.getBalance());
            if (isSuccess) {
              double purchaseAmount = stock.getPrice() * qty;
              portfolio.buyStock(stock, qty, purchaseAmount);
              goBack = !goBack;
            } // if
          } // while
          goBack = !goBack; // resets goBack to false
          break;
        case 3: // add funds
          double amount = validator.fundValidation(ink, input, portfolio.getBalance());
          portfolio.addFunds(amount);
          // print the new balance
          System.out.printf("New balance: $%.2f\n", portfolio.getBalance());
          break;
        case 4: // withdraw funds
          ink.printWithdrawFunds(portfolio.getBalance());
          double withdrawAmount = input.nextDouble();
          boolean success = portfolio.withdrawFunds(withdrawAmount);
          if (success) {
            System.out.printf(ANSI_GREEN + "Successfully withdrew $%.2f\n" + ANSI_RESET, withdrawAmount);
          } else {
            System.out.println(ANSI_RED + "Failed to withdraw funds. Please try again." + ANSI_RESET);
          }
          break;
        case 5:
          ink.printNetWorth(portfolio.getNetWorth(), portfolio.getStocks());
          break;

        // sell stock
        case 6:
          ink.printSellStock(portfolio.getStocks());
          System.out.println("Enter the index of the stock you want to sell:");
          int stockIndex = input.nextInt();
          if (stockIndex < 1 || stockIndex > portfolio.getStocks().size()) {
            System.out.println("Invalid stock index. Please try again.");
            break;
          }
          Stock stockToSell = portfolio.getStocks().get(stockIndex - 1);
          System.out.println("Enter the quantity of stock to sell:");
          int sellQty = input.nextInt();
          if (sellQty < 1 || sellQty > stockToSell.getQty()) {
            System.out.println("Invalid quantity. Please try again.");
            break;
          }
          double sellAmount = stockToSell.getPrice() * sellQty;
          boolean isSold = portfolio.sellStock(stockToSell.getSymbol(), sellQty);
          if (isSold) {
            System.out.printf(Ink.ANSI_GREEN + "Successfully sold %d shares of %s.\n" + Ink.ANSI_RESET, sellQty,
                stockToSell.getSymbol());
            System.out.printf("You have received $%.2f.\n", sellAmount);
          } else {
            System.out.println(Ink.ANSI_RED + "Failed to sell stock. Please try again." + Ink.ANSI_RESET);
          }
          break;

        case 7: // close account
          ink.printCloseAccount();
          String confirmation = input.next();
          if (confirmation.equalsIgnoreCase("y")) {
            portfolio.closeAccount();
            System.out.println(ANSI_GREEN + "Account closed. All stocks sold. Net worth is now: "
                + portfolio.getNetWorth() + ANSI_RESET);
          } else {
            System.out.println("Account not closed.");
          }
          break;
        case 8:
          isDone = !isDone;
          break;
      } // switch
    } // while

  } // main()

  public static void seedStocks() {
    // the purpose is to create some TEST stocks!
    Stock stock = new Stock("Microsoft", "MSFT", 420.00, 100);
    portfolio.addStock(stock);
    stock = new Stock("Uber", "UBR", 120.00, 50);
    portfolio.addStock(stock);
    stock = new Stock("Nvidia", "NVD", 250.00, 90);
    portfolio.addStock(stock);
  } // seedStocks()

  public static void seedMarket() {
    ArrayList<Stock> stocks = new ArrayList<>();
    // the purpose is to create some TEST stocks for the Market
    Stock stock = new Stock("Adobe", "ADB", 20.00, 0);
    stocks.add(stock);
    stock = new Stock("Netflix", "NFX", 120.00, 0);
    stocks.add(stock);
    stock = new Stock("Apple", "APL", 250.00, 0);
    stocks.add(stock);
    stock = new Stock("Disney", "MOUSE", 1250.00, 0);
    stocks.add(stock);
    stock = new Stock("Microsoft", "MSFT", 420.00, 0);
    stocks.add(stock);
    stock = new Stock("Uber", "UBR", 120.00, 0);
    stocks.add(stock);
    stock = new Stock("Nvidia", "NVD", 900.00, 0);
    stocks.add(stock);
    market.setStocks(stocks);
  } // seedMarket()
} // class