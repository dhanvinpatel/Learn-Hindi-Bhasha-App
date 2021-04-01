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
 * Display all the colour items in a ListView.
 */
public class ColoursActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter coloursWordAdapter;
    private final ArrayList<CategoryWord> coloursList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeColoursData();
        initializeViews(coloursWordAdapter);
    }

    /**
     * Initialize the colours data.
     */
    private void initializeColoursData() {
        String[] englishTranslation = {"Colour", "Orange", "Blue", "Green", "Red", "Yellow", "Black",
                "White", "Pink", "Purple", "Brown", "What is your favorite colour?",
                "My favorite colour is blue."};

        String[] hindiTranslation = {"Rang", "Narangi", "Neela", "Hara", "Laal", "Peela", "Kaala",
                "Safed", "Gulabi", "Baingani", "Bhoora", "Aapka pasandeeda rang kaunsa hai?",
                "Mera pasandeeda rang neela hai."};

        String[] devanagariFonts = {"रंग", "नारंगी", "नीला", "हरा", "लाल", "पीला", "काला", "सफेद",
                "गुलाबी", "बैंगनी", "भूरा", "आपका पसंदीदा रंग कौन सा है।", "मेरा पसंदीदा रंग नीला है।"};

        int[] hindiPronunciations = {R.raw.colour, R.raw.colour_orange, R.raw.colour_blue,
                R.raw.colour_green, R.raw.colour_red, R.raw.colour_yellow, R.raw.colour_black,
                R.raw.colour_white, R.raw.colour_pink, R.raw.colour_purple, R.raw.colour_brown,
                R.raw.colour_favorite, R.raw.colour_favorite_blue};

        int[] wordImages = {R.drawable.ic_colour_wheel, R.drawable.ic_colour_orange, R.drawable.ic_colour_blue,
                R.drawable.ic_colour_green, R.drawable.ic_colour_red, R.drawable.ic_colour_yellow,
                R.drawable.ic_colour_black, R.drawable.ic_colour_white, R.drawable.ic_colour_pink,
                R.drawable.ic_colour_purple, R.drawable.ic_colour_brown, -1, -1};

        // Store the colours data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            coloursList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        coloursWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, coloursList);
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
                coloursWordAdapter.getFilter().filter(query);
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
                coloursWordAdapter.getFilter().filter(newText);
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