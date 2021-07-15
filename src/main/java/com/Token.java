package main.java.com;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedList;

public class Token {


  protected enum Types {
    INTEGER,
    PLUS,
    MINUS,
    MULT,
    DIV,
    LPAR,
    RPAR,
    EOF
  };

  private Types type;
  private LinkedList<Character> value;

  public Token(Types type, LinkedList<Character> value) {
    this(type);
    this.value = value;
  }


  public Token(Types type) {
    this.type = type;
  }

  public Types getType() {return this.type;}

  public String getValue() {
    String results = "";
    for(Character c: this.value)
      results+= c;

    return results;
  }

  public String toString() {
    return "Token("+ this.getType().toString() + ", " + this.getValue() + ")";
  }
}