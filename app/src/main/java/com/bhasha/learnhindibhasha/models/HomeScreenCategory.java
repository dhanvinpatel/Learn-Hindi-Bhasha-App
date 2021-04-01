package com.bhasha.learnhindibhasha.models;

/**
 * HomeScreenCategory class represents the various categories to be display on the home screen.
 */
public class HomeScreenCategory {
    // Image ID of a category.
    private int categoryImageId;

    // Name of the category.
    private String categoryName;

    /**
     * Create new HomeScreenCategory object.
     *
     * @param categoryName    is the name of the category.
     * @param categoryImageId is the image ID of the category.
     */
    public HomeScreenCategory(String categoryName, int categoryImageId) {
        this.categoryName = categoryName;
        this.categoryImageId = categoryImageId;
    }

    /**
     * Get the image ID of the category.
     *
     * @return int the image ID.
     */
    public int getCategoryImageId() {
        return categoryImageId;
    }

    /**
     * Get the name of the category.
     *
     * @return String the category name.
     */
    public String getCategoryName() {
        return categoryName;
    }
}
