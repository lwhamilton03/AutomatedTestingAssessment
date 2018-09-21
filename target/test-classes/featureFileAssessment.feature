Feature: To have a working backend with customisable options
  As an admin
  I want to add, remove and update my data on my database
  So that I can go on to host correct data for other applications

Scenario: Add a User to the database
  Given that you are on the create UserScreen
  When the User details are entered on the Create UserScreen 
  And the details are submitted on the Create UserScreen
  Then the Username should be visible on the UsersScreen

Scenario Outline: Add a User to the database
  Given that you are on the create UserScreen
  When the User details "<Username>" username, "<Password>" password, "<ConfirmPassword>" confirm Password, "<FullName>" Fullname and "<EmailAddress>" EmailAddress are entered on the Create UserScreen 
  And the details are submitted on the Create UserScreen
  Then the "<Username>" username should be visible on the UsersScreen
  
Examples:
  | Username | Password | ConfirmPassword | FullName | EmailAddress |
  | Chris95 | guest | guest | Christopher Perrins | christopher.perrins@qa.com |
  | M4TT | chick3nWing | chick3nWing | matthewhunt | matthew.hunt@qa.com |
  | Dev | D@T@ | D@T@ | Dev Gonsai | dev.gonsai@qa.com | 
  | matthewhunt | chick3nWing | chick3nWing | Matttt | matthew.hunt@qa.com |
  
Scenario Outline: View the details of a User on a database
  Given the "<Username>" username is visible on the UsersScreen
  When the "<Username>" username with Full Name "<FullName>" is clicked on the UserScreen
  Then the User Profile should display the "<Username>" username with FullName "<FullName>" on the ProfileScreen
  
Examples:
  | Username | FullName |
  | matthewhunt | Matttt |
#  | Chris95 | Christopher Perrins |
#  | Dev | Dev Gonsai |
  
  
Scenario Outline: Updating the full name of a User
  Given the "<Username>" Username's profile page has been loaded
  And the configure button has been clicked on the profile page username "<Username>"
  When I change the old full name on the Configure Page to a new full name "<NewFullName>"
  And I save the changes to the Configure Page
  Then the Configure Page should show the new email address "<NewFullName>"
  
Examples:
  | Username | NewFullName |
  | Chris95 | Chrissss |
  | Dev | DEvvE |
  | matthewhunt | Matttt |
   