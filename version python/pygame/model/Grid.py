
from random import randint
from .Case import Case


class Grid:

    def __init__(self, width, height, nb_bomb):
        self.width = width
        self.height = height
        self.nb_bomb = nb_bomb
        self.generateGrid()

    def __str__(self):
        res = []
        for y in range(self.height):
            res.append("\n")
            for x in range(self.width):
                case = self.getCaseAt(x, y)
                if case.is_visible():
                    res.append(case.__str__())
                    res.append(" ")
                else:
                    res.append("- ")
        return ''.join(res)

    def gridView(self):
        res = []
        for y in range(self.height):
            res.append("\n")
            for x in range(self.width):
                case = self.getCaseAt(x, y)
                res.append(case.__str__())
                res.append(" ")
        return ''.join(res)

    def generateGrid(self):
        self.grid = []
        for y in range(self.height):
            self.grid.append([])
            for x in range(self.width):
                self.grid[y].append(Case(x, y))
        self._place_bomb()
        self._generateValues()

    def _place_bomb(self):
        cpt = 0
        while cpt != self.nb_bomb:
            xrand = randint(0, self.width-1)
            yrand = randint(0, self.height-1)
            case = self.getCaseAt(xrand, yrand)
            if not case.is_bomb():
                cpt += 1
                case.setValue(Case.BOMB_VALUE)

    def _generateValues(self):
        for y in range(self.height):
            for x in range(self.width):
                case = self.getCaseAt(x, y)
                if not case.is_bomb():
                    cpt_bomb = 0
                    neighbours = self.neighbours(x, y)
                    for neighbour in neighbours:
                        if neighbour.is_bomb():
                            cpt_bomb += 1
                    case.setValue(cpt_bomb)

    def getCaseAt(self, x, y):
        return self.grid[y][x]

    def getGrid(self):
        return self.grid

    def getWidth(self):
        return self.width

    def getHeight(self):
        return self.height

    def is_in_index(self, x, y):
        return x >= 0 and x < self.width and y >= 0 and y < self.height

    def neighbours(self, x, y, r=1):
        neighbours = []
        for j in range(y-r, y+r+1):
            for i in range(x-r, x+r+1):
                if not (i == x and j == y) and self.is_in_index(i, j):
                    neighbours.append(self.getCaseAt(i, j))
        return neighbours

    def discoverAt(self, x, y):
        open_list = []
        first_case = self.getCaseAt(x, y)
        first_case.setVisible(True)
        if not first_case.is_bomb():
            open_list.append(first_case)
        while open_list:
            current_case = open_list.pop()
            if current_case.is_propagable():
                neighbours = self.neighbours(
                    current_case.getX(), current_case.getY())
                for neighbour in neighbours:
                    if not neighbour.is_visible():
                        neighbour.setVisible(True)
                        open_list.append(neighbour)
