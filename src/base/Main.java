package base;

import calculator.Calculator;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) throws Exception {

        try {
            Scanner in = new Scanner(System.in);
            String inputLine = in.nextLine();
            in.close();

            Calculator cl = new Calculator(inputLine);
            cl.processData(true);
            String res = cl.calculate();

            System.out.println(res);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
