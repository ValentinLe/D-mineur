
package game;

public class Number extends Tile {

  private int value;
  private boolean discover;

  public Number(int x, int y, int value) {
    super(x,y);
    this.value = value;
    this.discover = false;
  }

  public Number(int x, int y) {
    this(x,y,0);
  }

  public int getValue() {
    return this.value;
  }

  public boolean isDiscover() {
    return this.discover;
  }

  public boolean isBombe() {
    return false;
  }

  public void setValue(int newValue) {
    this.value = newValue;
  }

  public String toString() {
    return "" + this.value;
  }
}
