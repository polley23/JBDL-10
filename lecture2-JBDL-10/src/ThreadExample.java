//
//public class ThreadExample {
//    public static void main(String[] args) {
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Happy Coding");
//            }
//        };
//        Runnable runnable2 = new Runnable() {
//            @Override
//            public void run() {
//                for(int i =1 ;i<=10;i++){
//                    if(i==6){
//                        Thread.yield();
//                    }
//                    System.out.println(i);
//
//                }
//            }
//        };
//        Thread thread1 = new Thread(runnable2);
//        Thread thread2 = new Thread(runnable);
//        thread1.setPriority(Thread.MAX_PRIORITY);
//        thread2.setPriority(Thread.MIN_PRIORITY);
//
//
//
//        thread1.start();
//        thread2.start();
//    }
//}
