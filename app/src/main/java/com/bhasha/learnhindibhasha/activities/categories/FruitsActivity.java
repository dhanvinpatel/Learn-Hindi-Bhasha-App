package com.bhasha.learnhindibhasha.activities.categories;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import com.bhasha.learnhindibhasha.activities.home.HomeScreenActivity;
import com.bhasha.learnhindibhasha.models.CategoryWord;
import com.bhasha.learnhindibhasha.adapters.CategoryWordAdapter;
import com.bhasha.learnhindibhasha.R;
import java.util.ArrayList;

/**
 * Display all the fruit names in a ListView.
 */
public class FruitsActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter fruitsWordAdapter;
    private final ArrayList<CategoryWord> fruitsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeFruitsData();
        initializeViews(fruitsWordAdapter);
    }

    /**
     * Initialize the fruits data.
     */
    private void initializeFruitsData() {
        String[] englishTranslation = {"Fruit", "Apple", "Apricot", "Banana", "Cherry", "Coconut",
                "Custard-Apple", "Grape", "Jackfruit", "Lemon", "Mango", "Orange", "Papaya",
                "Pear", "Pineapple", "Pomegranate", "Strawberry", "Sugarcane", "Tamarind",
                "Watermelon", "Almond", "Cashew", "Dates", "Peanut", "Pistachio", "Walnut"};

        String[] hindiTranslation = {"Phal", "Seb", "Khubaani", "Kela", "Cherry", "Nariyal",
                "Sitaphal", "Angoor", "Katahal", "Nimbu", "Aam", "Santra", "Papeeta", "Nashpati",
                "Ananas", "Anaar", "Strawberry", "Ganna", "Imli", "Tarbooj", "Badam", "Kaju",
                "Khajoor", "Moongfali", "Pista", "Akhrot"};

        String[] devanagariFonts = {"फल", "सेब", "खुबानी", "केला", "चेरी", "नारियल", "सीताफल", "अंगूर",
                "कटहल", "नीम्बू", "आम", "संतरा", "पपीता", "नाशपाती", "अनानास", "अनार", "स्ट्रॉबेरी", "गन्ना",
                "इमली", "तरबूज", "बादाम", "काजू", "खजूर", "मूंगफली", "पिस्ता", "अखरोट"};

        int[] hindiPronunciations = {R.raw.fruit, R.raw.fruit_apple, R.raw.fruit_apricot,
                R.raw.fruit_banana, R.raw.fruit_cherry, R.raw.fruit_coconut, R.raw.fruit_custard_apple,
                R.raw.fruit_grape, R.raw.fruit_jackfruit, R.raw.fruit_lemon, R.raw.fruit_mango,
                R.raw.fruit_orange, R.raw.fruit_papaya, R.raw.fruit_pear, R.raw.fruit_pineapple,
                R.raw.fruit_pomegranate, R.raw.fruit_strawberry, R.raw.fruit_sugarcane,
                R.raw.fruit_tamarind, R.raw.fruit_watermelon, R.raw.fruit_almond, R.raw.fruit_cashew,
                R.raw.fruit_dates, R.raw.fruit_peanut, R.raw.fruit_pistachio, R.raw.fruit_walnut};

        int[] wordImages = {R.drawable.ic_fruits, R.drawable.ic_fruit_apple, R.drawable.ic_fruit_apricot,
                R.drawable.ic_fruit_banana, R.drawable.ic_fruit_cherry, R.drawable.ic_fruit_coconut,
                R.drawable.ic_fruit_custard_apple, R.drawable.ic_fruit_grape, R.drawable.ic_fruit_jackfruit,
                R.drawable.ic_fruit_lemon, R.drawable.ic_fruit_mango, R.drawable.ic_fruit_orange,
                R.drawable.ic_fruit_papaya, R.drawable.ic_fruit_pear, R.drawable.ic_fruit_pineapple,
                R.drawable.ic_fruit_pomegranate, R.drawable.ic_fruit_strawberry, R.drawable.ic_fruit_sugarcane,
                R.drawable.ic_fruit_tamarind, R.drawable.ic_fruit_watermelon, R.drawable.ic_fruit_almond,
                R.drawable.ic_fruit_cashew, R.drawable.ic_fruit_dates, R.drawable.ic_fruit_peanut,
                R.drawable.ic_fruit_pistachio, R.drawable.ic_fruit_walnut};

        // Store the fruits data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            fruitsList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        fruitsWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, fruitsList);
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
                fruitsWordAdapter.getFilter().filter(query);
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
                fruitsWordAdapter.getFilter().filter(newText);
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