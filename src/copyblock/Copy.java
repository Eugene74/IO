package copyblock;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Copy extends Thread {

    private static final int BLOCK_SIZE = 10240/*4096*//*1024*/;  //(буфер специально маленький для демонстрации прогресса)

    private String src, dest;
    private IProgress progress;
    private long counter;

    public Copy(String src, String dest, IProgress progress) {
        this.src = src;
        this.dest = dest;
        this.progress = progress;
    }

    public void run() {
        try {
            copyFile();
        } catch (IOException e) {
            return;
        }
    }

    private void copyFile() throws IOException {
        RandomAccessFile in = new RandomAccessFile(src, "r");
        try {
            final double onePerсent = in.length() / 100.0;
            counter = 0;

            RandomAccessFile out = new RandomAccessFile(dest, "rw");
            try {
                byte[] buf = new byte[BLOCK_SIZE];
                int r;

                do {
                    r = in.read(buf, 0, buf.length);
                    if (r > 0) {
                        out.write(buf, 0, r);
                        counter += r;
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (progress != null)
                            progress.update(counter / onePerсent);
                    }
                } while (r > 0);
            } finally {
                out.close();
            }
        } finally {
            in.close();
        }
    }
}
