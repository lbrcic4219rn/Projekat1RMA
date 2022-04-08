package rs.raf.projekat1.view.viewpager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.view.fragments.NewTicketFragment;
import rs.raf.projekat1.view.fragments.ProfileFragment;
import rs.raf.projekat1.view.fragments.StatisticsFragment;
import rs.raf.projekat1.view.fragments.TicketListFragment;
import rs.raf.projekat1.view.fragments.listFragments.DoneFragment;
import rs.raf.projekat1.view.fragments.listFragments.InProgressFragment;
import rs.raf.projekat1.view.fragments.listFragments.ToDoFragment;

public class PagerAdapterTabs extends FragmentPagerAdapter {

    private final int FRAGMENT_COUNT = 3;
    public static final int FRAGMENT_TODO = 0;
    public static final int FRAGMENT_IN_PROGRESS = 1;
    public static final int FRAGMENT_DONE = 2;


    public PagerAdapterTabs(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case FRAGMENT_TODO: fragment = new ToDoFragment(); break;
            case FRAGMENT_IN_PROGRESS: fragment = new InProgressFragment(); break;
            default: fragment = new DoneFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case FRAGMENT_TODO: return "ToDo";
            case FRAGMENT_IN_PROGRESS: return "In progress";
            default: return "done";
        }
    }
}
