
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

public class Rm_girl {

    public Rm_girl(int gi) {
        int j, k;
        try {
            FileWriter fileOut = new FileWriter("girl.csv");/** Emptying the file*/
            fileOut.write("");
            fileOut.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String output = "";
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        String sb = "";
        Random random = new Random();
        /** Generating Random Values for Attributes of Girls*/
        for (k = 0; k < gi; k++) {
            sb = "";
            for (j = 0; j < 8; j++) {
                char c = chars[random.nextInt(chars.length)];
                sb += c;
            }
            output = sb.toString();
            int i = random.nextInt(10);
            output += "," + i;
            i = random.nextInt(10);
            output += "," + i;
            i = random.nextInt(10000);
            output += "," + i;
            output += ",null,single";
            // System.out.println(output);
            /** Saving the data generated in the csv file for Girls*/
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("girl.csv", true));
                bw.write(output + "\r\n");
                bw.flush();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
