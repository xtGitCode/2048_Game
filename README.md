# Software-Maintenance-Coursework---20303967
Name: Gan Xiao Thung\
Student ID: 20303967
# How To Run
### Requirements
* Javafx-sdk-19 (need to link path to project structure - libraries)
### How to Compile? 
1. Open GanXiaoThung_JavaVersion folder in IDE
2. Build project
3. Click run and program will start 
# Javadoc Path
# Key Features
## Working Properly
### Background music
### Images
### User log in and sign up
*   username and password
### Main Menu 
*   Different display and buttons for user and guest
### Leaderboard 
*   Display top 10 users with highest score in database
### 2048 Game
*   Added game modes - endless or timer
*   Change color theme
*   Scores
*   Save score function
*   End and win condition
*   Display score and best score of user during end/win game scene
## Future Improvements
### Main Menu
*   When user returns to menu, the guest version of menu will display instead of user version (user needs to log in again)
### Game
*   When change board size from 4x4 or 6x6 to 8x8, error will occur in program
*   When 2 different merges are to happen in same cell row, only 1 is merged
# New Classes
### Controller (handle main menu operations)
*   Login / Logout Button
*   Start Game Button
*   Leaderboard Button
*   Quit Button
### EndGameController (handle end game and win game operations)
*   Return to main menu Button
*   Quit Button
### gameModeController (handle game mode operations)
*   Choose color theme
*   Choose board size
*   Choose game mode
### leadController (handle leaderboard operations)
*   display leaderboard
*   Return to main menu button
### LoginController (handle log in and sign up operations)
*   Login : Check username and password to match with database record
*     if not then show error
*     if correct then bring user to user menu
*   Signup : store username and password to database
*     prompt user to login 
* Board - (generate board and operate cells movement)
# Refactoring Done
* Break down class to smaller subclasses
* Rename variables and methods 
* Change class variables to private and use getters/ setters to improve encapsulation
* Fix bugs in source code
* Rearranged and simplified code
* Removed redundant parameters, variables and imports
* Removed EndGame.java to replace with endGame.fxml
# Modified Classes
### Account
*   Account paramters take in username, password and score
*   Implemented makeNewAccount method to add new account to array and text file
*   New method -> sortAccounts (sort accounts in arraylist ascending by score)
*   New method -> createdAccounts (add all created accounts in text file to arraylist)
### GameScene
*   Break down original class to subclass - Board
*     Moved methods and variables related to cells to Board class
*   New method -> updateScore (updates current score to users database)
*   Rename method game to showGame
*   Implement new game mode - timer mode (after timer ends game stops)
*   Added save score button for user to save their current score anytime 
### Board (Previously in GameScene)
*   Fix scoring 
*   Fix key valid movement (only arrow keys and ESC are valid)
*   Fix cells spawning so that only when cells moved, new cell is spawn
*   Added sound effect for when cells merge
*   Change position of board to center
### Cell
*   Simplify setColorByNumber method's switch case 
*   Change design of cell by making rectangle edge more curve
### Main
*   Removed all scenes initialization
*   Only initialize menu scene when runs
### TextMaker
*   Change font size to smaller
