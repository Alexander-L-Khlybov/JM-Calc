package calculator;

import primitives.AllowedCharacters;
import primitives.Arabic;
import primitives.Operand;
import primitives.Roman;

import java.util.ArrayList;

import input_data_processing.CheckInputData;
import input_data_processing.InputDataProcessing;

public class Calculator {
    private String inputData;
    private String operator = null;
    private Operand leftOperand = null;
    private Operand rightOperand = null;

    private boolean dataIsProcessed = false;

    public Calculator(){

        this("");
    }

    public Calculator(String inData){

        this.inputData = inData;
    }

    public void setNewInputData(String newInData){

        this.inputData = newInData;
        this.dataIsProcessed = false;
    }

    public void processData() throws Exception {

        this.inputData = InputDataProcessing.eraseSpaces(this.inputData);
        CheckInputData.checkInputData(this.inputData);
        ArrayList<String> splitRes = InputDataProcessing.splitData(this.inputData);

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
            if (AllowedCharacters.isRoman(leftOperand.toString().charAt(0))){
                if (Integer.parseInt(((Roman)leftOperand).toArabic().toString()) > 10 ||
                    Integer.parseInt(((Roman)rightOperand).toArabic().toString()) > 10){
                    throw new Exception("Incorrect args.");
                }
            } else {
                if (Integer.parseInt(leftOperand.toString()) < 1 ||
                    Integer.parseInt(leftOperand.toString()) > 10 ||
                    Integer.parseInt(rightOperand.toString()) < 1 ||
                    Integer.parseInt(rightOperand.toString()) > 10 )
                    throw new Exception("Incorrect args.");
            }
        }
    }

    public String calculate() throws Exception{

        if (!dataIsProcessed)
            this.processData();

        Object resObj = null;

        switch (this.operator){
            case "+":
                resObj = this.leftOperand.plus(this.rightOperand);
                break;
            case "-":
                resObj = this.leftOperand.subtract(this.rightOperand);
                break;
            case "*":
                resObj = this.leftOperand.multiply(this.rightOperand);
                break;
            case "/":
                resObj = this.leftOperand.divide(this.rightOperand);
                break;
        }

        return resObj.toString();
    }
}
