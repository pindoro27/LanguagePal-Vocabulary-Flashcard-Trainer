# 📝LanguagePal: Vocabulary Flashcard Trainer

## 📖Description / Overview

LanguagePal is a Java console application designed to make vocabulary learning easy and personalized.
It lets you create your own flashcards, organize them by language and category, and quiz yourself anytime.
The program also tracks which words you struggle with and automatically prioritizes them during quizzes, helping you learn more effectively.

This project was built to demonstrate core Object-Oriented Programming (OOP) concepts while solving a real learning need.

**Why it’s useful:**

Instead of memorizing words randomly, LanguagePal helps you focus on the words you struggle with, making your learning more efficient and fun.

Example scenario:
Imagine you are learning Spanish. You can create flashcards for "Hola" (Hello) and "Gracias" (Thank you). During the quiz, if you keep missing "Gracias," the program will ask it more often so you can remember it better.

## OOP Concepts Applied

LanguagePal is a great example of object-oriented programming in action. Here’s how the main OOP principles were used:


### 🔒Encapsulation


- All flashcard data (word, meaning, language, category, and wrong count) is private.

- You can only access or change these values using methods like getWord(), setMeaning(), incrementWrongCount().

**Purpose**: Keeps your data safe from accidental changes and centralizes control.


### 👨‍👩‍👧‍👦Inheritance


- BasicFlashcard inherits from the abstract Flashcard class.

- InvalidInput inherits from Java’s Exception class to create custom error messages.

**Purpose:** Lets us reuse code instead of rewriting everything, keeping the project organized.

### 🔄Polymorphism

- Methods like display() and quiz() are abstract in Flashcard and implemented in BasicFlashcard.

- The program stores all flashcards as Flashcard objects but calls the actual method from BasicFlashcard dynamically.

**Purpose:** You can have different types of flashcards in the future without changing how the program works.


### 🎨Abstraction


- The Flashcard class defines what a flashcard should have (word, meaning, language, category, wrongCount) and what actions it should perform (display(), quiz()), but it doesn’t define exactly how.

**Purpose**: Forces subclasses to define the actual behavior, keeping the program flexible and scalable.


## 🏗️ Program Structure
### 📁 Main Classes and Their Roles


**📝Flashcard (Abstract Base Class)**


- Stores common properties for all flashcards: word, meaning, language, category, wrongCount

- Declares abstract methods display() and quiz() that all flashcards must implement

- Provides toString() for a standard text representation


**✨BasicFlashcard (Concrete Class)**

- Implements display() and quiz() methods

- Handles the quiz behavior (updates wrongCount)

- Extends Flashcard with concrete functionality


**🎯LanguagePal (Main Application Class)**


- Manages all flashcards through an array

- Handles user input with a menu-driven interface

- Provides functionality to add, edit, delete, view, and quiz flashcards


**⚠️InvalidInput (Utility / Custom Exception Class)**

- Handles invalid input from the user

- Extends Exception for custom error messages


**▶️Main (Entry Point)**

- Seeds sample flashcards

- Launches the LanguagePal program


## 📊 Class Relationships


### Inheritance Relationships (IS-A)


- BasicFlashcard IS-A Flashcard → inherits common properties and behavior

- InvalidInput IS-A Exception → custom error handling


### Composition Relationships (HAS-A)


- LanguagePal HAS-A Flashcard[] → array stores all flashcards

- LanguagePal HAS-A Scanner → handles user input


### Dependency Relationships (USES)

- Main USES LanguagePal → seeds sample data and starts the program

- LanguagePal USES BasicFlashcard → creates new flashcards dynamically


### Association Relationships


- LanguagePal ASSOCIATED-WITH Flashcard → manages all flashcard operations and quizzes


### 🎮 Main Classes and Their Roles (Icons for readability)


- Flashcard 📝 – Abstract base class defining properties and required methods


- BasicFlashcard ✨ – Concrete flashcard implementing quiz and display functionality


- LanguagePal 🎯 – Main controller for flashcards and quizzes, menu-driven interface


- InvalidInput ⚠️ – Custom exception for invalid user inputs


- Main ▶️ – Entry point that seeds data and starts the program


## 🚀How to Run the Program
### Requirements


- Java JDK (version 8 or higher) installed

- Command-line terminal or IDE (like Eclipse, IntelliJ, or NetBeans)


### Steps


1. Open your terminal or IDE and navigate to the project folder.

2. Compile all .java files:

      javac *.java

3. Run the program:

      java Main

4. Follow the menu prompts to add flashcards, view them, or start a quiz session.

      Tip: You can leave the language filter blank to quiz all flashcards at once.


## 🖼️Sample Output


### Here’s what the program looks like in action:


<img width="338" height="411" alt="image" src="https://github.com/user-attachments/assets/f443957e-d3ee-4eea-a2df-40dfbf0ace74" />


<p align="center">
<b>Thank you!</b>
</p>






