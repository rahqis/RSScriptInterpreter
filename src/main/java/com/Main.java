package main.java.com;

import java.io.EOFException;
import java.util.Scanner;
import main.java.com.Interpreter;

public class Main {

  public static String calc = "calc> ";

  public static void main(String args[]) {
    while (true) {
        Scanner s = new Scanner(System.in);
        System.out.print(calc);
        String input = s.nextLine();
        Interpreter interpreter = new Interpreter(input);
        int result = interpreter.expression();
        System.out.println(calc + result);
    }
  }

}