
package game;

public class Board {

  private int width;
  private int height;
  private Tile[][] grid;

  public Board(int width, int height) {
    this.width = width;
    this.height = height;
    createGrid();
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

}
