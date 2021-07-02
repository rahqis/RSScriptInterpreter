package main.java.com;

import java.util.EnumMap;
import java.util.EnumSet;

public class Token {
  private String INTEGER = "INTEGER"; // 0
  private String PLUS = "PLUS";   // 1
  private String EOF = "EOF";   // 2

  protected enum Types {INTEGER, PLUS, EOF};

  private Types type;
  private Character value;

  public Token(Types type, Character value) {
    this(type);
    this.value = value.charValue();
  }

  public Token(Types type) {
    this.type = type;
  }

  public Types getType() {return this.type;}

  public Character getValue() {
    return this.value;}

  public String toString() {
    return "Token("+ this.getType().toString() + ", " + this.getValue() + ")";
  }
}