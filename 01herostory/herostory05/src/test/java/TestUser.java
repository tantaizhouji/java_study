import java.util.concurrent.atomic.AtomicInteger;

public class TestUser {
    public int currHp;

    public AtomicInteger safeCurrHp;

    synchronized public void subtractHp(int delta) {
        if (delta <= 0) {
            return;
        }

        this.currHp = this.currHp - delta;
    }

    synchronized public void attkUser(TestUser targetUser) {
        if (null == targetUser) {
            return;
        }

        int subtractHp = 0;

        synchronized (this) {
            subtractHp = 10;
        }

        targetUser.subtractHp(subtractHp);
    }
}
