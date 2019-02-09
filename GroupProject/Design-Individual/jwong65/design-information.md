# Design Information

**1. A user will be able to choose to log in as a specific player or log in as the administrator when starting the application.  For simplicity, any authentication is optional, and you may assume there is a single system running the application.**

The Administrator and Player class extend the User class and contains the function Login() to start the application.

**2. The application will allow players to (1) choose a cryptogram to solve, (2) solve cryptograms, and (3) view the list of player statistics.**

The User class gives administrators and players the ability to view player statistics via the ViewPlayerStatistics() function.  Additionally, the Player can choose between two functions to either PickCryptogram() to initially select the cryptogram and move to that screen and secondly, use the SolveCryptogram() function to attempt to solve a cryptogram.  The SolveCryptogram() function takes as a parameter the user’s String submission to be evaluated.  The result is a boolean value indicating to the user if they correctly solved it or not.

**3. The application will allow the administrator to (1) create a cryptogram, (2) create a player, and (3) view the list of player statistics.**

The Administrator class extends the User class to view the player statistics via the ViewPlayerStatistics() function.  The administrator also has the following functions to meet the above criteria: 1) CreateUser() that allows them to create a user that is either a Player or another Administrator by going to the create page. 2) SaveUser() to actually store the new user with provided parameters.  3) CreateCryptogram() to create a new cryptogram.

**4. A cryptogram will have a solution (the plaintext phrase) and a maximum number of allowed solution attempts for each of three difficulty categories.**  

The Cryptogram class contains the Solution as a string as well as three variables indicating the maximum number of allowed integer solution attempts: 1) MaxNumEasyAttempts for the easy difficulty. 2) MaxNumNormalAttempts for the normal difficulty. 3) MaxNumHardAttempts for the hard difficulty.

**5. To add a player, the administrator will enter the following player information:
a. A first name
b. A last name
c. A unique username
d. A difficulty category: easy, normal or hard.**

The Administrator class contains a function that requires they input as a parameter in the function SaveUser() the FirstName String, the LastName String, the Username String, and an integer for the difficulty of easy, normal, hard as represented by the integers (1, 2, 3).  0 is the default value for the class but should require the administrator to input a correct integer.

**6. To add a new cryptogram, the administrator will:
a. Enter a unique cryptogram name.
b. Enter a solution (unencoded) phrase.
c. Enter the number of allowed incorrect solution attempts for the easy difficulty.
d. Enter the number of allowed incorrect solution attempts for the normal difficulty.
e. Enter the number of allowed incorrect solution attempts for the hard difficulty.
f. Edit any of the above steps as necessary.
g. Save the complete cryptogram.
h. View a confirmation that the name assigned to the cryptogram is unique and return to the main menu, or be returned to editing the cryptogram after any error is displayed.**

The administrator can go create a cryptogram by firstly calling the CreateCryptogram() function to navigate to a view that will allow them to create it.  They will then call the SaveCryptogram() function and provide all the necessary parameters to formally create and save the cryptogram.  Specifically, they will provide a string Name, a string Solution, and integers for the number of allowed incorrect solutions for the easy, normal, and hard difficulty (MaxNumEasyAtttempts, MaxNumNormalAttempts, MaxNumHardAttempts).

The administrator can later edit a specific cryptogram if it is created by calling EditCryptogram() and passing the unique cryptogram name so as to identify which one to edit.

The SaveCryptogram() function should save the completed cryptogram.  A confirmation view allowing the administrator to return to the main menu or edit screen will be shown but it is not captured in the diagram. However, if the cryptogram is correctly saved, the SaveCryptogram() function will return true indicating proper inputs and a false indicating an error and that the administrator must resolve it.

**7. The encrypted phrase for the cryptogram will be generated for each player starting a new cryptogram by:
a. Replacing each letter with another letter randomly, so that all of any particular letter are replaced with the same other letter, such as all A’s becoming C’s, and every letter is paired with a unique encrypted letter.
b. Preserving the capitalization in the original phrase.
c. Preserving any non-alphabetic characters (such as punctuation or white space) unaltered.**

The actual implementation of the encryption process and rules are not captured in the diagram.  However, when the user calls PickCryptogram() and passes a provided name for the cryptogram they wish to solve, the Cryptogram class will create a new CryptogramAttempt() object uniquely for that user.  They will then call GenerateAttemptForUser() that should create the CryptogramAttempt object and populate it an encrypted phrase string EncryptedPhrase.

**8. To choose and then solve a cryptogram, a player will:
a. View the list of all unsolved cryptograms alongside their status as in progress or unstarted, and choose a cryptogram to solve.**

When the User logs in as a Player via the Login() function, they will be presented with a list of unsolved cryptograms retrieved by the CryptogramAttempts class using the Status attribute via the private function ViewUnsolved().  This will look through the list of CryptogramAttempt objects and look for all the attempts for the specified user by their Username.  They will then select the cryptogram to solve using the PickCryptogram() function with a provided unique CryptogramName().

