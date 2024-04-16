# Dockerfile equipe n°8

## Instructions pour lancer l'application

- Vérifiez si docker est installé :
```shell
docker --version
```

- Cloner le référentiel :
```shell
git clone  https://github.com/CelimS/docker_sae203.git
```

- Aller au référentiel du clone :
```shell
cd ../docker_sae203/
```

- Construisez l'image décrite dans dockerfile avec docker build : 
```shell
docker build -t docker_sae203 .
```

- Lancer le serveur web :
```shell
docker run --name docker_sae203 -d -p 7825:7825 docker_sae203
```

- Vérifier que l'application est en cours d'exécution. Pour ce faire, ouvrez un navigateur et tapez ```localhost:8080```

- Vérifier que le conteneur associé est actif :
```shell
docker ps
```

- La sortie de ```docker ps``` doit être similaire à :
```shell
CONTAINER ID   IMAGE           COMMAND             CREATED          STATUS           PORTS                                   NAMES
fbdeb6bced26   docker_sae203   "java ChatServer"   4 seconds ago    Up 3 seconds     0.0.0.0:7825->7825/tcp                  docker_sae203
```

- Finalement, arrêtez le conteneur avec la commande suivante (les dernières chiffres sont le code de hachage affiché par docker ps):
```shell
docker stop b8f8f406b03c
```

- Encore, si on souhaite supprimer le conteneur, on peut taper :
```shell
docker rm b8f8f406b03c
```

**NOTE :** Au lieu du code de hachage, on peut toujours taper le nom du conteneur. Dans le cas d'exemple ce nom est ```quirky_tesla```

**ERREURS POSSIBLES :**
- La construction de l'image docker peut s'arreter lors du téléchargement de jdk. Il vous suffit de relancer la construction pou régler le problème
- N'oubliez pas de remplacer les adresses ip des fichiers chatserver.java et chatclient.java par l'adresse que vous utilisez
- Vous pouvez changer le port sur lequel le serveur tournera, mais n'oubliez pas de les changer dans tous les fichiers concernés et dans le conteneur

