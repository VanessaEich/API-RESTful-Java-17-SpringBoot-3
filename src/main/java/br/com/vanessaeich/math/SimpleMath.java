package br.com.vanessaeich.math;

/**
 * @author Vanessa Eich on 10/01/2023
 */
public class SimpleMath {

    public Double sum (Double numberOne, Double numberTwo) {

        return numberOne + numberTwo;
    }

    public Double subtraction (Double numberOne, Double numberTwo) {

        return numberOne - numberTwo;
    }

    public Double multiplication (Double numberOne, Double numberTwo) {

        return numberOne * numberTwo;
    }

    public Double division (Double numberOne, Double numberTwo) {

        return numberOne / numberTwo;
    }

    public Double mean (Double numberOne, Double numberTwo) {

        return (numberOne + numberTwo) / 2;
    }

    public Double squareRoot (Double number) {

        return Math.sqrt(number);
    }
}
