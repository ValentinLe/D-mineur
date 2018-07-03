
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class Content extends JPanel implements ModelListener {

  private Board b;

  public Content(Board b) {
    this.b = b;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.drawRect(1,1,15,15);
    g.drawString("" + this.b.getBombesNoFlag(),18,14);
  }

  @Override
  public void update(Object src) {
    this.repaint();
  }
}
