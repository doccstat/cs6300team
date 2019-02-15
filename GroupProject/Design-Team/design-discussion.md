# **Design Discussion**


## Individual Designs

### **Design 1**


![alt_text](images\design_1.png)


**Pros:**

*   The design has most of the attributes and methods needed to meet the requirements
*   Very clear UI/UX flow from the perspective of an app
*   Good use of dependencies/associations between all classes
*   Clear function names and availability of methods for each user

**Cons:**

*   `view_player_stats()` is repeated in two classes (Player and Administrator). I think it would be better to move it to User.

    Here I have some disagreement. I don't think we should merge them into player class. The function return different outputs for player and administrator. If they are merged into player class, every time administrators access the list of player stats need to call the function from player class. And it seems a little complicated to make the function return more elements for administrator.

*   The `display()` methods and the AdminView are GUI related. According to requirements: "you do not have to show any purely GUI specific classes, if they are only doing user display and input and not performing any significant business logic."
*   Some class names are not nouns or noun phrases.
*   Not as clear on the specifics of the interactions between the various Cryptogram classes
*   Unsure if difficulty category/progress are strings/ints/enums/etc.

    Enum would be a good choice! String and int are both viable and I don't think they have merits over each other.

*   Functions are missing parameters

    We should add "+,-,~,#,_" etc in our final design to show the attributes as public or private or something else. Since we want some attributes to be private in case we made mistakes while implementation.




**Design 2**



![alt_text](images\design_2.png)


**Pros:**



*   The CryptogramAttempt class and the relation checkSolution will help to save the Cryptogram status and player statistics
*   Includes use of multiplicity,
*   Separation of functions that are publicly/privately visible
*   Parameter named arguments and types

**Cons:**



*   Initializing strings to "" and numbers to 0. I don't see why we should do this.
*   No specific class for Player Statistics
*   Clarity required to understand the flow of generating the CryptogramAttempt along with the solution
*   Not all lines/dependencies/associations are included correctly
*   Some functions' returns are not explicit.



**Design 3**




![alt_text](images\design_3.png)


**Pros: **



*   Clear usage of multiplicity relationships between entities
*   Great use of hashMaps to uniquely identify cryptograms, agreed!
*   Overall UML is understandable and not overly complicated

**Cons:**



*   The way PlayerStats is currently implemented, it appears it could be directly implemented in Player as opposed to being a separate class
*   The ability to Create a Player/Cryptogram from the Administrator is not explicitly clear in the UML although it is stated in the descriptions
*   Is it viable making list of player stats into database, since I don't think list of player stats is a class but I can't tell why... List of player stats functions like _time_ in the lecture P3L2 library example.

**Design 4**


![alt_text](images\design_4.png)


**Pros:**



*   Clear classes, having the needed attributes and methods
*   Defines Player/Administrator as separate objects
*   Shows actionable relationships between classes

**Cons:**



*   Seems to define Player/Administrator as separate classes without a inherited idea of a User class
*   Unclear if a cryptogram can be solved by multiple players or only be for a single player.  This is because there is a `markAsComplete()` function but it seems to imply that there is only a single copy.
*   Function parameters show type but unsure what the parameter names are


# Team Design


![alt_text](images/Copy-of4.png "image_tooltip")


The template for our team design was lifted from Justin's (Design 2) diagram. From the discussion amongst the team members, Design 2 offered a simple and intuitive layout of the classes required and a large list of attributes and methods for us to add/remove to our team design. In addition, Design 3 also shared a similar idea on how to best represent the requirements making Design 2 an easy way to begin our final team design.

Design 1

The primary difference in Design 1 is that it's too abstract and lacked a focus on only the object oriented classes necessary for the system. This is seen through the inclusion of the Player Statistics class and the two user type specific UI screens. The final team design handle the player statistics view requirement with a simple inclusion of the getListPlayerStats() method. The complexity of which user type sees which screen wasn't necessary in the class diagram. Aside from the difference in class structure, Design 1 included many of the same attributes and methods as the final team design such as strings for name, solution, completed, etc.

Design 2

As Design 2 became the template of our team design, it shares more similarities than differences. The final team design included detailed parameters to the existing methods of Design 2's classes. For example, CreateUser() became CreatePlayer(firstName: String, lastName: String, username: String, category: int). The Cryptogram class in the team design included additional methods necessary to implement the items in Requirement 8.

Design 3

The team design and Design 3 share similar classes and relationships. The major difference lies in how the the Player Statistics requirements should be represented. Design 3 chose to represent this requirement in a PlayerStats class with relationships to the Player and User class. The team design simplified this requirement with the getListPlayerStats() method in both the Administrator and Player classes.

Design 4

Design 4 similarly represented some of the requirements with the Administrator, Player, and Cryptogram classes. Same as Design 1 and 3, Design 4 included a class to for the player statistics requirement.


# Summary

Remote collaboration on any complex projects, including technical designs, is always a challenge. One of the advantages of this team is that most of the members shared similar ideas on how the requirements should be represented. This allowed us to quickly form a foundation and systematically review each new idea from individual members.

Some of the lessons learned from this process includes:



*   Sharing and learning the constraints of each member. Being in different time zones could pose a challenge, by considering this, we learn to not expect immediate responses from our messages or view each message requiring immediate action.
*   Finding common ground fast. Reconciling the ideas of four individuals could be a monumental task as the team decides which one to include or remove. By focusing on the ones common to all, each new idea and its merits gets weighted against a system we already agreed on.
*
*

<!-- Docs to Markdown version 1.0β15 -->
