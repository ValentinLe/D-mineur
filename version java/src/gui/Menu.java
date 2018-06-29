
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
    zoneButton.setLayout(new GridLayout(3,1,50,50));

    JButton bPlay = new JButton("Play");
    bPlay.setFocusable(false);
    bPlay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new SelectLevel();
        Menu.this.dispose();
      }
    });
    zoneButton.add(bPlay);

    JButton bConfigure = new JButton("Configure level");
    bConfigure.setFocusable(false);
    bConfigure.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        //Menu.this.dispose();
      }
    });

    zoneButton.add(bConfigure);

    JButton bQuit = new JButton("Quit");
    bQuit.setFocusable(false);
    bQuit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Menu.this.dispose();
      }
    });

    zoneButton.add(bQuit);

    this.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();

    this.add(zoneButton, gc);


    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
