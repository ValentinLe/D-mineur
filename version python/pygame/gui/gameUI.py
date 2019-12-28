
import pygame


pygame.init()


def loadImg(filename, size):
    img = pygame.image.load(filename)
    return pygame.transform.scale(img, size)


def run(grid):

    tileSize = 50

    numbers = []
    for i in range(1, 9):
        img = loadImg("images/" + str(i) +
                      ".png", (tileSize, tileSize))
        numbers.append(img)

    tile = loadImg("images/tile.png", (tileSize, tileSize))
    emptyTile = loadImg("images/tileEmpty.png", (tileSize, tileSize))
    bomb = loadImg("images/bombe.png", (tileSize, tileSize))

    pygame.display.set_caption("Demineur")
    window = pygame.display.set_mode(
        (grid.getWidth()*tileSize, grid.getHeight()*tileSize))

    launched = True
    while launched:
        window.fill((0, 0, 0))
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                launched = False
            elif event.type == pygame.MOUSEBUTTONDOWN:
                x = int(event.pos[0]/tileSize)
                y = int(event.pos[1]/tileSize)
                if grid.is_in_index(x, y):
                    grid.discoverAt(x, y)

        for j in range(grid.getHeight()):
            for i in range(grid.getWidth()):
                case = grid.getCaseAt(i, j)
                if case.is_visible():
                    window.blit(emptyTile, [i*tileSize, j*tileSize])
                    if case.is_number():
                        window.blit(numbers[case.getValue()-1],
                                    [i*tileSize, j*tileSize])
                    elif case.is_bomb():
                        window.blit(bomb, [i*tileSize, j*tileSize])
                else:
                    window.blit(tile, [i*tileSize, j*tileSize])
        pygame.display.flip()
