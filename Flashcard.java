// Flashcard.java
public abstract class Flashcard {
    private String word;
    private String meaning;
    private String language;
    private String category;
    private int wrongCount; // Higher = more priority in quizzes (simple spaced repetition heuristic)

    public Flashcard(String word, String meaning, String language, String category) {
        this.word = word;
        this.meaning = meaning;
        this.language = language;
        this.category = category;
        this.wrongCount = 0;
    }

    // Getters and Setters (Encapsulation)
    public String getWord() { return word; }
    public void setWord(String word) { this.word = word; }

    public String getMeaning() { return meaning; }
    public void setMeaning(String meaning) { this.meaning = meaning; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public int getWrongCount() { return wrongCount; }
    public void incrementWrongCount() { this.wrongCount++; }
    public void resetWrongCount() { this.wrongCount = 0; }

    // Abstract methods (Abstraction)
    public abstract void display();
    public abstract boolean quiz(String answer);

    @Override
    public String toString() {
        return String.format("Word: %s | Meaning: %s | Lang: %s | Category: %s | Priority: %d",
                word, meaning, language, category, wrongCount);
    }
}
