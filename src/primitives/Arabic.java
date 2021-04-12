package primitives;

public class Arabic implements Operand {
    private String left = "";

    Arabic(){}

    public Arabic(String op){
        left = new String(op);
    }

    public Arabic(Arabic op){
        this.left = new String(op.left);
    }

    private String multiplyStr(String str, int mult){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < mult; i++)
            res.append(str);
        return res.toString();
    }

    @Override
    public Operand plus(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        Integer res = Integer.parseInt(this.left) + Integer.parseInt(((Arabic)right).left);
        return new Arabic(res.toString());
    }

    @Override
    public Operand subtract(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        Integer res = Integer.parseInt(this.left) - Integer.parseInt(((Arabic)right).left);
        return new Arabic(res.toString());
    }

    @Override
    public Operand multiply(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        Integer res = Integer.parseInt(this.left) * Integer.parseInt(((Arabic)right).left);
        return new Arabic(res.toString());
    }

    @Override
    public Operand divide(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        if (Integer.parseInt(((Arabic)right).left) == 0) {
            throw new Exception("Divide by zero.");
        }

        Integer res = Integer.parseInt(this.left) / Integer.parseInt(((Arabic)right).left);
        return new Arabic(res.toString());
    }

    public Roman arabicToRoman () throws Exception {

        if(Integer.parseInt(left) > 3999)
            throw new Exception("\tIt is impossible to write the number in roman numerals\n"+"\tArabic number: "+left);

        int ar = Integer.parseInt(left);
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
