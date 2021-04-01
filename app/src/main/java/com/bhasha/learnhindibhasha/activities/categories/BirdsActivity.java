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
 * Display all the birds names in a ListView.
 */
public class BirdsActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter birdsWordAdapter;
    private final ArrayList<CategoryWord> birdsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeBirdsData();
        initializeViews(birdsWordAdapter);
    }

    /**
     * Initialize the birds data.
     */
    private void initializeBirdsData() {
        String[] englishTranslation = {"Bird", "Crane", "Crow", "Duck", "Eagle", "Flamingo", "Hen", "Ostrich",
                "Owl", "Parrot", "Peacock", "Pigeon", "Swan", "Woodpecker"};

        String[] hindiTranslation = {"Panchee", "Saaras", "Kauvaa", "Batakh", "Cheel", "Raajahans", "Murgee",
                "Shutaramurg", "Ullu", "Tota", "Mor", "Kabootar", "Hans", "Kathaphodava"};

        String[] devanagariFonts = {"पंछी", "सारस", "कौवा", "बत्तख", "चील", "राजहंस", "मुर्गी", "शुतुरमुर्ग", "उल्लू",
                "तोता", "मोर", "कबूतर", "हंस", "कठफोड़वा"};

        int[] hindiPronunciations = {R.raw.bird, R.raw.bird_crane, R.raw.bird_crow, R.raw.bird_duck, R.raw.bird_eagle,
                R.raw.bird_flamingo, R.raw.bird_hen, R.raw.bird_ostrich, R.raw.bird_owl, R.raw.bird_parrot,
                R.raw.bird_peacock, R.raw.bird_pigeon, R.raw.bird_swan, R.raw.bird_woodpecker};

        int[] wordImages = {R.drawable.ic_bird, R.drawable.ic_bird_crane, R.drawable.ic_bird_crow,
                R.drawable.ic_bird_duck, R.drawable.ic_bird_eagle, R.drawable.ic_bird_flamingo,
                R.drawable.ic_bird_hen, R.drawable.ic_bird_ostrich, R.drawable.ic_bird_owl,
                R.drawable.ic_bird_parrot, R.drawable.ic_bird_peacock, R.drawable.ic_bird_pigeon,
                R.drawable.ic_bird_swan, R.drawable.ic_bird_woodpecker};

        // Store the birds data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            birdsList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        birdsWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, birdsList);
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
                birdsWordAdapter.getFilter().filter(query);
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
                birdsWordAdapter.getFilter().filter(newText);
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