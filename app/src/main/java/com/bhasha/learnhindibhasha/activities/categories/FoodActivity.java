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
 * Display all the food items in a ListView.
 */
public class FoodActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter foodWordAdapter;
    private final ArrayList<CategoryWord> foodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeFoodData();
        initializeViews(foodWordAdapter);
    }

    /**
     * Initialize the food data.
     */
    private void initializeFoodData() {
        String[] englishTranslation = {"Food", "How does it taste?", "It is sour.", "It is sweet.",
                "It is spicy.", "It is bitter.", "It is salty.", "Are you hungary?", "I am not hungary.",
                "I am really hungary.", "How is the food?", "The food is delicious.",
                "Have you had breakfast?", "I already ate.", "Can I get some water?",
                "I am a vegetarian.", "I am a non vegetarian.", "What is your favorite food?",
                "My favorite food is pizza."};

        String[] hindiTranslation = {"Khaana", "Iska svaad kaisa hai?", "Yeh khatta hai.",
                "Yeh meetha hai.", "Yeh teekha hai.", "Yeh kadava hai.", "Yeh namkeen hai.",
                "Kya aap ko bhookh lagi hai?", "Mujhe bhookh nahi hai.", "Mujhe bohat bhookh lagi hai.",
                "Khaana kaisa hai?", "Khaana svaadisht hai.", "Apne naashta kiya?", "Maine kha liya.",
                "Kya mujhe paani mil sakta hai.", "Main shakahari hoon.", "Main mansahari hoon.",
                "Aap ka pasandeeda khaana kya hai?", "Mera pasandeeda khaana pizza hai."};

        String[] devanagariFonts = {"खाना", "इसका स्वाद कैसा है?", "ये खट्टा है।", "ये मीठा है।", "ये तीखा है।",
                "ये कड़वा है।", "ये नमकीन है।", "क्या आपको भूख लगी है।", "मुझे भूख नही है।", "मुझे बोहत भूख लगी है।",
                "खाना कैसा है?", "खाना स्वादिष्ट है।", "आपने नाश्ता किया?", "मेंने खा लिया।", "क्या मुझे पानी मिल सकता है?",
                "मैं शाकाहारी हूं।", "मैं मांसाहारी हूं।", "आपका पसंदीदा खाना क्या है?", "मेरा पसंदीदा खाना pizza है।"};

        int[] hindiPronunciations = {R.raw.food_01, R.raw.food_02, R.raw.food_03, R.raw.food_04,
                R.raw.food_05, R.raw.food_06, R.raw.food_07, R.raw.food_08, R.raw.food_09,
                R.raw.food_10, R.raw.food_11, R.raw.food_12, R.raw.food_13, R.raw.food_14,
                R.raw.food_15, R.raw.food_16, R.raw.food_17, R.raw.food_18, R.raw.food_19};

        int[] wordImages = new int[19];

        Arrays.fill(wordImages, -1);

        // Store the food data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            foodList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        foodWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, foodList);
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
                foodWordAdapter.getFilter().filter(query);
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
                foodWordAdapter.getFilter().filter(newText);
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