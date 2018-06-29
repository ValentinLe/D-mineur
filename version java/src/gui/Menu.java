
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class Menu extends JFrame {

  public Menu() {
    this.setTitle("Demineur");

    JPanel zoneButton = new JPanel();
    zoneButton.setLayout(new GridBagLayout());
    GridBagConstraints gcb = new GridBagConstraints();
    gcb.gridx = 0;
    gcb.gridy = 0;

    JButton bPlay = new JButton("Play");
    bPlay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Menu.this.dispose();
        new Interface(new Board(10,10,10));
      }
    });
    zoneButton.add(bPlay, gcb);

    JButton bQuit = new JButton("Quit");
    bQuit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Menu.this.dispose();
      }
    });
    gcb.gridy = 1;
    zoneButton.add(bQuit,gcb);

    this.add(zoneButton);

    this.setLayout(new GridLayout(2,1));

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
