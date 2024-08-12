package PrintSerialNumbersUsingThread.src.main.java.org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!\n");

        Main main  = new Main();

        MyThread mt1 = new MyThread(1, main);
        Thread t1 = new Thread(mt1);
        t1.start();

        MyThread mt2 = new MyThread(2, main);
        Thread t2 = new Thread(mt2);
        t2.start();

    }

    int curValue = 1;
    int max_value = 10;
    public synchronized void printSequenceNumber(String threadName) {
        while (curValue < 10) {
            System.out.println(threadName + "\t" + curValue++);

            notifyAll();
            try {
                if (curValue < max_value)
                    wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class MyThread implements Runnable {
    String name;
    Main main;

    public MyThread(int counter, Main main) {
        name = "Thread_" + counter;
        this.main = main;
    }

    @Override
    public void run() {
        main.printSequenceNumber(name);
    }
}
