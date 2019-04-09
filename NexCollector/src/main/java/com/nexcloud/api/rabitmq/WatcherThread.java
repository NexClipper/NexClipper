package com.nexcloud.api.rabitmq;

public class WatcherThread extends Thread {

   private Watchable watchable;
   private long interval;
   private boolean run;

   public WatcherThread(Watchable watchable, long interval) {
      this.watchable = watchable;
      this.interval = interval;
      this.run = true;
   }

   @Override
   public void run() {
      while (this.run) {
         this.watchable.watch();
         try {
            Thread.sleep(this.interval);
         } catch (InterruptedException ignored) {
         }
      }
   }

   public void cancel() {
      this.run = false;
   }
}