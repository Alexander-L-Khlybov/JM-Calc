package input_data_processing;

import primitives.AllowedCharacters;
import java.util.ArrayList;

public class InputDataProcessing {

    public static String eraseSpaces(String inData){

        StringBuilder tmp = new StringBuilder(inData);
        boolean spc;

        do{
            spc = false;
            for (int i = 0; i < tmp.length(); i++){
                if (tmp.charAt(i) == ' ')
                    spc = true;
            }

            for (int i = 0; i < tmp.length(); i++){
                if (tmp.charAt(i) == ' ')
                    tmp.delete(i, i + 1);
            }
        } while (spc);

        return tmp.toString();
    }

    public static ArrayList<String> splitData(String inData){
        // работает только после проведенной проверки

        int i;

        for (i = 0; i < inData.length(); i++){
            if (AllowedCharacters.isOperation(inData.charAt(i))) break;
        }

        ArrayList<String> res = new ArrayList<>();

        res.add(inData.substring(0, i));
        res.add(inData.substring(i, i + 1));
        res.add(inData.substring(i + 1));

        return res;
    }



}
