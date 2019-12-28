from fonctions import *
from tkinter import *


class ManipGrille:
    def __init__(self,g,nbBombe,tailleG,cv,imb,imd,text_nbBombe,imc):
        self.tailleG=tailleG
        self.nbBombe=nbBombe
        self.g=g
        self.cv=cv
        self.imB=imb
        self.imD = imd
        self.bombeRes = self.nbBombe
        self.text_nbBombe = text_nbBombe
        self.message(self.text_nbBombe,self.bombeRes)
        self.imC = imc
        
    
    def dessinerGrille(self,g,perdu=False):
        self.cv.delete(ALL)
        for i in range(self.tailleG):
            for j in range(self.tailleG):
                self.dessinerCase(30,i,j,self.g[i][j].value,perdu) 


    def dessinerCase(self,cote,i,j,x,perdu = False):
        x0,y0 = (cote)*i+2,(cote)*j+2
        x1,y1 = x0 + cote, y0 + cote
        xm,ym = x0 + cote//2,y0 + cote//2

        couleurFont = {"1":"#3366ff","2":"#009900","3":"#ffff00","4":"#ff5500","5":"#ff3300","6":"#cc0000","7":"#cc0066","8":"#660033"}
        case = self.g[i][j]
        if not(case.drap):
            if not(case.find):
                self.cv.create_rectangle(x0,y0,x1,y1,fill="#b3b3b3")
            else:
                if x!='b' and x!=0:
                    self.cv.create_rectangle(x0,y0,x1,y1,fill="#999999")
                    self.cv.create_text(xm,ym,text=x,font="monospace 15 bold",fill=couleurFont[str(x)])
                if x==0:
                    self.cv.create_rectangle(x0,y0,x1,y1,fill="#999999")
                    self.cv.create_text(xm,ym,text=" ")
                if x == 'b' and not(perdu):
                    self.cv.create_rectangle(x0,y0,x1,y1,fill="red")
                    self.cv.create_image(xm+1,ym+1,image=self.imB)
                if x=='b' and perdu:
                    self.cv.create_rectangle(x0,y0,x1,y1,fill="#b3b3b3")
                    self.cv.create_image(xm+1,ym+1,image=self.imB)            
        else:
            if perdu and case.value != 'b':
                self.cv.create_rectangle(x0,y0,x1,y1,fill="#b3b3b3")
                self.cv.create_image(xm,ym,image=self.imC)
            else:
                self.cv.create_rectangle(x0,y0,x1,y1,fill="#b3b3b3")
                self.cv.create_image(xm+1,ym+1,image=self.imD)

    def positionClic(self,event):
        ''' renvoi la position [x,y] du clic '''
        x = event.x - 2
        y = event.y - 2 
        x = int(x/30)
        y = int(y/30)
        return x,y

    def showBombe(self,cote,x,y):
        for i in range(self.tailleG):
            for j in range(self.tailleG):
                if self.g[i][j].value == 'b':
                    self.g[i][j].find = True

    def positionCase(self,event):
        x,y = self.positionClic(event)
        if x < self.tailleG and y < self.tailleG and not(self.g[x][y].drap) and not(self.g[x][y].find):
            if self.g[x][y].value == 0:
                l = propa(self.g,x,y)
            elif self.g[x][y].value != 'b':
                l = [[x,y]]
            else:
                l =  [[x,y]]
                self.cv.bind("<Button-1>",self.rien)
                self.cv.bind("<Button-3>",self.rien)
                self.showBombe(30,x,y)
                return self.dessinerGrille(self.g,True),self.dessinerCase(30,x,y,'b')
            for x in l:
                self.g[x[0]][x[1]].find = True
            self.dessinerGrille(self.g)
        if compteBombe(self.g) == self.nbBombe:
            self.cv.bind("<Button-1>",self.rien)
            self.cv.bind("<Button-3>",self.rien)
            self.showBombe(30,x,y)
            return self.dessinerGrille(self.g,True)

    def drapeau(self,event):
        x,y = self.positionClic(event)
        if x < self.tailleG and y < self.tailleG:
            ## switch ##
            if not(self.g[x][y].find):
                if self.bombeRes > 0 and not(self.g[x][y].drap):
                    self.g[x][y].drap = True
                    self.bombeRes -= 1
                    self.message(self.text_nbBombe,str(self.bombeRes))
                elif self.g[x][y].drap:
                    self.g[x][y].drap = False
                    self.bombeRes += 1
                    self.message(self.text_nbBombe,str(self.bombeRes))
        self.dessinerGrille(self.g)

    def message(self,zone_text,m):
        zone_text.config(state=NORMAL)
        zone_text.delete("0.0",END)
        zone_text.insert(END,str(m))
        zone_text.config(state=DISABLED)

    def rien(self,event=''):
        return
    
