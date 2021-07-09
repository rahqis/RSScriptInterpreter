package main.java.com;

import java.util.ArrayList;
import java.util.LinkedList;
import main.java.com.Token.Types;

public class Interpreter {

  private String text;
  private int pos;
  private Token currentToken;
  private Character currentChar;

  public Interpreter(String text) {
    this.text = text;
    this.pos = 0;
    this.currentChar = text.charAt(pos);
  }

  public void parseError() {
    throw new RuntimeException("Error parsing input");
  }

  public void advancePos() {
    pos++;

    if(pos<=this.text.length()-1)
      this.currentChar = this.text.charAt(pos);
    else
      this.currentChar = null;
  }

  public void whitespace() {
    while(this.currentChar.equals(' '))
      advancePos();
  }

  public Boolean isInteger() {
    return (this.currentChar.charValue() >= 48 && this.currentChar.charValue() <= 57);
  }

  public LinkedList integer() {
    LinkedList<Character> intResults = new LinkedList<>();
    while(this.currentChar != null && isInteger()) {
      intResults.add(this.currentChar);
      advancePos();
    }
    return intResults;
  }

  public Token nextToken() {
    Token token = null;
    LinkedList<Character> concatTokens = new LinkedList<>();

    if(this.currentChar == null)
      return new Token(Types.EOF);

    if(this.currentChar.equals(' '))
      whitespace();

    if (isInteger()) { // Follows ASCII for Digit char values
      token = new Token(Types.INTEGER, integer());
      return token;
    } else if(this.currentChar.equals('+')) {
      concatTokens.add(this.currentChar);
      token = new Token(Types.PLUS, concatTokens);
      advancePos();
      return token;
    } else if(this.currentChar.equals('-')) {
      concatTokens.add(this.currentChar);
      token = new Token(Types.MINUS, concatTokens);
      advancePos();
      return token;
    } else
      parseError();

    return token;
  }

  public void eatToken(Types tokenType){
    if(this.currentToken.getType()==tokenType)
      currentToken = nextToken();
    else
      parseError();
  }

  public int expression() {
    currentToken = nextToken();

    Token left = this.currentToken;
    eatToken(Types.INTEGER);

    Token operation = this.currentToken;
    if(this.currentToken.getType().equals(Types.PLUS))
      eatToken(Types.PLUS);
    else if(this.currentToken.getType().equals(Types.MINUS))
      eatToken(Types.MINUS);

    Token right = this.currentToken;
    eatToken(Types.INTEGER);
    int leftValue = Integer.parseInt(left.getValue());
    int rightValue = Integer.parseInt(right.getValue());
    int result = 0;
    if(operation.getType().equals(Types.PLUS))
      result = leftValue + rightValue;
    else if (operation.getType().equals(Types.MINUS))
      result = leftValue - rightValue;

    return result;
  }
}