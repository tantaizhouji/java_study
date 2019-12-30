import java.util.concurrent.atomic.AtomicInteger;

public class TestMultiThread {
    public static void main(String[] args) {
        for (int i = 1; i <= 50000; i++) {
            System.out.println("第" + i + "次");
            (new TestMultiThread()).test4();
        }
    }

    private void test1() {
        TestUser newUser = new TestUser();
        newUser.currHp = 100;

        Thread[] threadArray = new Thread[2];

        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i] = new Thread(() -> {
                newUser.currHp = newUser.currHp - 10;
            });
        }

        threadArray[0].start();
        threadArray[1].start();

        try {
            threadArray[0].join();
            threadArray[1].join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (newUser.currHp != 80) {
            throw new RuntimeException("当前血量错误, currHp = " + newUser.currHp);
        }

        System.out.println("血量正确, currHp = " + newUser.currHp);
    }

    private void test2() {
        TestUser newUser = new TestUser();
        newUser.currHp = 100;

        Thread[] threadArray = new Thread[2];

        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i] = new Thread(() -> {
                newUser.subtractHp(10);
            });
        }

        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i].start();
        }

        try {
            for (int i = 0; i < threadArray.length; i++) {
                threadArray[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (newUser.currHp != 80) {
            throw new RuntimeException("当前血量错误, currHp = " + newUser.currHp);
        }

        System.out.println("血量正确, currHp = " + newUser.currHp);
    }

    private void test3() {
        TestUser user1 = new TestUser();
        user1.currHp = 100;
        TestUser user2 = new TestUser();
        user2.currHp = 100;

        Thread[] threadArray = new Thread[2];

        threadArray[0] = new Thread(() -> {
            user1.attkUser(user2);
        });
        threadArray[1] = new Thread(() -> {
            user2.attkUser(user1);
        });

        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i].start();
        }

        try {
            for (int i = 0; i < threadArray.length; i++) {
                threadArray[i].join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("测试完成");
    }

    private void test4() {
        TestUser newUser = new TestUser();
        newUser.safeCurrHp = new AtomicInteger(100);

        Thread[] threadArray = new Thread[2];

        for (int i = 0; i < threadArray.length; i++) {
            threadArray[i] = new Thread(() -> {
                newUser.safeCurrHp.addAndGet(-10);
            });
        }

        for (Thread thread : threadArray) {
            thread.start();
        }

        try {
            for (Thread thread : threadArray) {
                thread.join();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (newUser.safeCurrHp.get() != 80) {
            throw new RuntimeException("当前血量错误, currHp = " + newUser.safeCurrHp.get());
        }

        System.out.println("血量正确, currHp = " + newUser.safeCurrHp.get());
    }
}
