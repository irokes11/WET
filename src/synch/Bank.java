package synch;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * A bank with a number of bank accounts that uses locks for serializing access.
 */
public class Bank
{
   private final double[] accounts;
   private Lock bankLock;
   private Condition sufficientFunds;

   /**
    * Constructs the bank.
    * @param n the number of accounts
    * @param initialBalance the initial balance for each account
    */
   public Bank(int n, double initialBalance)
   {
      accounts = new double[n];
      Arrays.fill(accounts, initialBalance);
      bankLock = new ReentrantLock();
      sufficientFunds = bankLock.newCondition();
   }

   /**
    * Transfers money from one account to another.
    * @param from the account to transfer from
    * @param to the account to transfer to
    * @param amount the amount to transfer
    */
   public void transfer(int from, int to, double amount) throws InterruptedException
   {
      bankLock.lock();
      try
      {
         while (accounts[from] < amount)
            sufficientFunds.await();
         System.out.print(Thread.currentThread());
         accounts[from] -= amount;
         System.out.printf(" %10.2f from %d to %d", amount, from, to);
         accounts[to] += amount;
         System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());
         sufficientFunds.signalAll();
      }
      finally
      {
         bankLock.unlock();
      }
   }

   
   public  void odsetki2(int account, double interests, double amount)
		   throws InterruptedException
		   {  bankLock.lock(); //usage of banklock as in transfer
   try{
		   
	   while (accounts[account] <amount){
		   System.out.println("Insufficient Cash on acc");
		   sufficientFunds.await(); // wait in case no cash
	   }
		   accounts[account] = accounts[account] + accounts[account]* interests;
		   getTotalBalance();
		   sufficientFunds.signalAll(); //Notification to all that there are funds
	   }
   finally {
	   bankLock.unlock();}
   }

   //added only waiting time 
   public  void odsetki3(int account, double interests, double amount)
		   throws InterruptedException
		   {  bankLock.lock(); //usage of banklock as in transfer
   try{
		   
	   while (accounts[account] <amount){
		   System.out.println("Insufficient Cash on acc");
		   sufficientFunds.await(50,TimeUnit.MILLISECONDS); // awaits some time in case no cash
	   }
		   accounts[account] = accounts[account] + accounts[account]* interests;
		   getTotalBalance();
		   sufficientFunds.signalAll(); //Notification to all that there are funds
	   }
   finally {
	   bankLock.unlock();}
   }
   
   /**
    * Gets the sum of all account balances.
    * @return the total balance
    */
   public double getTotalBalance()
   {
      bankLock.lock();
      try
      {
         double sum = 0;

         for (double a : accounts)
            sum += a;

         return sum;
      }
      finally
      {
         bankLock.unlock();
      }
   }

   /**
    * Gets the number of accounts in the bank.
    * @return the number of accounts
    */
   public int size()
   {
      return accounts.length;
   }
   
     // added interests
 public  void AddInterestRates(int accountNumber,double Rate ) {
	 bankLock.lock();
	 try
	 {
	   System.out.print(Thread.currentThread());
	   double balance = accounts[accountNumber] * (1+Rate/100);
	   accounts[accountNumber] = balance;
	   System.out.printf(" Balance on account %d after raise %1.0f percent, = %5.2f" ,accountNumber,Rate,balance);
	   System.out.printf("Total Balance: %10.2f%n",  getTotalBalance());
	   sufficientFunds.signalAll();
	   
	   }
	 finally
	 {
		 bankLock.unlock();
	 }
	 }
   
}
