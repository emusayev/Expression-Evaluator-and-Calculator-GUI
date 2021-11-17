package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
    /**
     * construct operand from string token.
     */
    private int operand;
    public Operand(String token) {
        try{
            this.operand = Integer.parseInt(token);
        }catch(NumberFormatException error){
            System.out.println("parseInt: NumberFormatException");
            System.exit(1);
        }
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        operand = value;
    }

    /**
     * return value of operand
     */
    public int getValue() {

        return operand;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        try{
            Integer.parseInt(token);
        }catch(NumberFormatException error){
            return false;
        }
        return true;
    }
}