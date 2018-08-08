
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class BackgroundRepeat extends JPanel {

  private String image;
  private Image bg;

  public BackgroundRepeat(String im) {
    this.image = im;
    this.bg = Toolkit.getDefaultToolkit().getImage(im);
  }

  @Override
  public void paintComponent(Graphics g) {
    int widthBg = this.bg.getWidth(null);
    int heightBg = this.bg.getHeight(null);
    if (widthBg != -1 && heightBg != -1) {
      for (int j = 0; j < this.getHeight(); j += heightBg) {
        for (int i = 0; i < this.getWidth(); i += widthBg) {
          g.drawImage(this.bg,i,j,null);
        }
      }
    }
  }
}
