
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class ConfigureLevel extends JFrame {

  public ConfigureLevel() {
    this.setTitle("Demineur");

    Dimension dimButton = new Dimension(500,150);
    int sizeFont = 25;

    ButtonMenu bBack = new ButtonMenu("Back to menu", dimButton, sizeFont);
    bBack.setFocusable(false);
    bBack.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Menu();
        ConfigureLevel.this.dispose();
      }
    });

    this.add(bBack);

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
