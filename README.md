# Random Password Generator
## An all-in-one configurable password generator to help users defeat procrastination
![Sample image of the the program generating a password](https://i.imgur.com/Dl7AFHt.png)
---

Welcome to the Random Password Generator, written by Eric McDaniel - January 2019.

#### Purpose
Procrastination is a terrible habit, and the immediate attention of maintaining our presence on social media doesn't help. Social media has its place and benefits, however it can negatively impact our productivity if we are frequently distracted by notications, pop-ups, tweets, and other non-essential activities. After finding myself too easily distracted by Facebook instead of focusing in school work, this application was written to help remove these distractions without completely eliminating accounts by generating a new password so that one can temporarily change it, eliminating temptation and providing a solution to work on the things that matter.

#### Why do I need this application?
+ You don't if you're disciplined. However this is a good alternative for those who are susceptible to temptations and find themselves justifying unproductivity.  
+ You easily <i>could</i> uninstall or deactivate your account, and I wouldn't discourage that decision. The reason why I don't do so is because I would like to maintain my presence on social media. I want to be able to be tagged in pictures, statuses, and events from family and friends. If I were to deactivate my account, my profile can no longer be accessible, preventing me from being tagged in photos that I would want to be tagged in, for example. 
+ You don't want to eliminate yourself from social media entirely. You just want to remove distractions.
+ This program is designed to be a solution that would last a longer duration than the act of silencing your phone from notifications. This fits nicely in the middle between a few-hour phone shutdown and a multi-month Facebook deactivation. This program works best for those aiming to avoid Facebook from Monday through Friday.

#### Usage
##### <i>"Oh it'll just be five minutes."</i>
Changing your password can be risky. Regardless if you think of normal words or memorize a string of nonsense, you risk the temptation of logging back in if you remember what it was. I can randomly makeup a password like `correcthorsebatterystaple`, <a href="https://xkcd.com/936/">but I've already memorized it.</a> If you enter a password that you won't remember, you're locking yourself out. Asking a peer or loved one to type a password in for you requires trust and, needless to say, two people to accomplish a simple task.

This program works by generating a random password that should not be memorized. Users can copy/paste the password into their social media account and log out. Users should forget that password, otherwise the password is lkely too short if users can recall it after logging out. When the time comes to retreive the password, this program makes doing so easy. All passwords are stored into an internal database and are timestamped.

#### Note: Non-Linux users beware
This program was primarily intended for Linux users, and was not tested in a Windows or macOS environments. Should you be brave enough, please mention me at `@McDanielES` if you find an issue in those operating systems. Alternatively, you can open a new issue on GitHub.

---

#### Get the Application
+ Being a Java application, users will need to install Oracle's Java Development Kit (JDK). <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">The JDK can be downloaded here.</a>
+ For those unfamiliar with GitHub, click the green "Clone or download" button, download the repository as a ZIP, and extract it on your local machine. Cloning/ Forking/ or downloading via SSH will work just as fine too for users with previous GitHub experience.
+ Compile the source code using the `javac` command. You must be in the root directory containing the source code.
```
javac RandomPassword.java
```

#### Run the Application
+ Run the program using the `java` keyword, followed by the name of the program (without the <i>.java</i> suffix).
```
java RandomPassword
```

By default, the help menu will appear if no arguments were provided.

### Required Flags:
| Flag | Description |
| --- | --- |
| `-c` | Generates a random password containing a mixture of characters and numbers. |
| `-n` | Generates a random password containing only numbers. |
|      | \-or\- |  |
| `-p` | Print the most recent password to the console window. |
| `-a` | Print ALL of the stored timestamped passwords. |
| `-f` | Redirect ALL timestamped passwords to a separate textfile named "SavedPasswords.txt" |

### Optional Flags:
| Flag | Description |
| --- | --- |
| `-s <VALUE>` | Customize the password's length. The default size of 8 numbers and/or characters will otherwise be used. |
| `-D` | Clear entire database entries. You will have to enter a confirmation number. |
| `-h` | Print this help dialog again. |

#### Contact the Author
Should you find an error in the program or would like to contact me for any suggestions, improvements, or threats, you can use GitHub's `@McDanielES` mention system to contact me. I will try to respond as soon as I am available.

I am a second-year computer science student at the University of Wisconsin - Fox Valley. I am learning the fundamenals of programming in Java, Visual Basic.NET, Python, and C++. This program was written primarily for my benefit and to apply the skills learned in class into a real-world context. I am aware that it may not be realistic and often crude or unsophisticated. But <i>live and learn</i>.

You are free to clone, fork, modify and use this application as you please.