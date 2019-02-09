# Requirement

1. A user will be able to choose to log in as a specific player or log in as the administrator when starting the application. For simplicity, any authentication is optional, and you may assume there is a single system running the application.
	* **Since the process of the login are not the purpose of this UML diagram, we will simply ignore what is before the login process and just focus on players and administrators.**
2. The application will allow players to (1) choose a cryptogram to solve, (2) solve cryptograms, and (3) view the list of player statistics.
	* **Here we can see that each player has a list of unsolved cryptograms and remaining attempts if already attempted. He can choose a cryptogram from the list to solve. When the player is solving cryptograms, he calles the function from cryptogram, and hands in the parameters. The function(operation) from the cryptogram returns if the solution is correct. A list of player statistics is a standalone object and is not a class.**
3. The application will allow the administrator to (1) create a cryptogram, (2) create a player, and (3) view the list of player statistics.
	* **Here administrators have an operation that creates a cryptogram, an operation to create a player. And the administrators can read the list of player statistics just like players.**
4. A cryptogram will have a solution (the plaintext phrase) and a maximum number of allowed solution attempts for each of three difficulty categories.  
	* **The solution is an attribute inside of cryptograms. We can call solutions directly from cryptograms. And every cryptogram has default attempts specific to each of the three difficulty categories, which is an attribute within the cryptogram class as well.**
5. To add a player, the administrator will enter the following player information:
	* A first name
	* A last name
	* A unique username
	* A difficulty category: easy, normal or hard.
	* **There is a construction function inside of player class, which takes four parameters as input. These four attributes are inside of player class. When we call the construction function of class player, we will create a new player. And this operation is called by administrators.**
6. To add a new cryptogram, the administrator will:
	* Enter a unique cryptogram name.
	* Enter a solution (unencoded) phrase.
	* Enter the number of allowed incorrect solution attempts for the easy difficulty.
	* Enter the number of allowed incorrect solution attempts for the normal difficulty.
	* Enter the number of allowed incorrect solution attempts for the hard difficulty.
	* Edit any of the above steps as necessary.
	* Save the complete cryptogram.
	* View a confirmation that the name assigned to the cryptogram is unique and return to the main menu, or be returned to editing the cryptogram after any error is displayed.
	* **Just like adding players, administrators can add cryptograms, and the construction function inside of cryptogram class need these parameters. These are attributes of cryptogram class. While creating cryptograms, the operation will return if the name is unique. Here the method of returning uniqueness is not our concern, and we just assume that after creation, the operation returns and the administrator will need to return to menu or edit the attributes of the cryptogram.**
7. The encrypted phrase for the cryptogram will be generated for each player starting a new cryptogram by:
	* Replacing each letter with another letter randomly, so that all of any particular letter are replaced with the same other letter, such as all A’s becoming C’s, and every letter is paired with a unique encrypted letter.
	* Preserving the capitalization in the original phrase.
	* Preserving any non-alphabetic characters (such as punctuation or white space) unaltered.
	* **There is a constructor function(operation) inside the cryptogram class. Everytime a player get a new cryptogram that he/she has never attempted before, the cryptogram will be randomized according to the rules.**
8. To choose and then solve a cryptogram, a player will:
	* View the list of all unsolved cryptograms alongside their status as in progress or unstarted, and choose a cryptogram to solve.
	* View the chosen cryptogram and number of incorrect solution attempts remaining (starting at whatever number is allowed for the player’s difficulty level for that cryptogram).  If the cryptogram has not been played by this player before, the fully encrypted phrase should be generated and displayed.  If the cryptogram is in progress, the previous state of the phrase should be displayed.
	* Match the replacement and encrypted letters together, and view the resulting potential solution.
	* When all letters in the cryptogram are replaced and they are satisfied with the potential solution, submit their answer.
	* Get a result indicating that the solution was successful, or decrementing the number of incorrect solution attempts remaining if it was unsuccessful.
	* At any point, the player may return to the list of unsolved cryptograms to try another.
	* If the number of incorrect solution attempts reaches zero, they will get a result that the cryptogram game was lost, and this cryptogram will be marked as complete, unavailable for this player to attempt again. They will then return to the menu.
	* If the player successfully solves the cryptogram, they will get a result that the cryptogram game was won, and this cryptogram will be marked as complete, unavailable for this player to attempt again.  They will then return to the menu.
	* **Here we can see that the player has several operations which take different input and return different outputs. To view the list of all unsolved cryptograms, the player has an operation called `getListOfUnsolvedAttempts()`. Also, if a player want to solve a specific cryptogram, he will use the function `Cryptogram getCryptogram(int)`. After solving the cryptogram, there is one function `boolean solveCryptogram(int,String)`, which returns whether the answer is correct. In the mean time, if the answer is incorrect, the remaining attempts will decrease, which will be performed in side the function. At any point, the player can save the current state of the cryptogram and choose another cryptogram to solve.**
9. The list of player statistics will display a list of players in descending order of number of cryptograms won.  The entry for each player will show their first name, the number of cryptograms won, and the number of cryptograms lost.  An administrator should also see the username and difficulty status of the player.
	* **The list of player statistics is a standalone object instead of a class. The realization of the attributes inside of the list are first names, the number of cryptograms won, and the numebr of cryptograms lost, username and categories. Since the last two items are not visible to players, we have different realizations of functions between player class and administrator class.**
10. The user interface must be intuitive and responsive.
	* **We are not required to write a UML diagram for this requirement since it's not the main purpose of the system.**
11. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.
	* **We are not required to write a UML diagram for this requirement since it's the implementation requirement and not the main purpose of the system. It's part of the realization.**

# Extraction

1. Classes
	* Player
	* Cryptogram
	* Administrator
2. Attributes
	* Player
		* (String[]) listOfUnsolvedAttempts
		* (String) firstName
		* (String) lastName
		* (String) username
		* (String) category
	* Cryptogram
		* (String) solution
		* (int[3]) attempts
		* (String) name
		* (String) previousState
	* Administrator
	* ListPlayerStats
		* (String[]) firstName:
		* (int[]) numberOfWon
		* (int[]) numberOfLost
		* (String[]) username
		* (String[]) category
3. Operations
	* Player
		* Cryptogram getCryptogram(int)
		* boolean solveCryptogram(int,String)
		* getListPlayerStats()
		* getListOfUnsolvedAttempts()
		* saveCurrentState(int,String)
	* Cryptogram
		* Cryptogram()
		* boolean getCorrectness()
		* boolean getCompleteness()
		* markAsComplete()
	* Administrator
		* createPlayer(String,String,String,String)
		* getListPlayerStats()
		* boolean createCryptogram(String,String,int,int,int)
		* boolean editCryptogram(String,String,int,int,int)
4. Relationships
	* Player can access ListPlayerStats
	* Administrator can access ListPlayerStats with more information
	* Administrator can create Player
	* Administrator can create Cryptogram
	* Player can access Cryptogram