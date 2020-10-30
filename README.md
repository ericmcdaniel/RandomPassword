# Random Password Generator
## A configurable command line password generator to help users defeat procrastination
![Sample image of the the program generating a password](https://i.imgur.com/XJQ9au3.png)

---

Welcome to the Random Password Generator, written by Eric McDaniel - January 2019.

#### Purpose
Procrastination is a terrible habit, and the immediate attention of maintaining our presence on social media doesn't help. Social media has its place and benefits, however it can negatively impact our productivity if we are frequently distracted by notifications, pop-ups, tweets, and other non-essential activities. After finding myself too easily distracted by Facebook instead of focusing in school work, this application was written to help remove these distractions without completely deactivating social media accounts by generating a new password so that one can temporarily change it, eliminating temptation and providing a solution to work on the things that matter.

#### Why do I need this application?
+ You don't if you're disciplined. However this is a good alternative for those who are susceptible to temptations and find themselves justifying "temporary" unproductivity.  
+ You easily <i>could</i> uninstall apps or deactivate your accounts, and I would not discourage such a decision. The reason why I don't do so is because I would like to maintain my presence on social media. I want my family and friends to be able to tag me in pictures, status updates, and events. If I were to deactivate my account, my profile is no longer publicly accessible, preventing others from tagging me in photos that I would want to be tagged in. This isn't strictly for social media publicity, but is for personal record keeping as well. For example, being tagged simplifies searching my timeline to find my daughter's first basketball game. 
+ You don't want to eliminate yourself from social media entirely, but are content with separation. You simply want to remove distractions.
+ This program is designed to be a solution that would last a longer duration than the act of silencing your phone from notifications. This timeline fit nicely between a few-hour stint with your phone turned off, and a committed multi-month Facebook deactivation. This program is ideal for those aiming to avoid Facebook distractions for a busy week, such as from Monday through Friday.

#### Usage
##### <i>"Oh it'll just be for five minutes...."</i>
Changing your password can be risky. Whether if you construct a password consisting of normal words or memorize a string of nonsense letters, you risk the temptation of logging back in if you can remember what it was. I can randomly make up a password like `correcthorsebatterystaple`, [but I've already memorized it.](https://xkcd.com/936/) If you enter a password that you won't remember, you're locking yourself out. Asking a peer or loved one to type a password in for you requires a relationship built with trust and, needless to say, more people than necessary to accomplish a simple task.

This program works by generating a random password that should not be memorized. Users can copy/paste the password into their social media account and log out. Users should forget that password, otherwise the password is likely too short if users can recall it after logging out. When the time comes to retrieve the password, this program makes doing so easy. All passwords are stored into an internal database and are timestamped.

#### Note: Non-Linux users beware
This program was primarily intended for Linux users, and was not tested in a Windows or macOS environments. Actually, I lied. It was intended just for me. Should you be brave enough, please mention me at `@McDanielES` if you find an issue in Windows or macOS. Alternatively, you can open a new issue on GitHub.

---

### Get the Application
+ Being a Java application, users will need to install Oracle's Java Development Kit (JDK). [The JDK can be downloaded here.](http://www.oracle.com/technetwork/java/javase/downloads/index.html) The `*.class` files are not provided, and the `javac` tool is not available if you only have the JRE.
+ For those unfamiliar with GitHub, click the green "Clone or download" button, download the repository as a ZIP, and extract it on your local machine. Cloning/ Forking/ or downloading via SSH will work just as fine too for users with previous GitHub experience.
+ Compile the source code using the `javac` command. You must be in the root directory containing the `RandomPassword.java` file.
```
javac RandomPassword.java
```

### Run the Application
+ Run the program using the `java` keyword, followed by the name of the program (without the <i>.java</i> suffix).
```
java RandomPassword [ARGUMENTS]
```
To get the most out of this utility, set an alias in your shell's <i>rc</i> file to have java reference the path of the program. This also eliminates typing the keyword `java`. For example, you can append:
```
alias RandomPassword='java -cp ~/<path>/<to>/<directory>/RandomPassword RandomPassword'
```
This would require you to only need to type `RandomPassword` into your shell. You can even shorten it to `RP` or whatever you choose.

By default, the help menu will appear if no arguments were provided.

#### Required Flags:
| Flag | Description |
| --- | --- |
| `-c` | Generates a random password containing a mixture of characters and numbers |
| `-n` | Generates a random password containing only numbers |
| \-or\- |  |
| `-p` | Print the most recent stored password to the console window |
| `-a` | Print ALL of the stored passwords to the console window, timestamped |
| `-f` | Redirect ALL timestamped passwords to a separate textfile named "SavedPasswords.txt" |

#### Optional Flags:
| Flag | | Description |
| --- | --- | --- |
| `-s` | `<VALUE>` | Customize the password's length. Otherwise the default size of 8 characters and/or numbers will be used |
| `-D` | | Clear entire database of passwords. You will have to enter a confirmation number |
| `-h` | | Print this help dialog again |

#### Contact the Author
Should you find an error in the program or would like to contact me for any suggestions, improvements, or threats, you can use GitHub's `@McDanielES` mention system to contact me. I will try to respond as soon as I am available.

I am a second-year computer science student at the University of Wisconsin - Fox Valley. I am learning the fundamentals of programming in Java, Visual Basic.NET, Python, and C++. This program was written primarily for my benefit and to apply the skills learned in class into a real-world context. I am aware that it may not be realistic and often crude or unsophisticated. But <i>live and learn</i>.

You are free to clone, fork, modify and use this application as you please.