package com.bhasha.learnhindibhasha.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bhasha.learnhindibhasha.models.CategoryWord;
import com.bhasha.learnhindibhasha.R;
import java.util.ArrayList;

/**
 * Custom adapter of type CategoryWord to populate the custom rows of the ListView with an ArrayList.
 */
public class CategoryWordAdapter extends ArrayAdapter<CategoryWord> {
    private final int resource;
    private final ArrayList<CategoryWord> categoryWord;
    private final ArrayList<CategoryWord> categoryWordData;

    /**
     * Create object of the CategoryWordAdapter class.
     *
     * @param context       is the current context used to inflate the layout file.
     * @param resource      is the layout resource ID.
     * @param categoryWord  is the CategoryWord objects to represent in the ListView.
     */
    public CategoryWordAdapter(Context context, int resource, ArrayList<CategoryWord> categoryWord) {
        super(context, 0, categoryWord);
        this.resource = resource;
        this.categoryWord = categoryWord;
        categoryWordData = new ArrayList<>(categoryWord);
    }

    /**
     * Number of items in the data set represented by the CategoryWordAdapter.
     *
     * @return int  the size of the categoryWord ArrayList.
     */
    @Override
    public int getCount() {
        return categoryWord.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position          is the position of the item whose data is being requested within the adapter's data set.
     * @return CategoryWord     the data at the specified position.
     */
    @Override
    public CategoryWord getItem(int position) {
        return categoryWord.get(position);
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
        View wordView = convertView;

        // Check if an existing view item can be reused, if not then inflate new view item.
        if (wordView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            wordView = layoutInflater.inflate(resource, parent, false);
        }

        // Find the word from the list of word based on the specified position.
        CategoryWord currentWord = categoryWord.get(position);

        // Find the English word TextView and set the English word in the TextView.
        TextView englishWordTextView = (TextView) wordView.findViewById(R.id.englishWordTextView);
        englishWordTextView.setText(currentWord.getEnglishWord());

        // Find the Hindi word TextView and set the Hindi word in the TextView.
        TextView hindiWordTextView = (TextView) wordView.findViewById(R.id.hindiWordTextView);
        hindiWordTextView.setText(currentWord.getHindiWord());

        // Find the Devanagari font TextView and set the Devanagari font in the TextView.
        TextView devanagariFontTextView = (TextView) wordView.findViewById(R.id.devanagariFontTextView);
        devanagariFontTextView.setText(currentWord.getDevanagariFont());

        // Find the Hindi word ImageButton and set the Hindi word pronunciation with the ImageButton.
        ImageButton wordImageButton = (ImageButton) wordView.findViewById(R.id.hindiWordImageButton);
        wordImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the Hindi word pronunciation and play the pronunciation.
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(), currentWord.getPronunciationResourceId());
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                        mp.release();
                    }
                });
                mediaPlayer.start();
            }
        });

        return wordView;
    }

    /**
     * Filter constrains data with a filtering pattern.
     *
     * @return Filter
     */
    @Override
    public Filter getFilter() {
        return categoryWordFilter;
    }

    private final Filter categoryWordFilter = new Filter() {
        /**
         * Filter the data according to the constraint.
         *
         * @param constraint        is the constraint used to filter the data.
         * @return FilterResults    the results of the filtering operation.
         */
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CategoryWord> filterResultData = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filterResultData.addAll(categoryWordData);
            } else {
                String searchString = constraint.toString().toLowerCase().trim();
                for (CategoryWord categoryWordItem : categoryWordData) {
                    if (categoryWordItem.getEnglishWord().toLowerCase().contains(searchString) ||
                            categoryWordItem.getHindiWord().toLowerCase().contains(searchString) ||
                            categoryWordItem.getDevanagariFont().toLowerCase().contains(searchString)) {

                        filterResultData.add(categoryWordItem);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filterResultData;
            filterResults.count = filterResultData.size();

            return filterResults;
        }

        /**
         * Publish the filtering results in the user interface.
         *
         * @param constraint    is the constraint used to filter the data.
         * @param results       the results of the filtering operation.
         */
        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categoryWord.clear();
            categoryWord.addAll((ArrayList<CategoryWord>) results.values);
            notifyDataSetChanged();
        }
    };
}
