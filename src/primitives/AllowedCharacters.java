package primitives;

import java.util.HashSet;

public class AllowedCharacters {
    private static final HashSet<Character> OPERATION;
    static{
        OPERATION = new HashSet<>();

        OPERATION.add('+');
        OPERATION.add('-');
        OPERATION.add('*');
        OPERATION.add('/');
    }

    private static final HashSet<Character> ROMAN_OPERANDS;
    static {
        ROMAN_OPERANDS = new HashSet<Character>();

        ROMAN_OPERANDS.add('I');
        ROMAN_OPERANDS.add('V');
        ROMAN_OPERANDS.add('X');
        ROMAN_OPERANDS.add('L');
        ROMAN_OPERANDS.add('C');
        ROMAN_OPERANDS.add('D');
        ROMAN_OPERANDS.add('M');
    }

    private static final HashSet<Character> ARABIC_OPERANDS;
    static {
        ARABIC_OPERANDS = new HashSet<Character>();

        ARABIC_OPERANDS.add('0');
        ARABIC_OPERANDS.add('1');
        ARABIC_OPERANDS.add('2');
        ARABIC_OPERANDS.add('3');
        ARABIC_OPERANDS.add('4');
        ARABIC_OPERANDS.add('5');
        ARABIC_OPERANDS.add('6');
        ARABIC_OPERANDS.add('7');
        ARABIC_OPERANDS.add('8');
        ARABIC_OPERANDS.add('9');
    }

    public static boolean isOperation (Character op){

        return OPERATION.contains(op);
    }

    public static boolean isArabic (Character ar){

        return ARABIC_OPERANDS.contains(ar);
    }

    public static boolean isRoman (Character ro){

        return ROMAN_OPERANDS.contains(ro);
    }

    public static boolean isAllowedCharacter (Character ac){

        return isOperation(ac) || isArabic(ac) || isRoman(ac);
    }

}
