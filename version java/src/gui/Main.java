
package gui;

import game.*;

public class Main {
  public static void main(String[] args) {
    Board b = new Board(10,10,10);
    System.out.println("" + b.toString());
    new Interface(b);
  }
}
