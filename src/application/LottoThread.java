package application;

public class LottoThread extends Thread {

    int runNo;
    LottoGenerator gen;
    StringBuilder sb;

    public LottoThread(int runNo, LottoGenerator gen, StringBuilder sb) {
        this.runNo = runNo;
        this.gen = gen;
        this.sb = sb;
    }

    @Override
    public void run() {
        int number = gen.getNumber();
        Database.save(runNo, number);

        synchronized (sb) {
            sb.append("Ball " + runNo + ": " + number);
        }
    }
}
