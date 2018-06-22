
package game;

public class Bombe extends Tile {

  public Bombe(int x, int y) {
    super(x,y);
  }

  public boolean isBombe() {
    return true;
  }

  public String toString() {
    return "b";
  }
}
