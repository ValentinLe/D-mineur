
package game;

import java.util.Random;
import java.util.ArrayList;

public class Board {

  private int width;
  private int height;
  private Tile[][] grid;

  public Board(int width, int height, int nbBombes) {
    this.width = width;
    this.height = height;
    createGrid();
    generateBombe(nbBombes);
    calculateNumbers();
  }

  public Tile[][] getGrid() {
    return this.grid;
  }

  public int getWidth() {
    return this.width;
  }

  public int getHeight() {
    return this.height;
  }

  public void createGrid() {
    this.grid = new Tile[this.height][this.width];
    for (int j = 0; j<this.height; j++) {
      for (int i = 0; i<this.width; i++) {
        this.grid[j][i] = new Number(i,j);
      }
    }
  }

  public void calculateNumbers() {
    for (int j = 0; j<this.height; j++) {
      for (int i = 0; i<this.width; i++) {
        if (!(this.grid[j][i] instanceof Bombe)) {
          int value = countBombe(consvois(i,j));
          ((Number)this.grid[j][i]).setValue(value);
        }
      }
    }
  }

  public int randNum(int min, int max) {
    Random rand = new Random();
    int num = rand.nextInt((max - min) + 1) + min;
    return num;
  }

  public void generateBombe(int nb) {
    int cpt = 0;
    Random rand;
    while(cpt != nb) {
      int randX = randNum(0, this.height - 1);
      int randY = randNum(0, this.width - 1);
      Tile tile = this.grid[randY][randX];
      if (!(tile instanceof Bombe)) {
        this.grid[randY][randX] = new Bombe(randX,randY);
        cpt += 1;
      }
    }
  }

  public boolean isInIndex(int i, int j) {
    return 0<=i && i<this.width && 0<=j && j<this.height;
  }

  public ArrayList<Tile> consvois(int i, int j) {
    ArrayList<Tile> listConsvois = new ArrayList<> ();
    for (int nj = j-1; nj<(j+2); nj++) {
      for (int ni = i-1; ni<(i+2); ni++) {
        if (isInIndex(ni,nj) && (ni!=i || nj!=j)) {
          listConsvois.add(this.grid[nj][ni]);
        }
      }
    }
    return listConsvois;
  }

  public int countBombe(ArrayList<Tile> listTile) {
    int cpt = 0;
    for (Tile tile : listTile) {
      if (tile instanceof Bombe) {
        cpt += 1;
      }
    }
    return cpt;
  }

  public String toString() {
    String ch = "";
    for (int j = 0; j<this.height; j++) {
      for (int i = 0; i<this.width; i++) {
        ch += this.grid[j][i].toString() + " ";
      }
      ch += "\n";
    }
    return ch;
  }

}
