> ## Design Information
>
1. A user will be able to choose to log in as a specific player or log in as the administrator when starting the application.  For simplicity, any authentication is optional, and you may assume there is a single system running the application.
> To realize this requirement, I added a parent class User and two children classes Player and Administrator that inherits from the User Class.
2. The application will allow players to (1) choose a cryptogram to solve, (2) solve cryptograms, and (3) view the list of player statistics.
> To realize this requirement, I added to the Player class a function chooseCryptogram() that returns the chosen cryptogram, and a function solveCryptogram(c: Cryptogram) that takes as input the chosen cryptogram and returns the proposed solution. I added a function getPlayersStats() to the User class since both Player and Administrator can view the list of players statistics.
3. The application will allow the administrator to (1) create a cryptogram, (2) create a player, and (3) view the list of player statistics.
> To realize this requirement, I added a uni-directional association between Administrator and Player, where the Administrator can create many Players.
> I also added a Cryptogram class and a uni-directional association between Administrator and Cryptogram which will allow the Administrator to create many Cryptograms.
4. A cryptogram will have a solution (the plaintext phrase) and a maximum number of allowed solution attempts for each of three difficulty categories.
> To realize this requirement, I added and attribute solution to the Cryptogram class, and a hashmap attempts that maps each difficulty category to its maximum number of allowed solution.
5. To add a player, the administrator will enter the following player information:
 * a. first name
 * b. last name
 * c. unique username 
 
 A difficulty category: easy, normal or hard.
> To realize this requirement, I added the attributes first name, last name and username to the User class, since both the Administrator and Player should have a full name and username ( I assumed that the Administrator should also provide his name and username). 
> I added the difficultyCategory to the Player class. The Adminstrator should enter one of these three values: easy, normal, hard. If he enters any value other than those values, an error should be displayed. This was not represented in the diagrambecause the error handling will be detailed in the implementation.
6.  To add a new cryptogram, the administrator will:
 * a. Enter a unique cryptogram name.
 * b. Enter a solution (unencoded) phrase.
 * c. Enter the number of allowed incorrect solution attempts for the easy difficulty.
 * d. Enter the number of allowed incorrect solution attempts for the normal difficulty.
 * e. Enter the number of allowed incorrect solution attempts for the hard difficulty.
 * f. Edit any of the above steps as necessary.
 * g. Save the complete cryptogram.
 * h. View a confirmation that the name assigned to the cryptogram is unique and return to the main menu, or be returned to editing the cryptogram after any error is displayed.
> To realize this requirement, I added tha name, solution and attempts attributes to the Cryptogram class. The Administrator will be able to edit these attributes through the getters and setters thanks to the added association between Administrator and Cryptogram (in requirement 3).
> The step h will be handled within the GUI implementation.
7. The encrypted phrase for the cryptogram will be generated for each player starting a new cryptogram by:
 * a. Replacing each letter with another letter randomly, so that all of any particular letter are replaced with the same other letter, such as all A’s becoming C’s, and every letter is paired with a unique encrypted letter.
 * b. Preserving the capitalization in the original phrase.
 * c. Preserving any non-alphabetic characters (such as punctuation or white space) unaltered.
> To realize this requirement, I added the encrypt() function to the Cryptogram class. This function will generate the encrypted phrase following the steps above.
8. To choose and then solve a cryptogram, a player will:
 * a.  View the list of all unsolved cryptograms alongside their status as in progress or unstarted, and choose a cryptogram to solve.
> To realize this requirement, I added a hashmap cryptogramStatus which maps each cryptogram name to its status. cryptogramStatus will be initially empty. 
 * b.  View the chosen cryptogram and number of incorrect solution attempts remaining (starting at whatever number is allowed for the player’s difficulty level for that cryptogram).  If the cryptogram has not been played by this player before, the fully encrypted phrase should be generated and displayed.  If the cryptogram is in progress, the previous state of the phrase should be displayed.
> If cryptogram has not been played, the cryptogram name and status will be added to cryptogramStatus and encrypt() will be called. 
> If the cryptogram is in progress, the previous state is already saved in cryptogramStatus.
 * c.  Match the replacement and encrypted letters together, and view the resulting potential solution.
 * d.  When all letters in the cryptogram are replaced and they are satisfied with the potential solution, submit their answer.
 * e. Get a result indicating that the solution was successful, or decrementing the number of incorrect solution attempts remaining if it was unsuccessful.
 * f. At any point, the player may return to the list of unsolved cryptograms to try another.
> To realize these requirements, I added the function calculateResult(player_solution: string) that returns true if all letters in the cryptogram are replaced and they are satisfied with the potential solution. Otherwise, it will decrement the number of incorrect solution attempts remaining and returns false. The player_solution is the returned value of solveCyptogram() function in Player class, and it can be accessed thanks to the association between Player and Cryptogram.
 * g.  If the number of incorrect solution attempts reaches zero, they will get a result that the cryptogram game was lost, and this cryptogram will be marked as complete, unavailable for this player to attempt again. They will then return to the menu.
 * h.  If the player successfully solves the cryptogram, they will get a result that the cryptogram game was won, and this cryptogram will be marked as complete, unavailable for this player to attempt again.  They will then return to the menu.
> Depending on the calculateResult() output and the incorrect solution attempts, the cryptogram status will be updated, and the number of won/lost cryptograms will be incremented.
9. The list of player statistics will display a list of players in descending order of number of cryptograms won.  The entry for each player will show their first name, the number of cryptograms won, and the number of cryptograms lost.  An administrator should also see the username and difficulty status of the player.
> To realize this requirement, I added a class PlayerStats that has 3 functions: getWonCryptograms() that returns the number of won cryptograms for a player, getLostCryptograms() that returns the number of lost cryptograms for a player and getPlayerName() that returns the player name. Then these stats will be 'collected' through the getPlayersStats() function in User class, which returns an ordered list of PlayerStats.
> The administrator will be able to see the username and difficulty status of the player through the functions getPlayerUsername() and getPlayerDifficulty().
10. The user interface must be intuitive and responsive.
> This is not represented in my design, as it will be handled entirely within the GUI implementation
11. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
> This requirements will be realized by choosing attribute types (like hashmap) that offer fast access to other attributes.
 


 


