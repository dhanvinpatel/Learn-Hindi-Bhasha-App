package com.bhasha.learnhindibhasha.activities.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bhasha.learnhindibhasha.R;
import com.bhasha.learnhindibhasha.activities.categories.BirdsActivity;
import com.bhasha.learnhindibhasha.activities.categories.BodyActivity;
import com.bhasha.learnhindibhasha.activities.categories.DirectionsActivity;
import com.bhasha.learnhindibhasha.adapters.CategoryWordAdapter;
import com.bhasha.learnhindibhasha.models.HomeScreenCategory;
import com.bhasha.learnhindibhasha.adapters.HomeScreenAdapter;
import com.bhasha.learnhindibhasha.activities.categories.AlphabetActivity;
import com.bhasha.learnhindibhasha.activities.categories.AnimalsActivity;
import com.bhasha.learnhindibhasha.activities.categories.CalendarActivity;
import com.bhasha.learnhindibhasha.activities.categories.ColoursActivity;
import com.bhasha.learnhindibhasha.activities.categories.FamilyActivity;
import com.bhasha.learnhindibhasha.activities.categories.FoodActivity;
import com.bhasha.learnhindibhasha.activities.categories.FruitsActivity;
import com.bhasha.learnhindibhasha.activities.categories.GreetingsActivity;
import com.bhasha.learnhindibhasha.activities.categories.NumbersActivity;
import com.bhasha.learnhindibhasha.activities.categories.SpicesActivity;
import com.bhasha.learnhindibhasha.activities.categories.TimeActivity;
import com.bhasha.learnhindibhasha.activities.categories.VegetablesActivity;
import com.bhasha.learnhindibhasha.activities.categories.WeatherActivity;
import java.util.ArrayList;

/**
 * Display all the categories.
 */
public class HomeScreenActivity extends AppCompatActivity {

    private CardView wordCardView;
    private CardView wordImageCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        String[] categories = {"Numbers", "Alphabet", "Colours", "Animals", "Birds", "Greetings", "Family",
                "Time", "Calendar", "Weather", "Food", "Fruits", "Vegetables", "Spices",
                "Directions", "Body"};

        int[] categoryId = {R.drawable.ic_numbers, R.drawable.ic_alphabets, R.drawable.ic_colours,
                R.drawable.ic_lion, R.drawable.ic_bird, R.drawable.ic_greetings, R.drawable.ic_family,
                R.drawable.ic_time, R.drawable.ic_calendar, R.drawable.ic_weather, R.drawable.ic_food,
                R.drawable.ic_fruits, R.drawable.ic_vegetables, R.drawable.ic_spices, R.drawable.ic_direction,
                R.drawable.ic_body};

        // HomeScreenCategory ArrayList stores all the categories.
        ArrayList<HomeScreenCategory> homeScreenCategory = new ArrayList<HomeScreenCategory>();

        for (int i = 0; i < categories.length; i++) {
            homeScreenCategory.add(new HomeScreenCategory(categories[i], categoryId[i]));
        }

        // Create HomeScreenAdapter objects.
        HomeScreenAdapter homeScreenAdapter = new HomeScreenAdapter(this, R.layout.item_category, homeScreenCategory);

        // Find the category GridView and pass the homeScreenAdapter to categoryGridView to display the categories.
        GridView categoryGridView = (GridView) findViewById(R.id.categoryGridView);
        categoryGridView.setAdapter(homeScreenAdapter);

