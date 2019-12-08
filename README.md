# Java_MiniProject
#### Brief presentation:

This is a mini Java project of Karaoke for students in Polytech Nantes Info4. 
It aims to practice using different components of Java programming language to construct a relatively complet
system which enables users to listen to music from a list of music and adjust some features of the music
at the same time, for example the speed and pitch.

#### Instructions:
In order to correctly run this program, firstly we need to run the server side, which is `MainServer` ,
then run `MainClient`. In the windows that shows up, you need to log in with user name, after
that you are free to chose a preferred music from our music library. And 
please enjoy it.


#### Different components(class) of the project:

- `GUIs`:

- `PitchControl`:
This class is used to modify the pitch of the chosen music before/during playing.

- `findAllMidiFiles`:
This class is mainly used to identify all midi-type files in specified directory and 
return the result in required format.

- `LyricsListener`:
This class focus on reading the integrated lyrics message inside the midi file during playing
 in order to show the lyrics at corresponding time.
 
- `GeneralData`:
This is a class specifically used to store information of all users and calculate 
different kinds of global characteristics of this system.

- `MainServer`:
This is the server-side program that should be run at the very first place, which coordinates all the global information
 needed and serve to send the required music and collect user information.

- `MainClient`:
Of course this is the client-side program which should be run after launching the server
side program. It has multiple functionality including changing speed/pitch of the 
playing music. Users are able to adjust some features of the music by drawing the 
control bar in the GUI.

