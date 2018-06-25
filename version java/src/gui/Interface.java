
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
    this.setTitle("Demineur");
    this.b = b;
    this.tileSize = getTileSize();

    View view = new View(b, 83);
    view.setBackground(Color.GREEN);

    this.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx = 0;
    gc.gridy = 0;

    this.add(view, gc);

    view.addMouseListener(new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {
          if (!Interface.this.b.isFinished()) {
            Board b = Interface.this.b;
            int x = e.getX();
            int y = e.getY();
            x = Math.round(x/Interface.this.tileSize);
            y = Math.round(y/Interface.this.tileSize);
            if (0 <= x && x < b.getWidth() && 0 <= y && y < b.getHeight()) {
              Tile[][] grid = b.getGrid();
              if (e.getButton() == MouseEvent.BUTTON1) {
                b.clic(grid[y][x]);
              } else if (e.getButton() == MouseEvent.BUTTON3) {
                b.flagTile(grid[y][x]);
              }
            }
          }
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    });

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //this.setUndecorated(false);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public int getTileSize() {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Insets insets = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
    int undecoratedHeight = insets.bottom + insets.top;
    int undecoratedWidth = insets.left + insets.right;

    int width = (int)Math.round(screenSize.getWidth()) - undecoratedWidth;
    int height = (int)Math.round(screenSize.getHeight()) - undecoratedHeight;
    int sizeWidth = Math.round(width/this.b.getWidth());
    int sizeHeight = Math.round(height/this.b.getHeight());
    int min = Math.min(sizeWidth,sizeHeight);
    System.out.println("" + sizeWidth + " " + sizeHeight + " " + min);
    return min;
  }
}
