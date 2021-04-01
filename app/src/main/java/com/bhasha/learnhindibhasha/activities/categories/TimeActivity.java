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
 * Display all the time items in a ListView.
 */
public class TimeActivity extends HomeScreenActivity {

    private SearchView searchView;
    private CategoryWordAdapter timeWordAdapter;
    private ArrayList<CategoryWord> timeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeTimeData();
        initializeViews(timeWordAdapter);
    }

    /**
     * Initialize the time data.
     */
    private void initializeTimeData() {
        String[] englishTranslation = {"Time", "What time is it?", "It is three o'clock.", "Clock",
                "1 o'clock", "2 o'clock", "3 o'clock", "4 o'clock", "5 o'clock", "6 o'clock",
                "7 o'clock", "8 o'clock", "9 o'clock", "10 o'clock", "11 o'clock", "12 o'clock",
                "12:30", "1:30", "2:30", "Quarter to two", "Quarter past two", ""};

        String[] hindiTranslation = {"Samay", "Kya samay hua hai?", "Teen baje hain.", "Ghadee",
                "Ek baje", "Do baje", "Teen baje", "Chaar baje", "Paanch baje", "Chhah baje",
                "Saat baje", "Aath baje", "Nau baje", "Das baje", "Gyaarah baje", "Baarah baje",
                "Sade baarah", "Dedh", "Dhaee", "Paune do", "Sava do"};

        String[] devanagariFonts = {"समय", "क्या समय हुआ है?", "तीन बजे हैं.", "घड़ी", "एक बजे",
                "दो बजे", "तीन बजे", "चार बजे", "पांच बजे", "छह बजे", "सात बजे", "आठ बजे", "नौ बजे",
                "दस बजे", "ग्यारह बजे", "बारह बजे", "साढे बारह", "डेढ़", "ढाई", "पौने दो", "सवा दो"};

        int[] hindiPronunciations = {R.raw.time_01, R.raw.time_02, R.raw.time_03, R.raw.time_04,
                R.raw.time_05, R.raw.time_06, R.raw.time_07, R.raw.time_08, R.raw.time_09,
                R.raw.time_10, R.raw.time_11, R.raw.time_12, R.raw.time_13, R.raw.time_14,
                R.raw.time_15, R.raw.time_16, R.raw.time_17, R.raw.time_18, R.raw.time_19,
                R.raw.time_20, R.raw.time_21};

        int[] wordImages = new int[21];
        Arrays.fill(wordImages, -1);

        // Store the time data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            timeList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        timeWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, timeList);
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
                timeWordAdapter.getFilter().filter(query);
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
                timeWordAdapter.getFilter().filter(newText);
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