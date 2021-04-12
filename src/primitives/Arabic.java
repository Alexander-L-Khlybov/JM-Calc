package primitives;

public class Arabic implements Operand {
    private String left;

    private String multiplyStr(String str, int mult){

        StringBuilder res = new StringBuilder();
        for (int i = 0; i < mult; i++)
            res.append(str);
        return res.toString();
    }

    Arabic() throws Exception {

        this("");
    }

    public Arabic(String op) throws Exception{

        for(int i = 0; i < op.length(); i++){
            if(i == 0 && op.charAt(0) == '-' && op.length() > 1) continue;
            if (!AllowedCharacters.isArabic(op.charAt(i)))
                throw new Exception("No Arabic num.");
        }
        this.left = op;
    }

    public Arabic(Arabic op){

        this.left = op.left;
    }

    @Override
    public Operand plus(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        int res = Integer.parseInt(this.left) + Integer.parseInt(((Arabic)right).left);
        return new Arabic(Integer.toString(res));
    }

    @Override
    public Operand subtract(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        int res = Integer.parseInt(this.left) - Integer.parseInt(((Arabic)right).left);
        return new Arabic(Integer.toString(res));
    }

    @Override
    public Operand multiply(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        int res = Integer.parseInt(this.left) * Integer.parseInt(((Arabic)right).left);
        return new Arabic(Integer.toString(res));
    }

    @Override
    public Operand divide(Operand right) throws Exception {

        if (!(right instanceof Arabic))
            throw new Exception("Invalid arg.");

        if (Integer.parseInt(((Arabic)right).left) == 0) {
            throw new Exception("Divide by zero.");
        }

        int res = Integer.parseInt(this.left) / Integer.parseInt(((Arabic)right).left);
        return new Arabic(Integer.toString(res));
    }

    public Roman toRoman () throws Exception {

        if(Integer.parseInt(this.left) > 3999)
            throw new Exception("\tIt is impossible to write the number in roman numerals\n"+"\tArabic number: "+left);

        int ar = Integer.parseInt(this.left);
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

        return this.left;
    }

}
