
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class Interface extends JFrame {

  private Board b;
  private View view;
  private JPanel pauseMenu;
  private int tileSize;
  private boolean menuActive;
  private GridBagConstraints gc;

  public Interface(Board b) {
    this.setTitle("Demineur");
    this.b = b;
    System.out.println("" + this.b.toString());
    this.menuActive = false;

    this.view = new View(b, 10);
    view.setBackground(Color.GREEN);

    this.pauseMenu = new JPanel(new GridLayout(2,1));
    this.pauseMenu.setFocusable(false);
    JButton bRestart = new JButton("Restart");
    bRestart.setFocusable(false);
    bRestart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Container cp = Interface.this.getContentPane();
        cp.removeAll();
        cp.add(Interface.this.view, Interface.this.gc);
        cp.validate();
        Interface.this.menuActive = false;
        Interface.this.b.restart();

      }
    });
    this.pauseMenu.add(bRestart);

    JButton bMenu = new JButton("Back to menu");
    bMenu.setFocusable(false);
    bMenu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Interface.this.dispose();
        new Menu();
      }
    });
    this.pauseMenu.add(bMenu);

    this.setLayout(new GridBagLayout());
    this.gc = new GridBagConstraints();

    this.gc.gridx = 0;
    this.gc.gridy = 0;

    this.add(this.view, this.gc);


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

    this.addKeyListener(new KeyListener(){
       @Override
       public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            Container cp = Interface.this.getContentPane();
            cp.removeAll();
            if (Interface.this.menuActive) {
              cp.add(Interface.this.view, Interface.this.gc);
              cp.validate();
              Interface.this.menuActive = false;
            } else {
              cp.add(Interface.this.pauseMenu, Interface.this.gc);
              cp.validate();
              Interface.this.menuActive = true;
            }
            Interface.this.repaint();
          }

          if (e.getKeyCode() == KeyEvent.VK_R) {
            Interface.this.b.restart();
          }
       }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    });

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    this.tileSize = getTileSize();
    this.view.setSizeTile(this.tileSize);
    this.repaint();
  }

  public Integer getTileSize() {
    Insets insetsWindow = this.getInsets();

    int width = this.getWidth();
    int height = this.getHeight();

    int sizeWidth = Math.round(width/this.b.getWidth());
    int sizeHeight = Math.round(height/this.b.getHeight());

    int min = Math.min(sizeWidth,sizeHeight);

    return min - 1;
  }
}
