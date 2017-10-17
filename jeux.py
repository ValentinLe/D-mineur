import tkinter as tk
import fonctions as fct
import interfaceGrafique as ig
from grille import *

class Frame_param:
    def __init__(self,fen):
        self.fen = fen
        self.frame = tk.Frame(fen)
        self.frame.pack()
        self.fen.wm_title("Paramètres de la grille")

        self.textInfo = tk.Text(self.fen,width=60,height = 3)
        self.textInfo.config(state=tk.DISABLED)
        self.textInfo.pack()

        cadre = tk.Frame(self.fen)

        ct = tk.Frame(cadre)
        labelTaille = tk.Label(ct,text="Taille de la grille (10-30)",font="monospace 10")
        self.inputTaille = tk.Entry(ct)
        labelTaille.pack()
        self.inputTaille.pack()
        ct.pack(pady = 10)

        cb = tk.Frame(cadre)
        labelNbBombe = tk.Label(cb,text="Nombre de bombes (10-99)",font="monospace 10")
        self.inputNbBombe = tk.Entry(cb)
        labelNbBombe.pack()
        self.inputNbBombe.pack()
        cb.pack(pady = 10)


        cadre.pack(pady=10,padx=10)

        bouttonValid = tk.Button(self.fen,text="Jouer",font="monospace 10",command=lambda: self.jouer())
        bouttonValid.pack(pady=10)

        
    def jouer(self):
        nbBombe = self.inputNbBombe.get()
        tailleG = self.inputTaille.get()
        if self.test_input(nbBombe,tailleG):
            self.quitter()
            fj = tk.Tk()
            jeu=Frame_jeux(fj,int(nbBombe),int(tailleG))

    def test_input(self,nbBombe,tailleG):
        try:
            nbBombe = int(nbBombe)
            tailleG = int(tailleG)
        except:
            self.message("Tu n'as pas rentré(e) un entier dans chaque case")
            return False
        if nbBombe<10 or 99<nbBombe:
            self.message("Tu n'as pas rentré(e) un nombre correct de bombes")
            return False
        if tailleG<10 or 30<tailleG:
            self.message("Tu n'as pas rentré(e) une taille correcte pour la grille")
            return False
        return True

    def message(self,m=""):
        self.textInfo.config(state=tk.NORMAL)
        self.textInfo.delete("0.0",tk.END)
        self.textInfo.insert(tk.END,'\n' + m)
        self.textInfo.config(state=tk.DISABLED)

    def quitter(self):
        self.fen.destroy()


class Frame_jeux:
    def __init__(self,fen,nbBombe,tailleG):

        self.fen = fen
        self.fen.wm_title("Démineur")

        self.imB = tk.PhotoImage(file="images/bombe_fini.png")
        self.imD = tk.PhotoImage(file="images/drap2.png")
        self.imC = tk.PhotoImage(file="images/croix1.png")
        self.tailleG = tailleG
        self.nbBombe = nbBombe
        self.g = fct.grilleDemineur(self.tailleG,self.nbBombe)


        longueur = self.tailleG*30+2
        hauteur = self.tailleG*30+2

        cdg = tk.Frame(self.fen)
        cdd = tk.Frame(self.fen)
        cdg.pack(side=tk.LEFT)
        cdd.pack(side=tk.RIGHT)

        self.cv = tk.Canvas(self.fen,width=longueur,height=hauteur)
        self.cv.pack()


        bj = tk.Button(cdd,text="Rejouer",font="monospace 10",command=lambda: self.bjouer())
        bj.pack(pady=20)

        bparam = tk.Button(cdd,text="Paramètres",font="monospace 10",command=lambda: self.setParams())
        bparam.pack(pady=20)
        
        frame_bombe = tk.Frame(cdd)
        lab_nbBombe = tk.Label(frame_bombe,text="Bombes")
        lab_nbBombe.pack()
        self.text_nbBombe = tk.Text(frame_bombe,width=10,height = 1)
        self.text_nbBombe.pack()
        self.text_nbBombe.config(state=tk.DISABLED)
        frame_bombe.pack(pady=20)

        bquit = tk.Button(cdd,text="Quitter",font="monospace 10",command=lambda: self.bquit())
        bquit.pack(pady=20)

        self.g = ig.ManipGrille(self.g,self.nbBombe,self.tailleG,self.cv,self.imB,self.imD,self.text_nbBombe,self.imC)

        self.g.dessinerGrille(self.g)

        self.bjouer()

        
    def bjouer(self):
        self.g = fct.grilleDemineur(self.tailleG,self.nbBombe)
        self.grille = ig.ManipGrille(self.g,self.nbBombe,self.tailleG,self.cv,self.imB,self.imD,self.text_nbBombe,self.imC)
        self.grille.dessinerGrille(self.g)
        self.cv.bind("<Button-1>",self.grille.positionCase)
        self.cv.bind("<Button-3>",self.grille.drapeau)

    def bquit(self):
        ''' ferme la fenêtre '''
        self.fen.destroy()

    def setParams(self):
        self.bquit()
        param = tk.Tk()
        p = Frame_param(param)
        param.mainloop()

if __name__=="__main__":
    param = tk.Tk()
    p=Frame_param(param)
    param.mainloop()
