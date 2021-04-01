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
 * Display all the weather items in a ListView.
 */
public class WeatherActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter weatherWordAdapter;
    private final ArrayList<CategoryWord> weatherList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeWeatherData();
        initializeViews(weatherWordAdapter);
    }

    /**
     * Initialize the weather data.
     */
    private void initializeWeatherData() {
        String[] englishTranslation = {"Weather", "How is the weather?", "It is sunny outside.",
                "It is very hot.", "It is raining.", "It is snowing.", "It is very cold.",
                "It is cloudy.", "It is windy.", "It will rain tomorrow.", "Nice weather."};

        String[] hindiTranslation = {"Mausam", "Mausam kaisa hai?", "Baahar dhoop hai.",
                "Bahut garmi hai.", "Baarish ho rahee hai.", "Barph gir rahee hai.",
                "Bahut rhand hai.", "Baadal chhae hai.", "Havayen chal rahee hai.",
                "Kal Baarish hogee.", "Achchha mausam hai."};

        String[] devanagariFonts = {"मौसम", "मौसम कैसा है?", "बाहर धूप है।", "बहुत गरमी है।",
                "बारिश हो रही है।", "बर्फ गिर रही है।", "बहुत ठंड है।", "बादल छाए है।", "हवाएँ चल रही है।",
                "कल बारिश होगी।", "अच्छा मौसम है।"};

        int[] hindiPronunciations = {R.raw.weather_01, R.raw.weather_02, R.raw.weather_03,
                R.raw.weather_04, R.raw.weather_05, R.raw.weather_06, R.raw.weather_07,
                R.raw.weather_08, R.raw.weather_09, R.raw.weather_10, R.raw.weather_11};

        int[] wordImages = new int[11];
        Arrays.fill(wordImages, -1);

        // Store the weather data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            weatherList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        weatherWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, weatherList);
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
                weatherWordAdapter.getFilter().filter(query);
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
                weatherWordAdapter.getFilter().filter(newText);
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