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

    private String multiplyStr(String str, int mult){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < mult; i++)
            res.append(str);
        return res.toString();
    }

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
                if (roman.get(romanLine.charAt(i - 1)) != 1 ||
                    roman.get(romanLine.charAt(i - 1)) != 10 ||
                    roman.get(romanLine.charAt(i - 1)) != 100)
                    throw new Exception("Violation of the rule of subtraction");
                // число слева от вычитаемого должно быть больше вычитаемого
                if (i > 1 && roman.get(romanLine.charAt(i - 2)) <= roman.get(romanLine.charAt(i - 1)))
                    throw new Exception("Violation of the rule of subtraction");
                // уменьшаемое не может быть старше вычитаемого больше чем на две позиции
                if (roman.get(romanLine.charAt(i)) / roman.get(romanLine.charAt(i - 1)) > 10)
                    throw new Exception("Violation of the rule of subtraction");
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

        return arabicToRoman(res);
    }

    @Override
    public Operand subtract(Operand right) throws Exception{
        if (!(right instanceof Roman))
            throw new Exception("Invalid arg.");

        Arabic lf = new Arabic(this.romanToArabic());
        Arabic rg = new Arabic(((Roman)right).romanToArabic());

        Arabic res = (Arabic) lf.subtract(rg);

        return arabicToRoman(res);
    }

    @Override
    public Operand multiply(Operand right) throws Exception{
        if (!(right instanceof Roman))
            throw new Exception("Invalid arg.");

        Arabic lf = new Arabic(this.romanToArabic());
        Arabic rg = new Arabic(((Roman)right).romanToArabic());

        Arabic res = (Arabic) lf.multiply(rg);

        return arabicToRoman(res);
    }

    @Override
    public Operand divide(Operand right) throws Exception{
        if (!(right instanceof Roman))
            throw new Exception("Invalid arg.");

        Arabic lf = new Arabic(this.romanToArabic());
        Arabic rg = new Arabic(((Roman)right).romanToArabic());

        Arabic res = (Arabic) lf.divide(rg);

        return arabicToRoman(res);
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
            if(sub.add(i)) continue;
            res += roman.get(tmp[i]);
        }

        return new Arabic(Integer.toString(res));
    }

    public Roman arabicToRoman (Arabic arabic) throws Exception {
        if(Integer.parseInt(arabic.toString()) > 3999)
            throw new Exception("\tIt is impossible to write the number in roman numerals\n"+"\tArabic number: "+arabic.toString());
        int ar = Integer.parseInt(arabic.toString());
        StringBuilder res = new StringBuilder();

        // обрабатываем тысячи
        if (ar / 1000 > 0){
            res.append(multiplyStr("M", ar / 1000));
            ar %= 1000;
        }

        // обрабатываем сотни
        if (ar / 100 == 9)
            res.append("CM");
        else {
            if (ar / 500 == 1) {
                res.append("D");
                ar %= 500;
            }
            if (ar / 100 == 4)
                res.append("CD");
            else
                res.append(multiplyStr("C", ar / 100));
        }
        ar %= 100;

        // обрабатываем десятки
        if (ar / 10 == 9)
            res.append("XC");
        else {
            if (ar / 50 == 1){
                res.append("L");
                ar %= 50;
            }
            if (ar / 10 == 4)
                res.append("XL");
            else
                res.append(multiplyStr("X", ar / 10));
        }
        ar %= 10;

        // обрабатываем единицы
        if (ar == 9)
            res.append("IX");
        else {
            if (ar / 5 == 1) {
                res.append("V");
                ar %= 5;
            }
            if (ar == 4)
                res.append("IV");
            else
                res.append(multiplyStr("I", ar));
        }

        return new Roman(res.toString());
    }

    @Override
    public String toString (){
        return new String(this.left);
    }
}
