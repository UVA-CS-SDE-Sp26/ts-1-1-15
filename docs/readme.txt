2/3/2026
Project Contributors:
Nathaneal Wattier
Michael Farace
Ellie Kim
Claudia Castagna

TopSecret documentation starter file

UserInterface
---------------------------
goal: manage all command line inputs and outputs.
1) take arguments and either appropriately call ProgramControl.listFiles(), ProgramControl.retrieve(int), or
ProgramControl.retrieve(int,String)


FileHandler
---------------------------------
+String getContents(String filename)
+ArrayList<String> getFilenames()
---------------------------------
goal: manage the file folder named data.
1) take request (from program control) for file as String filename, return contents of file as a String
2) take request (from program control) for the list of filenames, return ArrayList of filenames


ProgramControl
----------------------------
+String listFiles()
+String retrieve(int num)
+String retrieve(int num,String key)
----------------------------
goal: connect command line to file handler, connect file contents with deciphering.
1) receive request (from userinterface) with no arguments, return list of files available (from
filehandler) as String
2) receive request (from userinterface) with int argument, return corresponding file contents as String
3) receive request (from userinterface) with int and String argument.


Cipher
---------------------------------------------
+String decipher(String contents)
+String decipher(String contents, String key)
---------------------------------------------
goal: manage ciphering feature. Manage file named key.txt, in folder named ciphers
1) receive request (from programcontrol) with String argument, decipher and return a String
2) receive request (from programcontrol) with String argument and a String key, decipher and return a String
Note to decipherer, I don't know why, but key.txt contains more than one u, and more than one U, on both lines.
