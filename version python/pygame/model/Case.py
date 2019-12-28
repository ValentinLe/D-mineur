
class Case:

    BOMB_VALUE = -1

    def __init__(self, x, y, value=0):
        self.x = x
        self.y = y
        self.value = value
        self.visible = False

    def __str__(self):
        if self.is_bomb():
            return "b"
        else:
            return str(self.getValue())

    def __repr__(self):
        return str(self.value)

    def strCoords(self):
        return "(" + str(self.x) + "," + str(self.y) + ")"

    def getX(self):
        return self.x

    def getY(self):
        return self.y

    def getValue(self):
        return self.value

    def is_bomb(self):
        return self.value == Case.BOMB_VALUE

    def is_visible(self):
        return self.visible

    def is_number(self):
        return self.value != 0 and not self.is_bomb()

    def is_propagable(self):
        return self.value == 0

    def setValue(self, value):
        self.value = value

    def setVisible(self, visible):
        self.visible = visible
