
package game;

import java.util.Random;
import java.util.ArrayList;
import gui.AbstractModelListener;

public class Board extends AbstractModelListener {

  private int width;
  private int height;
  private Tile[][] grid;
  private boolean over;

  public Board(int width, int height, int nbBombes) {
    this.width = width;
    this.height = height;
    this.over = false;
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

  public boolean isOver() {
    return this.over;
  }

  public void createGrid() {
    this.grid = new Tile[this.height][this.width];
    for (int j = 0; j<this.height; j++) {
      for (int i = 0; i<this.width; i++) {
        this.grid[j][i] = new Tile(i,j);
      }
    }
  }

  public void calculateNumbers() {
    for (int j = 0; j<this.height; j++) {
      for (int i = 0; i<this.width; i++) {
        if (!this.grid[j][i].isBombe()) {
          int value = countBombe(consvois(i,j));
          this.grid[j][i].setValue(value);
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
      if (!tile.isBombe()) {
        this.grid[randY][randX].setBombe(true);
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
      if (tile.isBombe()) {
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

  public ArrayList<Tile> transfertNumber(ArrayList<Tile> listFinal, ArrayList<Tile> listTransfert) {
    ArrayList<Tile> listTransfertRes = new ArrayList<>();
    for (Tile tile : listTransfert) {
      if (!tile.isBombe() && tile.getValue()!=0) {
        listFinal.add(tile);
      } else {
        listTransfertRes.add(tile);
      }
    }
    return listTransfertRes;
  }

  public ArrayList<Tile> propagation(Tile initTile) {
    ArrayList<Tile> listFinal = new ArrayList<>();
    ArrayList<Tile> listTransfert = new ArrayList<>();
    listTransfert.add(initTile);
    ArrayList<Tile> listPropagation = new ArrayList<>();
    while(!listTransfert.isEmpty()) {
      listTransfert = this.transfertNumber(listFinal,listTransfert);
      listPropagation = this.propagationList(listFinal, listTransfert);
      listFinal.addAll(listTransfert);
      listTransfert = new ArrayList<>();
      listTransfert.addAll(listPropagation);
      listPropagation = new ArrayList<>();
    }
    return listFinal;
  }

  public ArrayList<Tile> propagationList(ArrayList<Tile> listFinal, ArrayList<Tile> listTransfert) {
    ArrayList<Tile> listPropagation = new ArrayList<>();
    for(Tile tile : listTransfert) {
      if (!listFinal.contains(tile)) {
        ArrayList<Tile> voisins = this.consvois(tile.getX(), tile.getY());
        for(Tile vois : voisins) {
          if (!listFinal.contains(vois) && !listTransfert.contains(vois) &&
          !listPropagation.contains(vois) && !vois.isFlag()) {
            listPropagation.add(vois);
          }
        }
      }
    }
    return listPropagation;
  }

  public void setDiscoverAll(ArrayList<Tile> listTile) {
    for (Tile tile : listTile) {
      tile.setDiscover(true);
    }
  }

  public boolean isFinished() {
    for (int j = 0; j<this.height; j++) {
      for (int i = 0; i<this.width; i++) {
        if (!this.grid[j][i].isBombe() && !this.grid[j][i].isDiscover()) {
          return false;
        }
      }
    }
    this.over = true;
    return true;
  }

  public void flagTile(Tile tile) {
    if (!tile.isDiscover()) {
      if (tile.isFlag()) {
        tile.setFlag(false);
      } else {
        tile.setFlag(true);
      }
    }
    this.fireChange();
  }

  public void clic(Tile tileClic) {
    if (!this.over && !tileClic.isFlag()) {
      if (!tileClic.isBombe() && !tileClic.isDiscover()) {
        ArrayList<Tile> listPropagation = this.propagation(tileClic);
        this.setDiscoverAll(listPropagation);
      } else if (tileClic.isBombe()) {
        this.over = true;
      }
      this.fireChange();
    }
  }

}
