import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static void main(String[] args) {
        int sleep = 5000;

        List<Integer> shop1 = getList(10, 10);
        List<Integer> shop2 = getList(10, 15);
        List<Integer> shop3 = getList(15, 20);

        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LongAdder longAdder = new LongAdder();
        shop1.forEach(i -> executorService.submit(() -> longAdder.add(i)));
        shop2.forEach(i -> executorService.submit(() -> longAdder.add(i)));
        shop3.forEach(i -> executorService.submit(() -> longAdder.add(i)));

        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        System.out.println(longAdder.sum());

    }

    private static List<Integer> getList(int count, int income) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            list.add(random.nextInt(income));
        }
        return list;
    }
}
