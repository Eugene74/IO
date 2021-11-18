package util;

import copyblock.IProgress;

public class Progress implements IProgress {
    private String s;
    private long n = 0;

    public Progress(String s) {
        this.s = s;
    }

    @Override
    public void update(double p) {
        final long rounded = Math.round(p);
        long add = rounded - n;
        n = rounded;
        //System.out.println("add "+add);
        while (add-- > 0) {
        }
        System.out.print(s);
    }
}
