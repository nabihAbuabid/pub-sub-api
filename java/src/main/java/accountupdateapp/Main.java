package accountupdateapp;

import utility.ExampleConfigurations;
import java.io.IOException;

import static utility.CommonContext.printStatusRuntimeException;

public class Main {
  public static void main(String[] args) throws IOException, InterruptedException {
    // For this example specifying only the required configurations in the arguments.yaml is enough.
    ExampleConfigurations subscriberParams = new ExampleConfigurations("arguments.yaml");

    Thread thread1=null, thread2=null;
    try {
      ExampleConfigurations subscriberParams1 = subscriberParams;
      subscriberParams1.setLoginUrl("https://corsa04-Basic-2015798750.vpod.t.force.com");
      subscriberParams1.setUsername("test-6ewhgdvgoymw@example.com");
      subscriberParams1.setPassword("rlf4wnDtijldp");
      AccountListener ac1 = new AccountListener(subscriberParams1);

      thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            ac1.startApp();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          ac1.stopApp();
        }
      });
      thread1.start();


      ExampleConfigurations subscriberParams2 = subscriberParams;
      subscriberParams2.setLoginUrl("https://trusted-intelligence-5309.scratch.my.salesforce.com/");
      subscriberParams2.setUsername("test-stnpbx33obzi@example.com");
      subscriberParams2.setPassword("x4Trxczuhkdyk");
      AccountListener ac2 = new AccountListener(subscriberParams2);

      thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
          try {
            ac2.startApp();
          } catch (InterruptedException e) {
            throw new RuntimeException(e);
          }
          ac2.stopApp();
        }
      });
      thread2.start();
    } catch (Exception e) {
      printStatusRuntimeException("Error during AccountListener", e);
    }

  }
}
