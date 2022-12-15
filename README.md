# Software-Maintenance-Coursework---20303967
Name: Gan Xiao Thung\
Student ID: 20303967
## How To Run
### Requirements
* Javafx-sdk-19 (need to link path to project structure - libraries)
### How to Compile? 
1. Open new Javafx project in IntelliJ
2. Copy src folder to project
3. Build project
4. Click run and program will start 
## Javadoc Path
## Key Features
# Working Properly
* Account log in and sign up
* Main Menu (guest and user version)
* Leaderboard 
* 2048 Game
*   Game mode - endless and timer
*   Choose theme
*   Scores
*   Save score function
*   End and win condition
# Future Improvements
* Main Menu
*   When user returns to menu, the guest version of menu will display instead of user version (user needs to log in again)
* Game
*   When change board size from 4x4 or 6x6 to 8x8, error will occur in program
*   When 2 different merges are to happen in same cell row, only 1 is merged
## New Classes
* Controller
* EndGameController
* gameModeController
* leadController
* LoginController
* Board - generate board and operate cells movement 
## Modified Classes
* Account
*   Implemented accounts
*   New methods -> sortAccounts, createdAccounts
* GameScene
*   break down class to subclass - Board
* Cell
*   made some changes on design 
* Main
*   removed all scenes initialization
*   Only initialize menu scene when runs
* TextMaker
*   made some changes on design
