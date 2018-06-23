
package game;

public class Tile {

  private int x;
  private int y;
  private int value;
  private boolean discover;
  private boolean bombe;
  private boolean flag;


  public Tile(int x, int y, int value) {
    this.x = x;
    this.y = y;
    this.value = value;
    this.discover = false;
    this.bombe = false;
    this.flag = false;
  }

  public Tile(int x, int y) {
    this(x,y,0);
  }

  public int getX() {
    return this.x;
  }

  public int getY() {
    return this.y;
  }

  public int getValue() {
    return this.value;
  }

  public boolean isDiscover() {
    return this.discover;
  }

  public boolean isFlag() {
    return this.flag;
  }

  public boolean isBombe() {
    return this.bombe;
  }

  public void setValue(int newValue) {
    this.value = newValue;
  }

  public void setBombe(boolean bombe) {
    this.bombe = bombe;
  }

  public void setFlag(boolean newFlag) {
    this.flag = flag;
  }

  public void setDiscover(boolean newDiscover) {
    this.discover = newDiscover;
  }

  public String toString() {
    if (!this.isBombe()) {
      return "" + this.value;
    } else {
      return "x";
    }
  }
}
