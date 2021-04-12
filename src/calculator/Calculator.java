package calculator;

import primitives.AllowedCharacters;
import primitives.Arabic;
import primitives.Operand;
import primitives.Roman;

import java.util.ArrayList;

import static input_data_processing.CheckInputData.checkInputData;
import static input_data_processing.InputDataProcessing.eraseSpaces;
import static input_data_processing.InputDataProcessing.splitData;
import static primitives.AllowedCharacters.isRoman;

public class Calculator {
    private String inputData = null;
    private String operator = null;
    private Operand leftOperand = null;
    private Operand rightOperand = null;

    private boolean dataIsProcessed = false;


    public Calculator(String inData){

        this.inputData = new String(inData);
    }

    public void setNewInputData(String newInData){

        this.inputData = new String(newInData);
        this.dataIsProcessed = false;
    }

    public void processData() throws Exception {

        this.inputData = eraseSpaces(this.inputData);
        checkInputData(this.inputData);

        ArrayList<String> splitRes = splitData(this.inputData);

        if(AllowedCharacters.isRoman(splitRes.get(0).charAt(0))){
            leftOperand = new Roman(splitRes.get(0));
            operator = splitRes.get(1);
            rightOperand = new Roman(splitRes.get(2));
        } else {
            leftOperand = new Arabic(splitRes.get(0));
            operator = splitRes.get(1);
            rightOperand = new Arabic(splitRes.get(2));
        }

        this.dataIsProcessed = true;
    }

    public void processData(boolean inputConstraint) throws Exception {
        this.processData();
        if (inputConstraint){

        }
    }

    public String calculate() throws Exception{

        Object resObj = null;

        switch (operator){
            case "+":
                resObj = leftOperand.plus(rightOperand);
                break;
            case "-":
                resObj = leftOperand.subtract(rightOperand);
                break;
            case "*":
                resObj = leftOperand.multiply(rightOperand);
                break;
            case "/":
                resObj = leftOperand.divide(rightOperand);
                break;
        }

        return resObj.toString();
    }
}
