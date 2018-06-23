
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class View extends JPanel {

  private Board b;
  private int sizeTile;

  public View(Board b, int sizeTile) {
    this.b = b;
    this.sizeTile = sizeTile;
    this.setPreferredSize(new Dimension(b.getWidth()*sizeTile + 1, b.getHeight()*sizeTile + 1));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Tile[][] grid = this.b.getGrid();
    for (int j = 0; j<this.b.getHeight(); j++) {
      for (int i = 0; i<this.b.getWidth(); i++) {
        g.drawRect(i*sizeTile,j*sizeTile,sizeTile,sizeTile);
        if (grid[j][i].isDiscover()) {
          g.drawString(grid[j][i].toString(),i*sizeTile + (sizeTile/2),j*sizeTile + (sizeTile/2));
        }
      }
    }
  }
}
