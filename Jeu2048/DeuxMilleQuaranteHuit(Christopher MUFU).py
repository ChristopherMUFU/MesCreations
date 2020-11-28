

#Bibliothèques graphique (natives avec python 3)
from tkinter import *
from tkinter.messagebox import *

#Bibliothèque de l'aléa (native avec python 3)
#Pour appeler un nombre entier au hasard entre a et b
#on fait poetiquement : randint(a, b)
from random import randint

#Ajout volontaire du module random() de la bibliothèque random
#Afin de controler la fréquence d'apparition du 2 étant supérieur à celui du 4
from random import random



#Ca fait plaisir de signer son travail ! Mettez à jour cette variable ^_^
auteur="Christopher MUFU"

"""
*********************************************************************************
*																				*
*							PROGRAMMATION DU JEU 2048							*
*																				*
*********************************************************************************
	Mais bien sur que vous connaissez ce jeu dont l'objectif est d'arriver
	à la puissance 11 du nombre préféré d'un informaticien : 2048 (2^11).

	Lorsque la flèche gauche est utilisée, tous les nombres se retrouvent projeter
	sur la gauche et une règle s'applique : après ce mouvement tous les nombres identiques
	sur la gauche fusionnent et passent à la puissance supérieur. Voici des exemples :

		2 4 X 4 8 X <-
			deviens : 2 8 8 X X X
			(hé oui, le 8 est crée mais il n'a pas été encore poussé pour fusionner avec l'autre)

		2 2 X 2 X 2 <-
			deviens : 4 4 X X X X
			(même principe, les 4 ne peuvent pas fusionner parce qu'ils viennent d'apparaitre)

		16 16 16 X X X <-
			deviens : 32 16 X X X X
			(quand il y a "égalité" on va dire que c'est contre le rebord que ça fusione)
			(cela ne fait donc pas 16 32 X X X X)

	Bien sur c'est le même principe par un mouvement sur la droite, en haut et en bas.

	La partie se termine dès qu'on atteint le nombre 2048 (et on gagne)
	ou si plus aucun mouvement n'est possible (et on perd) et que la grille est complète !

	Pour que ça soit sympa, l'interface graphique est donné (gratuit, c'est bibi qui offre)
	Ceci implique QUE VOUS NE POUVEZ PAS SUPPRIMER LES FONCTIONS CI-DESSOUS.
	Mais rien ne vous interdit de les modifier (d'ailleurs il FAUT les modifier)
	Une seule exception : PAS TOUCHE A LA FONCTION 'FENETRE' qui gère la fenètre graphique !
	Pour le reste :
		- a vous de modifier les autres fonctions existantes (sans modifier le type d'entrée et de sortie)
		- d'introduire d'autre fonctions ou procédures (autant que vous voulez, aucune restriction)
		- de divisier ce projet en plein de sous tache peut-etre plus facile
		- de commenter votre code (même pour vous ça sera plus confortable)




	Allez ! Une petite pièce SOS pour vous guider :
		la grille de jeu sera assimillé à un tableau à deux entrées
							grille[i][j]
		le premier indice (i) correspondant à la ligne (de haut en bas)
		le second indice (j) correspondant à la colonne (de gauche à droite)
		Une case vide sera représenté par le nombre 0 (l'interface ne l'affiche pas)
		pour faire simple tous les tableaux seront des dictionnaires
		(comme ça on se prend pas la tête à jouer avec la mémoire)

"""









#Initialisation de valeurs qui vont fluctuer selon la version du jeu (pui.2 ou Fibo)

val_1 = 0 #Valeur de départ (commun)
val_2 = 0 #Valeur de départ (plus rare)
val_fin = 0 #Valeur de fin de partie

#variables liées à la gestion de la version pui.2 et Fibo
fiboVal1 = 0
fiboVal2 = 0
fiboVal3 = 0
fiboVal4 = 0









#Initialisation : début de partie
#Il y a trois ou quatres valeurs (en général 2 mais parfois, rarement, un 4)

def Init(dim) :
	GrilleJeu={}
	for i in range(dim) :
		GrilleJeu[i]={}
		for j in range(dim) :
			GrilleJeu[i][j]=0

	i = 0 #indice de ligne
	j = 0 #indice de colonne
	test = 0 #variable de test aléatoire

	nbCase = randint(3,4) #nbre de case présent sur la grille

	for k in range(nbCase):
		i = randint(0, dim-1)
		j = randint(0, dim-1)

		test = random()

		if test >= 0.8: #détermine les chances d'apparition des deux valeurs de départ
			GrilleJeu[i][j] = val_2 #4 (pui.2) ou 2 (fibo)
		else:
			GrilleJeu[i][j] = val_1 #2 (pui.2) ou 1 (fibo)


	return GrilleJeu












#Renvoie 1 si c'est gagné
#Renvoie -1 si c'est perdu
#Renvoie 0 sinon

def TestFin(grille) :
	dim=len(grille)
	GrilleJeu={}
	compt = 0 #compteur permettant de savoir le nombre de cases remplies
	verif = 0 #variable de vérification en cas de victoire ou poursuite de la partie

	for i in range(dim) :
		GrilleJeu[i]={}
		for j in range(dim) :
			GrilleJeu[i][j]=grille[i][j]

			if GrilleJeu[i][j] == 0: #Présence d'une case vide
				verif = 0
			elif GrilleJeu[i][j] == val_fin: #2048 (pui.2) ou 233 (fibo)
				verif = 1
			else :
				compt = compt + 1

	if 	verif == 1 :
		return 1
	elif (compt == len(grille)*len(grille)
	and Test(GrilleJeu)==True): #Toutes les cases sont remplies
		return -1
	else:
		return 0









#Ajout d'une fonction testant si il est possible de réaliser les fonctions liées au mouvement du jeu
#Présente dans la fonction TestFin

