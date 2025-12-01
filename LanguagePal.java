
import java.util.Scanner;

public class LanguagePal {
    private Flashcard[] cards;
    private int count;
    private Scanner scanner;
    private final int MAX_CARDS = 300;

    public LanguagePal() {
        cards = new Flashcard[MAX_CARDS];
        count = 0;
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("=== Welcome to LanguagePal: Vocabulary Flashcard Trainer ===");
        boolean running = true;

        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            try {
                switch (choice) {
                    case "1": addFlashcard(); break;
                    case "2": viewAll(); break;
                    case "3": editFlashcard(); break;
                    case "4": deleteFlashcard(); break;
                    case "5": quizSession(); break;
                    case "6": running = false; System.out.println("Goodbye! Keep learning!"); break;
                    default: throw new InvalidInput("Choose a valid option (1–6).");
                }
            } catch (InvalidInput e) {
                System.out.println("Input Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected Error: " + e.getMessage());
            }
        }
    }

    private void showMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Flashcard");
        System.out.println("2. View All Flashcards");
        System.out.println("3. Edit Flashcard");
        System.out.println("4. Delete Flashcard");
        System.out.println("5. Start Quiz Session");
        System.out.println("6. Exit");
        System.out.print("Enter choice (1-6): ");
    }

    private void addFlashcard() throws InvalidInput {
        if (count >= MAX_CARDS)
            throw new InvalidInput("Flashcard limit reached.");

        System.out.print("Word: ");
        String word = scanner.nextLine().trim();
        System.out.print("Meaning: ");
        String meaning = scanner.nextLine().trim();
        System.out.print("Language: ");
        String language = scanner.nextLine().trim();
        System.out.print("Category: ");
        String category = scanner.nextLine().trim();

        if (word.isEmpty() || meaning.isEmpty())
            throw new InvalidInput("Word and meaning cannot be empty.");

        cards[count++] = new BasicFlashcard(word, meaning, language, category);
        System.out.println("Flashcard added successfully!");
    }

    private void viewAll() {
        if (count == 0) {
            System.out.println("No flashcards yet.");
            return;
        }
        System.out.println("\n=== All Flashcards ===");
        for (int i = 0; i < count; i++) {
            System.out.printf("%d) %s\n", i + 1, cards[i].toString());
        }
    }

    private void editFlashcard() throws InvalidInput {
        if (count == 0) throw new InvalidInput("No flashcards available.");
        viewAll();
        System.out.print("Select flashcard number to edit: ");
        int idx = parseIndex(scanner.nextLine());
        if (idx < 0 || idx >= count) throw new InvalidInput("Invalid index.");

        Flashcard f = cards[idx];
        System.out.println("Editing: " + f.getWord());
        System.out.print("New Word (blank to keep): ");
        String w = scanner.nextLine();
        if (!w.trim().isEmpty()) f.setWord(w.trim());


        System.out.print("New Meaning (blank to keep): ");
        String m = scanner.nextLine();
        if (!m.trim().isEmpty()) f.setMeaning(m.trim());

        System.out.print("New Language (blank to keep): ");
        String l = scanner.nextLine();
        if (!l.trim().isEmpty()) f.setLanguage(l.trim());

        System.out.print("New Category (blank to keep): ");
        String c = scanner.nextLine();
        if (!c.trim().isEmpty()) f.setCategory(c.trim());
        System.out.println("Flashcard updated!");
    }

    private void deleteFlashcard() throws InvalidInput {
        if (count == 0) throw new InvalidInput("No flashcards available.");
        viewAll();
        System.out.print("Select flashcard number to delete: ");
        int idx = parseIndex(scanner.nextLine());
        if (idx < 0 || idx >= count) throw new InvalidInput("Invalid index.");

        for (int i = idx; i < count - 1; i++) {
            cards[i] = cards[i + 1];
        }
        cards[--count] = null;
        System.out.println("Flashcard deleted!");
    }

    private void quizSession() throws InvalidInput {
        if (count == 0) throw new InvalidInput("No flashcards to quiz.");
        System.out.print("Limit quiz to one language? (leave blank for all): ");
        String langFilter = scanner.nextLine().trim();

        Flashcard[] pool = buildPool(langFilter);
        if (pool.length == 0) {
            System.out.println("No flashcards match that language.");
            return;
        }

        System.out.print("How many questions? ");
        int num;
        try {
            num = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            throw new InvalidInput("Please enter a valid number.");
        }
        if (num <= 0) throw new InvalidInput("Number must be greater than zero.");

        int correct = 0;
        for (int i = 0; i < pool.length && i < num; i++) {
            Flashcard f = pool[i];
            System.out.println("\nDefine: " + f.getWord());
            System.out.print("Your answer: ");
            String ans = scanner.nextLine();
            boolean result = f.quiz(ans);
            if (result) {
                System.out.println("Correct!");
                correct++;
            } else {
                System.out.println("Incorrect. Meaning: " + f.getMeaning());
            }
        }
        System.out.println("\nQuiz complete! You scored " + correct + "/" + num);
    }

    private Flashcard[] buildPool(String langFilter) {
        int matches = 0;
        for (int i = 0; i < count; i++) {
            if (langFilter.isEmpty() || cards[i].getLanguage().equalsIgnoreCase(langFilter))
                matches++;
        }

        Flashcard[] pool = new Flashcard[matches];
        int idx = 0;
        for (int i = 0; i < count; i++) {
            if (langFilter.isEmpty() || cards[i].getLanguage().equalsIgnoreCase(langFilter))
                pool[idx++] = cards[i];
        }

        
        for (int i = 0; i < pool.length - 1; i++) {
            for (int j = 0; j < pool.length - i - 1; j++) {
                if (pool[j].getWrongCount() < pool[j + 1].getWrongCount()) {
                    Flashcard temp = pool[j];
                    pool[j] = pool[j + 1];
                    pool[j + 1] = temp;
                }
            }
        }
        return pool;
    }

    private int parseIndex(String raw) {
        try {
            return Integer.parseInt(raw.trim()) - 1;
        } catch (Exception e) {
            return -1;
        }
    }

    // 🔹 Multilingual Flashcards
    public void seedSample() {
        // Spanish
        cards[count++] = new BasicFlashcard("Hola", "Hello", "Spanish", "Greetings");
        cards[count++] = new BasicFlashcard("Gracias", "Thank you", "Spanish", "Politeness");
        cards[count++] = new BasicFlashcard("Libro", "Book", "Spanish", "Nouns");
        cards[count++] = new BasicFlashcard("Adiós", "Goodbye", "Spanish", "Farewell");

        // Tagalog
        cards[count++] = new BasicFlashcard("Kumusta", "How are you", "Tagalog", "Greetings");
        cards[count++] = new BasicFlashcard("Salamat", "Thank you", "Tagalog", "Politeness");
        cards[count++] = new BasicFlashcard("Paalam", "Goodbye", "Tagalog", "Farewell");
        cards[count++] = new BasicFlashcard("Kaibigan", "Friend", "Tagalog", "Nouns");

        // French
        cards[count++] = new BasicFlashcard("Bonjour", "Good morning / Hello", "French", "Greetings");
        cards[count++] = new BasicFlashcard("Merci", "Thank you", "French", "Politeness");
        cards[count++] = new BasicFlashcard("Livre", "Book", "French", "Nouns");
        cards[count++] = new BasicFlashcard("Au revoir", "Goodbye", "French", "Farewell");

        // Japanese
        cards[count++] = new BasicFlashcard("Konnichiwa", "Hello / Good afternoon", "Japanese", "Greetings");
        cards[count++] = new BasicFlashcard("Arigatou", "Thank you", "Japanese", "Politeness");
        cards[count++] = new BasicFlashcard("Sayonara", "Goodbye", "Japanese", "Farewell");
        cards[count++] = new BasicFlashcard("Hon", "Book", "Japanese", "Nouns");

        // Korean
        cards[count++] = new BasicFlashcard("Annyeonghaseyo", "Hello", "Korean", "Greetings");
        cards[count++] = new BasicFlashcard("Gamsahamnida", "Thank you", "Korean", "Politeness");
        cards[count++] = new BasicFlashcard("Annyeong", "Goodbye", "Korean", "Farewell");
        cards[count++] = new BasicFlashcard("Chingu", "Friend", "Korean", "Nouns");

        // Italian
        cards[count++] = new BasicFlashcard("Ciao", "Hello / Goodbye", "Italian", "Greetings");
        cards[count++] = new BasicFlashcard("Grazie", "Thank you", "Italian", "Politeness");
        cards[count++] = new BasicFlashcard("Libro", "Book", "Italian", "Nouns");
        cards[count++] = new BasicFlashcard("Amico", "Friend", "Italian", "Nouns");

        // German
        cards[count++] = new BasicFlashcard("Hallo", "Hello", "German", "Greetings");
        cards[count++] = new BasicFlashcard("Danke", "Thank you", "German", "Politeness");
        cards[count++] = new BasicFlashcard("Buch", "Book", "German", "Nouns");
        cards[count++] = new BasicFlashcard("Auf Wiedersehen", "Goodbye", "German", "Farewell");
    }
}