The list of unsolved cryptograms are denoted based on whether the user has a CryptogramAttempt object for it that has status isComplete=false.  If no CryptogramAttempt object has been created for this player, it is assumed that the list is generated based on the remaining Cryptogram objects in the list.

**b. View the chosen cryptogram and number of incorrect solution attempts remaining (starting at whatever number is allowed for the player’s difficulty level for that cryptogram).  If the cryptogram has not been played by this player before, the fully encrypted phrase should be generated and displayed.  If the cryptogram is in progress, the previous state of the phrase should be displayed.**

The Player can select a cryptogram using PickCryptogram() with a provided correct name.  When the corresponding CryptogramAttempt object is retrieved, it will be unique to that user.  The Difficulty indicates the level of difficulty (1=easy, 2=normal, and 3=hard).  

The AttemptsRemaining is the number of attempts remaining for that user and should be initialized to correct maximum number of atttempts when the GenerateAttemptForUser() is called.  It decreases as the user tries to solve it. 

This will also create the encrypted phrase, EncryptPhrase, for displaying to the user.  If the isCompleted boolean is set to false, then the Player is still in-progress for this attempt along with AttemptsRemaining being greater than 0.  To display the previous state of the phrase, we store the player’s attempt in the string atttribute CurrentSubmissionState.  The user would “save” this submission that was in-progress using the SaveSubmissionState() function and passing in the AttemptString string parameter they are using.

**c. Match the replacement and encrypted letters together, and view the resulting potential solution.**

The CryptogramAttempt class contains the CurrentSubmissionState string that the user will pass in.  To check against the potential solution, they will need to call the Cryptogram class and use its CheckSolution() function by providing the string input.  Since the CryptogramAttempt class is linked to the Cryptogram itself, it should know which cryptogram it is referencing and hence compare with the Solution attribute.  The specific comparison matching function is not displayed in the UML but is assumed to be contained within the CheckSolution() function.

**d. When all letters in the cryptogram are replaced and they are satisfied with the potential solution, submit their answer.**

The player can save their current submission by calling SaveSubmission() with their current solution and then call SolveCryptogram() to actually submit and check whether their solution is correct.  

**e. Get a result indicating that the solution was successful, or decrementing the number of incorrect solution attempts remaining if it was unsuccessful.**

When the player calls SolveCryptogram() with their attempt, the CryptogramAttempt class will call the CheckSolution() function.  This function returns a boolean true indicating a successful solution and will set the attempt state boolean isComplete to true.  If the player fails, the CheckSolution() will return false, decrease the number in AttemptsRemaining, and save the submission via the SaveSubmissionState() with their attempted solution.

**f. At any point, the player may return to the list of unsolved cryptograms to try another.**

The user can return back to a list of unsolved cryptograms by selecting Back() on the UI.  The UI state for the system is not represented in this UML implementation.

**g. If the number of incorrect solution attempts reaches zero, they will get a result that the cryptogram game was lost, and this cryptogram will be marked as complete, unavailable for this player to attempt again. They will then return to the menu.**

When the user calls SolveCryptogram() and the attempt is checked with the CheckSolution() function, the AttemptsRemaining attribute is decremented.  isComplete is set to true if AttemptsRemaining is 0.  The user is then sent back to the main menu but this is not represented in the UML implementation.

**h. If the player successfully solves the cryptogram, they will get a result that the cryptogram game was won, and this cryptogram will be marked as complete, unavailable for this player to attempt again.  They will then return to the menu.**

When the user calls SolveCryptogram() and the CheckSolution() function returns true with the provided attempt solution, then the AttemptsRemaining attribute is decremented, isSolved is finally set to true and the isComplete boolean is also set to true.

**9. The list of player statistics will display a list of players in descending order of number of cryptograms won.  The entry for each player will show their first name, the number of cryptograms won, and the number of cryptograms lost.  An administrator should also see the username and difficulty status of the player.**

Each User class has the function ViewPlayerStatistics() that allows an administrator or player to go to a view with the player statistics.  A boolean value is passed indicating whether this is an Administrator or Player so as to show each type of user unique information.  The specific implementation performing the sorting and displaying the correct information is not displayed in the UML diagram.  It is assumed that the function to retrieve, sort, and display Player information is contained with the ViewPlayerStatistics() function.  However, the player statistics are checked by looking through the list of CryptogramAttempts and looking for the number of isComplete=true objects for a user and looking for the boolean isSolved=true. 

**10. The user interface must be intuitive and responsive.**

This is not represented in the design since it is handled in the UI implementation.

**11. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.**

This is not represented in the design as it is handled by the specific implementation of the coding logic.
