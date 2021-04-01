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
 * Display all the vegetable names in a ListView.
 */
public class VegetablesActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter vegetablesWordAdapter;
    private final ArrayList<CategoryWord> vegetablesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeVegetablesData();
        initializeViews(vegetablesWordAdapter);
    }

    /**
     * Initialize the vegetables data.
     */
    private void initializeVegetablesData() {
        String[] englishTranslation = {"Vegetables", "Beetroot", "Bitter Gourd", "Bottle Gourd",
                "Brinjal (Eggplant)", "Cabbage", "Carrot", "Capsicum (Bell Pepper)", "Cauliflower",
                "Chilli", "Cluster Bean", "Coriander", "Corn", "Cucumber", "Garlic", "Ginger",
                "Green Onion (Scallion)", "Okra", "Onion", "Peas", "Potato", "Radish", "Spinach",
                "Sweet Potato", "Tomato"};

        String[] hindiTranslation = {"Sabzi", "Chukandar", "Karela", "Lauki", "Baingan", "Patta Gobhi",
                "Gajar", "Shimla Mirch", "Phool Gobhi", "Mirch", "Guar", "Dhaniya", "Makka", "Kheera",
                "Lahsun", "Adrak", "Hara Pyaz", "Bhindi", "Pyaj", "Matar", "Aloo", "Mooli", "Palak",
                "Shakarakand", "Tamatar"};

        String[] devanagariFonts = {"सब्ज़ी", "चुकंदर", "करेला", "लौकी", "बैंगन", "पत्तागोभी", "गाजर",
                "शिमला मिर्च", "फूल गोभी", "मिर्च", "ग्वार", "धनिया", "मक्का", "खीरा", "लहसुन", "अदरक",
                "हरा प्याज", "भिन्डी", "प्याज", "मटर", "आलू", "मूली", "पालक", "शकरकंद", "टमाटर"};

        int[] hindiPronunciations = {R.raw.vegetable_01, R.raw.vegetable_02, R.raw.vegetable_03,
                R.raw.vegetable_04, R.raw.vegetable_05, R.raw.vegetable_06, R.raw.vegetable_07,
                R.raw.vegetable_08, R.raw.vegetable_09, R.raw.vegetable_10, R.raw.vegetable_11,
                R.raw.vegetable_12, R.raw.vegetable_13, R.raw.vegetable_14, R.raw.vegetable_15,
                R.raw.vegetable_16, R.raw.vegetable_17, R.raw.vegetable_18, R.raw.vegetable_19,
                R.raw.vegetable_20, R.raw.vegetable_21, R.raw.vegetable_22, R.raw.vegetable_23,
                R.raw.vegetable_24, R.raw.vegetable_25};

        int[] wordImages = {R.drawable.ic_vegetables, R.drawable.ic_vegetable_beetroot,
                R.drawable.ic_vegetable_bitter_gourd, -1, R.drawable.ic_vegetable_brinjal,
                R.drawable.ic_vegetable_cabbage, R.drawable.ic_vegetable_carrot,
                R.drawable.ic_vegetable_capsicum, R.drawable.ic_vegetable_cauliflower,
                R.drawable.ic_vegetable_chilli, -1, R.drawable.ic_vegetable_coriander,
                R.drawable.ic_vegetable_corn, R.drawable.ic_vegetable_cucumber,
                R.drawable.ic_vegetable_garlic, R.drawable.ic_vegetable_ginger,
                R.drawable.ic_vegetable_green_onion, R.drawable.ic_vegetable_okra,
                R.drawable.ic_vegetable_onion, R.drawable.ic_vegetable_peas,
                R.drawable.ic_vegetable_potato, R.drawable.ic_vegetable_radish,
                R.drawable.ic_vegetable_spinach, R.drawable.ic_vegetable_sweet_potato,
                R.drawable.ic_vegetable_tomato};

        // Store the vegetables data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            vegetablesList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        vegetablesWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, vegetablesList);
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
                vegetablesWordAdapter.getFilter().filter(query);
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
                vegetablesWordAdapter.getFilter().filter(newText);
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