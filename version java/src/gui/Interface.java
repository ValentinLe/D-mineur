
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class Interface extends JFrame {

  private Board b;

  public Interface(Board b) {
    //this.setResizable(false);
    this.setTitle("Demineur");


    View view = new View(b, 50);
    this.add(view);

    pack();
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
