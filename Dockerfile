##défini debain:latest comme l'image parent
FROM debian:latest

##maj du package et installation de openjdk
RUN apt-get update && apt-get install -y default-jdk

##défini le dossier de travail à /app
WORKDIR /app

##copie le contenue du dossier actuel dans le conteneur /app
COPY . /app

##compilation du serveur de chat
RUN javac -encoding UTF-8 ChatServer.java

##lance chatserver lors du lancement du conteneur
CMD ["java", "ChatServer"]  