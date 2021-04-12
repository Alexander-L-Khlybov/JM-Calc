package input_data_processing;

import primitives.AllowedCharacters;

public class CheckInputData {

    public static void checkInputData (String inputData) throws Exception {

        // проверка на наличие иных символов
        for (int i = 0; i < inputData.length(); i++){
            if(!AllowedCharacters.isAllowedCharacter(inputData.charAt(i)))
                throw new Exception("Incorrect input data.");
        }

        // проверка на корректность наличия символа операции
        int tmp = 0;
        for (int i = 0; i < inputData.length(); i++){
            if(AllowedCharacters.isOperation(inputData.charAt(i)))
                tmp++;
        }
        if (tmp == 0)
            throw new Exception("Operation symbol not specified.");
        if (tmp != 1)
            throw new Exception("Too many op symbols.");

        // проверка на местоположение символа операции
        if (AllowedCharacters.isOperation(inputData.charAt(0)))
            throw new Exception("The operation character cannot be at the beginning of the line.");
        if (AllowedCharacters.isOperation((inputData.charAt(inputData.length() - 1))))
            throw new Exception("The operation character cannot be at the end of the line");

        // проверка на наличие римских и арабских цифр в строке одновременно
        boolean arabic = false, roman = false;
        for (int i = 0; i < inputData.length(); i++){
            if (AllowedCharacters.isArabic(inputData.charAt(i)))
                arabic = true;
            else if (AllowedCharacters.isRoman(inputData.charAt(i)))
                roman = true;
        }
        if (arabic && roman)
            throw new Exception("Arabic and Roman numerals cannot be on the same line.");
    }

}
