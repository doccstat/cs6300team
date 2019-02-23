# Test Plan

**Author**: Team 11

## 1 Testing Strategy

### 1.1 Overall strategy

Testing can be based on the different frameworks from the diagrams above. 

#### 1.1.1 Unit testing strategies:
##### 1. User
* `viewPlayerStatistics()` return a list of players in descending order of number of cryptograms won. The entry for each player consists of String and two int numbers.

##### 2. Administrator
* `createPlayer()` can successfully create a player, provided valid `firstName`, `lastName`, `username` and `category`
* `createCryptogram()` can successfully create a cryptogram, provided valid `name`, `solution` and `maxAttempts`
* `saveCryptogram()` should respond with correct boolean value, provided valid name, solution, difficulty, and three maxAttempts for different categories. If the return value is false, then we need to call the `createCryptogram()` function again. The number of call of `saveCryptogram()` should be less than maxAttempts of the corresponding category

##### 3. Player
* test publicity of every instance in the player class
* `getUnsolved()` will successfully return a list of CryptogramAttempt
* `getCryptogram()` will successfully return a cryptogram, provided valid cryptogram name and will properly handle invalid inputs
* `saveCurrentState()` will operate on the current cryptogram, providing valid attempted solution and can handle invalid inputs properly
* `solveCryptogram()` need a proper input string and will return whether the solution is correct
* `getWonCryptograms()` will return the number of cryptograms that player have successfully solved before running out of maxAttempts
* `getLostCryptograms()` will return the number of cryptograms that player have already reached the maxAttempts 

##### 4. Cryptogram
* All the instances should have the correct publicity
* `generateAttemptForUser()` will return a `CryptogramAttempt`, providing the valid username and can handle invalid inputs
* `checkSolution()` will return whether the solution matches the solution, providing the parameter, playerSolution

##### 5. CryptogramAttempt
* All the instances should have the correct publicity
* `saveSubmissionState()` is used to record the state of submissions, providing valid `attemptString`. If a cryptogram is already solved i.e. `isSolved == true`, then `saveSubmissionState()` should not be called. Otherwise, the method should return the cryptogram is already solved.

#### 1.1.2. Integration Testing
1. User can successfully login as a player or an administrator
2. Integration between `listPlayerStatistics()` and the database
3. Integration between User Interface and class creation, method calling.
4. The app should monitor any input provided in the user interface and handle properly
5. After detecting any invalid input, the app should notify the user about changes and input requirement

#### 1.1.3. System Testing
1. Users can access cryptograms created, save the current state.
2. Users can access the list of player statistics
3. Users can return to the previous state and submit solutions
4. Other users can see the changes in the list of player statistics
3. Administrators can access the list of player statistics with more details

#### 1.1.4. Regression Testing
1. Whenever we want to modify methods, we need to re-run every test related to that method and every method that would be using the return of the modified method.
2. When we want to add some instances, we only need to test methods that involves operations of the new instances.

### 1.2 Test Selection
1. Identify the parameters the test will be based upon
2. Since we already have unit testing, integration testing, system testing and regression testing, we can identify the test cases based upon the parameters.
3. Consolidate on the tests that should be executed in sequence

### 1.3 Adequacy Criterion
1. Depending on the final decision of our testing tools, we need to take the cost of purchasing or licensing of the tool
2. If we won’t depend on the other tools, then we should assess the quality of the test cases as follows:
* We should cover any corner cases which includes: a string is too long, the number is too big, the input is not valid, the input is null, the number is valid but it causes reach outside of the memory allocated.
* The test cases to be used should cover all methods that takes what we want to test as input.
3. Testing time should be as short as possible.
4. The test cases should be small, while covering all the tests required.
5. Investment through automation should be as small as possible.

### 1.4 Bug Tracking
To keep track of issues, bugs, and enhancement requests during the development process of Cryptogram, we will use Trello.  Trello is a web-based project management software that can organize and list tasks.  To keep track of bugs, a team member will create an item and log it under the bugs list.  There will also be separate lists to track enhancement requests.

### 1.5 Technology
To test the Cryptogram app, we will use several testing frameworks.  For functional unit tests, JUnit version 4.12 will be used.  This will then test specific classes and data functions.

For UI testing, we will use the Android Espresso test framework. 

## 2 Test Cases

