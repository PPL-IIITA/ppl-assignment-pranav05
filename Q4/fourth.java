
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.lang.*;

/**
 *
 * @author Pranav
 */
public class fourth {

    static Boy b[] = new Boy[100];
    /**
     * Array of Object for Boys
     */
    static Girl g[] = new Girl[100];
    /**
     * Array of Object for Girls
     */
    static int cb, cg;

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        fourth f= new fourth();
        Random random = new Random();
        int bo, gi;
        /**
         * Generating Random number of bOys and Girls
         */
        bo = random.nextInt(10);
        gi = random.nextInt(10);
        /**
         * Getting values for attributes of Boys and Girls
         */
        if (bo > gi) {
            Rm_boy rb = new Rm_boy(bo);
            Rm_girl rg = new Rm_girl(gi);
        } else {
            Rm_boy rb = new Rm_boy(gi);
            Rm_girl rg = new Rm_girl(bo);
        }

        int j, k;
        /**
         * Fetching data from csv file for Boys and Girls and storing them in
         * respective Array of Objects for further Calculations
         */

        try {

            cb = 0;
            File fl = new File("boy.csv");
            BufferedReader br = new BufferedReader(new FileReader(fl));
            String str;
            str = br.readLine();
            while (str != null) {

                String bf[] = str.split(",");
                b[cb] = new Boy();

                b[cb].name = bf[0];
                b[cb].att = Integer.parseInt(bf[1]);
                b[cb].iq = Integer.parseInt(bf[2]);
                b[cb].budget = Double.parseDouble(bf[3]);
                b[cb].min_att = Integer.parseInt(bf[4]);
                b[cb].gf = bf[5];
                b[cb].stat = bf[6];

                //System.out.println(str);
                cb++;
                str = br.readLine();

            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {

            cg = 0;
            File fl = new File("girl.csv");
            BufferedReader br = new BufferedReader(new FileReader(fl));
            String str;
            str = br.readLine();
            while (str != null) {
                String gf[] = str.split(",");
                g[cg] = new Girl();
                g[cg].name = gf[0];
                g[cg].att = Integer.parseInt(gf[1]);
                g[cg].iq = Integer.parseInt(gf[2]);
                g[cg].main_bud = Double.parseDouble(gf[3]);
                g[cg].bf = gf[4];
                g[cg].stat = gf[5];
                //System.out.println(str);
                str = br.readLine();
                cg++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /**
         * Doing the calculations according to the question i.e Allocating Girls
         * to Boys
         */
        for (j = 0; j < cg; j++) {
            for (k = 0; k < cb; k++) {
                if (g[j].stat.equals("single") && g[j].main_bud <= b[k].budget && b[k].stat.equals("single") && g[j].att >= b[k].min_att) {
                    g[j].bf = b[k].name;
                    b[k].gf = g[j].name;
                    g[j].stat = "committed";
                    b[k].stat = "committed";
                    g[j].bf_id = k;
                    break;
                }
            }
        }
        /**
         * Saving the result of commitments in the log file along with the time
         * stamp
         */
        try {
            //File fl1=new File("H:\\documents backup\\NetBeansProjects\\Ppl_q1\\src\\log.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter("log.csv"));
            String str;

            for (j = 0; j < cg; j++) {
                if (g[j].stat.equals("committed")) {
                    //System.out.println("hello");
                    String timeStamp1 = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                    String timeStamp2 = new SimpleDateFormat("hh.mm.ss").format(new Date());
                    str = g[j].name + " is committed to " + g[j].bf + " on " + timeStamp1 + " at " + timeStamp2;
                    // System.out.println(str);
                    bw.write(str + "\r\n");
                    bw.flush();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        int i,cg1=0;
        
        for(i=0;i<cg;i++){
            if(g[i].stat.equals("committed")){
                cg1++;
            }
        }
        Gift gif[] = new Gift[100];
        for (i = 0; i < 100; i++) {
            gif[i] = new Gift();
            gif[i].price = random.nextInt(1000);
            gif[i].value = random.nextInt(3);
        }
        for (i = 0; i < cg; i++) {
            if (g[i].stat.equals("committed")) {
                while (g[i].gift < g[i].main_bud) {
                    int r = random.nextInt(100);
                    g[i].gift += gif[r].price;
                }
            }
        }
        try {
            FileWriter fileOut = new FileWriter("happy.csv");/** Emptying the file*/
            fileOut.write("");
            fileOut.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter fileOut = new FileWriter("newlog.csv");/** Emptying the file*/
            fileOut.write("");
            fileOut.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            FileWriter fileOut = new FileWriter("breakup.csv");/** Emptying the file*/
            fileOut.write("");
            fileOut.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String str = "";
        int max, max_i = 0, k1 = random.nextInt(10);
        for (j = 0; j < k1 && j <cg1; j++) {
            max = 999999;
            for (i = 0; i < cg; i++) {
                if (g[i].stat.equals("committed") && g[i].happy == 0) {
                    if (max > g[i].gift) {
                        max = g[i].gift;
                        max_i = i;
                    }
                }
            }
          //  System.out.println(max_i);
            g[max_i].stat="single";
            b[g[max_i].bf_id].stat="single";
            try {
                //File fl1=new File("H:\\documents backup\\NetBeansProjects\\Ppl_q1\\src\\log.csv");
                BufferedWriter bw = new BufferedWriter(new FileWriter("breakup.csv",true));
                String timeStamp1 = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                String timeStamp2 = new SimpleDateFormat("hh.mm.ss").format(new Date());
                str = g[max_i].name + " broke up with " + g[max_i].bf + " on " + timeStamp1 + " at " + timeStamp2;
                bw.write(str + "\r\n");
                bw.flush();
                str="";

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        for (j = 0; j < cg; j++) {
            for (k = 0; k < cb; k++) {
                if (g[j].stat.equals("single") && g[j].main_bud <= b[k].budget && b[k].stat.equals("single") && g[j].att >= b[k].min_att && g[j].bf_id!=k) {
                    g[j].bf = b[k].name;
                    b[k].gf = g[j].name;
                    g[j].stat = "committed";
                    b[k].stat = "committed";
                    g[j].bf_id = k;
                    break;
                }
            }
        }
        /**
         * Saving the result of commitments in the log file along with the time
         * stamp
         */
        try {
            //File fl1=new File("H:\\documents backup\\NetBeansProjects\\Ppl_q1\\src\\log.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter("newlog.csv"));
            //String str;

            for (j = 0; j < cg; j++) {
                if (g[j].stat.equals("committed")) {
                    //System.out.println("hello");
                    String timeStamp1 = new SimpleDateFormat("dd.MM.yyyy").format(new Date());
                    String timeStamp2 = new SimpleDateFormat("hh.mm.ss").format(new Date());
                    str = g[j].name + " is committed to " + g[j].bf + " on " + timeStamp1 + " at " + timeStamp2;
                    // System.out.println(str);
                    bw.write(str + "\r\n");
                    bw.flush();
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        //System.err.println(k1);
    }

}
