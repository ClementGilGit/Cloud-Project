GIL Clément  
BOUDOU Thibault 

Rapport : Projet Cloud 
Licence Professionnelle Développement d’Applications Intranet/Internet  

1. Structure du Projet 
 Nous avons décomposé le projet en 3 services dont 2 sont en Google App Engine (GAE) 
et 1 Heroku. Nous avons optés pour réaliser le ShoppingService et le StockService  en GAE et 
le WholeSalerService est réalisé avec Heroku. Nous avons décidé que ce soit le StockService 
qui soit en relation direct avec le Datastore (Base de Données de Google App Engine) car il 
nous semblait plus logique d’avoir une liste de Livres en BDD et que par conséquent lorsque 
le stock d’un livre est mis à jour, que le StockService soit le seul à modifier les données. 
Le client est un client Guzzle en PHP, il permet à l’utilisateur de réaliser des actions sur nos 
services grâce à une interface que nous avons réalisé avec l’aide de du Framework Bootstrap. 

2. Difficultés Rencontrées

 Lors de la réalisation de ce projet, nous avons été confrontés à 2 difficultés majeures 
que nous avons réussi à résoudre. Tout d’abord, nous avons été confrontés au problème 
d’association du compte Google avec Eclipse. En effet, nous avions commencé à réaliser le 
projet pendant notre période en entreprise et il nous était possible de nous connecter et 
déployer nos services. Cependant, quelques temps après Google à changer sa méthode 
d’authenfication et il nous était plus possible de nous connecter au Plugin Eclipse GAE et par 
conséquent nous ne pouvions pas non plus déployer nos services. Pour pallier à ce problème, 
nous avons attendu que Google propose une mise à jour du Plugin Eclipse et nous avons 
téléchargé le nouveau SDK de Google. Nous avons donc pu déployer les services GAE avec le 
nouveau plugin. 
 La deuxième difficulté a été de convertir une Response en String pour que nous 
puissions renvoyer le stock en JSON. Cependant nous n’avions pas accès à la méthode 
«readEntity » permettant de convertir une Response en String car nous avions des conflits 
dans nos librairies. C’est une supprimant une librairie inutile que nous avons pu résoudre ce 
conflit et que nous avons donc pu effectuer la conversion.   

3. Répartition des tâches 

Concernant la répartition des tâches : 

 Thibault s’est occupé du Client Guzzle et du ShoppingService et de la résolution des 
problèmes détaillé ci-dessus. 
 Clément s’est occupé du StockService et du WholeSalerService 

Mais nous avons tout de même travaillé ensemble sur toutes les entités du projet car nous 
nous sommes entraidés afin de résoudre ensemble les différents problèmes bloquant.  
Conclusion 
 Nous avons réussi à réaliser les 3 services ainsi que les contraintes imposant d’utiliser 
Gae et Heroku ainsi qu’un service avec une Base de Données. Nous avons réalisé le client 
Guzzle et tous les services renvoient du JSON. Nous avons également géré les pannes 
éventuelles des services en remontant au client un message d’erreur pour lui informer qu’un 
service est indisponible. 