        // Intent to different category depending on the chosen category.
        categoryGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chooseCategory(position);
            }
        });
    }

    /**
     * Intent to a chosen category from the list of categories.
     *
     * @param position  is the GridView position of the chosen category.
     */
    private void chooseCategory(int position) {
        switch (position) {
            case 0:
                // Create intent and start the NumbersActivity.
                Intent numbersIntent = new Intent(HomeScreenActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
                break;
            case 1:
                // Create intent and start the AlphabetActivity.
                Intent alphabetIntent = new Intent(HomeScreenActivity.this, AlphabetActivity.class);
                startActivity(alphabetIntent);
                break;
            case 2:
                // Create intent and start the ColoursActivity.
                Intent coloursIntent = new Intent(HomeScreenActivity.this, ColoursActivity.class);
                startActivity(coloursIntent);
                break;
            case 3:
                // Create intent and start the AnimalsActivity.
                Intent animalsIntent = new Intent(HomeScreenActivity.this, AnimalsActivity.class);
                startActivity(animalsIntent);
                break;
            case 4:
                // Create intent and start the BirdsActivity.
                Intent birdsIntent = new Intent(HomeScreenActivity.this, BirdsActivity.class);
                startActivity(birdsIntent);
                break;
            case 5:
                // Create intent and start the GreetingsActivity.
                Intent greetingsIntent = new Intent(HomeScreenActivity.this, GreetingsActivity.class);
                startActivity(greetingsIntent);
                break;
            case 6:
                // Create intent and start the FamilyActivity.
                Intent familyIntent = new Intent(HomeScreenActivity.this, FamilyActivity.class);
                startActivity(familyIntent);
                break;
            case 7:
                // Create intent and start the TimeActivity.
                Intent timeIntent = new Intent(HomeScreenActivity.this, TimeActivity.class);
                startActivity(timeIntent);
                break;
            case 8:
                // Create intent and start the CalendarActivity.
                Intent calendarIntent = new Intent(HomeScreenActivity.this, CalendarActivity.class);
                startActivity(calendarIntent);
                break;
            case 9:
                // Create intent and start the WeatherActivity.
                Intent weatherIntent = new Intent(HomeScreenActivity.this, WeatherActivity.class);
                startActivity(weatherIntent);
                break;
            case 10:
                // Create intent and start the FoodActivity.
                Intent foodIntent = new Intent(HomeScreenActivity.this, FoodActivity.class);
                startActivity(foodIntent);
                break;
            case 11:
                // Create intent and start the FruitsActivity.
                Intent fruitsIntent = new Intent(HomeScreenActivity.this, FruitsActivity.class);
                startActivity(fruitsIntent);
                break;
            case 12:
                // Create intent and start the VegetablesActivity.
                Intent vegetablesIntent = new Intent(HomeScreenActivity.this, VegetablesActivity.class);
                startActivity(vegetablesIntent);
                break;
            case 13:
                // Create intent and start the SpicesActivity.
                Intent spicesIntent = new Intent(HomeScreenActivity.this, SpicesActivity.class);
                startActivity(spicesIntent);
                break;
            case 14:
                // Create intent and start the DirectionsActivity.
                Intent directionsIntent = new Intent(HomeScreenActivity.this, DirectionsActivity.class);
                startActivity(directionsIntent);
                break;
            case 15:
                // Create intent and start the BodyActivity.
                Intent bodyIntent = new Intent(HomeScreenActivity.this, BodyActivity.class);
                startActivity(bodyIntent);
                break;
            default:
                break;
        }
    }

    /**
     * Initialize category word views.
     *
     * @param categoryWordAdapter   is the CategoryWordAdapter for the chosen category.
     */
    public void initializeViews(CategoryWordAdapter categoryWordAdapter) {
        // Find the word ListView and pass the adapter to display the category words.
        ListView categoryListView = (ListView) findViewById(R.id.wordListView);
        categoryListView.setAdapter(categoryWordAdapter);

        // Find the CardView's that displays the clicked word and word image.
        wordCardView = (CardView) findViewById(R.id.wordCardView);
        wordImageCardView = (CardView) findViewById(R.id.wordImageCardView);

        // Display the clicked word from the ListView.
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                wordImageCardView.setVisibility(View.VISIBLE);
                wordImageCardView.setCardBackgroundColor(ContextCompat.getColor(
                        HomeScreenActivity.this, R.color.green_700));
                wordImageCardView.setCardElevation(0);
                wordCardView.setVisibility(View.VISIBLE);

                // Find the Hindi word TextView and set the Hindi word in the TextView.
                TextView hindiTextView = (TextView) findViewById(R.id.hindiTextView);
                hindiTextView.setText(categoryWordAdapter.getItem(position).getHindiWord());

                // Find the Devanagari font TextView and set the Devanagari font in the TextView.
                TextView devanagariTextView = (TextView) findViewById(R.id.devanagariTextView);
                devanagariTextView.setText(categoryWordAdapter.getItem(position).getDevanagariFont());

                // Find the word ImageView and set the word image in the ImageView.
                ImageView wordImageView = (ImageView) findViewById(R.id.wordImageView);

                // Check if the word image is present or not and set the visibility accordingly.
                if (categoryWordAdapter.getItem(position).imagePresent()) {
                    wordImageView.getLayoutParams().height = 150;
                    wordImageView.getLayoutParams().width = 150;
                    wordImageView.setImageResource(categoryWordAdapter.getItem(position).getImageResourceId());
                    wordImageCardView.setVisibility(View.VISIBLE);
                } else {
                    wordImageCardView.setVisibility(View.GONE);
                }
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
}