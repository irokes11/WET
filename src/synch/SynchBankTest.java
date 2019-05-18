package synch;

/**
 * This program shows how multiple threads can safely access a data structure.
 * 
 * @version 1.32 2018-04-10
 * @author Cay Horstmann
 */
public class SynchBankTest {
	public static final int NACCOUNTS = 20;
	public static final double INITIAL_BALANCE = 1;
	public static final double MAX_AMOUNT = 1000;
	public static final int DELAY = 5;

	public static void main(String[] args) throws InterruptedException
   {
      Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
      for (int i = 0; i < NACCOUNTS; i++)
      {
         int fromAccount = i;
         Runnable r= () -> {
            try
            {
               while (true)
               {
            	  bank.odsetki2(fromAccount, 0.02,1); // Added additional interests
            	  bank.odsetki3(10, 0.01, 1);  // interests for specific account nr 10
            	  bank.AddInterestRates(fromAccount, 3);
            	  
               
                  Thread.sleep((int) (DELAY * Math.random()));
               }
            }
            catch (InterruptedException e)
            {
            }            
         };
         
  
         
         Thread t= new Thread(r);
         t.start();
         t.join();
     

}
}}