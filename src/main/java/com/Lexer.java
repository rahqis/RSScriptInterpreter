package main.java.com;

import java.util.LinkedList;
import main.java.com.Token.Types;

public class Lexer {

  private Character currentChar;
  private String text;
  private int pos;

  public Lexer(String text) {
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
    } else if(this.currentChar.equals('*')) {
      concatTokens.add(this.currentChar);
      token = new Token(Types.MULT, concatTokens);
      advancePos();
      return token;
    } else if (this.currentChar.equals('/')) {
      concatTokens.add(this.currentChar);
      token = new Token(Types.DIV, concatTokens);
      advancePos();
      return token;
    } else
      parseError();

    return token;
  }
}
