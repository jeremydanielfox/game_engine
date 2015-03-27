Use Cases:
----------
###Graphics Authoring Environment use cases
1. Load a saved file to continue editing
   - user selects saved file from FileChooser
   - method either creates an instance of XStream or calls some method that can convert this XML back into necessary objects
   - all the parameters are set using these objects (should probably define which parameters can be saved somewhere)
2. export data file to be used by game player
   - user clicks export data option on menu
   - all the set parameters, objects (ex. Tower with x attributes and objects), etc. are saved using XStream (really simple)
   - Choose save location of XML file
3. Create a new object (Tower, Enemy, etc.)
   - User selects option from EditablesPanel in the GUI for type of object
   - EditorView changes to show properties that can be edited. This depends on type, and the editable properties are read from a properties file from the Game Engine
   - User can click on world to place object and coordinates are saved as location in double coordinates
1. Create a projectile (similar to 4)
   - AoE (ex. destroy tower to hurt enemies)
   - splash (rocket)
   - chain
   - arrow
   - tracking
1. Define properties of a Tower (range, fire rate, projectile, etc.)
   - EditorView on the side with the possible editable properties read from properties file from GameEngine
   - should give the users default values AND the ability to randomly select parameters
1. Set up store
   - First tab that will open when beginning a new game is Game Preferences (can choose how many levels, yes/no store, etc).
   - Store is an Editable, editing will be similar to adding a new object
1. Define Tower upgrades
   - Tower upgrades are editables that can be added to towers
   - Parameters should include how much it costs, etc.
1. Define properties of an Enemy (speed, health, etc.) 
   - Similar to Defining the property of a tower
1. Set game preferences/style
   - First thing that user sees is Preferences
   - allows to choose type of tower defense game (side view, aerial view)
   - hero option (extension)
   - fieldrunner (dynamically define path by tower)
   - Setting win/lose conditions
   - There will be a class/data structure that has its properties bound to these preferences. For these it’s reasonable to hardcode and give the user some options, rather than allowing them to customize entirely
1. Define path
1. Set tile preferences
1. Design on top of a background image
   - import an image from FileChooser and set the background
   - use tiles with no image to set if a user can build on top of that
1. Set up Enemy Wave properties (starting location, number of enemies, etc.)
1. Creating tabs for different levels/preferences (winning conditions, levels etc)	
1. Running the created environment
1. Randomize level settings
1. Mass add a terrain
select area
choose object, round down so it fits (for example, if there are 100 tiles horizontally, and the object is 55 tiles, then we have just one object, and try to fit it in with that algorithm)


###Game engine use cases
1. Player buys and then places tower
2. Tower detects enemy in range and shoots projectile
3. Projectile hits and kills enemy, score/money updated
4. A wave of enemies enters
5. Player pauses, plays, fast forwards game
6. Player upgrades a tower
   - Upgrade trees
8. Player removes a tower
9. Player buys movable tower, defines path for it, places tower on path, tower moves along path
10. Enemies follow a path
11. Enemies search for a target
12. Player wins by all enemies being killed
13. Player loses by enemies reaching end of path and all lives are gone
14. Player activates cheat codes
   - Freezes game but can still move towers
   - Infinite money
   - Infinite lives
   -  All enemies gone from screen
20. Player beats level one, transitions to level two
1. New tower is unlocked/purchased
2. Player places one tower at start of game, has to defend it with other towers
3. Player has to defend multiple towers
4. User controls character that goes around and builds towers
25. Different attack types
   -Direct damage: damage over time (traditional HP) 
   - Splash damage: damage falloff effect (e.g. grenade)
   - Triple Damage: chance each hit will be 3x damage
   - Mana Gain: hits return player health/magic/tokens 
   - Chain Hit: chance each hit (and successive hits) will hit other monsters
   - Poison: deals certain damage over time period; stackable
   - Shock: chance that monster is paralyzed for up to 1 sec
   - Slow: slows down monster
   - Armor reduce: chance that monster’s armor reduced by a number
35. Explosion occurs on screen
36. Enemy changes color while dying
37. Projectile moves on screen
38. Player purchases terrain for map
   - Trenches
   - Traps
   - Water
   - Barriers
1. Player tries to place tower where they can’t
1. Monster characteristics
   - immunity to certain towers
   - extra resistance to certain towers
   - ability to spawn new monsters
   - flyers - avoid the maze path
1. Notifications
   - Wave approaching
   - Achievement unlocked
1. User wants to change which controls
1. Explosion sound occurs
1. Splash screen
1. Camera changes
   -Total map
   -Follows player
   -Player can click to zoom, drag to pan.
1. Saves progress
1. Load progress
1. Player vs. player combat
1. User clicks on existing unit
