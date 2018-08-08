
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

    Dimension dimButton = new Dimension(500,150);
    int sizeFont = 25;

    BackgroundRepeat background = new BackgroundRepeat("../ressources/images/test.png");
    background.setLayout(new FlowLayout(FlowLayout.CENTER,0,100));

    JPanel zoneButton = new JPanel();
    zoneButton.setLayout(new GridLayout(3,1,50,50));

    ButtonMenu bPlay = new ButtonMenu("Play", dimButton, sizeFont);
    bPlay.setFocusable(false);
    bPlay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new SelectLevel();
        Menu.this.dispose();
      }
    });
    zoneButton.add(bPlay);

    ButtonMenu bConfigure = new ButtonMenu("Configure level", dimButton, sizeFont);
    bConfigure.setFocusable(false);
    bConfigure.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new ConfigureLevel();
        Menu.this.dispose();
      }
    });

    zoneButton.add(bConfigure);

    ButtonMenu bQuit = new ButtonMenu("Quit", dimButton, sizeFont);
    bQuit.setFocusable(false);
    bQuit.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Menu.this.dispose();
      }
    });

    zoneButton.add(bQuit);

    JPanel cont = new JPanel();
    cont.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();

    zoneButton.setOpaque(false);
    cont.add(zoneButton, gc);
    cont.setOpaque(false);

    background.add(cont);
    this.add(background);

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
