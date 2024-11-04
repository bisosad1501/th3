package com.example.th3.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.th3.ui.fragments.CovidListFragment;
import com.example.th3.ui.fragments.CovidDetailFragment;
import com.example.th3.ui.fragments.CovidSearchStatFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new CovidListFragment();
            case 1:
                return new CovidDetailFragment();
            case 2:
                return new CovidSearchStatFragment();
            default:
                return new CovidListFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3; // Số lượng tab
    }
}