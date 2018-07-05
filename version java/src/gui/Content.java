
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class Content extends JPanel implements ModelListener {

  private Board b;
  private Image bombe;

  public Content(Board b) {
    this.b = b;
    this.bombe = Toolkit.getDefaultToolkit().getImage("../ressources/images/bombeIcon.png");
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawImage(this.bombe,1,1,14,14,this);
    g.drawString("" + this.b.getBombesNoFlag(),18,14);
  }

  @Override
  public void update(Object src) {
    this.repaint();
  }
}
