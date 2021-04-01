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
 * Display all the direction items in a ListView.
 */
public class DirectionsActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter directionsWordAdapter;
    private final ArrayList<CategoryWord> directionsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeDirectionsData();
        initializeViews(directionsWordAdapter);
    }

    /**
     * Initialize the directions data.
     */
    private void initializeDirectionsData() {
        String[] englishTranslation = {"Direction", "East", "West", "North", "South", "Left", "Right",
                "Top", "Bottom", "Ahead", "Behind", "Inside", "Outside", "Straight", "Fast", "Slow",
                "Nearby", "Here", "There", "Turn around", "Turn left", "Turn right", "Opposite"};

        String[] hindiTranslation = {"Disha", "Poorv", "Pashchim", "Uttar", "Dakshin", "Bayein",
                "Daayein", "Oopar", "Neeche", "Aage", "Peechhe", "Andar", "Baahar", "Seedhe",
                "Tej", "Dheere", "Aas paas", "Yahaan", "Vahaan", "Mudo", "Bayein Mudiye",
                "Daayein mudiye", "Saamane"};

        String[] devanagariFonts = {"दिशा", "पूर्व", "पश्चिम", "उत्तर", "दक्षिण", "बाएं", "दाएं", "ऊपर", "नीचे",
                "आगे", "पीछे", "अंदर", "बाहर", "सीधे", "तेज", "धीरे", "आस पास", "यहां", "वहाँ", "मुड़ो",
                "बांए मुड़िए", "दांए मुड़िए", "सामने"};

        int[] hindiPronunciations = {R.raw.direction_01, R.raw.direction_02, R.raw.direction_03,
                R.raw.direction_04, R.raw.direction_05, R.raw.direction_06, R.raw.direction_07,
                R.raw.direction_08, R.raw.direction_09, R.raw.direction_10, R.raw.direction_11,
                R.raw.direction_12, R.raw.direction_13, R.raw.direction_14, R.raw.direction_15,
                R.raw.direction_16, R.raw.direction_17, R.raw.direction_18, R.raw.direction_19,
                R.raw.direction_20, R.raw.direction_21, R.raw.direction_22, R.raw.direction_23};

        int[] wordImages = {R.drawable.ic_direction, R.drawable.ic_direction_east,
                R.drawable.ic_direction_west, R.drawable.ic_direction_north, R.drawable.ic_direction_south,
                R.drawable.ic_direction_left, R.drawable.ic_direction_right, R.drawable.ic_direction_up,
                R.drawable.ic_direction_down, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
                R.drawable.ic_direction_turn_left, R.drawable.ic_direction_turn_right, -1};

        // Store the directions data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            directionsList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        directionsWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, directionsList);
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
                directionsWordAdapter.getFilter().filter(query);
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
                directionsWordAdapter.getFilter().filter(newText);
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