
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class SelectLevel extends JFrame {

  public SelectLevel() {
    this.setTitle("Demineur");

    Dimension dimButton = new Dimension(500,100);
    int sizeFont = 25;

    JPanel zoneButton = new JPanel();
    zoneButton.setLayout(new GridLayout(5,1,50,50));

    ButtonMenu bEasy = new ButtonMenu("Easy", dimButton, sizeFont);
    bEasy.setFocusable(false);
    bEasy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(10,10,10));
        SelectLevel.this.dispose();
      }
    });

    ButtonMenu bMedium = new ButtonMenu("Medium", dimButton, sizeFont);
    bMedium.setFocusable(false);
    bMedium.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(20,20,50));
        SelectLevel.this.dispose();
      }
    });

    ButtonMenu bHard = new ButtonMenu("Hard", dimButton, sizeFont);
    bHard.setFocusable(false);
    bHard.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(30,30,100));
        SelectLevel.this.dispose();
      }
    });

    ButtonMenu bExtreme = new ButtonMenu("Extreme", dimButton, sizeFont);
    bExtreme.setFocusable(false);
    bExtreme.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(50,50,300));
        SelectLevel.this.dispose();
      }
    });

    ButtonMenu bBack = new ButtonMenu("Back to menu", dimButton, sizeFont);
    bBack.setFocusable(false);
    bBack.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Menu();
        SelectLevel.this.dispose();
      }
    });

    zoneButton.add(bEasy);
    zoneButton.add(bMedium);
    zoneButton.add(bHard);
    zoneButton.add(bExtreme);
    zoneButton.add(bBack);

    this.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();

    gc.gridx = 0;
    gc.gridy = 0;
    this.add(zoneButton,gc);

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
