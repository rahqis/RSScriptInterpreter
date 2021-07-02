package main.java.com;

import main.java.com.Token.Types;

public class Interpreter {

  private String text;
  private int pos;
  private Token current_token;

  public Interpreter(String text) {
    this.text = text;
    this.pos = 0;
  }

  public void parseError() {
    throw new RuntimeException("Error parsing input");
  }

  public Token nextToken() {
    Token token = null;
    String text = this.text;
    if (this.pos > text.length() - 1) {
      token = new Token(Types.EOF);
      return token;
    }

    Character current_char = text.charAt(this.pos);

    if (current_char.charValue() >= 48 && current_char.charValue() <= 57) { // Follows ASCII for Digit char values
      token = new Token(Types.INTEGER, current_char);
      this.pos++;
    } else if(current_char == '+') {
      token = new Token(Types.PLUS, current_char);
      this.pos++;
    } else
      parseError();

    return token;
  }

  public void eatToken(Types tokenType){
    if(this.current_token.getType()==tokenType)
      current_token = nextToken();
    else
      parseError();
  }

  public int expression() {
    current_token = nextToken();

    Token left = this.current_token;
    eatToken(Types.INTEGER);

    Token operation = this.current_token;
    eatToken(Types.PLUS);

    Token right = this.current_token;
    eatToken(Types.INTEGER);
    Character leftValue = left.getValue();
    Character rightValue = right.getValue();
    int result = Character.getNumericValue(leftValue) + Character.getNumericValue(rightValue);
    return result;
  }
}