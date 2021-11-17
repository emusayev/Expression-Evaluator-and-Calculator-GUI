
package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.exceptions.InvalidTokenException;
import edu.csc413.calculator.operators.*;
import java.util.HashMap;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer expressionTokenizer;
  private static final String delimiters = " ()+/*-^";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int evaluateExpression(String expression ) {
    String expressionToken;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.expressionTokenizer = new StringTokenizer(expression, this.delimiters, true);

    // initialize operator stack - necessary with operator priority schema
    operatorStack.push(Operator.getOperator("$"));
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators


    while ( this.expressionTokenizer.hasMoreTokens() ) {

      if (!(expressionToken = this.expressionTokenizer.nextToken()).equals(" ")) {
        //  check if the token is an operand
        if (Operand.check(expressionToken)) {
          operandStack.push(new Operand(expressionToken));
        } else {
          if (!Operator.check(expressionToken)) {
          }
          // Check whether the operand stack is an open parenthesis
          if (expressionToken.equals("(")) {
            operatorStack.push(Operator.getOperator("("));
            continue;
          }
          // Check whether the operand stack is a closed parenthesis
          if (expressionToken.equals(")")) {
            // Execute expression if you close parentheses
            while (operatorStack.peek().priority() >= 1){
              Operator operatorfromStack = operatorStack.pop();
              Operand operandTwo = operandStack.pop();
              Operand operandOne = operandStack.pop();
              operandStack.push(operatorfromStack.execute(operandOne,operandTwo));

            }
            operatorStack.pop();
            continue;
          }
          //TODO Operator is abstract - these two lines will need to be fixed:
          // The Operator class should contain an instance of a HashMap,
          // and values will be instances of the Operators.  See Operator class
          // skeleton for an example.

          Operator newOperator = Operator.getOperator(expressionToken);

          while (operatorStack.peek().priority() >= newOperator.priority()) {
            /* Executes operator with operandOne representing the left-hand operand
               and operandTwo representing the right-hand operand*/
            Operand operandTwo = operandStack.pop();
            Operand operandOne = operandStack.pop();
            Operator operatorfromStack = operatorStack.pop();

            operandStack.push (operatorfromStack.execute(operandOne,operandTwo));
          }
          operatorStack.push(newOperator);
        }
      }
    }
    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks,
    // that is, we should keep evaluating the operator stack until it is empty;
    // Suggestion: create a method that processes the operator stack until empty.
     do{
       Operand operandTwo = operandStack.pop();
       Operand operandOne = operandStack.pop();
       Operator operatorfromStack = operatorStack.pop();
      operandStack.push(operatorfromStack.execute(operandOne,operandTwo));
    }
    while (operatorStack.peek().priority() >= 1);// the operatorStack checks any priority =>1
    {
      return operandStack.pop().getValue();
    }
  }
}