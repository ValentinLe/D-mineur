
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class Menu extends JFrame {

  private Board b;

  public Menu(Board b) {
    this.setTitle("Demineur");
    this.b = b;

    JButton bPlay = new JButton("Play");
    bPlay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Menu.this.dispose();
        new Interface(Menu.this.b);
      }
    });
    this.add(bPlay);

    JButton bQuit = new JButton("Quit");
    bQuit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Menu.this.dispose();
      }
    });
    this.add(bQuit);

    this.setLayout(new GridLayout(2,1));

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
