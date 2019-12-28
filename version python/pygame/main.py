
from model.Grid import Grid
from gui.gameUI import run


def main():
    grid = Grid(10, 10, 10)
    run(grid)


if __name__ == "__main__":
    main()
