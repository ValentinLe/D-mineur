
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import game.*;

public class View extends JPanel implements ModelListener {

  private Board b;
  private HashMap<Integer,Image> numbers;
  private Dimension dim;
  private Image bombe;
  private Image bombeActivate;
  private Image flag;
  private Image tile;
  private Image tileEmpty;
  private Image noBombe;
  private int sizeTile;

  public View(Board b, int sizeTile) {
    this.b = b;
    this.numbers = getMapNumbers();
    this.bombe = Toolkit.getDefaultToolkit().getImage("../ressources/images/bombe.png");
    this.bombeActivate = Toolkit.getDefaultToolkit().getImage("../ressources/images/bombeActivate.png");
    this.flag = Toolkit.getDefaultToolkit().getImage("../ressources/images/flag.png");
    this.tile = Toolkit.getDefaultToolkit().getImage("../ressources/images/tile.png");
    this.tileEmpty = Toolkit.getDefaultToolkit().getImage("../ressources/images/tileEmpty.png");
    this.noBombe = Toolkit.getDefaultToolkit().getImage("../ressources/images/noBombe.png");
    this.b.addListener(this);
    this.sizeTile = sizeTile;
    this.dim = new Dimension(b.getWidth()*sizeTile, b.getHeight()*sizeTile);
    this.setPreferredSize(this.dim);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Tile[][] grid = this.b.getGrid();
    for (int j = 0; j<this.b.getHeight(); j++) {
      for (int i = 0; i<this.b.getWidth(); i++) {
        if (grid[j][i].isDiscover() && !grid[j][i].isFlag()) {
          // les numeros
          g.drawImage(this.tileEmpty,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
          if (grid[j][i].getValue() != 0) {
            g.drawImage(this.numbers.get(grid[j][i].getValue()),i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
          }
        } else if (!grid[j][i].isDiscover() && grid[j][i].isFlag()) {
          // les drapeaux
          if (this.b.isOver()) {
            // fin de partie
            if (!grid[j][i].isBombe()) {
              // drapeaux pas bombe
              g.drawImage(this.tile,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
              g.drawImage(this.noBombe,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
            } else {
              // bombe drapeaux
              g.drawImage(this.tile,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
              g.drawImage(this.flag,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
            }
          } else {
            g.drawImage(this.tile,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
            g.drawImage(this.flag,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
          }

        } else if (this.b.isOver() && grid[j][i].isBombe()) {
          g.drawImage(this.tile,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
          if (grid[j][i].isBombeClicked()) {
            // bombe cliquee
            g.drawImage(this.bombeActivate,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
          } else {
            // les autres bombes
            g.drawImage(this.bombe,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
          }
        } else {
          g.drawImage(this.tile,i*sizeTile,j*sizeTile,sizeTile,sizeTile,this);
        }
      }
    }
  }

  public void setSizeTile(int newSize) {
    this.sizeTile = newSize;
    this.setPreferredSize(new Dimension(this.b.getWidth()*sizeTile, this.b.getHeight()*sizeTile));
    this.update(this);
  }

  public HashMap<Integer,Image> getMapNumbers() {
    HashMap<Integer,Image> map = new HashMap<>();
    Image img;
    for (int i = 1; i<9; i++) {
      img = Toolkit.getDefaultToolkit().getImage("../ressources/images/" + i + ".png");
      map.put(i,img);
    }
    return map;
  }

  @Override
  public void update(Object src) {
    this.repaint();
  }
}
