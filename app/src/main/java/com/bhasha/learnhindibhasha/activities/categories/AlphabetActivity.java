package com.bhasha.learnhindibhasha.activities.categories;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import com.bhasha.learnhindibhasha.adapters.AlphabetAdapter;
import com.bhasha.learnhindibhasha.models.CategoryWord;
import com.bhasha.learnhindibhasha.R;
import java.util.ArrayList;

/**
 * Display all the alphabets in a GridView.
 */
public class AlphabetActivity extends AppCompatActivity {
    private AlphabetAdapter alphabetAdapter;
    private final ArrayList<CategoryWord> alphabetList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        initializeAlphabetData();

        // Find the alphabet GridView and pass the alphabetAdapter to alphabetGridView to display the alphabets.
        GridView alphabetGridView = (GridView) findViewById(R.id.alphabetGridView);
        alphabetGridView.setAdapter(alphabetAdapter);
    }

    /**
     * Initialize the alphabet data.
     */
    private void initializeAlphabetData() {
        String[] devanagariFonts = {"अ", "आ", "इ", "ई", "उ", "ऊ", "ए", "ऐ", "ओ", "औ", "अं", "अः",
                "ऋ", "क", "ख", "ग", "घ", "ङ", "च", "छ", "ज", "झ", "ञ", "ट", "ठ", "ड", "ढ", "ण",
                "त", "थ", "द", "ध", "न", "प", "फ", "ब", "भ", "म", "य", "र", "ल", "व", "श", "ष",
                "स", "ह", "क्ष", "त्र", "ज्ञ"};

        String[] englishTranslation = {"a", "ā\n(aa)", "i", "ī\n(ii)", "u", "ū\n(uu)", "e", "ai",
                "o", "au", "aṅ", "aḥ", "ṛ", "ka", "kha", "ga", "gha", "ṅa", "ca", "cha", "ja", "jha",
                "ña", "ṭa", "ṭha", "ḍa", "ḍha", "ṇa", "ta", "tha", "da", "dha", "na", "pa", "pha",
                "ba", "bha", "ma", "ya", "ra", "la", "va", "śa", "ṣa", "sa", "ha", "ksa", "tra", "gya"};

        int[] hindiPronunciations = {R.raw.alphabet_01, R.raw.alphabet_02, R.raw.alphabet_03,
                R.raw.alphabet_04, R.raw.alphabet_05, R.raw.alphabet_06, R.raw.alphabet_07,
                R.raw.alphabet_08, R.raw.alphabet_09, R.raw.alphabet_10, R.raw.alphabet_11,
                R.raw.alphabet_12, R.raw.alphabet_13, R.raw.alphabet_14, R.raw.alphabet_15,
                R.raw.alphabet_16, R.raw.alphabet_17, R.raw.alphabet_18, R.raw.alphabet_19,
                R.raw.alphabet_20, R.raw.alphabet_21, R.raw.alphabet_22, R.raw.alphabet_23,
                R.raw.alphabet_24, R.raw.alphabet_25, R.raw.alphabet_26, R.raw.alphabet_27,
                R.raw.alphabet_28, R.raw.alphabet_29, R.raw.alphabet_30, R.raw.alphabet_31,
                R.raw.alphabet_32, R.raw.alphabet_33, R.raw.alphabet_34, R.raw.alphabet_35,
                R.raw.alphabet_36, R.raw.alphabet_37, R.raw.alphabet_38, R.raw.alphabet_39,
                R.raw.alphabet_40, R.raw.alphabet_41, R.raw.alphabet_42, R.raw.alphabet_43,
                R.raw.alphabet_44, R.raw.alphabet_45, R.raw.alphabet_46, R.raw.alphabet_47,
                R.raw.alphabet_48, R.raw.alphabet_49};

        // Store the alphabet data in an ArrayList.
        for (int i = 0; i < devanagariFonts.length; i++) {
            alphabetList.add(new CategoryWord(devanagariFonts[i], englishTranslation[i], hindiPronunciations[i]));
        }

        // Create AlphabetAdapter objects.
        alphabetAdapter = new AlphabetAdapter(this, R.layout.item_alphabet, alphabetList);
    }
}