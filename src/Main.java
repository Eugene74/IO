
/*
1. Напишите программу которая скопирует файлы
   (с заранее определенным разрешением — например только .txt)
   изкаталога источника в каталог приемник.
*/

import copyblock.Copy;
import util.MyFileFilter;
import util.Progress;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();

        new Main().copyFilesEndsWithX("FilesFolder1",
                "FilesFolder2", ".txt", list);

        for (String s : list)
            System.out.println(s);
    }

    private void copyFilesEndsWithX(String srcPath, String dstPath, String ext, ArrayList<String> list) {
        File dir = new File(srcPath);
        File[] files = dir.listFiles(new MyFileFilter(ext));

        for (int i = 0; i < files.length; i++) {
            list.add(srcPath + "\\" + files[i].getName());
            new Copy(list.get(i), dstPath + "\\" + files[i].getName(),
                    new Progress("\u001B[3" + (4 + i) + "m" + files[i].getName() + "\u001B[0m")).start();
        }

    }
}
