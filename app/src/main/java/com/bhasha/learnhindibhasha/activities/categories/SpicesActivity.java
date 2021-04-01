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
 * Display all the spices names in a ListView.
 */
public class SpicesActivity extends HomeScreenActivity {

    private SearchView searchView;
    private CategoryWordAdapter spicesWordAdapter;
    private ArrayList<CategoryWord> spicesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeSpicesData();
        initializeViews(spicesWordAdapter);
    }

    /**
     * Initialize the spices data.
     */
    private void initializeSpicesData() {
        String[] englishTranslation = {"Spice", "Asafoetida", "Basil", "Bay Leaf", "Black Pepper",
                    "Cardamom", "Chilli Powder", "Cinnamon", "Clove", "Coriander Powder", "Cumin",
                    "Curry Leaves", "Dry Ginger Powder", "Fenufreek", "Jaggery", "Mint", "Mustard",
                    "Rock Salt", "Saffron", "Salt", "Sesame", "Sugar", "Turmeric"};

        String[] hindiTranslation = {"Masala", "Heeng", "Tulsi", "Tej Patta", "Kali Mirch", "Elaichi",
                    "Laal Mirch Powder", "Dalchini", "Laung (Lavang)", "Dhaniya Powder", "Jeera",
                    "Kari Patte", "Sonth", "Methi", "Gud", "Pudina", "Sarson", "Sendha Namak",
                    "Kesar", "Namak", "Til", "Chini", "Haldi"};

        String[] devanagariFonts = {"मसाला", "हिंग", "तुलसी", "तेज पत्ता", "काली मिर्च", "इलायची",
                "लाल मिर्च पाउडर", "दालचीनी", "लौंग", "धनिया पाउडर", "जीरा", "करी पत्ते", "सोंठ", "मेथी",
                "गुड़", "पुदीना", "सरसों", "सेंधा नमक", "केसर", "नमक", "तिल", "चीनी", "हल्दी"};

        int[] hindiPronunciations = {R.raw.spice_01, R.raw.spice_02, R.raw.spice_03,
                R.raw.spice_04, R.raw.spice_05, R.raw.spice_06, R.raw.spice_07, R.raw.spice_08,
                R.raw.spice_09, R.raw.spice_10, R.raw.spice_11, R.raw.spice_12, R.raw.spice_13,
                R.raw.spice_14, R.raw.spice_15, R.raw.spice_16, R.raw.spice_17, R.raw.spice_18,
                R.raw.spice_19, R.raw.spice_20, R.raw.spice_21, R.raw.spice_22, R.raw.spice_23};

        int[] wordImages = new int[23];
        Arrays.fill(wordImages, -1);

        // Store the spices data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            spicesList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        spicesWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, spicesList);
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
                spicesWordAdapter.getFilter().filter(query);
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
                spicesWordAdapter.getFilter().filter(newText);
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