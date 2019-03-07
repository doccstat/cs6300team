# User Manual 
##  Introduction
The Cryptogram app is an application for kids to practice solving cryptograms. Our application users can be  the administrator and the players.
## Administrator Manual
The administrator is the one who can add players or cryptograms to the existing list of players and cryptograms.
First the administrator should login by entering his username and password. 
If he enters the correct username and password, he will be redirected to the menu screen. He can choose to add a player by clicking on "Add Player", add a cryptogram by clicking on "Add Cryptogram" or view all players statistics by clicking on "Player Statistics". He can also logout by clicking on "Logout".
### 1. Add Cryptogram  
If the administrator clicks on "Add Cryptogram", he will be redirected to another screen, where he needs to enter the information needed to create a new cryptogram. 
If any of the fields is left empty, an error message will show up and the cryptogram will not be added.
#### 1.1 The Cryptogram Name 
The administrator needs to enter a cryptogram name. This name should be different from any existing cryptogram name. If he enters an existing name, or a name not having any letters an error message will show up and the cryptogram will not be added.
#### 1.2 The Cryptogram Solution
The administrator needs to enter the solution for the cryptogram. If he enters a string not containing any letters, an error message will show up and the cryptogram will not be added.
#### 1.3 The Easy Attempts
The administrator needs to enter the number of attempts allowed for players with easy category. If the entered value is not a number, an error message will show up and the cryptogram will not be added.
#### 1.4 The Normal Attempts 
The administrator needs to enter the number of attempts allowed for players with normal category. If the entered value is not a number, an error message will show up and the cryptogram will not be added.
#### 1.5 The Hard Attempts 
The administrator needs to enter the number of attempts allowed for players with hard category. If the entered value is not a number, an error message will show up and the cryptogram will not be added.
once all the fields are validated and the Add Cryptogram button is clicked, the new cryptogram is added to the list of cryptograms. A confirmation popup will show up and the administrator will be redirected to the menu.
### 2. Add Player
If the administrator clicks on "Add Player", he will be redirected to another screen, where he needs to enter the information needed to add a new player. 
If any of the fields is left empty, an error message will show up and the player will not be added.
#### 2.1 First Name
The administrator needs to enter the player's first name . If he enters a string containing non letters characters, or the length of the string is less than 3 or more than 25 an error message will show up and the player will not be added.
#### 2.2 Last Name
The administrator needs to enter the player's last name .Similarly to first name,  if he enters a string containing non letters characters,or the length of the string is less than 3 or more than 25 an error message will show up and the player will not be added.
#### 2.3 Username
The administrator needs also to enter the player's username. The username should be unique (not an existing username),   contains at least one letter and the length of the string should be less than 3 or more than 25. If not,  an error message will show up and the player will not be added.
#### 2.4 Password
The administrator needs to enter the player's password. The entered password should contain at least one letter and one number and the length of the string should be less than 3 or more than 25. If not, an error message will show up and the player will not be added.
#### 2.5 Category
The administrator needs to select the player's category. When clicking on the category spinner, 3 categories will be listed: Easy, Normal, Hard and the administrator can select one category.
Once all the fields are validated and the "Add Player" button is clicked, the new player is added to the list of players.
### 3. Player Statistics
If the administrator clicks on "Player Statistics", he will be redirected to another screen, where he can see the list all players first names, usernames, their number of cryptograms won and their number of cryptograms lost. The list is sorted by the number of cryptograms won.

## Player Manual
This application is designed specifically for kids. So the players are the kids who will try to solve the cryptograms they choose, then compare their results with other players (kids). 
First a player should login by entering his username and password. 
If he enters the correct username and password, he will be redirected to the menu screen. From there, he can click on "Choose Cryptogram " or "Player Statistics", or he can logout.
### 1. Choose Cryptogram
If the player clicks on  "Choose Cryptogram ", he will be redirected to another screen, where he can view the list all the cryptograms that he can solve, within their status. The cryptograms that he already started will have a "In progress" status, and the cryptograms that he never started will have a "Not started" status.
#### 1.1 In Progress Cryptograms
If the player clicks on a cryptogram name have as status "in progress", he will be redirected to another screen where he can view the number of his attempts remaining for the chosen cryptogram and his last attempt to solve the cryptogram (last phrase). He will also be able to retry to solve the cryptogram by entering a letter under each letter of the ecrypted phrase. Then he can click the submit button if he wants to see if his attempt is correct. If his attempt is correct, he wins the game and a text "Congratulations! You won the game" appears. He can then go back to menu by clicking the back to menu button.
Otherwise, if his attempts is incorrect his number of attempts remaining will decrease by 1 and he can go back to menu by clicking the back to menu button . If the number of attempts remaining  was 0 when he submitted the attempt, he losts the game and a text "You lost the game" appears and this cryptogram will not appear again in the list of cryptograms to solve. He can then go back to menu by clicking the back to menu button. 
#### 1.2  Not Started Cryptograms
If the player clicks on a cryptogram name have as status "not started", he will see a similar screen to the "in progress" cryptograms and can expect the same screens when trying to solve the cryptogram. The only diffrence is that the number of attempts remaining is the number of attempts set by the administrator -1, and there is no last phrase since this is his first attempt.
### 2. Player Statistics
If the player clicks on "Player Statistics", he will be redirected to another screen, where he can see the list all players first names, their number of cryptograms won and their number of cryptograms lost. The list is sorted by the number of cryptograms won.
