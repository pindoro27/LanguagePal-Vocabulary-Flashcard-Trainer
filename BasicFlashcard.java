
public class BasicFlashcard extends Flashcard {

    public BasicFlashcard(String word, String meaning, String language, String category) {
        super(word, meaning, language, category);
    }

    
    public void display() {
        System.out.println("===== Flashcard =====");
        System.out.println("Word     : " + getWord());
        System.out.println("Meaning  : " + getMeaning());
        System.out.println("Language : " + getLanguage());
        System.out.println("Category : " + getCategory());
        System.out.println("Priority : " + getWrongCount());
        System.out.println("---------------------");
    }

    
    public boolean quiz(String answer) {
        if (answer == null) return false;
        boolean correct = getMeaning().trim().equalsIgnoreCase(answer.trim());
        if (!correct) {
            incrementWrongCount(); 
        } else {
            resetWrongCount();
        }
        return correct;
    }
}
