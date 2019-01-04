# Random Password Generator
## An all-in-one customizable password generator to help users defeat procrastination
![Sample image of the the program generating a password](https://i.imgur.com/Dl7AFHt.png)
---

Welcome to the Random Password Generator, written by Eric McDaniel, in January 2019.

#### Purpose
Procrastination is a terrible habit, and connecting with social media demands our immediate attention. While social media has it's place and benefits, it can negatively impact our productivity if we are constantly distracted by notications, popups, tweets, and other non-essential astivities. Afer finding myself too frequently on Facebook instead of engaging in school work, this applciation was written to generate a new password so that one can temporarily change it and remove themselves from distractions.

#### Why do I need this application?
+ You don't if you're disciplined. But if you find yourself susceptible, this is a good alternative.
+ You easily <i>could</i> uninstall or deactivate your accounts, making that a fair decision. The reason why  don't is because I want to be able tobe tagged in pictures, status, and events from family and friends. If my account is deactivated, my name is no longer indexed, preventing me from being tagged in photos that I would want to be tagged in, for example. 
+ You don't want to eliminate yourself from social media, you just want to remove distractions.
+ This program is designed to be a a solution that would last a longer duration than the act of silencing your phone from notifications.

#### Usage
Changing your password can be tricky. If you know what you entered, then you risk the temptation of logging in. <i>"Oh it'll just be five minutes."</i> If you enter something you won't remember, you're locking yourself out. Having someone type a password in for you involves having a lot of trust.

This program works best by generating a random password that cannot be memorized. Users can copy/paste the password into their social media account and log out. Users will (likely) forget that password, and when the time comes to retreive the password, this program makes doing do easy. All passwords are stored into an internal database and are timestamped.

#### Note: Non-Linux users beware
This program was primarily intended for Linux users, and was not tested in a Windows or macOS environments.

---

#### Get the Application:
+ Being a Java application, users will need to install Oracle's Java Development Kit (JDK). <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">The JDK can be downloaded here.</a>
+ For those unfamiliar with GitHub, click the green "Clone or download" button, download the repository as a ZIP, and extract it on your local machine. Cloning/ Forking/ or downloading via SSH will work just as fine too for users with previous GitHub experience.
+ Compile the source code using the `javac` command. You must be in the directory containing the source code.
```
javac RandomPassword.java
```

#### Run the Application
+ Run the program using the `java` keyword, followed by the name of the program (without the <i>.java</i> suffix).
```
java RandomPassword
```

By default, the help menu will appear if no arguments were provided.

Required Flags:
+ `-c` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; A random password will get generated which contains a mixture of characters and numbers.
+ `-n` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; A random password will get generated which contains numbers only.

 -or-

+ `-p` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Print the most recent password to the console window.
+ `-a` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Print ALL of the stored timestamped passwords.
+ `-f` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Redirect ALL timestamped passwords to a separate textfile named "SavedPasswords.txt"

Optional Flags:
+ `-s` \<VALUE\> Customize the password's length. The default size of 8 numbers and/or characters will otherwise be used.
+ `-D` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Clear entire database entries. You will have to enter a confirmation number.
+ `-h` &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Print this help dialog again.

#### Contact the Author
Should you find an error in the program or would like to contact me for suggestion/ improvements, you can use GitHub's `@McDanielES` mention system to contact me. I will try to respond as soon as I am available.

I am a second-year computer science student at the University of Wisconsin - Fox Valley. I am learning the fundamenals of programming in Java, Visual Basic.NET, Python, and C++. This program was written to apply the skills learned in class into a real-world context. I am aware that it may not be realistic and often crude or unsophisticated. But Live and Learn.

You are free to clone, fork, modify and use this application as you please.