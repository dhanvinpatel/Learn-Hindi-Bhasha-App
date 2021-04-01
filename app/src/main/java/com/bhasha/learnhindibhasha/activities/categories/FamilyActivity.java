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
 * Display all the family relations in a ListView.
 */
public class FamilyActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter familyWordAdapter;
    private final ArrayList<CategoryWord> familyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeFamilyData();
        initializeViews(familyWordAdapter);
    }

    /**
     * Initialize the family data.
     */
    private void initializeFamilyData() {
        String[] englishTranslation = {"Family", "Relation", "Relatives", "Father", "Mother",
                "Sister", "Brother", "Husband", "Wife", "Children", "Son", "Daughter",
                "Grandfather (father's father)", "Grandmother (father's mother)",
                "Uncle (father's brother)", "Aunt (father's brother's wife)",
                "Aunt (father's sister)", "Uncle (father's sister's husband)",
                "Grandfather (mothers's father)", "Grandmother (mothers's mother)",
                "Uncle (mothers's brother)", "Aunt (mothers's brother's wife)",
                "Aunt (mothers's sister)", "Uncle (mothers's sister's husband)",
                "Sister-in-law (brother's wife)", "Nephew (brother's son)", "Niece (brother's daughter)",
                "Brother-in-law (sister's husband)", "Nephew (sister's son)", "Niece (sister's daughter)"};

        String[] hindiTranslation = {"Parivaar", "Rishta", "Ristedar", "Pita (Papa)", "Maa (Mummy)",
                "Bahan (Didi)", "Bhai", "Pati", "Patni", "Bachche", "Beta", "Beti", "Dada", "Dadi",
                "Chacha", "Chachi", "Bua", "Phoopha", "Nana", "Nani", "Mama", "Mami", "Mausi", "Mausa",
                "Bhabhi", "Bhatija", "Bhatiji", "Jija", "Bhanja", "Bhanji"};

        String[] devanagariFonts = {"परिवार", "रिश्ता", "रिश्तेदार", "पिता (पापा)", "माँ (मम्मी)", "बहन (दीदी)",
                "भाई", "पति", "पत्नी", "बच्चे", "बेटा", "बेटी", "दादा", "दादी", "चाचा", "चाची", "बुआ", "फूफा",
                "नाना", "नानी", "मामा", "मामी", "मौसी", "मौसा", "भाभी", "भतीजा", "भतीजी", "जीजा", "भांजा", "भांजी"};

        int[] hindiPronunciations = {R.raw.family_01, R.raw.family_02, R.raw.family_03, R.raw.family_04,
                R.raw.family_05, R.raw.family_06, R.raw.family_07, R.raw.family_08, R.raw.family_09,
                R.raw.family_10, R.raw.family_11, R.raw.family_12, R.raw.family_13, R.raw.family_14,
                R.raw.family_15, R.raw.family_16, R.raw.family_17, R.raw.family_18, R.raw.family_19,
                R.raw.family_20, R.raw.family_21, R.raw.family_22, R.raw.family_23, R.raw.family_24,
                R.raw.family_25, R.raw.family_26, R.raw.family_27, R.raw.family_28, R.raw.family_29,
                R.raw.family_30};

        int[] wordImages = {R.drawable.ic_family, R.drawable.ic_family_relation, R.drawable.ic_family_relatives,
                R.drawable.ic_family_father, R.drawable.ic_family_mother, R.drawable.ic_family_sister,
                R.drawable.ic_family_brother, R.drawable.ic_family_husband, R.drawable.ic_family_wife,
                R.drawable.ic_family_children, R.drawable.ic_family_son, R.drawable.ic_family_daughter,
                R.drawable.ic_family_dada, R.drawable.ic_family_dadi, R.drawable.ic_family_uncle,
                R.drawable.ic_family_aunt, R.drawable.ic_family_bua, R.drawable.ic_family_phoopha,
                R.drawable.ic_family_nana, R.drawable.ic_family_nani, R.drawable.ic_family_mama,
                R.drawable.ic_family_mami, R.drawable.ic_family_mausi, R.drawable.ic_family_mausa,
                R.drawable.ic_family_bhabhi, R.drawable.ic_family_bhatija, R.drawable.ic_family_bhatiji,
                R.drawable.ic_family_jija, R.drawable.ic_family_bhanja, R.drawable.ic_family_bhanji};

        // Store the family data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            familyList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        familyWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, familyList);
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
                familyWordAdapter.getFilter().filter(query);
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
                familyWordAdapter.getFilter().filter(newText);
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