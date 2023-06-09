# u0-project-Gutenberg-Snoopy

## Description

`Gutenberg` is a command line application that allows users to interact with a list of books that have been made freely available by the good people at [Project Gutenberg](https://www.gutenberg.org/).

> **NOTE:** We don't have data for **all** the Project Gutenberg books, but we've got close to 10,000. That's probably plenty.

See [Description.md](./DESCRIPTION.md)

## Table of Contents

1. [Installation](#installation)
-   [WSL_Setup](#wslsetup)
-   [Visual_Studio_Code_Setup](#vscodesetup)
-   [Java_Installation](#javasetup)
-   [Gradle_Installation](#gradleinstall)
2. [Usage](#usage)
3. [Credits](#credits)

## Installation <a name="installation"></a>

First, you will need to use a terminal, in our case we utilized WSL. See below for a setup of your environment to ensure a smooth interaction. 

## WSL Setup <a name="wslsetup"></a>

1. Mac based users:
    ![Mac_WSL_Setup](images/Mac_WSL_Setup_1.png)

2. Windows based users:
    ![Windows_WSL_Setup](images/Windows_WSL_Setup_1.png)

3. This is to help get additional addons and configurations 
    ![Additional_WSL_Configuration](images/Updates_additional_environment_build.png)

This should get your Terminal setup as our team had it configured.

## Visual Studio Code Setup <a name="vscodesetup"></a>

1. Mac Users:
    ![Mac_VSCode_Setup](images/Mac_VSCode_Install.png)

2. Windows Users: 
    ![Windows_VSCode_Setup](images/Windows_VSCode_Install.png)

## Java Installation <a name="javasetup"></a>

![Java_Setup](images/Java_installation.png)

## Gradle Installation <a name="gradleinstall"></a>
Installation:
![Gradle_Setup](images/Gradle_install.png)

Configuration: 
![Gradle_Configuration](images/Gradle_Configuration.png)


## Usage <a name="usage"></a>

To start, you will need to clone the repository. In the terminal type the following:

1. mkdir "insert file name here"
2. cd "file name of your new directory"
3. This is for the https: git clone https://github.com/nss-se-cohort-3/u0-project-gutenberg-snoopy.git
-  This is for the SSH: git clone git@github.com:nss-se-cohort-3/u0-project-gutenberg-snoopy.git 
4. ./gradlew build
5. ./gradlew run

Here are screenshots of the application and the interface:

When you run the application within the terminal this is how your screen should appear. 
![Gutenberg_App](images/Gutenberg_App.png)

This is an example of one of the functions. I started by choosing Option 3, search books by author, then  entered agatha. This was the return of that data. 
![Gutenberg_Example](images/Gutenberg_Example.png)


## Credits <a name="credits"></a>

Our team consisted of the following individuals below, please feel free to check out their profiles for past, present and future projects.

https://github.com/larisssssa

https://github.com/JessicaE2344

https://github.com/Brecghan

https://github.com/allenpotts13