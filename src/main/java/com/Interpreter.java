package main.java.com;

import java.util.ArrayList;
import java.util.LinkedList;
import main.java.com.Token.Types;

public class Interpreter {

  private Lexer lex;
  private Token currentToken;

  public Interpreter(Lexer lex) {
    this.lex = lex;
    this.currentToken = lex.nextToken();
  }

  public void eatToken(Types tokenType){
    if(this.currentToken.getType()==tokenType)
      currentToken = lex.nextToken();
    else
      lex.parseError();
  }

  public Integer factor(){

    Token temp = this.currentToken;
    eatToken(Types.INTEGER);
    return Integer.parseInt(temp.getValue());
  }

  public Integer term(){
    int result = factor();

    while (currentToken.getType().equals(Types.MULT)||currentToken.getType().equals(Types.DIV)) {
      Token tempTok = this.currentToken;
      if (tempTok.getType().equals(Types.MULT)) {
        eatToken(Types.MULT);
        result = result * factor();
      } else if (tempTok.getType().equals(Types.DIV)) {
        eatToken(Types.DIV);
        result = result / factor();
      }
    }

    return result;
  }

  public int expression() {
    int result = term();

    while (currentToken.getType().equals(Types.MINUS)||currentToken.getType().equals(Types.PLUS)) {

      Token tempTok = this.currentToken;
      if (tempTok.getType().equals(Types.PLUS)) {
        eatToken(Types.PLUS);
        result = result + term();
      } else if (tempTok.getType().equals(Types.MINUS)) {
        eatToken(Types.MINUS);
        result = result - term();
      }
    }



    return result;
  }
}