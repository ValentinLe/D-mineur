
from grille import *
import random


def creergrille(cote,val):
    grille=[]
    for i in range(cote):
        grille.append([])
        for j in range(cote):
            grille[i].append(Case(val,False,False))
    return grille

def aleabombe(grille,nbbombe,cote):
    posbombe=[]
    for i in range(nbbombe):
        p=True
        while p==True:
            posx=random.randint(0,cote-1)
            posy=random.randint(0,cote-1)
            if [posx,posy] not in posbombe:
                p=False
                posbombe.append([posx,posy])
                
    for i in range(nbbombe):
        grille[posbombe[i][0]][posbombe[i][1]].value="b"
    return grille

def voisins(L,i,j):
    ''' renvoi les coordonnées de toutes les cases voisines à la case L[j][i] '''
    if i==0:
        if j==0:
           L=[[i,j+1],[i+1,j],[i+1,j+1]]
        elif j==len(L)-1:
            L=[[i,j-1],[i+1,j],[i+1,j-1]]
        else:
            L=[[i,j-1],[i,j+1],[i+1,j],[i+1,j-1],[i+1,j+1]]

    elif i==len(L)-1:
        if j==0:
            L=[[i,j+1],[i-1,j],[i-1,j+1]]
        elif j==len(L)-1:
            L=[[i,j-1],[i-1,j],[i-1,j-1]]
        else:
            L=[[i-1,j],[i,j-1],[i,j+1],[i-1,j+1],[i-1,j-1]]
    else:
        if j==0:
            L=[[i,j+1],[i+1,j],[i+1,j+1],[i-1,j],[i-1,j+1]]
        elif j==len(L)-1:
            L=[[i,j-1],[i+1,j],[i+1,j-1],[i-1,j],[i-1,j-1]]
        else:
            L=[[i-1,j-1],[i-1,j],[i-1,j+1],[i,j-1],[i,j+1],[i+1,j-1],[i+1,j],[i+1,j+1]]
    return L


def pointbombe(grille):
    for i in range(len(grille)):
        for j in range(len(grille)):
            if grille[i][j].value!="b":
                L=voisins(grille,i,j)
                c=0
                for k in range(len(L)):
                    if grille[L[k][0]][L[k][1]].value=="b":
                        c+=1
                grille[i][j].value=c
    return grille


def propa(grille,i,j):
    L1=[]
    L2=[[i,j]]
    while L2!=[]:
        # on met les éléments de type nombre dans L1
        cpt = 0
        for k in range(len(L2)):
            if not(testvide(grille,L2[k-cpt][0],L2[k-cpt][1])) and not(grille[L2[k-cpt][0]][L2[k-cpt][1]].drap):
                L1.append(L2[k-cpt])
                del L2[k-cpt]
                cpt += 1
        # on propage avec ceux vide qui reste dans L2
        listepropa=propagation(grille,L1,L2)
        # on met les cases de L2 dans L1
        L1 += L2
        L2=[]
        # on met les éléments de propa dans L2
        for x in listepropa:
            L2.append([x[0],x[1]])
    return L1
        

def propagation(grille,L1,L2):
    L = []
    for i in range(len(L2)):
        if L2[i] not in L1:
            vois = voisins(grille,L2[i][0],L2[i][1])
            for z in vois:
                if z not in L1 and z not in L2 and z not in L and not(grille[z[0]][z[1]].drap):
                    L.append(z)
    return L

def testvide(grille,i,j):
    return grille[i][j].value==0

def testbombe(grille,i,j):
    return grille[i][j].value=="b"


def printgrille(grille):
    h = "\n"
    for j in range(len(grille)):
        for i in range(len(grille)):
            h=h+ 2*" " + str(grille[i][j].value)
        h += '\n'
    print(h)

def compteBombe(grille):
    cpt = 0
    n = len(grille)
    for i in range(n):
        for j in range(n):
            if not(grille[i][j].find):
                cpt += 1
    return cpt

def grilleDemineur(tailleG,nbBombe):
    g=creergrille(tailleG,0)
    g=aleabombe(g,nbBombe,tailleG)
    g=pointbombe(g)
    return g
