package com.bhasha.learnhindibhasha.activities.categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.bhasha.learnhindibhasha.models.CategoryWord;
import com.bhasha.learnhindibhasha.adapters.CategoryWordAdapter;
import com.bhasha.learnhindibhasha.R;
import java.util.ArrayList;

/**
 * Display all the numbers in a ListView.
 */
public class NumbersActivity extends AppCompatActivity {
    private SearchView searchView;
    private CardView wordCardView;
    private CategoryWordAdapter numbersWordAdapter;
    private final ArrayList<CategoryWord> numbersList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        initializeNumbersData();
        initializeViews();
    }

    /**
     * Initialize the numbers data.
     */
    private void initializeNumbersData() {
        String[] englishNumbers = {"Zero - 0 (०)", "One - 1 (१)", "Two - 2 (२)", "Three - 3 (३)",
                "Four - 4 (४)", "Five - 5 (५)", "Six - 6 (६)", "Seven - 7 (७)", "Eight - 8 (८)",
                "Nine - 9 (९)", "Ten - 10 (१०)", "Eleven - 11 (११)", "Twelve - 12 (१२)", "Thirteen - 13 (१३)",
                "Fourteen - 14 (१४)", "Fifteen - 15 (१५)", "Sixteen - 16 (१६)", "Seventeen - 17 (१७)",
                "Eighteen - 18 (१८)", "Nineteen - 19 (१९)", "Twenty - 20 (२०)", "Twenty One - 21 (२१)",
                "Twenty Two - 22 (२२)", "Twenty Three - 23 (२३)", "Twenty Four - 24 (२४)", "Twenty Five - 25 (२५)",
                "Twenty Six - 26 (२६)", "Twenty Seven - 27 (२७)", "Twenty Eight - 28 (२८)", "Twenty Nine - 29 (२९)",
                "Thirty - 30 (३०)", "Thirty One - 31 (३१)", "Thirty Two - 32 (३२)", "Thirty Three - 33 (३३)",
                "Thirty Four - 34 (३४)", "Thirty Five - 35 (३५)", "Thirty Six - 36 (३६)", "Thirty Seven - 37 (३७)",
                "Thirty Eight - 38 (३८)", "Thirty Nine - 39 (३९)", "Forty - 40 (४०)", "Forty One - 41 (४१)",
                "Forty Two - 42 (४२)", "Forty Three - 43 (४३)", "Forty Four - 44 (४४)", "Forty Five - 45 (४५)",
                "Forty Six - 46 (४६)", "Forty Seven - 47 (४७)", "Forty Eight - 48 (४८)", "Forty Nine - 49 (४९)",
                "Fifty - 50 (५०)", "Fifty One - 51 (५१)", "Fifty Two - 52 (५२)", "Fifty Three - 53 (५३)",
                "Fifty Four - 54 (५४)", "Fifty Five - 55 (५५)", "Fifty Six - 56 (५६)", "Fifty Seven - 57 (५७)",
                "Fifty Eight- 58 (५८)", "Fifty Nine - 59 (५९)", "Sixty - 60 (६०)", "Sixty One - 61 (६१)",
                "Sixty Two - 62 (६२)", "Sixty Three - 63 (६३)", "Sixty Four - 64 (६४)", "Sixty Five - 65 (६५)",
                "Sixty Six - 66 (६६)", "Sixty Seven - 67 (६७)", "Sixty Eight - 68 (६८)", "Sixty Nine - 69 (६९)",
                "Seventy - 70 (७०)", "Seventy One - 71 (७१)", "Seventy Two - 72 (७२)", "Seventy Three - 73 (७३)",
                "Seventy Four - 74 (७४)", "Seventy Five - 75 (७५)", "Seventy Six - 76 (७६)", "Seventy Seven- 77 (७७)",
                "Seventy Eight - 78 (७८)", "Seventy Nine - 79 (७९)", "Eighty - 80 (८०)", "Eighty One - 81 (८१)",
                "Eighty Two - 82 (८२)", "Eighty Three - 83 (८३)", "Eighty Four - 84 (८४)", "Eighty Five - 85 (८५)",
                "Eighty Six - 86 (८६)", "Eighty Seven - 87 (८७)", "Eighty Eight - 88 (८८)", "Eighty Nine - 89 (८९)",
                "Ninety - 90 (९०)", "Ninety One - 91 (९१)", "Ninety Two - 92 (९२)", "Ninety Three - 93 (९३)",
                "Ninety Four - 94 (९४)", "Ninety Five - 95 (९५)", "Ninety Six - 96 (९६)", "Ninety Seven - 97 (९७)",
                "Ninety Eight - 98 (९८)", "Ninety Nine - 99 (९९)", "One Hundred - 100 (१००)", "Two Hundred - 200 (२००)",
                "Three Hundred - 300 (३००)", "Four Hundred - 400 (४००)", "Five Hundred - 500 (५००)",
                "Six Hundred - 600 (६००)", "Seven Hundred - 700 (७००)", "Eight Hundred - 800 (८००)",
                "Nine Hundred - 900 (९००)", "One Thousand - 1,000 (१,०००)", "Ten Thousand - 10,000 (१०,०००)",
                "One Hundred Thousand - 100,000 (१,००,०००)", "Ten Million - 10,000,000 (१००,००,०००)"};

        String[] hindiNumbers = {"Shunya", "Ek", "Do", "Teen",  "Chaar", "Paanch", "Cheh", "Saat", "Aath",
                "Nau", "Das", "Gyaarah", "Baarah", "Terah", "Chaudah", "Pandrah", "Solah", "Satrah", "Athaarah",
                "Unnees", "Bees", "Ikkess", "Baees", "Taees", "Chaubees", "Pachchees", "Chhabbees", "Sattaees",
                "Atthaees", "Untees", "Tees", "Ikatees", "Battees", "Taintees", "Chautees", "Paintees", "Chatees",
                "Saintees", "Adatees", "Untalees", "Chalees", "Iktalees", "Bayalees", "Taintalees", "Chavalees",
                "Paintalees", "Chiyalees", "Saintalees", "Adatalees", "Unchaas", "Pachaas", "Ikyavan", "Baavan",
                "Tirpan", "Chauvan", "Pachpan", "Chappan", "Sattavan", "Athaavan", "Unsath", "Saath", "Iksath",
                "Baasath", "Tresath", "Chausath", "Painsath", "Chiyasath", "Sadsath", "Adsath", "Unahttar",
                "Sattar", "Ikahttar", "Bahattar", "Tihattar", "Chauhattar", "Pachhattar", "Chihattar", "Satahattar",
                "Athahattar", "Unasee", "Assee", "Ikyasee", "Bayasee", "Tirasee", "Chaurasee", "Pachasee",
                "Chiyaasee", "Sataasee", "Athasee", "Nauasi", "Nabbe", "Ikyaanave", "Bayaanve", "Tiranave",
                "Chauraanve", "Panchaanve", "Chiyaanve", "Santaanve", "Athaanve", "Ninyaanve", "Sau", "Do sau",
                "Teen sau", "Chaar sau", "Paanch sau", "Cheh sau", "Saat sau", "Aath sau", "Nau sau",
                "Hazaar", "Das hazaar", "Ek laakh", "Ek crore"};

        String[] devanagariFonts = {"शून्य", "एक", "दो","तीन", "चार", "पांच", "छह", "सात", "आठ", "नौ",
                "दस", "ग्यारह", "बारह", "तेरह", "चौदह", "पंद्रह", "सोलह", "सत्रह", "अठारह", "उन्नीस", "बीस",
                "इक्कीस", "बाईस", "तेईस", "चौबीस", "पच्चीस", "छब्बीस", "सत्ताईस", "अट्ठाईस", "उन्तीस", "तीस",
                "इकतीस", "बत्तीस", "तैंतीस", "चौंतीस", "पैंतीस", "छत्तीस", "सैंतीस", "अड़तीस", "उनतालीस", "चालीस",
                "इकतालीस", "बयालीस", "तैंतालीस", "चवालीस", "पैंतालीस", "छियालीस", "सैंतालीस", "अड़तालीस", "उनचास",
                "पचास", "इक्यावन", "बावन", "तिरपन", "चौवन", "पचपन", "छप्पन", "सत्तावन", "अट्ठावन", "उनसठ",
                "साठ", "इकसठ", "बासठ", "तिरसठ", "चौंसठ", "पैंसठ", "छियासठ", "सड़सठ", "अड़सठ", "उनहत्तर",
                "सत्तर", "इकहत्तर", "बहत्तर", "तिहत्तर", "चौहत्तर", "पचहत्तर", "छिहत्तर", "सतहत्तर", "अठहत्तर", "उनासी",
                "अस्सी", "इक्यासी", "बयासी", "तिरासी", "चौरासी", "पचासी", "छियासी", "सतासी", "अठासी", "नवासी",
                "नब्बे", "इक्यानवे", "बयान्वे", "तिरानवे", "चौरानवे", "पंचानवे", "छियानवे", "सत्तानवे", "अठानवे", "निन्यानवे",
                "सौ", "दो सौ", "तीन सौ", "चार सौ", "पांच सौ", "छे सौ", "सात सौ", "आठ सौ", "नौ सौ", "हज़ार",
                "दस हज़ार", "एक लाख", "एक करोड़"};

        int[] hindiPronunciations = {R.raw.number_zero, R.raw.number_one, R.raw.number_two, R.raw.number_three,
                R.raw.number_four, R.raw.number_five, R.raw.number_six, R.raw.number_seven, R.raw.number_eight,
                R.raw.number_nine, R.raw.number_ten, R.raw.number_eleven, R.raw.number_twelve, R.raw.number_thirteen,
                R.raw.number_fourteen, R.raw.number_fifteen, R.raw.number_sixteen, R.raw.number_seventeen,
                R.raw.number_eighteen, R.raw.number_nineteen, R.raw.number_twenty, R.raw.number_twenty_one,
                R.raw.number_twenty_two, R.raw.number_twenty_three, R.raw.number_twenty_four, R.raw.number_twenty_five,
                R.raw.number_twenty_six, R.raw.number_twenty_seven, R.raw.number_twenty_eight, R.raw.number_twenty_nine,
                R.raw.number_thirty, R.raw.number_thirty_one, R.raw.number_thirty_two, R.raw.number_thirty_three,
                R.raw.number_thirty_four, R.raw.number_thirty_five, R.raw.number_thirty_six, R.raw.number_thirty_seven,
                R.raw.number_thirty_eight, R.raw.number_thirty_nine, R.raw.number_forty, R.raw.number_forty_one,
                R.raw.number_forty_two, R.raw.number_forty_three, R.raw.number_forty_four, R.raw.number_forty_five,
                R.raw.number_forty_six, R.raw.number_forty_seven, R.raw.number_forty_eight, R.raw.number_forty_nine,
                R.raw.number_fifty, R.raw.number_fifty_one, R.raw.number_fifty_two, R.raw.number_fifty_three,
                R.raw.number_fifty_four, R.raw.number_fifty_five, R.raw.number_fifty_six, R.raw.number_fifty_seven,
                R.raw.number_fifty_eight, R.raw.number_fifty_nine, R.raw.number_sixty, R.raw.number_sixty_one,
                R.raw.number_sixty_two, R.raw.number_sixty_three, R.raw.number_sixty_four, R.raw.number_sixty_five,
                R.raw.number_sixty_six, R.raw.number_sixty_seven, R.raw.number_sixty_eight, R.raw.number_sixty_nine,
                R.raw.number_seventy, R.raw.number_seventy_one, R.raw.number_seventy_two, R.raw.number_seventy_three,
                R.raw.number_seventy_four, R.raw.number_seventy_five, R.raw.number_seventy_six, R.raw.number_seventy_seven,
                R.raw.number_seventy_eight, R.raw.number_seventy_nine, R.raw.number_eighty, R.raw.number_eighty_one,
                R.raw.number_eighty_two, R.raw.number_eighty_three, R.raw.number_eighty_four, R.raw.number_eighty_five,
                R.raw.number_eighty_six, R.raw.number_eighty_seven, R.raw.number_eighty_eight, R.raw.number_eighty_nine,
                R.raw.number_ninety, R.raw.number_ninety_one, R.raw.number_ninety_two, R.raw.number_ninety_three,
                R.raw.number_ninety_four, R.raw.number_ninety_five, R.raw.number_ninety_six, R.raw.number_ninety_seven,
                R.raw.number_ninety_eight, R.raw.number_ninety_nine, R.raw.number_one_hundred, R.raw.number_two_hundred,
                R.raw.number_three_hundred, R.raw.number_four_hundred, R.raw.number_five_hundred, R.raw.number_six_hundred,
                R.raw.number_seven_hundred, R.raw.number_eight_hundred, R.raw.number_nine_hundred, R.raw.number_thousand,
                R.raw.number_ten_thousand, R.raw.number_one_hundred_thousand, R.raw.number_ten_million};

        // Store the numbers data in an ArrayList.
        for (int i = 0; i < hindiNumbers.length; i++) {
            numbersList.add(new CategoryWord(englishNumbers[i], hindiNumbers[i], devanagariFonts[i],
                    hindiPronunciations[i]));
        }

        // Create CategoryWordAdapter objects.
        numbersWordAdapter = new CategoryWordAdapter(this, R.layout.item_word, numbersList);
    }

    /**
     * Initialize views.
     */
    private void initializeViews() {
        // Find the word ListView and pass the adapter to display the numbers.
        ListView numbersListView = (ListView) findViewById(R.id.wordListView);
        numbersListView.setAdapter(numbersWordAdapter);

        // Find the CardView's that displays the clicked word, Hindi number, and word image.
        wordCardView = (CardView) findViewById(R.id.wordCardView);

        // Display the clicked word from the ListView.
        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                wordCardView.setVisibility(View.VISIBLE);

                // Find the Hindi word TextView and set the Hindi word in the TextView.
                TextView hindiTextView = (TextView) findViewById(R.id.hindiTextView);
                hindiTextView.setText(numbersWordAdapter.getItem(position).getHindiWord());

                // Find the Devanagari font TextView and set the Devanagari font in the TextView.
                TextView devanagariTextView = (TextView) findViewById(R.id.devanagariTextView);
                devanagariTextView.setText(numbersWordAdapter.getItem(position).getDevanagariFont());
            }
        });

        // Hide the displayed word.
        wordCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wordCardView.setVisibility(View.GONE);
            }
        });
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
                numbersWordAdapter.getFilter().filter(query);
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
                numbersWordAdapter.getFilter().filter(newText);
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