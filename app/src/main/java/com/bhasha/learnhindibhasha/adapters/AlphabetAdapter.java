package com.bhasha.learnhindibhasha.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bhasha.learnhindibhasha.models.CategoryWord;
import com.bhasha.learnhindibhasha.R;
import java.util.ArrayList;

/**
 * Custom adapter of type CategoryWord to populate the alphabet GridView with an ArrayList.
 */
public class AlphabetAdapter extends ArrayAdapter<CategoryWord> {
    private final int resource;
    private final ArrayList<CategoryWord> alphabet;

    /**
     * Create object of the CategoryWordAdapter class.
     *
     * @param context   is the current context used to inflate the layout file.
     * @param resource  is the layout resource ID.
     * @param alphabet  is the CategoryWord objects to represent in the ListView.
     */
    public AlphabetAdapter(Context context, int resource, ArrayList<CategoryWord> alphabet) {
        super(context, 0, alphabet);
        this.resource = resource;
        this.alphabet = alphabet;
    }

    /**
     * Number of items in the data set represented by the CategoryWordAdapter.
     *
     * @return int  the size of the alphabet ArrayList.
     */
    @Override
    public int getCount() {
        return alphabet.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position          is the position of the item whose data is being requested within the adapter's data set.
     * @return CategoryWord     the data at the specified position.
     */
    @Override
    public CategoryWord getItem(int position) {
        return alphabet.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position  is the position of the item within the adapter's data set whose row id is requested.
     * @return long     the id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Provide a custom view that displays the data at the specified position in the data set.
     *
     * @param position      is the position of the item within the adapter's data set who's view is requested.
     * @param convertView   is the recycled view to be populated.
     * @param parent        is the parent ViewGroup that is used for inflation.
     * @return View         the custom view for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View alphabetView = convertView;

        // Check if an existing view item can be reused, if not then inflate new view item.
        if (alphabetView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            alphabetView = layoutInflater.inflate(resource, parent, false);
        }

        // Find the alphabet from the list of alphabets based on the specified position.
        CategoryWord currentAlphabet = alphabet.get(position);

        // Find the Devanagari font TextView and set the Devanagari font in the TextView.
        TextView devanagariFontTextView = (TextView) alphabetView.findViewById(R.id.alphabetDevanagariFont);
        devanagariFontTextView.setText(currentAlphabet.getDevanagariFont());

        // Find the Hindi word TextView and set the Hindi word in the TextView.
        TextView hindiWordTextView = (TextView) alphabetView.findViewById(R.id.alphabetTextView);
        hindiWordTextView.setText(currentAlphabet.getHindiWord());

        // Find the Hindi alphabet LinearLayout and set the Hindi alphabet pronunciation with the LinearLayout.
        LinearLayout linearLayout = (LinearLayout) alphabetView.findViewById(R.id.alphabetLinearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the Hindi alphabet pronunciation and play the pronunciation.
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),
                        currentAlphabet.getPronunciationResourceId());
                if (mediaPlayer != null) {
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            mp.release();
                        }
                    });
                    mediaPlayer.start();
                }
            }
        });

        return alphabetView;
    }
}