| **Purpose**                                                           | **Test Steps**                                                                                                                                                                                                                                  | **Expected Result**                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  | **Actual Result** | **Pass?** |
|-----------------------------------------------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------|-----------|
| Login as admin                                                        | 1. Launch app <br>2. User enters text in username and password fields <br>3. User selects login button <br>4. User logs in successfully                                                                                                                     | 1. App launches correctly without crashing 2. User can select username and password fields and enter text into each field. Password field should hide text. 3. Username and password are validated for correctness from User service. If username/password are incorrect, user is shown error for specific field. 4. User should be taken to the Administrator Menu                                                                                                                                                                                  |                   |           |
| View Player Statistics as Admin                                       | 1. Launch app 2. User logs in as Administrator 3. User goes to Administrator Menu 4. User selects View Player Statistics 5. User sees Player Statistics                                                                                         | 1. App launches correctly without crashing 2. User enters correct username and password to login as Administrator and does not receive any errors. 3. User is navigated to the Administrator Menu view that shows the “View Player Statistics” button as an option. 4. User is taken to the Player Statistics screen. 5. User should see a list of Players including their first name, username, wins, and losses. List should be scrollable if there are many Players. If there are no Players, the list should be empty.                           |                   |           |
| Create Player                                                         | 1. Launch app 2. User logs in as Admin 3. User goes to Admin menu 4. User selects Create Player 5. User enters in player details 6. User selects Add button                                                                                     | 1. App launches correctly without crashing 2. User enters correct username and password to login as Administrator and does not receive any errors 3. User is navigated to the Administrator menu view that contains the “Create Player” button as an option 4. User is taken to the Create Player screen. 5. User enters correct first name, last name, username, and difficulty level for the player. Username is unique. 6. User receives a message telling them that the new player has been successfully added                                   |                   |           |
| Create Cryptogram                                                     | 1. Launch app 2. User logs in as Admin 3. User goes to Admin menu 4. User selects Create Cryptogram 5. User enters in cryptogram name, solution, and number of attempts for each difficulty 6. User selects Add button.                         | 1. App launches correctly without crashing 2. User enters correct username and password to login as Administrator and does not receive any errors 3. User is navigated to the Administrator menu view that contains the “Create Cryptogram” button as an option 4. User is taken to the Create Cryptogram screen 5. User enters correct info for Cryptogram and does not receive any error messages. Cryptogram name must be unique. 6. User receives a message indicating the Cryptogram is created and then they are taken back to the Admin menu. |                   |           |
| Login as Player                                                       | 1. Launch app 2. User enters text in username and password fields 3. User selects login button 4. User logs in successfully                                                                                                                     | 1. App launches correctly without crashing 2. User can select username and password fields and enter text into each field. Password field should hide text. 3. Username and password are validated for correctness from User service. If username/password are incorrect, user is shown error for specific field. 4. User should be taken to the Player Menu                                                                                                                                                                                         |                   |           |
| View Player Statistics as Player                                      | 1. Launch app 2. User logs in as Player  3. User goes to Player Menu 4. User selects View Player Statistics 5. User sees Player Statistics                                                                                                      | 1. App launches correctly without crashing 2. User enters correct username and password to login as Administrator and does not receive any errors. 3. User is navigated to the Administrator Menu view that shows the “View Player Statistics” button as an option. 4. User is taken to the Player Statistics screen. 5. User should see a list of Players including their first name, username, wins, and losses. List should be scrollable if there are many Players. If there are no Players, the list should be empty                            |                   |           |
| Choose Unsolved Cryptogram                                            | 1. Launch app 2. Login as Player 3. Select “Choose Cryptogram” from Player menu 4. See list of Cryptograms 5. Select Cryptogram that has status “unsolved”                                                                                      | 1. App launches correctly without crashing 2. User enters correct username and password to login as Player. They then navigate to the Player Menu 3. User is taken to the Choose Cryptogram screen 4. User should see a list of cryptograms created by the admin 5. User selects a cryptogram that is unsolved and is navigated to it to solve it                                                                                                                                                                                                    |                   |           |
| Choose In-progress Cryptogram                                         | 1. Launch app 2. Login as Player 3. Select “Choose Cryptogram” from Player menu 4. See list of Cryptograms 5. Select Cryptogram that has status “in-progress”                                                                                   | 1. App launches correctly without crashing 2. User enters correct username and password to login as Player. They then navigate to the Player Menu 3. User is taken to the Choose Cryptogram screen 4. User should see a list of cryptograms created by the admin 5. User selects a cryptogram that is in-progress and is navigated to it to solve it                                                                                                                                                                                                 |                   |           |
| Solve Unsolved Cryptogram                                             | 1. Launch app 2. Login as Player 3. Select “Choose Cryptogram” 4. See list of Cryptograms 5. User selects “unsolved” cryptogram to solve 6. User enters in their submission into a text field                                                   | 1. App launches correctly without crashing 2. User enters correct username and password to login as Player. They then navigate to the Player Menu 3. User is taken to the Choose Cryptogram screen 4. User should see a list of cryptograms created by the admin 5. User selects a cryptogram that is unsolved and is navigated to it to solve it 6. User can type text into the field without any errors                                                                                                                                            |                   |           |
| Solve In-progress Cryptogram                                          | 1. Launch app 2. Login as Player 3. Select “Choose Cryptogram” 4. See list of Cryptograms 5. User selects an “in-progress” cryptogram to solve 6. User enters in their submission into a text field                                             | 1. App launches correctly without crashing 2. User enters correct username and password to login as Player. They then navigate to the Player Menu 3. User is taken to the Choose Cryptogram screen 4. User should see a list of cryptograms created by the admin 5. User selects a cryptogram that is in-progress and is navigated to it to solve it 6. User can type text into the field without any errors                                                                                                                                         |                   |           |
| Submit cryptogram with incorrect submission and no attempts remaining | 1. Launch app 2. Login as Player 3. Select “Choose Cryptogram” 4. See list of Cryptograms 5. User selects an “in-progress” or “unsolved” cryptogram to solve 6. User enters in their submission into a text field 7. User selects submit button | 1. App launches correctly without crashing 2. User enters correct username and password to login as Player. They then navigate to the Player Menu 3. User is taken to the Choose Cryptogram screen 4. User should see a list of cryptograms created by the admin 5. User selects a cryptogram that is in-progress or unsolved and is navigated to it to solve it 6. User can type text into the field without any errors 7. User navigates to view showing the game is over and they are out of attempts.                                            |                   |           |
| Submit cryptogram with correct submission                             | 1. Launch app 2. Login as Player 3. Select “Choose Cryptogram” 4. See list of Cryptograms 5. User selects an “in-progress” or “unsolved” cryptogram to solve 6. User enters in their submission into a text field 7. User selects submit button | 1. App launches correctly without crashing 2. User enters correct username and password to login as Player. They then navigate to the Player Menu 3. User is taken to the Choose Cryptogram screen 4. User should see a list of cryptograms created by the admin 5. User selects a cryptogram that is in-progress or unsolved and is navigated to it to solve it 6. User can type text into the field without any errors 7. User navigates to view showing they have solved cryptogram.                                                              |                   |           |



