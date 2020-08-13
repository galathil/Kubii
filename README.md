# Kubii (for MC 1.16.1)

![](https://static.galathil.fr/img/github/kubii.jpg)

Logo from https://www.pinterest.fr/pin/732116483151407553/

## Features
All features are optionals and configurable in config.yml file. It is well documented.
  - **Discord features** :
    - Create a connection between a text channel and Ingame chat to send messages in both ways
    - Can give a discord role when a player join the game. Remove it when it leave the game.
  - **No grief features** : Control in wich worlds (or dimensions) this settings are disabled :
    - Creeper explosions (grief terrain only)
    - Ghast fireballs (grief terrain only)
    - Enderman pickup
  - **Sign and book markup** : You can use characters "&" ingame to edit markups in books and signs (See : https://minecraft.gamepedia.com/Formatting_codes)
  - **Disable spawing** : Disable in specified worlds (or dimensions) the spawn of :
    - Slimes
    - Withers
    - Ghasts
    - Zombified piglins
  - **Disable "Too expensive!" for anvil** : If the price is more expensive than 39 level, it will cost 39 level
  - **Death location in chat** : Display death location in death message

## Requirements
  - JDK 11 (OpenJDK or Oracle JDK)
  - Spigot API 1.16.1
  - JDA 4.1 (https://github.com/DV8FromTheWorld/JDA)
  - Emoji Parser 5.1 (https://github.com/vdurmont/emoji-java)
  - Java org.json (https://repo1.maven.org/maven2/org/json/json/20190722/)

## Installation
TLDR : You can find a full package here : https://github.com/galathil/Kubii/releases

Manual installation :
  - Place your Kubii.jar in plugins directory.
  - Create a "kubii" folder and place jar dependancies (JDA, Emoji, Json) into it. Create a empty file called "kubiiAccounts.json"
  - launch your server, it create the config.yml file.
  - When you change a value in config.yml, restart the server.
  
## For developers :
  - Use the manifest include in the project when your generate the jar file
