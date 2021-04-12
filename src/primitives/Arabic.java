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

    @Override
    public String toString (){
        return new String(this.left);
    }
}
