package primitives;

import java.util.HashSet;

public class SuperAllowedCharacters {
    private static final HashSet<Character> ARABIC_OPERANDS;
    static {
        ARABIC_OPERANDS = new HashSet<>();

        ARABIC_OPERANDS.add(',');
        ARABIC_OPERANDS.add('.');
    }

    private static final HashSet<Character> OPERATION;
    static {
        OPERATION = new HashSet<>();

        OPERATION.add('(');
        OPERATION.add(')');
    }

    public static boolean isOperation (Character op){

        return AllowedCharacters.isOperation(op) || OPERATION.contains(op);
    }

    public static boolean isArabic (Character ar){

        return AllowedCharacters.isArabic(ar) || ARABIC_OPERANDS.contains(ar);
    }

    public static boolean isRoman (Character ro){

        return AllowedCharacters.isRoman(ro);
    }

    public static boolean isAllowedCharacter (Character ac){

        return isOperation(ac) || isArabic(ac) || isRoman(ac);
    }


}
