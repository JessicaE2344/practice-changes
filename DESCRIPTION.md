## Overview

`Gutenberg` is a command line application that allows users to interact with a list of books that have been made freely available by the good people at [Project Gutenberg](https://www.gutenberg.org/).

> **NOTE:** We don't have data for **all** the Project Gutenberg books, but we've got close to 10,000. That's probably plenty.

## The Existing Codebase

For this project your team will be starting with existing code. In the real world you will _very rarely_ start a project from scratch. Often, you'll join a team that has already been working for weeks, months or years on a system. Other times, your team may take over an existing application from another team.

However it happens, it's very common to start with existing code so at NSS most of our projects will have you start with an existing codebase.

Your team's job will be to add some features and fix some bugs. But first you'll need to understand how the existing code works...

## Exploring the Code

When presented with an existing codebase, the first thing to remember is that you should take your time exploring the code before you just dive in and start tweaking things.

Here are some steps you should follow.

1. Clone the code's repository.
    * For this project, one team member should click the Github Classroom link to generate the repository. Then each team member should clone the newly created repo to their local computers.
1. Try to build the project.
    * Run `./gradlew build`
    * If it doesn't build, your job is to figure out why and fix the problem.
1. Run the program.
    * At the root of the project, you should see a `gutenberg` command. Run it like this:
        ```sh
        ./gutenberg
        ```
1. Play with the program. See what it does. See what works and what doesn't.
    * Make some notes about what does and doesn't seem to work.
1. Now it's finally time to actually look at the code.
    * Open the project in Visual Studio Code.
    * Look for the `main` method. That's where Java starts, so it makes sense for you to start there too.
    * Run the program as you look through the code.
        * Can you find the code that prints the individual messages you see when you run it?
        * What happens when the user enters information at the various prompts?
1. Are there some areas of the code you don't understand? Try dropping in a few `System.out.println()` calls. Print variables. Print messages to yourself. You can always remove these later.
1. After you've done all this check in with your team.
    * What did they find that you missed?
    * What did you find that they missed?
    * What aspects of the program are still confusing?

> **NOTE:** A word of caution... It's very important to get to know a codebase before you begin working on it, but it's _impossible to **completely understand** the code before you jump into it_. Like with many things in software development (and life) it's important to find a balance, and it takes time to get that balance right.

## Changing the Code

When you have a reasonable understanding of this codebase, read through [REQUIREMENTS.md](./REQUIREMENTS.md) to see what changes you'll be making.
