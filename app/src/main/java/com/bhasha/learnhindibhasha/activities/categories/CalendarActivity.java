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
import java.util.Arrays;

/**
 * Display all the calendar items in a ListView.
 */
public class CalendarActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter calendarWordAdapter;
    private final ArrayList<CategoryWord> calendarList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeCalendarData();
        initializeViews(calendarWordAdapter);
    }

    /**
     * Initialize the calendar data.
     */
    private void initializeCalendarData() {
        String[] englishTranslation = {"Calendar", "Month", "January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December",
                "Year", "Today", "Tomorrow", "Day after tomorrow", "Yesterday", "Everyday", "Date",
                "Day", "Week", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                "Saturday", "What date is it today?", "Today is 1st March.", "Which day is it today?",
                "Today is Sunday."};

        String[] hindiTranslation = {"Panchaang", "Maheena", "Janvari", "Farvari", "March", "April",
                "Mai", "June", "July", "Agast", "Sitambar", "Aktubar", "Navambar", "Disambar", "Saal",
                "Aaj", "Kal (Aane Wala)", "Parson", "Kal (Beeta Hua)", "Har roz", "Tareekh", "Din",
                "Saptaah", "Ravivaar", "Somavaar", "Mangalvaar", "Budhvaar", "Guruvaar", "Shukravaar",
                "Shanivaar", "Aaj kya tareekh hai?", "Aaj ek March hia.", "Aaj kaunsa din hai?",
                "Aaj ravivaar hai."};

        String[] devanagariFonts = {"पंचांग", "महीना", "जनवरी", "फरवरी", "मार्च", "अप्रैल", "मई", "जून",
                "जुलाई", "अगस्त", "सितम्बर", "अक्टूबर", "नवम्बर", "दिसम्बर", "साल", "आज", "कल (आने वाला)",
                "परसों", "कल (बीता हुआ)", "हर रोज", "तारिख", "दिन", "सप्ताह", "रविवार", "सोमवार", "मंगलवार",
                "बुधवार", "गुरूवार", "शुक्रवार", "शनिवार", "आज क्या तारीख है।?", "आज एक मार्च है।",
                "आज कौन सा दिन है?", "आज रविवार है।"};

        int[] hindiPronunciations = {R.raw.calendar_01, R.raw.calendar_02, R.raw.calendar_03,
                R.raw.calendar_04, R.raw.calendar_05, R.raw.calendar_06, R.raw.calendar_07,
                R.raw.calendar_08, R.raw.calendar_09, R.raw.calendar_10, R.raw.calendar_11,
                R.raw.calendar_12, R.raw.calendar_13, R.raw.calendar_14, R.raw.calendar_15,
                R.raw.calendar_16, R.raw.calendar_17, R.raw.calendar_18, R.raw.calendar_19,
                R.raw.calendar_20, R.raw.calendar_21, R.raw.calendar_22, R.raw.calendar_23,
                R.raw.calendar_24, R.raw.calendar_25, R.raw.calendar_26, R.raw.calendar_27,
                R.raw.calendar_28, R.raw.calendar_29, R.raw.calendar_30, R.raw.calendar_31,
                R.raw.calendar_32, R.raw.calendar_33, R.raw.calendar_34};

        int[] wordImages = new int[34];
        Arrays.fill(wordImages, -1);

        // Store the calendar data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            calendarList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        calendarWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, calendarList);
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
                calendarWordAdapter.getFilter().filter(query);
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
                calendarWordAdapter.getFilter().filter(newText);
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