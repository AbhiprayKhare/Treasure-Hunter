# Treasure Hunter
This is a 2D Game made entirely on Java using java swing 
In this I have made 4 packages :
Package 1: This package contains 2 Classes 



1-> Entity Class: This class is basically superclass of Player and stores key attributes of player character like int speed, String direction which tells us the direction player is facing so that our image can be regulated accordingly , it also contains BufferedImage class stored as variables like up,down,left,right which are used to store images, boolean collisionOn variable is used store whether there is collision or not so that when player character tries to move over objects like trees,water and wall then it won't be allowed to do so, solidArea is used to calculate area of player we need to use for collision.



2->Player class: This is the subclass of Entity class . It sets the initial values of player coordinates ,speed and direction and also sets the values of variables up1,up2,down 1 etc with the respective sprites.There are 8 types of sprites for different movements of player like 2 sprites for each up,down ,left and right.From the keyHandler class it uses the value of up ,down ,left,right variables to set the direction of player Suppose if up in keyHandler class is true then direction will be assigned "up".It checks for collision and if there is no collision then updates the value of player coordinate. 


Now using sprite counter it checks how many times the function update was called and then accordingly changes the swaps the value SpriteNum from 1 to 2 and 2 to 1 this will help give player character walking movement effect

Then draw method is used to draw the player and objects using Graphics2d class.
