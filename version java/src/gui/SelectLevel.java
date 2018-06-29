
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


    JPanel zoneButton = new JPanel();
    zoneButton.setLayout(new GridLayout(5,1,50,50));

    JButton bEasy = new JButton("Easy");
    bEasy.setFocusable(false);
    bEasy.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(10,10,10));
        SelectLevel.this.dispose();
      }
    });

    JButton bMedium = new JButton("Medium");
    bMedium.setFocusable(false);
    bMedium.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(20,20,50));
        SelectLevel.this.dispose();
      }
    });

    JButton bHard = new JButton("Hard");
    bHard.setFocusable(false);
    bHard.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(30,30,100));
        SelectLevel.this.dispose();
      }
    });

    JButton bExtreme = new JButton("Extreme");
    bExtreme.setFocusable(false);
    bExtreme.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Interface(new Board(50,50,300));
        SelectLevel.this.dispose();
      }
    });

    JButton bBack = new JButton("Back to menu");
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
