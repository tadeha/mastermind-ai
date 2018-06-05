package mastermind;

import java.io.*;
import java.util.*;

public class file {

    public static int exists_file(String path) {

        File d = new File(path);
        if (d.exists()) {
            return 1;
        } else {
            return 0;
        }
    }

    public static void clear_file(String path) throws FileNotFoundException, IOException {
       File file = new File(path);
        FileOutputStream fooStream = new FileOutputStream(file, false);

        fooStream.write("".getBytes());
        fooStream.close();
    }

    public static void create_file(String path) {
        try {
            Formatter s = new Formatter(path);
            System.out.println("file created ");
            s.close();
        } catch (FileNotFoundException e) {
            System.out.println("error");
        }
    }

    public static void read_file(String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                String a = sc.nextLine();
                System.out.println(a);
            }
            sc.close();
        } catch (FileNotFoundException er) {
            System.out.println("cannot open the file\n");

        }
    }

    public static void write_file(String path, String a) {
        FileWriter fw;
        try {
            fw = new FileWriter(path, true);
            fw.write(a);
            fw.write(System.getProperty("line.separator"));
            fw.close();
        } catch (IOException v) {
            System.out.println("error");
        }
    }

    public static void write_file(String path) {
        FileWriter fw;
        try {
            fw = new FileWriter(path, true);
            fw.write("salam khoshamadid!\n");
            fw.write(System.getProperty("line.separator"));
            fw.close();
        } catch (IOException v) {
            System.out.println("error");
        }
    }

    public static void copy_file(String path, String path2) {
        try {
            Scanner sc = new Scanner(new File(path));
            while (sc.hasNext()) {
                String a = sc.nextLine();
                write_file(path2, a);
            }
            sc.close();
        } catch (FileNotFoundException er) {
            System.out.println("cannot open the file\n");
        }
    }
}
