public class OddEvenPrinter {
    int max;
    int i = 0;

    OddEvenPrinter(int max){
        this.max = max;
    }
    //thread1
    void printEven(){
        while (i<max){
            synchronized (this) {
                if (i % 2 == 0) {
                    System.out.println(i);
                    i++;
                    this.notify();
                }else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
   //thread2
   synchronized void  printOdd(){
        while (i<max){
            synchronized (this) {
                if (i % 2 != 0) {
                    System.out.println(i);
                    i++;
                    this.notify();
                }else {
                    try {
                        this.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
