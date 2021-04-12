package primitives;

public interface Operand {
    Operand plus(Operand right) throws Exception;
    Operand subtract(Operand right) throws Exception;
    Operand multiply(Operand right) throws Exception;
    Operand divide(Operand right) throws Exception;
}
