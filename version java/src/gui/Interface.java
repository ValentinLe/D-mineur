
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import game.*;

public class Interface extends JFrame {

  private Board b;
  private Content cont;
  private View view;
  private JPanel pauseMenu;
  private int tileSize;
  private boolean menuActive;
  private GridBagConstraints gc;
  private ArrayList<ButtonMenu> listButton;

  public Interface(Board b) {
    this.setTitle("Demineur");
    this.b = b;
    this.menuActive = false;
    this.listButton = new ArrayList<>();

    Dimension dimButton = new Dimension(500,100);
    int sizeFont = 25;

    this.cont = new Content(this.b);
    this.cont.setLayout(new BorderLayout());

    this.view = new View(b, 10);
    view.setBackground(Color.GREEN);


    ButtonMenu bResume = new ButtonMenu("Resume", dimButton, sizeFont);
    bResume.setFocusable(false);
    this.listButton.add(bResume);
    bResume.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Container cp = Interface.this.getContentPane();
        cp.removeAll();
        cp.add(Interface.this.cont);
        cp.validate();
        Interface.this.menuActive = false;
        Interface.this.setStateOffAll();
        Interface.this.repaint();
      }
    });


    ButtonMenu bRestart = new ButtonMenu("Restart", dimButton, sizeFont);
    bRestart.setFocusable(false);
    this.listButton.add(bRestart);
    bRestart.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Container cp = Interface.this.getContentPane();
        cp.removeAll();
        cp.add(Interface.this.cont);
        cp.validate();
        Interface.this.menuActive = false;
        Interface.this.setStateOffAll();
        Interface.this.b.restart();

      }
    });

    ButtonMenu bSelect = new ButtonMenu("Select dificulty", dimButton, sizeFont);
    bSelect.setFocusable(false);
    this.listButton.add(bSelect);
    bSelect.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new SelectLevel();
        Interface.this.dispose();
      }
    });

    ButtonMenu bConfigure = new ButtonMenu("Configure Level", dimButton, sizeFont);
    bConfigure.setFocusable(false);
    this.listButton.add(bConfigure);
    bConfigure.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new ConfigureLevel();
        Interface.this.dispose();
      }
    });

    ButtonMenu bMenu = new ButtonMenu("Back to menu", dimButton, sizeFont);
    bMenu.setFocusable(false);
    this.listButton.add(bMenu);
    bMenu.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Menu();
        Interface.this.dispose();
      }
    });

    JPanel zoneButton = new JPanel();
    zoneButton.setLayout(new GridLayout(5,1,20,20));

    zoneButton.add(bResume);
    zoneButton.add(bRestart);
    zoneButton.add(bSelect);
    zoneButton.add(bConfigure);
    zoneButton.add(bMenu);

    this.pauseMenu = new JPanel();
    this.pauseMenu.setLayout(new GridBagLayout());
    GridBagConstraints gcb = new GridBagConstraints();
    gcb.gridx = 0;
    gcb.gridy = 0;
    this.pauseMenu.setFocusable(false);
    this.pauseMenu.add(zoneButton,gcb);

    this.cont.setLayout(new GridBagLayout());
    this.gc = new GridBagConstraints();

    this.gc.gridx = 0;
    this.gc.gridy = 0;

    this.cont.add(this.view, this.gc);
    this.b.addListener(this.cont);

    this.add(cont);

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
              cp.add(Interface.this.cont);
              cp.validate();
              Interface.this.menuActive = false;
              Interface.this.setStateOffAll();
            } else {
              cp.add(Interface.this.pauseMenu);
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

  public void setStateOffAll() {
    for (ButtonMenu button : this.listButton) {
      button.setStateOff();
    }
  }

  public Integer getTileSize() {
    Insets insetsWindow = this.getInsets();

    int width = this.getWidth();
    int height = this.getHeight() - 26;

    int sizeWidth = Math.round(width/this.b.getWidth());
    int sizeHeight = Math.round(height/this.b.getHeight());

    int min = Math.min(sizeWidth,sizeHeight);

    return min;
  }
}
