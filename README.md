# Song-library

This is a Springboot Java project using MongoDB. Performs basic CRUD operations, showcasing the MongoDB. 
Data is imported into MongoDB from https://www.kaggle.com/albertsuarez/azlyrics. [Datasets b,k,j,d]

## Starting the application
In target folder there is a JAR file that is available for starting the app. 
However, we have some issues with database connection when application is started this way so we recomend to 
start the application by running it from the IDE. We use IntelliJ, so the way to start this application is from 
Run toolbar. 
If the application started you will see in the terminal the following message: 
"Started SongLibraryApplication in 2.625 seconds (JVM running for 3.713)"

## Using the application
Once the application is started, go to the following link: http://localhost:8081/songs

At the home page you will be presented with the search for songs. 
You can search songs by song name, or artist name, or the lyrics of the song. 
You cannot search for a song using the combination of these fields, just by one field.

If you do not know the exact song name, or artist name or complete lyrics,
you can enter the part you do know, and the application will find all songs/artist containing the text
you entered the search field.

For example, let's try to find the artist Backstreet Boys.
For the sake of the demo, let's say we are not sure what is the full artist name,
then we enter just "boys" in the artist field and hit search.

We will be presented with the list of all songs by the artist(s) that contain "boys" in their name.

Once we have a list of songs, or just one song, we are presented with the full document stored in the MongoDb.
You will notice that by the song, on the right, there is an option to add a comment. This will lead you to
the new form, providing you the fields to add a comment for the selected song. Once the comment is added, application
redirects you back to that song with your newly added comment. 
Comments field in MongoDB is an array field that stores the array of objects.

Moreover, in the top left corner you have an option to add a new song. Upon clicking on the button, application
redirects you to the form of adding completely new song.

And that is it.

