package com.bhasha.learnhindibhasha.activities.categories;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import com.bhasha.learnhindibhasha.R;
import com.bhasha.learnhindibhasha.activities.home.HomeScreenActivity;
import com.bhasha.learnhindibhasha.adapters.CategoryWordAdapter;
import com.bhasha.learnhindibhasha.models.CategoryWord;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Display all the greeting items in a ListView.
 */
public class GreetingsActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter greetingsWordAdapter;
    private final ArrayList<CategoryWord> greetingsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeGreetingsData();
        initializeViews(greetingsWordAdapter);
    }

    /**
     * Initialize the greetings data.
     */
    private void initializeGreetingsData() {
        String[] englishTranslation = {"Greetings", "Hello", "Good morning", "Good afternoon",
                "Good evening", "Good night", "What is your name?", "My name is Salman.",
                "What's up?", "How are you?", "I am fine", "Nice to meet you.", "Where are you from?",
                "We are from India.", "I am from India.", "Thank you", "Sorry", "Please",
                "Congratulations", "Welcome", "Good bye", "Good luck", "Happy birthday"};

        String[] hindiTranslation = {"Shubhakaamanaen", "Namaste", "Shubh prabath", "Shubh divas",
                "Shubh sandhya", "Shubh ratri", "Aap ka naam kya hai?", "Mera naam Salman hai.",
                "Kya ho raha hai?", "Aap kaise hai?", "Main theek hoon.", "Aap se milkar accha laga.",
                "Aap kahan se hai?", "Hum India se hain.", "Main India se hoon.", "Dhanyavaad",
                "Maaf keejiye", "Krpaya", "Badhaee ho", "Svaagat hai", "Alavida",
                "Shubhakaamanaen", "Janmadin kee shubhakaamanaen"};

        String[] devanagariFonts = {"शुभकामनाएँ", "नमस्ते", "शुभ प्रभात", "शुभ दिवस", "शुभ संध्या",
                "शुभ रात्रि", "आपका नाम क्या है?", "मेरा नाम Salman है।", "क्या हो रहा है?", "आप कैसे है?",
                "मैं ठीक हूँ।", "आपसे मिलकर अच्छा लगा।", "आप कहां से है?", "हम India से हैं।",
                "मैं India से हूँ।", "धन्यवाद", "माफ़ कीजिये", "कृपया", "बधाई हो", "स्वागत है",
                "अलविदा", "शुभकामनाएँ", "जन्मदिन की शुभकामनाएं"};

        int[] hindiPronunciations = {R.raw.greeting_01, R.raw.greeting_02, R.raw.greeting_03,
                R.raw.greeting_04, R.raw.greeting_05, R.raw.greeting_06, R.raw.greeting_07,
                R.raw.greeting_08, R.raw.greeting_09, R.raw.greeting_10, R.raw.greeting_11,
                R.raw.greeting_12, R.raw.greeting_13, R.raw.greeting_14, R.raw.greeting_15,
                R.raw.greeting_16, R.raw.greeting_17, R.raw.greeting_18, R.raw.greeting_19,
                R.raw.greeting_20, R.raw.greeting_21, R.raw.greeting_01, R.raw.greeting_22};

        int[] wordImages = new int[23];
        Arrays.fill(wordImages, -1);

        // Store the greetings data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            greetingsList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        greetingsWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, greetingsList);
    }

    /**
     * Initialize the contents of the Activity's options menu.
     * @param menu      is the options menu in which the menu items are placed.
     * @return boolean  the value is true so that the menu can be displayed.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the search_menu from XML.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView.
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);

        // Listen for changes to the query text in the search menu and filter the query.
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             * Filter query when the user submits the query.
             * @param query     is the query text that is to be submitted.
             * @return boolean  the value is true so that the query can be handled by the listener.
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                greetingsWordAdapter.getFilter().filter(query);
                searchView.clearFocus();
                return true;
            }

            /**
             * Filter query when query text is changed by the user.
             * @param newText   is the new content of the query text field.
             * @return boolean  the value is false so that the SearchView can perform the default action.
             */
            @Override
            public boolean onQueryTextChange(String newText) {
                greetingsWordAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    /**
     * Handles user selected item from the search menu.
     * @param item  is the menu item that was selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Check if the search menu item is present.
        if (item.getItemId() == R.id.search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Close SearchView when the activity has detected the user's press of the back key.
     */
    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            searchView.clearFocus();
            searchView.onActionViewCollapsed();
            searchView.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        } else {
            super.onBackPressed();
        }
    }
}