package synch2;

/**
 * This program shows how multiple threads can safely access a data structure,
 * using synchronized methods.
 * @version 1.32 2018-04-10
 * @author Cay Horstmann
 */
public class SynchBankTest2
{
   public static final int NACCOUNTS = 100;
   public static final double INITIAL_BALANCE = 5;
   public static final double MAX_AMOUNT = 1000;
   public static final int DELAY = 10;

   public static void main(String[] args) throws InterruptedException
   {
      Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
      for (int i = 0; i < NACCOUNTS; i++)
      {
         int fromAccount = i;
         Runnable r = () -> {
            try
            {
               while (true)
               { 
                
            	   
            	   bank.odsetki2(fromAccount, 0.02,Math.random()); // Added additional interests
            	   bank.odsetki3(10, 0.03, 1); //add interests for acc nr 10
            	   bank.AddInterestRates(fromAccount, 0.03);
            	   Thread.sleep((int) (DELAY * Math.random()+500)); //increased sleeping time
               }
            }
            catch (InterruptedException e)
            {
            }
         };
         Thread t = new Thread(r);
         t.start();
        // t.join(); // awaits for thread finish
         
         
      }
   }
}
