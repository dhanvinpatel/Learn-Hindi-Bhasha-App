package com.bhasha.learnhindibhasha.activities.categories;

import androidx.appcompat.app.AppCompatActivity;

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
 * Display all the body parts in a ListView.
 */
public class BodyActivity extends HomeScreenActivity {
    private SearchView searchView;
    private CategoryWordAdapter bodyWordAdapter;
    private final ArrayList<CategoryWord> bodyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeBodyData();
        initializeViews(bodyWordAdapter);
    }
    /**
     * Initialize the body data.
     */
    private void initializeBodyData() {
        String[] englishTranslation = {"Body", "Hair", "Head", "Eyes", "Ear", "Nose", "Lips", "Mouth",
                "Teeth", "Chin", "Tongue", "Neck", "Shoulder", "Hand", "Arm", "Elbow", "Finger", "Chest",
                "Heart", "Stomach", "Hip", "Thigh", "Knee", "Feet", "Toe"};

        String[] hindiTranslation = {"Shareer", "Baal", "Sir", "Aankhen", "Kaan", "Naak", "Honth",
                "Munh", "Daant", "Thuddi", "Jeebh", "Gardan", "Kandha", "Haath", "Baaju", "Kohani",
                "Ungli", "Chhaati", "Dil", "Pet", "Kamar", "Jaangh", "Ghutana", "Pair", "Pair ki ungli"};

        String[] devanagariFonts = {"शरीर", "बाल", "सिर", "आंखें", "कान", "नाक", "होंठ", "मुंह", "दांत",
                "ठुड्डी", "जीभ", "गर्दन", "कंधा", "हाथ", "बाजु", "कोहनी", "उंगली", "छाती", "दिल", "पेट", "कमर",
                "जांघ", "घुटना", "पैर", "पैर की उंगली"};

        int[] hindiPronunciations = {R.raw.body, R.raw.body_hair, R.raw.body_head, R.raw.body_eyes,
                R.raw.body_ears, R.raw.body_nose, R.raw.body_lips, R.raw.body_mouth, R.raw.body_teeth,
                R.raw.body_chin, R.raw.body_tongue, R.raw.body_neck, R.raw.body_shoulder, R.raw.body_hand,
                R.raw.body_arm, R.raw.body_elbow, R.raw.body_finger, R.raw.body_chest, R.raw.body_heart,
                R.raw.body_stomach, R.raw.body_hip, R.raw.body_thigh, R.raw.body_knee, R.raw.body_feet,
                R.raw.body_toe};

        int[] wordImages = {R.drawable.ic_body, -1, R.drawable.ic_body_head, R.drawable.ic_body_eye,
                R.drawable.ic_body_ear, R.drawable.ic_body_nose, R.drawable.ic_body_lips,
                R.drawable.ic_body_mouth, R.drawable.ic_body_teeth, R.drawable.ic_body_chin,
                R.drawable.ic_body_tongue, R.drawable.ic_body_neck, -1, R.drawable.ic_body_hand,
                R.drawable.ic_body_arm, R.drawable.ic_body_elbow, R.drawable.ic_body_finger, -1,
                R.drawable.ic_body_heart, R.drawable.ic_body_stomach, -1, -1, R.drawable.ic_body_knee,
                R.drawable.ic_body_feet, R.drawable.ic_body_toe};

        // Store the body data in an ArrayList.
        for (int i = 0; i < hindiTranslation.length; i++) {
            bodyList.add(new CategoryWord(englishTranslation[i], hindiTranslation[i],
                    devanagariFonts[i], hindiPronunciations[i], wordImages[i]));
        }

        // Create CategoryWordAdapter objects.
        bodyWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, bodyList);
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
                bodyWordAdapter.getFilter().filter(query);
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
                bodyWordAdapter.getFilter().filter(newText);
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