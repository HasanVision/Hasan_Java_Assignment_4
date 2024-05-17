package objects;

import java.util.ArrayList;
import java.util.Calendar;

public class Portfolio {

  private Calendar openDate = Calendar.getInstance();
  private Calendar closeDate = Calendar.getInstance();
  private double balance;
  private double netWorth;
  // composition!
  private ArrayList<Stock> stocks = new ArrayList<>();

  public Portfolio(double deposit) {
    this.balance = deposit;
    this.netWorth = 0.0;
  } // constructor

  // ==============>>
  // GETTERS
  public String getOpenDate() {
    return this.openDate.toString();
  }

  public String getCloseDate() {
    return this.closeDate.toString();
  }

  public double getBalance() {
    return this.balance;
  }

  public double getNetWorth() {
    // spidey sense if tingling...
    // To calculate your net worth, you subtract your total liabilities from your
    // total assets.
    double totalValueOfStocks = 0.0;
    for (Stock stock : stocks) {
      totalValueOfStocks += stock.getPrice() * stock.getQty();

    }
    this.netWorth = totalValueOfStocks + this.balance;
    return this.netWorth;
  }

  public void updateNetWorth() {
    double totalStockValue = 0.0;
    for (Stock stock : stocks) {
      totalStockValue += stock.getPrice() * stock.getQty();
    }
    this.netWorth = totalStockValue + this.balance;
  }

  public ArrayList<Stock> getStocks() {
    return this.stocks;
  }

  // ==============>>
  // SETTERS
  public void setCloseDate() {
    this.closeDate = Calendar.getInstance();
  }

  public void addFunds(double amount) {
    if (amount > 0)
      this.balance += amount;
  } // addFunds()

  public boolean withdrawFunds(double amount) {
    if (amount > 0 && amount <= this.balance) {
      this.balance -= amount;
      return true;
    }
    return false;
  }

  // =============>>
  // STOCKS
  public void addStock(Stock stock) {
    stocks.add(stock);
  } // addStock

  public void buyStock(Stock stock, int qty, double purchaseAmount) {
    // take the purchaseAmount out of our balance
    this.balance -= purchaseAmount;
    stock.setQty(qty); // sets the quantity
    stocks.add(stock); // appends our new stock
  } // buyStock()

  public boolean sellStock(String symbol, int qty) {
    for (int i = 0; i < stocks.size(); i++) {
      Stock stock = stocks.get(i);
      if (stock.getSymbol().equals(symbol) && stock.getQty() >= qty) {
        if (stock.getQty() == qty) {
          stocks.remove(i);
        } else {
          stock.setQty(stock.getQty() - qty);
        }
        balance += stock.getPrice() * qty; // Update balance
        updateNetWorth(); // Recalculate netWorth
        return true;
      }
    }
    return false;
  }

  public void closeAccount() {
    for (Stock stock : stocks) {
      balance += stock.getPrice() * stock.getQty();
    }
    stocks.clear();
    updateNetWorth();
    setCloseDate();
  }

} // class