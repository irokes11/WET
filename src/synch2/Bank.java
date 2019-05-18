package synch2;

import java.util.*;

/**
 * A bank with a number of bank accounts that uses synchronization primitives.
 */
public class Bank
{
   private final double[] accounts;

   /**
    * Constructs the bank.
    * @param n the number of accounts
    * @param initialBalance the initial balance for each account
    */
   public Bank(int n, double initialBalance)
   {
      accounts = new double[n];
      Arrays.fill(accounts, initialBalance);
   }

   /**
    * Transfers money from one account to another.
    * @param from the account to transfer from
    * @param to the account to transfer to
    * @param amount the amount to transfer
    */
   public synchronized void transfer(int from, int to, double amount) 
         throws InterruptedException
   {
      while (accounts[from] < amount)
         wait();
      System.out.print(Thread.currentThread());
      accounts[from] -= amount;
      System.out.printf(" %10.2f from %d to %d", amount, from, to);
      accounts[to] += amount;
      System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
      notifyAll();
   }

   public synchronized void odsetki2(int account, double interests, double amount)
		   throws InterruptedException
		   {
	   while (accounts[account] <amount){
		   System.out.println("Insufficient Cash on acc");
		   wait(); // wait in case no cash
	   }
		   accounts[account] = accounts[account] + accounts[account]* interests;
		   getTotalBalance();
		   notifyAll();
	   }
   // added waiting time when no cash
   public synchronized void odsetki3(int account, double interests, double amount)
		   throws InterruptedException
		   {
	   while (accounts[account] <amount){
		   System.out.println("Insufficient Cash on acc");
		   wait((long) Math.random()); // wait in case no cash for some random time
	   }
		   accounts[account] = accounts[account] + accounts[account]* interests;
		   getTotalBalance();
		   notifyAll();
	   }
   /**
    * Gets the sum of all account balances.
    * @return the total balance
    */
   public synchronized double getTotalBalance()
   {
      double sum = 0;

      for (double a : accounts)
         sum += a;

      return sum;
   }

   /**
    * Gets the number of accounts in the bank.
    * @return the number of accounts
    */
   public int size()
   {
      return accounts.length;
   }
   public synchronized void AddInterestRates(int accountNumber,double Rate ) {
	   
	   System.out.print(Thread.currentThread());
	   double balance = accounts[accountNumber] * (1+Rate/100);
	   accounts[accountNumber] = balance;
	   System.out.printf(" Balance on account %d after raise %1.0f percent, = %5.2f" ,accountNumber,Rate,balance);
	   System.out.printf("Total Balance: %10.2f%n",  getTotalBalance());
	   notifyAll();
	   
	   
   }
}