def Test(grille):
	GrilleJeu={}
	for i in range(dim) :
		GrilleJeu[i]={}
		for j in range(dim) :
			GrilleJeu[i][j]=grille[i][j]


	if (ActionHaut(GrilleJeu)==GrilleJeu
	and ActionBas(GrilleJeu)==GrilleJeu
	and ActionGauche(GrilleJeu)==GrilleJeu
	and ActionDroite(GrilleJeu)==GrilleJeu):
		return True
	else:
		return False










#Création d'une fonction ajoutant de façon aléatoire parmis les cases disponibles un 2 ou 4.

def Ajout(grille):

	compt = 0 #compteur permettant de savoir le nombre de cases remplies
	dim=len(grille)
	GrilleJeu={}
	for i in range(dim) :
		GrilleJeu[i]={}
		for j in range(dim) :
			GrilleJeu[i][j]=grille[i][j]

			if GrilleJeu[i][j] == 0:
				pass
			else:
				compt += 1


	if compt==len(grille)*len(grille): #Situation ou toutes les cases sont remplies

			return GrilleJeu

	else:
			i = 0 #indice de ligne
			j = 0 #indice de colonne
			compt = 0 #variable pour le while
			test = 0 #variable de test aléatoire présent dans la while

			while  compt < 1: #Nécessaire pour avoir seulement une valeur ajouté sur la grille
				i = randint(0, dim-1)
				j = randint(0, dim-1)

				if GrilleJeu[i][j] == 0: #Présence d'un case vide
					compt+=1
					test = random()

					if test >= 0.75: #détermine les chances d'apparition des deux valeurs de départ
						GrilleJeu[i][j] = val_2 # 4 (pui.2) ou 2 (fibo)
					elif test < 0.75:
						GrilleJeu[i][j] = val_1 # 2 (pui.2) ou 1 (fibo)


			return GrilleJeu














#Renvoie la grille mise à jour après un mouvement vers le haut
#En entrée : la grille avant le mouvement
#En sortie : la grille après le mouvement

