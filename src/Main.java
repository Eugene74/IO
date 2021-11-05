
/*
1. Напишите программу которая скопирует файлы (с заранее
        определенным разрешением — например только doc) из
        каталога источника в каталог приемник.
        */

import copyblock.Copy;
import copyblock.IProgress;
import util.MyFileFilter;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        new Main().copyFilesEndsWith_x( "D:\\Java\\OtherCurs2\\IO\\FilesFolder1",
                    "D:\\Java\\OtherCurs2\\IO\\FilesFolder2",".txt", list);

        for (String s : list)
            System.out.println(s);
    }

    private  void copyFilesEndsWith_x(String srcPath, String dstPath,String ext, ArrayList<String> list) {
        File dir = new File(srcPath);
        File[] files = dir.listFiles(new MyFileFilter(ext));

        for (int i = 0; i < files.length; i++) {
            list.add(srcPath + "\\"+files[i].getName());

        }
        for (int i = 0; i <list.size() ; i++) {
            new Copy(list.get(i), dstPath+"\\"+files[i].getName() , new Progress()).start();
        }
    }


    private static class Progress implements IProgress {
        long n = 0;

        @Override
        public void update(double p) {
            final long rounded = Math.round(p);
            long add = rounded - n;
            n = rounded;
            //System.out.println("add "+add);
            while (add-- > 0) {
            }
            System.out.print('.');
        }
    }
}
