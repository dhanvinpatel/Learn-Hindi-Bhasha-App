package com.bhasha.learnhindibhasha.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bhasha.learnhindibhasha.models.HomeScreenCategory;
import com.bhasha.learnhindibhasha.R;
import java.util.ArrayList;

/**
 * Custom adapter of type HomeScreenCategory to populate the custom rows of the ListView with an ArrayList.
 */
public class HomeScreenAdapter extends ArrayAdapter<HomeScreenCategory> {
    private final int resource;
    private final ArrayList<HomeScreenCategory> homeScreenCategory;

    /**
     * Create object of HomeScreenAdapter class.
     *
     * @param context               is the current context used to inflate the layout file.
     * @param resource              is the layout resource ID.
     * @param homeScreenCategory    is the HomeScreenCategory objects to represent in the ListView.
     */
    public HomeScreenAdapter(Context context, int resource, ArrayList<HomeScreenCategory> homeScreenCategory) {
        super(context, 0, homeScreenCategory);
        this.resource = resource;
        this.homeScreenCategory = homeScreenCategory;
    }

    /**
     * Number of items in the data set represented by the HomeScreenAdapter.
     *
     * @return int  the size of the homeScreenCategory ArrayList.
     */
    @Override
    public int getCount() {
        return homeScreenCategory.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position              is the position of the item whose data is being requested within the adapter's data set.
     * @return HomeScreenCategory   the data at the specified position.
     */
    @Override
    public HomeScreenCategory getItem(int position) {
        return homeScreenCategory.get(position);
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
        View categoryView = convertView;

        // Check if an existing view item can be reused, if not then inflate new view item.
        if (categoryView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
            categoryView = layoutInflater.inflate(resource, parent, false);
        }

        // Find the category from the list of categories based on the specified position.
        HomeScreenCategory categoryPosition = homeScreenCategory.get(position);

        // Find the category ImageView ID and set the category image in the ImageView.
        ImageView categoryImageView = (ImageView) categoryView.findViewById(R.id.categoryImageView);
        categoryImageView.setImageResource(categoryPosition.getCategoryImageId());

        // Find the category TextView and set the category name in the TextView.
        TextView categoryTextView = (TextView) categoryView.findViewById(R.id.categoryTextView);
        categoryTextView.setText(categoryPosition.getCategoryName());

        return categoryView;
    }
}
