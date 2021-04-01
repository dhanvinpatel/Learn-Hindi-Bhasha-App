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

/**
 * Display all the animals names in a ListView.
 */
public class AnimalsActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter animalsWordAdapter;
    private final ArrayList<CategoryWord> animalsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeAnimalsData();
        initializeViews(animalsWordAdapter);
    }

    /**
     * Initialize the animals data.
     */
    private void initializeAnimalsData() {
        String[] englishTranslation = {"Ant", "Bear", "Buffalo", "Butterfly", "Camel", "Cat",
                "Cow", "Crab", "Crocodile", "Deer", "Dog", "Donkey", "Elephant", "Frog", "Giraffe",
                "Goat", "Horse", "Lion", "Monkey", "Mouse (Rat)", "Pig", "Rabbit", "Sheep", "Snake",
                "Spider", "Tiger", "Turtle", "Zebra"};

        String[] hindiTranslation = {"Cheentee", "Bhaloo", "Bhainsa", "Titalee", "Oont", "Billee",
                "Gaay", "Kekada", "Magaramachchh", "Hiran", "Kuttaa", "Gadhaa", "Hathee", "Mendak",
                "Giraffe", "Bakari", "Ghoda", "Sher", "Bandar", "Chooha", "Suar", "Khargosh", "Bhed",
                "Saanp", "Makadi", "Baagh", "Kachhuaa", "Zebra"};

        String[] devanagariFonts = {"चींटी", "भालू", "भैंसा", "तितली", "ऊंट", "बिल्ली", "गाय", "केकड़ा",
                "मगरमच्छ", "हिरन", "कुत्ता", "गधा", "हाथी", "मेंढक", "जिराफ़", "बकरी", "घोडा", "शेर", "बंदर",
                "चूहा", "सूअर", "खरगोश", "भेड़", "साँप", "मकडी", "बाघ", "कछुआ", "ज़ेबरा"};

        int[] hindiPronunciations = {R.raw.animal_ant, R.raw.animal_bear, R.raw.animal_buffalo,
                R.raw.animal_butterfly, R.raw.animal_camel, R.raw.animal_cat, R.raw.animal_cow,
                R.raw.animal_crab, R.raw.animal_crocodile, R.raw.animal_deer, R.raw.animal_dog,
                R.raw.animal_donkey, R.raw.animal_elephant, R.raw.animal_frog, R.raw.animal_giraffe,
                R.raw.animal_goat, R.raw.animal_horse, R.raw.animal_lion, R.raw.animal_monkey,
                R.raw.animal_rat, R.raw.animal_pig, R.raw.animal_rabbit, R.raw.animal_sheep,
                R.raw.animal_snake, R.raw.animal_spider, R.raw.animal_tiger, R.raw.animal_turtle,
                R.raw.animal_zebra};

        int[] wordImages = {R.drawable.ic_animal_ant, R.drawable.ic_animal_bear,
                R.drawable.ic_animal_buffalo, R.drawable.ic_animal_butterfly, R.drawable.ic_animal_camel,
                R.drawable.ic_animal_cat, R.drawable.ic_animal_cow, R.drawable.ic_animal_crab,
                R.drawable.ic_animal_crocodile, R.drawable.ic_animal_deer, R.drawable.ic_animal_dog,
                R.drawable.ic_animal_donkey, R.drawable.ic_animal_elephant, R.drawable.ic_animal_frog,
                R.drawable.ic_animal_giraffe, R.drawable.ic_animal_goat, R.drawable.ic_animal_horse,
                R.drawable.ic_lion, R.drawable.ic_animal_monkey, R.drawable.ic_animal_rat,
                R.drawable.ic_animal_pig, R.drawable.ic_animal_rabbit, R.drawable.ic_animal_sheep,
                R.drawable.ic_animal_snake, R.drawable.ic_animal_spider, R.drawable.ic_animal_tiger,
                R.drawable.ic_animal_turtle, R.drawable.ic_animal_zebra};

        // Store the animals data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            animalsList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        animalsWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, animalsList);
    }

    /**
     * Initialize the contents of the Activity's options menu.
     *
     * @param menu is the options menu in which the menu items are placed.
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
                animalsWordAdapter.getFilter().filter(query);
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
                animalsWordAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    /**
     * Handles user selected item from the search menu.
     *
     * @param item is the menu item that was selected.
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