package primitives;

import java.util.ArrayList;
import java.util.HashMap;

public class Roman implements Operand{
    private static final HashMap<Character, Integer> roman;
    static {
        roman = new HashMap<>();

        roman.put('I', 1);
        roman.put('V', 5);
        roman.put('X', 10);
        roman.put('L', 50);
        roman.put('C', 100);
        roman.put('D', 500);
        roman.put('M', 1000);
    }

    private String left = "";



    private void checkRomanLine (String romanLine) throws Exception{

        // проверка вхождения иных символов
        for (int i = 0; i < romanLine.length(); i++){
            if (!roman.containsKey(romanLine.charAt(i)))
                throw new Exception("No Roman num.");
        }

        // проверка повторения символа более трех раз
        int tmp = 1;
        for (int i = 1; i < romanLine.length(); i++){
            if (romanLine.charAt(i) == romanLine.charAt(i - 1))
                tmp++;
            else
                tmp = 1;

            if (tmp > 3)
                throw new Exception("Incorrect Roman num.");
        }

        // проверка правила вычитания
        for (int i = 1; i < romanLine.length(); i++){
            // вычитается меньшее, стоящее слева от большего
            if (roman.get(romanLine.charAt(i - 1)) < roman.get(romanLine.charAt(i))){
                // вычитаемое должно быть степенью 10
                if (roman.get(romanLine.charAt(i - 1)) != 1 &&
                    roman.get(romanLine.charAt(i - 1)) != 10 &&
                    roman.get(romanLine.charAt(i - 1)) != 100)
                    throw new Exception("Violation of the rule of subtraction 1");
                // число слева от вычитаемого должно быть больше вычитаемого
                if (i > 1 && roman.get(romanLine.charAt(i - 2)) <= roman.get(romanLine.charAt(i - 1)))
                    throw new Exception("Violation of the rule of subtraction 2");
                // уменьшаемое не может быть старше вычитаемого больше чем на две позиции
                if (roman.get(romanLine.charAt(i)) / roman.get(romanLine.charAt(i - 1)) > 10)
                    throw new Exception("Violation of the rule of subtraction 3");
            }
        }

    }


    Roman(){}
    public Roman(String op) throws Exception {
        checkRomanLine(op);
        left = new String(op);
    }

    public Roman(Roman op){
        this.left = new String(op.left);
    }

    @Override
    public Operand plus(Operand right) throws Exception{
        if (!(right instanceof Roman))
            throw new Exception("Invalid arg.");

        Arabic lf = new Arabic(this.romanToArabic());
        Arabic rg = new Arabic(((Roman)right).romanToArabic());

        Arabic res = (Arabic) lf.plus(rg);

        return res.arabicToRoman();
    }

    @Override
    public Operand subtract(Operand right) throws Exception{
        if (!(right instanceof Roman))
            throw new Exception("Invalid arg.");

        Arabic lf = new Arabic(this.romanToArabic());
        Arabic rg = new Arabic(((Roman)right).romanToArabic());

        Arabic res = (Arabic) lf.subtract(rg);

        return res.arabicToRoman();
    }

    @Override
    public Operand multiply(Operand right) throws Exception{
        if (!(right instanceof Roman))
            throw new Exception("Invalid arg.");

        Arabic lf = new Arabic(this.romanToArabic());
        Arabic rg = new Arabic(((Roman)right).romanToArabic());

        Arabic res = (Arabic) lf.multiply(rg);

        return res.arabicToRoman();
    }

    @Override
    public Operand divide(Operand right) throws Exception{
        if (!(right instanceof Roman))
            throw new Exception("Invalid arg.");

        Arabic lf = new Arabic(this.romanToArabic());
        Arabic rg = new Arabic(((Roman)right).romanToArabic());

        Arabic res = (Arabic) lf.divide(rg);

        return res.arabicToRoman();
    }

    public Arabic romanToArabic(){

        char[] tmp = left.toCharArray();

        int res = 0;
        ArrayList<Integer> sub = new ArrayList<>();

        for (int i = 1; i < tmp.length; i++){
            if (roman.get(tmp[i - 1]) < roman.get(tmp[i])) {
                res -= roman.get(tmp[i - 1]);
                sub.add(i - 1);
            }
        }
        for (int i = 0; i < tmp.length; i++){
            if(sub.contains(i)) continue;
            res += roman.get(tmp[i]);
        }
        return new Arabic(Integer.toString(res));
    }

    @Override
    public String toString (){
        return new String(this.left);
    }
}
