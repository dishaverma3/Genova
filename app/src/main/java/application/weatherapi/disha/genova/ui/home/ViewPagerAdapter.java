package application.weatherapi.disha.genova.ui.home;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import application.weatherapi.disha.genova.ui.home.productList.ProductFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;


    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages.
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ProductFragment.newInstance("Men");
            case 1:
                return ProductFragment.newInstance("Women");
            case 2:
                return ProductFragment.newInstance("Unisex");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0: return "Men";
            case 1: return "Women";
            case 2: return "Unisex";
            default: return null;
        }
    }

}
