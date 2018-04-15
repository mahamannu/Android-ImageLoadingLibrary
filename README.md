# IMDb like App without external libraries

To appreciate the amount of work external libraries do for us in order to create a modern day app, where we load images on a screen, we create an app that avoids using any of the 3rd party libraries , like retrofit, picasso/fresco/glide, gson, okhttp etc. 

In particular, is the non-usage of image loading library like Fresco/Glide/Picasso, which requires us to build our own Bitmap handling, caching and Bitmap downloading mechanisms.

Feature List: The screen should show a list of upcoming movies in list form. Each row should consist of the following information about the movie:
1. Movie title
2. Release date
3. Popularity
4. Votes count
5. Movie Image
• One click of particular movie should display the details on a different Activity

API:- https://www.themoviedb.org/documentation/api
