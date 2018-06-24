
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class Interface extends JFrame {

  private Board b;
  private int tileSize;

  public Interface(Board b) {
    //this.setResizable(false);
    this.setTitle("Demineur");
    this.b = b;
    this.tileSize = 50;

    View view = new View(b, this.tileSize);
    this.add(view);

    view.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
          if (!Interface.this.b.isFinished()) {
            Board b = Interface.this.b;
            int x = e.getX();
            int y = e.getY();
            x = Math.round(x/Interface.this.tileSize);
            y = Math.round(y/Interface.this.tileSize);
            Tile[][] grid = b.getGrid();
            if (e.getButton() == MouseEvent.BUTTON1) {
              b.clic(grid[y][x]);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
              b.flagTile(grid[y][x]);
            }
          }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    });

    pack();
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
