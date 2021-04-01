package com.bhasha.learnhindibhasha.models;

/**
 * CategoryWord class represents the English and Hindi translation as well as the
 * Devanagari font and the pronunciation of a word.
 */
public class CategoryWord {
    // English translation of the word.
    private String englishWord;

    // Hindi translation of the word.
    private String hindiWord;

    // Devanagari font of the word.
    private String devanagariFont;

    // Hindi pronunciation resource id of the word.
    private int pronunciationResourceId;

    private static final int IMAGE_NOT_PRESENT = -1;

    // Image resource id for the word.
    private int imageResourceId = IMAGE_NOT_PRESENT;

    /**
     * Create object of the CategoryWord class.
     *
     * @param englishWord               is the English translation of the word.
     * @param hindiWord                 is the Hindi translation of the word.
     * @param devanagariFont            is the Devanagari font of the word.
     * @param pronunciationResourceId   is the Hindi pronunciation of the word.
     */
    public CategoryWord(String englishWord, String hindiWord, String devanagariFont,
                        int pronunciationResourceId) {
        this.englishWord = englishWord;
        this.hindiWord = hindiWord;
        this.devanagariFont = devanagariFont;
        this.pronunciationResourceId = pronunciationResourceId;
    }

    /**
     * Create object of the CategoryWord class.
     *
     * @param englishWord               is the English translation of the word.
     * @param hindiWord                 is the Hindi translation of the word.
     * @param devanagariFont            is the Devanagari font of the word.
     * @param pronunciationResourceId   is the Hindi pronunciation of the word.
     */
    public CategoryWord(String englishWord, String hindiWord, String devanagariFont,
                        int pronunciationResourceId, int imageResourceId) {
        this.englishWord = englishWord;
        this.hindiWord = hindiWord;
        this.devanagariFont = devanagariFont;
        this.pronunciationResourceId = pronunciationResourceId;
        this.imageResourceId = imageResourceId;
    }

    /**
     * Create object of the CategoryWord class.
     *
     * @param devanagariFont            is the Devanagari font of the word.
     * @param hindiWord                 is the Hindi translation of the word.
     * @param pronunciationResourceId   is the Hindi pronunciation of the word.
     */
    public CategoryWord(String devanagariFont, String hindiWord, int pronunciationResourceId) {
        this.devanagariFont = devanagariFont;
        this.hindiWord = hindiWord;
        this.pronunciationResourceId = pronunciationResourceId;
    }

    /**
     * Get the English translation of the word.
     *
     * @return String   the English translation.
     */
    public String getEnglishWord() {
        return this.englishWord;
    }

    /**
     * Get the Hindi translation of the word.
     *
     * @return String   the Hindi translation.
     */
    public String getHindiWord() {
        return this.hindiWord;
    }

    /**
     * Get the Devanagari font of the word.
     *
     * @return String   the Devanagari font.
     */
    public String getDevanagariFont() {
        return this.devanagariFont;
    }

    /**
     * Get the Hindi pronunciation of the word.
     *
     * @return int   the Hindi pronunciation.
     */
    public int getPronunciationResourceId() {
        return this.pronunciationResourceId;
    }

    /**
     * Get the image resource id of the word.
     *
     * @return int  the image resource id.
     */
    public int getImageResourceId() {
        return this.imageResourceId;
    }

    /**
     * Check if an image is present.
     *
     * @return boolean  the value is true if image is present otherwise false.
     */
    public boolean imagePresent() {
        return this.imageResourceId != IMAGE_NOT_PRESENT;
    }
}
