# Design information
### Calvin Lam | clam38

### Requirements:
1. A user will be able to choose to log in as a specific player or log in as the administrator when starting the application.  For simplicity, any authentication is optional, and you may assume there is a single system running the application.

    The user is represented as a class. The action of logging in is listed as the `log_in()` method in the `User` class. There is a association link between `User` and the `Player` and `Administrator` classes to denote the log in action. Authentication is crucial in any system and it's encapsulated in the `Log In` class as part of the log in action.

2. The application will allow players to (1) choose a cryptogram to solve, (2) solve cryptograms, and (3) view the list of player statistics.

    The player is represented in the `Player` class. The three actions is listed in the methods section: `choose_cryptogram()`, `solve_cryptogram()`, and `view_player_stats()`.

3. The application will allow the administrator to (1) create a cryptogram, (2) create a player, and (3) view the list of player statistics.

    The administrator is represented in the `Administrator` class. The three abilities is listed in the methods section. `create_cryptogram()`, `create_player()`, `view_player_stats()`.
    - For creating a cryptogram, there is an association link leading to the `Cryptogram` class. Note that a separate class `Create Cryptogram` was created to capture the complexity of the action in requirement `6`.
    - For creating a player, there is an association link leading to the `Player` class.
    - For viewing the player statistics, two association links were created. One to the `List of Player Statistics` and another to the `Admin View`. The `Admin View` class was created to meet requirement `9`.

4. A cryptogram will have a solution (the plaintext phrase) and a maximum number of allowed solution attempts for each of three difficulty categories.

    The cryptogram is represented in the `Cryptogram` class. The solution and number of attempts is listed in the attribute section of the `Cryptogram` class.

5. To add a player, the administrator will enter the following player information:
    - A first name
    - A unique username
    - A last name
    - A difficulty category: easy, normal or hard.

    An association link is placed from `Administrator` to `Player` class. This is further denoted with a "create player" label on the link. In the `Player` class, attributes: `first_name`, `last_name`, `username` and `difficulty` was created.

6. To add a new cryptogram, the administrator will:
    - Enter a unique cryptogram name.
    - Enter a solution (unencoded) phrase.
    - Enter the number of allowed incorrect solution attempts for the easy difficulty.
    - Enter the number of allowed incorrect solution attempts for the normal difficulty.
    - Enter the number of allowed incorrect solution attempts for the hard difficulty.
    - Edit any of the above steps as necessary.
    - Save the complete cryptogram.
    - View a confirmation that the name assigned to the cryptogram is unique and return to the main menu, or be returned to editing the cryptogram after any error is displayed.

    Due to the complexity of the create a cryptogram action and it's dependency of the `Cryptogram` class, a separate class was created; `Create a Cryptogram`.
    The attributes: (`name`, `solution`, `num_attempts`) and methods: (`edit()`, `display_confirmatio()`, `save()`, `return_main_menu()`, `return_editing()`) are listed in this class. It has a link to the association link between `Administrator` and `Cryptogram`.

7. The encrypted phrase for the cryptogram will be generated for each player starting a new cryptogram by:
    - Replacing each letter with another letter randomly, so that all of any particular letter are replaced with the same other letter, such as all A’s becoming C’s, and every letter is paired with a unique encrypted letter.
    - Preserving the capitalization in the original phrase.
    - Preserving any non-alphabetic characters (such as punctuation or white space) unaltered.

    The action of generating an encrypted phrase is placed as an `gen_encrypted_phrase()` method in the `Solve Cryptogram` class. The specific actions taken to generated the phrase need not be captured in the UML class diagram.


8. To choose and then solve a cryptogram, a player will:
    - View the list of all unsolved cryptograms alongside their status as in progress or unstarted, and choose a cryptogram to solve.
        - `Choose Cryptogram` class method: `view_all_cryptogram()`
    - View the chosen cryptogram and number of incorrect solution attempts remaining (starting at whatever number is allowed for the player’s difficulty level for that cryptogram).  If the cryptogram has not been played by this player before, the fully encrypted phrase should be generated and displayed.  If the cryptogram is in progress, the previous state of the phrase should be displayed.
        - `Solve Cryptogram` class attribute: `num_attempts`
        - `Solve Cryptogram` class attribute: `encrypted_phrase`
        - `Solve Cryptogram` class attribute: `difficulty`
        - `Solve Cryptogram` class attribute: `state`
        -
    - Match the replacement and encrypted letters together, and view the resulting potential solution.
        - `Solve Cryptogram` class method: `process_solution()`
    - When all letters in the cryptogram are replaced and they are satisfied with the potential solution, submit their answer.
        - `Solve Cryptogram` class method: `submit_answer()`
    - Get a result indicating that the solution was successful, or decrementing the number of incorrect solution attempts remaining if it was unsuccessful.
        - `Solve Cryptogram` class method: `return_result()`
    - At any point, the player may return to the list of unsolved cryptograms to try another.
        - `Solve Cryptogram` class method: `return_to_choose_cryptogram()`
    - If the number of incorrect solution attempts reaches zero, they will get a result that the cryptogram game was lost, and this cryptogram will be marked as complete, unavailable for this player to attempt again. They will then return to the menu.
        -  `Solve Cryptogram` class attribute: `complete`
    - If the player successfully solves the cryptogram, they will get a result that the cryptogram game was won, and this cryptogram will be marked as complete, unavailable for this player to attempt again.  They will then return to the menu.
        -  `Solve Cryptogram` class attribute: `complete`

    `Choosing a Cryptogram` and `Solving a Cryptogram` are two different actions with different attributes and methods that derive from the Cryptogram class. To adequately represent this, an association link from `Player` to `Solve Cryptogram` is drawn with a connected action of `Choose Cryptogram`. These two classes are also linked as dependencies of the `Cryptogram` class.

9. The list of player statistics will display a list of players in descending order of number of cryptograms won.  The entry for each player will show their first name, the number of cryptograms won, and the number of cryptograms lost.  An administrator should also see the username and difficulty status of the player.

    The attributes of player statistics viewable are `User` type dependent. Only the `Administrator` can see the **username** and **difficulty** level of the `Player`. To represent this, two view classes was created that are dependents of the `Player Statistics` class: `Admin View` and `List of Player Statistics`. The `Administrator` class have two association links pointing to both `Admin View` and `List of Player Statistics`. The `Player` class only has one association pointing to the `List of Player Statistics` class. The data for the player statistics comes directly from players solving the cryptograms. An dependency link was created between `Cryptogram` and `Player Statistics`.

    The attributes:
    - `Admin View` class attribute: `user_name`
    - `Admin View` class attribute: `difficulty`
    - `List of Player Statistics` class attribute: `first_name`
    - `List of Player Statistics` class attribute: `players`
    - `List of Player Statistics` class attribute: `games_won`
    - `List of Player Statistics` class attribute: `games_lost`

10. The user interface must be intuitive and responsive.

    This requirement is neither an action or a attribute of the system. While it is important, it is a qualitative requirement and it doesn't need to represented in the UML class diagram.

11. The performance of the game should be such that students do not experience any considerable lag between their actions and the response of the application.

    The requirement doesn't specific what constitutes `lag` and it's neither an action or attribute in our system. It won't be in the UML class diagram.
