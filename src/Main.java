import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    private Object lock1 = new Object();

    public static void main(String[] args) {

        Main main = new Main();

        long startTime = System.currentTimeMillis();

        main.work();

        long endTime = System.currentTimeMillis();

        System.out.println("Geçen zaman: " + (endTime - startTime));
        System.out.println("List1 in boyutu: " + main.list1.size());
        System.out.println("List2 nin boyutu: " + main.list2.size());

    }

    private void work(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                process();
            }
        });

        thread1.start();
        thread2.start();

        try{
            thread1.join();
            thread2.join();
        }catch(InterruptedException e){
        }

    }

    private void process() {
        for (int i = 0; i < 10000; i++){
            addNewIntegerToList1();
            addNewIntegerToList2();
        }
    }

    private void addNewIntegerToList1() {
        synchronized(lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(new Random().nextInt());
        }
    }

    private void addNewIntegerToList2() {
        synchronized(lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(new Random().nextInt());
        }
    }

}
