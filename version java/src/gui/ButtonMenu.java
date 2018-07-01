
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ButtonMenu extends JButton implements MouseListener {

  private Dimension size;
  private int width;
  private int height;
  private String text;
  private boolean hover;
  private boolean click;

  public ButtonMenu(String txt) {
    this.text = txt;
    this.hover = false;
    this.click = false;
    this.width = 500;
    this.height = 200;
    this.size = new Dimension(this.width, this.height);

    setVisible(true);
    setContentAreaFilled(false);
    setBorderPainted(false);
    addMouseListener(this);
    this.setPreferredSize(this.size);
  }

  @Override
  public Dimension getPreferredSize() {
    return this.size;
  }

  @Override
  public Dimension getMinimumSize() {
    return this.size;
  }

  @Override
  public Dimension getMaximumSize() {
    return this.size;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(new Color(128,255,210));
    g.fillRect(0,0, this.width, this.height);

    g.setColor(Color.GREEN);
    if (this.hover) {
      g.fillRect(0,0,this.width,this.height);
    }

    g.setColor(Color.RED);
    if (this.click) {
      g.fillRect(0,0,this.width,this.height);
    }

    g.setColor(Color.BLACK);
    Font font = new Font(Font.MONOSPACED, Font.BOLD, 25);
    g.setFont(font);
    g.drawString(this.text,this.getStringBestWidthSize(font),this.getStringBestHeightSize(font));

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    this.hover = true;
  }

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {
    this.click = true;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    this.click = false;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    this.hover = false;
  }

  public int getStringBestWidthSize(Font font) {
    FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
    int sizeFont = fontMetrics.stringWidth(this.text);
    int bestSize = Math.round((this.width - sizeFont)/2);
    return bestSize;
  }

  public int getStringBestHeightSize(Font font) {
    FontMetrics fontMetrics = this.getGraphics().getFontMetrics(font);
    int sizeFont = fontMetrics.getHeight();
    int bestSize = Math.round((this.height - sizeFont)/2 + fontMetrics.getAscent());
    return bestSize;
  }
}
