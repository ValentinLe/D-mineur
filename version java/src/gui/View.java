
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class View extends JPanel implements ModelListener {

  private Board b;
  private int sizeTile;

  public View(Board b, int sizeTile) {
    this.b = b;
    this.b.addListener(this);
    this.sizeTile = sizeTile;
    this.setPreferredSize(new Dimension(b.getWidth()*sizeTile, b.getHeight()*sizeTile));
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Tile[][] grid = this.b.getGrid();
    for (int j = 0; j<this.b.getHeight(); j++) {
      for (int i = 0; i<this.b.getWidth(); i++) {
        g.drawRect(i*sizeTile,j*sizeTile,sizeTile,sizeTile);
        if (grid[j][i].isDiscover() && !grid[j][i].isFlag()) {
          g.drawString(grid[j][i].toString(),i*sizeTile + (sizeTile/2),j*sizeTile + (sizeTile/2));
        } else if (grid[j][i].isBombe() && this.b.isOver()) {
          if (grid[j][i].isBombeClicked()) {
            g.drawString("X",i*sizeTile + (sizeTile/2),j*sizeTile + (sizeTile/2));
          } else {
            g.drawString(grid[j][i].toString(),i*sizeTile + (sizeTile/2),j*sizeTile + (sizeTile/2));
          }
        } else if (!grid[j][i].isDiscover() && grid[j][i].isFlag()) {
          g.drawString("F",i*sizeTile + (sizeTile/2),j*sizeTile + (sizeTile/2));
        }
      }
    }
  }

  public void setSizeTile(int newSize) {
    this.sizeTile = newSize;
    this.setPreferredSize(new Dimension(this.b.getWidth()*sizeTile, this.b.getHeight()*sizeTile));
    this.update(this);
  }

  @Override
  public void update(Object src) {
    this.repaint();
  }
}
