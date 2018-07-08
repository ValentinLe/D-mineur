
package gui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import game.*;

public class ConfigureLevel extends JFrame {

  private JLabel labelWidth;
  private JSlider sliderWidth;
  private JLabel labelHeight;
  private JSlider sliderHeight;
  private JLabel labelNbBombes;
  private JSlider sliderNbBombes;

  public ConfigureLevel() {
    this.setTitle("Demineur");

    Dimension dimButton = new Dimension(500,150);
    int sizeFont = 25;

    JPanel zoneControl = new JPanel();
    zoneControl.setLayout(new GridLayout(5,1,20,20));

    ButtonMenu bPlay = new ButtonMenu("Play", dimButton, sizeFont);
    bPlay.setFocusable(false);
    bPlay.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int width = ConfigureLevel.this.sliderWidth.getValue();
        int height = ConfigureLevel.this.sliderHeight.getValue();
        int nbBombes = ConfigureLevel.this.sliderNbBombes.getValue();
        new Interface(new Board(width, height, nbBombes));
        ConfigureLevel.this.dispose();
      }
    });

    JPanel zoneWidth = new JPanel();

    this.labelWidth = new JLabel();
    this.labelWidth.setFocusable(false);

    this.sliderWidth = new JSlider(10,70,10);
    this.sliderWidth.setFocusable(false);
    this.sliderWidth.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        ConfigureLevel.this.labelWidth.setText("Width : " + ConfigureLevel.this.sliderWidth.getValue());
        ConfigureLevel.this.setMaxValueNbBombes();
      }
    });

    labelWidth.setText("Width : " + this.sliderWidth.getValue());

    zoneWidth.setLayout(new GridLayout(2,1));
    zoneWidth.add(labelWidth);
    zoneWidth.add(sliderWidth);

    JPanel zoneHeight = new JPanel();

    this.labelHeight = new JLabel();
    this.labelHeight.setFocusable(false);

    this.sliderHeight = new JSlider(10,60,10);
    this.sliderHeight.setFocusable(false);
    this.sliderHeight.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        ConfigureLevel.this.labelHeight.setText("Height : " + ConfigureLevel.this.sliderHeight.getValue());
        ConfigureLevel.this.setMaxValueNbBombes();
      }
    });

    labelHeight.setText("Height :" + this.sliderHeight.getValue());

    zoneHeight.setLayout(new GridLayout(2,1));
    zoneHeight.add(labelHeight);
    zoneHeight.add(sliderHeight);

    JPanel zoneNbBombes = new JPanel();

    this.labelNbBombes = new JLabel("Bombes : ");
    this.labelNbBombes.setFocusable(false);

    this.sliderNbBombes = new JSlider(10,99,10);
    this.sliderNbBombes.setFocusable(false);
    this.sliderNbBombes.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        ConfigureLevel.this.labelNbBombes.setText("Bombes : " + ConfigureLevel.this.sliderNbBombes.getValue());
      }
    });

    this.labelNbBombes.setText("Bombes : " + this.sliderNbBombes.getValue());

    zoneNbBombes.setLayout(new GridLayout(2,1));
    zoneNbBombes.add(labelNbBombes);
    zoneNbBombes.add(sliderNbBombes);

    ButtonMenu bBack = new ButtonMenu("Back to menu", dimButton, sizeFont);
    bBack.setFocusable(false);
    bBack.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new Menu();
        ConfigureLevel.this.dispose();
      }
    });

    zoneControl.add(bPlay);
    zoneControl.add(zoneWidth);
    zoneControl.add(zoneHeight);
    zoneControl.add(zoneNbBombes);
    zoneControl.add(bBack);

    this.setLayout(new GridBagLayout());
    GridBagConstraints gc = new GridBagConstraints();
    gc.gridx = 0;
    gc.gridy = 0;
    this.add(zoneControl,gc);

    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setUndecorated(true);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public void setMaxValueNbBombes() {
    int width = this.sliderWidth.getValue();
    int height = this.sliderHeight.getValue();
    int maxValue = (width*height) - 1;
    this.sliderNbBombes.setMaximum(maxValue);
  }
}