def ActionHaut(grille) :

	dim=len(grille)
	GrilleJeu={}
	assembler={}

	for i in range(dim) :
		GrilleJeu[i]={}
		assembler[i]={}

		for j in range(dim) :
			GrilleJeu[i][j]=grille[i][j]
			assembler[i][j]=False #Variable servant à savoir si la case en question à déja subit une fusion ou non


	#Lecture de la grille de haut en bas
	j=0
	while j <= dim-1:

		i=0
		while i <= dim-1:

			"""
			Le programme est divisé en 3 cas principaux :
			 	- Le cas ou la case étudiée est tout en haut de la grille (Cas 1)
				- Le cas ou la case étudiée est juste en dessous d'une case remplie (Cas 2)
				- Le cas ou la case étudiée est juste en dessous d'une case vide (Cas 4)

			Ces 3 cas on un code utilisable pour la version du 2048 avec les puissances de deux
			et également la version avec la Suite de Fibonacci.

			Il y a néanmoins la présence de 2 cas suplémentaires liés à la version de la Suite de Fibonacci (Cas 3 et 5),
			notamment avec cas de la valeur 1 pouvant s'additionner avec un 2 ou lui même.

			"""




			#Cas 1 ou la case étudiée est tout en haut de la grille (version pui.2 et Fibo)
			if GrilleJeu[i][j] >= 1 and i == 0:
					pass





			#Cas 2 ou la case au dessus de la case étudiée est remplie (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and i != 0 and GrilleJeu[i-1][j] != 0 and assembler[i-1][j]==False:

					#Situation ou la case étudiée égale à 2 fusionne avec un 1 (version Fibo uniquement)
					if GrilleJeu[i][j]==2 and GrilleJeu[i-1][j]==1 and assembler[i-1][j]==False : #case ayant subit aucune fusion
							GrilleJeu[i-1][j] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0
							assembler[i-1][j]=True #Enregistrement de la fusion effectuée

					elif (GrilleJeu[i-1][j] == GrilleJeu[i][j] *fiboVal3 #Elément nécessaire au fonctionnement de la version pui.2
					and assembler[i-1][j]==False #case ayant subit aucune fusion

					#Elément nécessaire au fonctionnement de la version fibo
					#Explication du processus :
					#Soit X la case étudiée et Y la case au dessus de celle-ci, la fusion s'opère si :
					#Premier cas (ou Y > X) : Si  X > Y-X  et  X + (Y-X) > Y   avec X différent de Y
					#Exemple cas 1 avec Y=3 et X=2 : 3-2=1 donc 2 > 1  et  2+1=3 donc 3 > 3 (strictement)
					#Second cas (ou Y < X)  : Si  Y > X-Y  et  Y + (X-Y) > X   avec X différent de Y
					#Exemple cas 2 avec Y=3 et X=5 : 5-3=2 donc 3 > 2  et  3+2=5 donc 5 > 5 (strictement)

					or GrilleJeu[i][j] > ((GrilleJeu[i-1][j]-GrilleJeu[i][j])*fiboVal2)
					and GrilleJeu[i][j]+((GrilleJeu[i-1][j]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[i-1][j] != GrilleJeu[i][j]
					and assembler[i-1][j]==False #case ayant subit aucune fusion

					or GrilleJeu[i-1][j] > ((GrilleJeu[i][j]-GrilleJeu[i-1][j])*fiboVal2)
					and GrilleJeu[i-1][j]+((GrilleJeu[i][j]-GrilleJeu[i-1][j])*fiboVal2) > GrilleJeu[i-1][j] and GrilleJeu[i-1][j] != GrilleJeu[i][j]
					and assembler[i-1][j]==False): #case ayant subit aucune fusion

							GrilleJeu[i-1][j] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0
							assembler[i-1][j]=True #Enregistrement de la fusion effectuée

					else: pass





			#Cas 3 ou la case au dessus de la case étudiée qui vaut 1 est remplie (version Fibo uniquement)
			elif GrilleJeu[i][j] == 1 and i != 0 and GrilleJeu[i-1][j] != 0 and assembler[i-1][j]==False :
					if GrilleJeu[i-1][j] == 1 or GrilleJeu[i-1][j] == 2:
							GrilleJeu[i-1][j] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0
							assembler[i-1][j]=True #Enregistrement de la fusion effectuée

					else: pass





			#Cas 4 ou la case au dessus de la case étudiée est vide (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and i != 0 and GrilleJeu[i-1][j] == 0 and assembler[i-1][j]==False:

					k=i-1
					test=True #variable booléenne permettant de savoir si les cases éloignées sont remplies ou non
					marge=0 #variable enregistrant la première case remplie trouvée

					while k >= 0:
							test = GrilleJeu[k][j]==0
							if test==False :
								marge=GrilleJeu[k][j]
								break

							else: pass
							k-=1

					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
							GrilleJeu[0][j] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0

					#Situation ou la case étudiée égale à 2 fusionne avec un 1 (version Fibo uniquement)
					elif marge == 1 and GrilleJeu[i][j]==2 and assembler[i-1][j]==False:
							GrilleJeu[k][j] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0
							assembler[i-1][j]=True

					#Situation ou la première case trouvée est égale de la case étudiée (pui.2 et Fibo)
					#Même logique que le Cas 2, pour plus d'informations voir le Cas 2
					elif (marge == GrilleJeu[k][j] and GrilleJeu[k][j] == GrilleJeu[i][j]*fiboVal3
					and assembler[k][j]==False
					or GrilleJeu[i][j] > ((GrilleJeu[k][j]-GrilleJeu[i][j])*fiboVal2) and GrilleJeu[i][j]+((GrilleJeu[k][j]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[k][j] != GrilleJeu[i][j]
					and assembler[k][j]==False
					or GrilleJeu[k][j] > ((GrilleJeu[i][j]-GrilleJeu[k][j])*fiboVal2) and GrilleJeu[k][j]+((GrilleJeu[i][j]-GrilleJeu[k][j])*fiboVal2) > GrilleJeu[k][j] and GrilleJeu[k][j] != GrilleJeu[i][j]
					and assembler[k][j]==False):

							GrilleJeu[k][j] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0
							assembler[k][j]=True

					#Situation ou la première case trouvée est différente de la case étudiée (version pui.2 uniquement)
					elif (marge == GrilleJeu[k][j] and GrilleJeu[k][j]*fiboVal4 != GrilleJeu[i][j]*fiboVal4
					or assembler[k][j]==True):
							GrilleJeu[k+1][j] = GrilleJeu[i][j]
							GrilleJeu[i][j] = 0

					#Situation ou la première case trouvée est trop petite par rapport à la case étudiée (version Fibo uniquement)
					else:
							GrilleJeu[k+1][j] = GrilleJeu[i][j]
							GrilleJeu[i][j] = 0





			#Cas 5 ou la case au dessus de la case étudiée qui vaut 1 est vide (version Fibo uniquement)""""#
			elif GrilleJeu[i][j] == 1 and i != 0 and GrilleJeu[i-1][j] == 0 and assembler[i-1][j]==False:
					k=i-1
					test=True #variable booléenne permettant de savoir si les cases éloignées sont remplies ou non
					marge=0 #variable enregistrant la première case remplie trouvée
					while k >= 0:
						test = GrilleJeu[k][j]==0
						if test==False :
							marge=GrilleJeu[k][j]
							break
						else: pass
						k-=1

					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
						GrilleJeu[0][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation ou la première case trouvée est différente de 1 et 2
					elif marge == GrilleJeu[k][j] and GrilleJeu[k][j] != 1 and GrilleJeu[k][j] != 2 or assembler[k][j]==False:
						GrilleJeu[k+1][j] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation ou la première case trouvée est égale à 1 ou 2
					elif marge == GrilleJeu[k][j] and GrilleJeu[k][j] == 1 or GrilleJeu[k][j] == 2 and assembler[k][j]==False: #case ayant subit aucune fusion
						GrilleJeu[k][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[k][j]=True #Enregistrement de la fusion effectuée



			i+=1
		j+=1

	GrilleJeu=Ajout(GrilleJeu)


	return GrilleJeu








#Renvoie la grille mise à jour après un mouvement vers le bas
#En entrée : la grille avant le mouvement
#En sortie : la grille après le mouvement

def ActionBas(grille) :

	dim=len(grille)
	GrilleJeu={}
	assembler={}

	for i in range(dim) :
		GrilleJeu[i]={}
		assembler[i]={}

		for j in range(dim) :
			GrilleJeu[i][j]=grille[i][j]
			assembler[i][j]=False

	#Lecture de la grille de bas en haut (commençant par la droite)
	j=dim-1
	while j >= 0:

		i=dim-1
		while i >= 0:


			"""
			Le programme est divisé en 3 cas principaux :
			 	- Le cas ou la case étudiée est tout en bas de la grille (Cas 1)
				- Le cas ou la case étudiée est juste au dessus d'une case remplie (Cas 2)
				- Le cas ou la case étudiée est juste au dessus d'une case vide (Cas 4)

			Ces 3 cas on un code utilisable pour la version du 2048 avec les puissances de deux
			et également la version avec la Suite de Fibonacci.

			Il y a néanmoins la présence de 2 cas suplémentaires liés à la version de la Suite de Fibonacci (Cas 3 et 5),
			notamment avec cas de la valeur 1 pouvant s'additionner avec un 2 ou lui même.

			"""




			#Cas 1 ou la case étudiée est tout en bas de la grille (version pui.2 et Fibo)
			if GrilleJeu[i][j] >= 1 and i == dim-1:
					pass





			#Cas 2 ou la case en dessous de la case étudiée est remplie (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and i != dim-1 and GrilleJeu[i+1][j] != 0 and assembler[i+1][j]==False:

					if GrilleJeu[i][j]==2 and GrilleJeu[i+1][j]==1 and assembler[i+1][j]==False:
						GrilleJeu[i+1][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i+1][j]=True

					elif (GrilleJeu[i+1][j] == GrilleJeu[i][j]*fiboVal3 #Elément nécessaire au fonctionnement de la version pui.2
					and assembler[i+1][j]==False
					#Elément nécessaire au fonctionnement de la version fibo
					#Explication du processus :
					#Soit X la case étudiée et Y la case en dessous de celle-ci, la fusion s'opère si :
					#Premier cas (ou Y > X) : Si  X > Y-X  et  X + (Y-X) > Y   avec X différent de Y
					#Exemple cas 1 avec Y=3 et X=2 : 3-2=1 donc 2 > 1  et  2+1=3 donc 3 > 3 (strictement)
					#Second cas (ou Y < X)  : Si  Y > X-Y  et  Y + (X-Y) > X   avec X différent de Y
					#Exemple cas 2 avec Y=3 et X=5 : 5-3=2 donc 3 > 2  et  3+2=5 donc 5 > 5 (strictement)
					or GrilleJeu[i][j] > ((GrilleJeu[i+1][j]-GrilleJeu[i][j])*fiboVal2)
					and GrilleJeu[i][j]+((GrilleJeu[i+1][j]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[i+1][j] != GrilleJeu[i][j]
					and assembler[i+1][j]==False
					or GrilleJeu[i+1][j] > ((GrilleJeu[i][j]-GrilleJeu[i+1][j])*fiboVal2)
					and GrilleJeu[i+1][j]+((GrilleJeu[i][j]-GrilleJeu[i+1][j])*fiboVal2) > GrilleJeu[i+1][j] and GrilleJeu[i+1][j] != GrilleJeu[i][j]
					and assembler[i+1][j]==False):
						GrilleJeu[i+1][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i+1][j]=True
					else: pass





			#Cas 3 ou la case en dessous de la case étudiée qui vaut 1 est remplie (version Fibo uniquement)
			elif GrilleJeu[i][j] == 1 and i != dim-1 and GrilleJeu[i+1][j] != 0 and assembler[i+1][j]==False:
					if GrilleJeu[i+1][j] == 1 or GrilleJeu[i+1][j] == 2 and assembler[i+1][j]==False:
						GrilleJeu[i+1][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i+1][j]=True
					else: pass




			#Cas 4 ou la case en dessous d'une case intermédiaire est vide (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and i != dim-1 and GrilleJeu[i+1][j] == 0 and assembler[i+1][j]==False:
					k=i+1
					test=True #variable booléenne permettant de savoir si les cases éloignées sont remplies ou non
					marge=0 #variable enregistrant la première case remplie trouvée
					while k < dim:
						test = GrilleJeu[k][j]==0
						if test==False :
							marge=GrilleJeu[k][j]
							break
						else: pass
						k+=1

					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
						GrilleJeu[dim-1][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation fibo
					elif marge == 1 and GrilleJeu[i][j]==2 and assembler[k][j]==False:
						GrilleJeu[k][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[k][j]==True

					#Situation ou la première case trouvée est égale de la case étudiée (pui.2 et Fibo)
					#Même logique que le Cas 2, pour plus d'informations voir le Cas 2
					elif (marge == GrilleJeu[k][j] and GrilleJeu[k][j] == GrilleJeu[i][j]*fiboVal3
					and assembler[k][j]==False
					or GrilleJeu[i][j] > ((GrilleJeu[k][j]-GrilleJeu[i][j])*fiboVal2) and GrilleJeu[i][j]+((GrilleJeu[k][j]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[k][j] != GrilleJeu[i][j]
					and assembler[k][j]==False
					or GrilleJeu[k][j] > ((GrilleJeu[i][j]-GrilleJeu[k][j])*fiboVal2) and GrilleJeu[k][j]+((GrilleJeu[i][j]-GrilleJeu[k][j])*fiboVal2) > GrilleJeu[k][j] and GrilleJeu[k][j] != GrilleJeu[i][j]
					and assembler[k][j]==False):
						GrilleJeu[k][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[k][j]==True

					#Situation ou la première case trouvée est différente de la case étudiée (version pui.2 uniquement)
					elif (marge == GrilleJeu[k][j] and GrilleJeu[k][j]*fiboVal4 != GrilleJeu[i][j]*fiboVal4
					or assembler[k][j]==True):
						GrilleJeu[k-1][j] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation ou la première case trouvée est trop petite par rapport à la case étudiée (version Fibo uniquement)
					else:
						GrilleJeu[k-1][j] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0





			#Cas 5 ou la case en dessous de la case étudiée qui vaut 1 est vide (version Fibo uniquement)
			elif GrilleJeu[i][j] == 1 and i != dim-1 and GrilleJeu[i+1][j] == 0 and assembler[i+1][j]==False:
					k=i+1
					test=False
					marge=0
					while k < dim:
						test = GrilleJeu[k][j]==0
						if test==False :
							marge=GrilleJeu[k][j]
							break
						else: pass
						k+=1
					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
						GrilleJeu[dim-1][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
					#Situation ou la première case trouvée est différente de 1 et 2
					elif marge == GrilleJeu[k][j] and GrilleJeu[k][j] != 1 and GrilleJeu[k][j] != 2:
						GrilleJeu[k-1][j] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
					#Situation ou la première case trouvée est égale à 1 ou 2
					elif marge == GrilleJeu[k][j] and GrilleJeu[k][j] == 1 or GrilleJeu[k][j] == 2 and assembler[k][j]==False :
						GrilleJeu[k][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[k][j]==True




			i-=1
		j-=1

	GrilleJeu=Ajout(GrilleJeu)

	return GrilleJeu









#Renvoie la grille mise à jour après un mouvement vers la gauche
#En entrée : la grille avant le mouvement
#En sortie : la grille après le mouvement

def ActionGauche(grille) :
	dim=len(grille)
	GrilleJeu={}
	assembler={}
	for i in range(dim) :
		GrilleJeu[i]={}
		assembler[i]={}
		for j in range(dim) :
			GrilleJeu[i][j]=grille[i][j]
			assembler[i][j]=False #Variable servant à savoir si la case en question à déja subit une fusion ou non


	#Lecture de la grille de gauche à droite
	i=0
	while i <= dim-1:
		j=0
		while j <= dim-1:


			"""
			Le programme est divisé en 3 cas principaux :
			 	- Le cas ou la case étudiée est tout à gauche de la grille (Cas 1)
				- Le cas ou la case étudiée est juste à droite d'une case remplie (Cas 2)
				- Le cas ou la case étudiée est juste  à droite d'une case vide (Cas 4)

			Ces 3 cas on un code utilisable pour la version du 2048 avec les puissances de deux
			et également la version avec la Suite de Fibonacci.

			Il y a néanmoins la présence de 2 cas suplémentaires liés à la version de la Suite de Fibonacci (Cas 3 et 5),
			notamment avec cas de la valeur 1 pouvant s'additionner avec un 2 ou lui même.

			"""




			#Cas 1 ou la case étudiée est tout tout à gauche de la grille (version pui.2 et Fibo)
			if GrilleJeu[i][j] >= 1  and j == 0:
					pass




			#Cas 2 ou la case juste à gauche de la case étudiée est remplie (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and j != 0 and GrilleJeu[i][j-1] != 0 and assembler[i][j-1]==False:

					#Situation fibo
					if GrilleJeu[i][j]==2 and GrilleJeu[i][j-1]==1 and assembler[i][j-1]==False:
							GrilleJeu[i][j-1] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0
							assembler[i][j-1]=True

					elif (GrilleJeu[i][j-1] == GrilleJeu[i][j] *fiboVal3 #Elément nécessaire au fonctionnement de la version pui.2
					and assembler[i][j-1]==False
					#Elément nécessaire au fonctionnement de la version fibo
					#Explication du processus :
					#Soit X la case étudiée et Y la case au dessus de celle-ci, la fusion s'opère si :
					#Premier cas (ou Y > X) : Si  X > Y-X  et  X + (Y-X) > Y   avec X différent de Y
					#Exemple cas 1 avec Y=3 et X=2 : 3-2=1 donc 2 > 1  et  2+1=3 donc 3 > 3 (strictement)
					#Second cas (ou Y < X)  : Si  Y > X-Y  et  Y + (X-Y) > X   avec X différent de Y
					#Exemple cas 2 avec Y=3 et X=5 : 5-3=2 donc 3 > 2  et  3+2=5 donc 5 > 5 (strictement)
					or GrilleJeu[i][j] > ((GrilleJeu[i][j-1]-GrilleJeu[i][j])*fiboVal2)
					and GrilleJeu[i][j]+((GrilleJeu[i][j-1]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[i][j-1] != GrilleJeu[i][j]
					and assembler[i][j-1]==False
					or GrilleJeu[i][j-1] > ((GrilleJeu[i][j]-GrilleJeu[i][j-1])*fiboVal2)
					and GrilleJeu[i][j-1]+((GrilleJeu[i][j]-GrilleJeu[i][j-1])*fiboVal2) > GrilleJeu[i][j-1] and GrilleJeu[i][j-1] != GrilleJeu[i][j]
					and assembler[i][j-1]==False):

							GrilleJeu[i][j-1] += GrilleJeu[i][j]
							GrilleJeu[i][j] = 0
							assembler[i][j-1]=True

					else: pass





			#Cas 3 ou la case juste à gauche de la case étudiée qui vaut 1 est remplie (version Fibo uniquement)
			elif GrilleJeu[i][j] == 1 and j != 0 and GrilleJeu[i][j-1] != 0 and assembler[i][j-1]==False:
					if GrilleJeu[i][j-1] == 1 or GrilleJeu[i][j-1] == 2 and assembler[i][j-1]==False:
						GrilleJeu[i][j-1] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][j-1]=True

					else: pass




			#Cas 4 ou la case juste à gauche de la case étudiée est vide (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and j != 0 and GrilleJeu[i][j-1] == 0 and assembler[i][j-1]==False:

					k=j-1
					test=True #variable booléenne permettant de savoir si les cases éloignées sont remplies ou non
					marge=0 #variable enregistrant la première case remplie trouvée
					while k >= 0:
						test = GrilleJeu[i][k]==0
						if test==False :
							marge=GrilleJeu[i][k]
							break

						else: pass
						k-=1

					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
						GrilleJeu[i][0] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation fibo
					elif marge == 1 and GrilleJeu[i][j]==2 and assembler[i][k]==False:
						GrilleJeu[i][k] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][k]=True

					#Situation ou la première case trouvée est égale de la case étudiée (pui.2 et Fibo)
					#Même logique que le Cas 2, pour plus d'informations voir le Cas 2
					elif (marge == GrilleJeu[i][k] and GrilleJeu[i][k] == GrilleJeu[i][j]*fiboVal3
					and assembler[i][k]==False
					or GrilleJeu[i][j] > ((GrilleJeu[i][k]-GrilleJeu[i][j])*fiboVal2) and GrilleJeu[i][j]+((GrilleJeu[i][k]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[i][k] != GrilleJeu[i][j]
					and assembler[i][k]==False
					or GrilleJeu[i][k] > ((GrilleJeu[i][j]-GrilleJeu[i][k])*fiboVal2) and GrilleJeu[i][k]+((GrilleJeu[i][j]-GrilleJeu[i][k])*fiboVal2) > GrilleJeu[i][k] and GrilleJeu[i][k] != GrilleJeu[i][j]
					and assembler[i][k]==False):
						GrilleJeu[i][k] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][k]=True

					#Situation ou la première case trouvée est différente de la case étudiée (version pui.2 uniquement)
					elif (marge == GrilleJeu[i][k] and GrilleJeu[i][k]*fiboVal4 != GrilleJeu[i][j]*fiboVal4
					or assembler[i][k]==False):
						GrilleJeu[i][k+1] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation ou la première case trouvée est trop petite par rapport à la case étudiée (version Fibo uniquement)
					else:
						GrilleJeu[i][k+1] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0





			#Cas 5 ou la case juste à gauche de la case étudiée qui vaut 1 est vide (version Fibo uniquement)
			elif GrilleJeu[i][j] == 1 and j != 0 and GrilleJeu[i][j-1] == 0 and assembler[i][j-1]==False:
					k=j-1
					test=True #variable booléenne permettant de savoir si les cases éloignées sont remplies ou non
					marge=0 #variable enregistrant la première case remplie trouvée
					while k >= 0:
						test = GrilleJeu[i][k]==0
						if test==False :
							marge=GrilleJeu[i][k]
							break
						else: pass
						k-=1

					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
						GrilleJeu[0][j] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
					#Situation ou la première case trouvée est différente de 1 et 2
					elif marge == GrilleJeu[i][k] and GrilleJeu[i][k] != 1 and GrilleJeu[i][k] != 2:
						GrilleJeu[i][k+1] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
					#Situation ou la première case trouvée est égale à 1 ou 2
					elif marge == GrilleJeu[i][k] and GrilleJeu[i][k] == 1 or GrilleJeu[i][k] == 2 and assembler[i][k]==False:
						GrilleJeu[i][k] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][k]=True


			j+=1
		i+=1

	GrilleJeu=Ajout(GrilleJeu)

	return GrilleJeu










#Renvoie la grille mise à jour après un mouvement vers la droite
#En entrée : la grille avant le mouvement
#En sortie : la grille après le mouvement

def ActionDroite(grille) :
	dim=len(grille)
	GrilleJeu={}
	assembler={}
	for i in range(dim) :
		GrilleJeu[i]={}
		assembler[i]={}
		for j in range(dim) :
			GrilleJeu[i][j]=grille[i][j]
			assembler[i][j]=False


	#Lecture de la grille de droite à gauche (commençant par le bas)
	i=dim-1
	while i >= 0:

		j=dim-1
		while j >= 0:


			"""
			Le programme est divisé en 3 cas principaux :
			 	- Le cas ou la case étudiée est tout à droite de la grille (Cas 1)
				- Le cas ou la case étudiée est juste à gauche d'une case remplie (Cas 2)
				- Le cas ou la case étudiée est juste à gauche d'une case vide (Cas 4)

			Ces 3 cas on un code utilisable pour la version du 2048 avec les puissances de deux
			et également la version avec la Suite de Fibonacci.

			Il y a néanmoins la présence de 2 cas suplémentaires liés à la version de la Suite de Fibonacci (Cas 3 et 5),
			notamment avec cas de la valeur 1 pouvant s'additionner avec un 2 ou lui même.

			"""


			#Cas 1 ou la case étudiée est tout à droite de la grille (version pui.2 et Fibo)
			if GrilleJeu[i][j] >= 1 and j == dim-1:
					pass




			#Cas 2 ou la case à droite de la case étudiée est remplie (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and j != dim-1 and GrilleJeu[i][j+1] != 0 and assembler[i][j+1]==False:

					if GrilleJeu[i][j]==2 and GrilleJeu[i][j+1]==1 and assembler[i][j+1]==False:
						GrilleJeu[i][j+1] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][j+1]=True

					elif (GrilleJeu[i][j+1] == GrilleJeu[i][j]*fiboVal3 #Elément nécessaire au fonctionnement de la version pui.2
					and assembler[i][j+1]==False

					#Elément nécessaire au fonctionnement de la version fibo
					#Explication du processus :
					#Soit X la case étudiée et Y la case en dessous de celle-ci, la fusion s'opère si :
					#Premier cas (ou Y > X) : Si  X > Y-X  et  X + (Y-X) > Y   avec X différent de Y
					#Exemple cas 1 avec Y=3 et X=2 : 3-2=1 donc 2 > 1  et  2+1=3 donc 3 > 3 (strictement)
					#Second cas (ou Y < X)  : Si  Y > X-Y  et  Y + (X-Y) > X   avec X différent de Y
					#Exemple cas 2 avec Y=3 et X=5 : 5-3=2 donc 3 > 2  et  3+2=5 donc 5 > 5 (strictement)
					or GrilleJeu[i][j] > ((GrilleJeu[i][j+1]-GrilleJeu[i][j])*fiboVal2)
					and GrilleJeu[i][j]+((GrilleJeu[i][j+1]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[i][j+1] != GrilleJeu[i][j]
					and assembler[i][j+1]==False
					or GrilleJeu[i][j+1] > ((GrilleJeu[i][j]-GrilleJeu[i][j+1])*fiboVal2)
					and GrilleJeu[i][j+1]+((GrilleJeu[i][j]-GrilleJeu[i][j+1])*fiboVal2) > GrilleJeu[i][j+1] and GrilleJeu[i][j+1] != GrilleJeu[i][j]
					and assembler[i][j+1]==False):
						GrilleJeu[i][j+1] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
					else: pass




			#Cas 3 ou la case à droite de la case étudiée qui vaut 1 est remplie (version Fibo uniquement)
			elif GrilleJeu[i][j] == 1 and j != dim-1 and GrilleJeu[i][j+1] != 0 and assembler[i][j+1]==False:
					if GrilleJeu[i][j+1] == 1 or GrilleJeu[i][j+1] == 2:
						GrilleJeu[i][j+1] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][j+1]=True
					else: pass




			#Cas 4 ou la case est juste à droite d'une case étudiée est vide (version pui.2 et Fibo)
			elif GrilleJeu[i][j] > 1 and j != dim-1 and GrilleJeu[i][j+1] == 0 and assembler[i][j+1]==False:
					k=j+1
					test=True #variable booléenne permettant de savoir si les cases éloignées sont remplies ou non
					marge=0 #variable enregistrant la première case remplie trouvée
					while k < dim:
						test = GrilleJeu[i][k]==0
						if test==False :
							marge=GrilleJeu[i][k]
							break
						else: pass
						k+=1

					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
						GrilleJeu[i][dim-1] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation fibo
					elif marge == 1 and GrilleJeu[i][j]==2 and assembler[i][k]==False:
						GrilleJeu[i][k] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][k]=True

					#Situation ou la première case trouvée est égale de la case étudiée (pui.2 et Fibo)
					elif (marge == GrilleJeu[i][k] and GrilleJeu[i][k] == GrilleJeu[i][j]*fiboVal3 #Elément nécessaire au fonctionnement de la version pui.2
					and assembler[i][k]==False
					or GrilleJeu[i][j] > ((GrilleJeu[i][k]-GrilleJeu[i][j])*fiboVal2) and GrilleJeu[i][j]+((GrilleJeu[i][k]-GrilleJeu[i][j])*fiboVal2) > GrilleJeu[i][j] and GrilleJeu[i][k] != GrilleJeu[i][j]
					and assembler[i][k]==False
					or GrilleJeu[i][k] > ((GrilleJeu[i][j]-GrilleJeu[i][k])*fiboVal2) and GrilleJeu[i][k]+((GrilleJeu[i][j]-GrilleJeu[i][k])*fiboVal2) > GrilleJeu[i][k] and GrilleJeu[i][k] != GrilleJeu[i][j]
					and assembler[i][k]==False):
						GrilleJeu[i][k] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation ou la première case trouvée est différente de la case étudiée (version pui.2 uniquement)
					elif (marge == GrilleJeu[i][k] and GrilleJeu[i][k]*fiboVal4 != GrilleJeu[i][j]*fiboVal4
					or assembler[i][k]==True) :
						GrilleJeu[i][k-1] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0

					#Situation ou la première case trouvée est trop petite par rapport à la case étudiée (version Fibo uniquement)
					else:
						GrilleJeu[i][k-1] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0




			#Cas 5 ou la case à droite de la case étudiée qui vaut 1 est vide (version Fibo uniquement)
			elif GrilleJeu[i][j] == 1 and j != dim-1 and GrilleJeu[i][j+1] == 0 and assembler[i][j+1]==False:
					k=j+1
					test=False
					marge=0
					while k < dim:
						test = GrilleJeu[i][k]==0
						if test==False :
							marge=GrilleJeu[i][k]
							break
						else: pass
						k+=1
					#Situation ou aucune case remplie n'est trouvée
					if marge == 0:
						GrilleJeu[i][dim-1] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
					#Situation ou la première case trouvée est différente de 1 et 2
					elif (marge == GrilleJeu[i][k] and GrilleJeu[i][k] != 1 and GrilleJeu[i][k] != 2
					or assembler[i][k]==True):
						GrilleJeu[i][k-1] = GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
					#Situation ou la première case trouvée est égale à 1 ou 2
					elif marge == GrilleJeu[i][k] and GrilleJeu[i][k] == 1 or GrilleJeu[i][k] == 2 and assembler[i][k]==False:
						GrilleJeu[i][k] += GrilleJeu[i][j]
						GrilleJeu[i][j] = 0
						assembler[i][k]=True




			j-=1
		i-=1

	GrilleJeu=Ajout(GrilleJeu)

	return GrilleJeu













#--------------------------------------------------------------------------------------------------------------------------------------------------------------------------


def FENETRE(dim) :

	def recupPartie() :
		dim=len(JEU)
		X={}
		for i in range(dim) :
			X[i]={}
			for j in range(dim) :
				try : X[i][j]=int(JEU[i][j].get())
				except : X[i][j]=0
		return X

	def InjectionPartie(X) :
		for i in range(dim) :
			for j in range(dim) :
				try : (JEU[i][j]).set(X[i][j])
				except : (JEU[i][j]).set(0)

				if(int(JEU[i][j].get())==0) : (JEU[i][j]).set("")

	def Clavier(mouvement):
		CMPT.set(CMPT.get()+1)

		touche = mouvement.keysym
		# déplacement vers le haut
		if touche == 'Up': ActionHaut0()
		# déplacement vers le bas
		if touche == 'Down': ActionBas0()
		# déplacement vers la droite
		if touche == 'Right': ActionDroite0()
		# déplacement vers la gauche
		if touche == 'Left': ActionGauche0()

	def ActionRecommencer() :
		X=Init(dim)
		for i in range(dim) :
			for j in range(dim) :
				(JEU[i][j]).set(X[i][j])
				if(X[i][j]==0) : (JEU[i][j]).set("")
		CMPT.set(0)

	def ActionHaut0() :
		InjectionPartie(ActionHaut(recupPartie()))
		TestFin0(recupPartie())

	def ActionBas0() :
		InjectionPartie(ActionBas(recupPartie()))
		TestFin0(recupPartie())

	def ActionGauche0() :
		InjectionPartie(ActionGauche(recupPartie()))
		TestFin0(recupPartie())

	def ActionDroite0() :
		InjectionPartie(ActionDroite(recupPartie()))
		TestFin0(recupPartie())

	def TestFin0(X) :
		test=TestFin(X)
		if(test==1) : showinfo("FIN DE PARTIE", "Bravo ! Vous avez gagné en "+str(CMPT.get())+" coups. Recommencer ?")
		if(test==-1) :showinfo("FIN DE PARTIE", "Perdu ! Il vous a fallu "+str(CMPT.get())+" coups pour perdre... comment dire. On s'arrête là ou on recommence ?")
		if(test!=0) : ActionRecommencer()





	fenetre = Tk()
	fenetre.title('2048 par '+auteur)

	CMPT=IntVar()
	CMPT.set(0)

	X=Init(dim)
	JEU={}
	for i in range(dim) :
		JEU[i]={}
		for j in range(dim) :
			JEU[i][j]=StringVar()
			(JEU[i][j]).set(X[i][j])
			if(X[i][j]==0) : (JEU[i][j]).set("")

	base=70 #Taille en px d'un carré
	if(dim>7) :
		base=50
	marge=10 #Marge de beauté

	hauteur = dim*base+2*marge+100
	largeur = dim*base+2*marge

	canvas = Canvas(fenetre, background="white", width=largeur, height=hauteur )

	case={}
	for i in range(dim) :
		case[i]={}
		for j in range(dim) :
			canvas.create_rectangle((base*i+marge,base*j+marge), (base*(i+1)+marge,base*(j+1)+marge),fill='gray', width=5, outline='#7BD42D')

			c_fg='white'
			c_bg ='gray'


			if((JEU[i][j]).get()==0) : c_fg='white'



			L=Label(fenetre, textvariable=JEU[i][j], fg=c_fg, bg=c_bg)
			L.place(x=base*j+base//2, y=base*i+base//2, anchor="nw")







	txt="Nombre de mouvement : "
	L=Label(fenetre, text=txt, fg='black', bg='white')
	L.place(x=marge, y=(hauteur-base), anchor="sw")

	L=Label(fenetre, textvariable=CMPT, fg='black', bg='white')
	L.place(x=marge+len(txt)*7, y=(hauteur-base), anchor="sw")


	fenetre.geometry(str(largeur)+"x"+str(hauteur))
	BoutonQuitter = Button(fenetre, text ='Quitter', command = fenetre.destroy, fg='white', activeforeground='green', bg='green', activebackground='white' )
	BoutonQuitter.place(x=marge, y=hauteur-marge, anchor="sw")
	BoutonRecommencer = Button(fenetre, text ='Recommencer', command = ActionRecommencer, fg='white', activeforeground='green', bg='green', activebackground='white')
	BoutonRecommencer.place(x=largeur-marge, y=hauteur-marge, anchor="se")

	canvas.focus_set()
	canvas.bind('<Key>',Clavier)

	canvas.grid()
	fenetre.mainloop()



#------------------------------------------------------------------------------------------------------------------------------------------------------------------------


print("")
print("")
print("Bienvenue sur notre jeu du 2048, nous te proposons deux version du jeu.")
print("")
print("Jeu du 2048 d'origine (puissance de 2) : 1")
print("Jeu du 2048 version suite de Fibonacci : 2")
print("")

#Variable liée à la gestion des deux versions
ver = -1
dim = 0

while ver < 0 or ver > 3 :
	ver=int(input("Choisi ta version du 2048 (1 ou 2): "))

#Variable de la version pui.2
if ver == 1:
	val_1 = 2 #Valeur de départ (commun)
	val_2 = 4 #Valeur de départ (plus rare)
	val_fin = 2048 #Valeur de fin de partie

	#Valeurs liées à la gestion de la version pui.2
	fiboVal1 = 0
	fiboVal2 = 10000000000
	fiboVal3 = 1
	fiboVal4 = 1
	while(dim<2 or dim>11) :
		try : dim=int(input("Quelle taille votre 2048 (entre 3 et 10): "))
		except : dim=0


#Variable de la version Fibo
elif ver == 2:
	val_1 = 1 #Valeur de départ (commun)
	val_2 = 2 #Valeur de départ (plus rare)
	val_fin = 233 #Valeur de fin de partie

	#Valeurs liées à la gestion de la version Fibo
	fiboVal1 = 1
	fiboVal2 = 1
	fiboVal3 = -1
	fiboVal4 = 0
	while(dim<2 or dim>11) :
		try : dim=int(input("Quelle taille votre 2048 (entre 3 et 10): "))
		except : dim=0



#Fonction principale
FENETRE(dim)







"""
*********************************************************************************
*																				*
*					PROGRAMMATION DU JEU 2048 - EVOLUTION						*
*																				*
*********************************************************************************
	Hein ! Quoi ! Vous avez fini en avance et vous voulez avancer un petit
	peu plus ! Génial. Si vous alliez explorer les tutoriels d'explications
	de cette étrange bibliothèque tkinter... peut-être que vous trouverez
	un truc pour rajouter un peu de couleur ou une petite musique de fond ^_^
	A vous de voir !

	Hé sinon, si au lieu de faire apparaitre des puissances de 2 succéssives
	on mettait les termes de la suite de Fibonacci (1 2 3 5 8 13 etc)
	Et si on laissait l'utilisateur choisir son mode de jeu :
	puissance de 2 ou suite de Fibonacci ;-)

	Qu'est-ce qu'on pourrait encore améliorer !?
	A vous de me faire une surpise, mais n'oubliez pas de préparer la
	page web pour la soutenance !

"""
