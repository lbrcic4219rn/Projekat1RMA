package rs.raf.projekat1.view.viewpager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import rs.raf.projekat1.view.fragments.NewTicketFragment;
import rs.raf.projekat1.view.fragments.ProfileFragment;
import rs.raf.projekat1.view.fragments.StatisticsFragment;
import rs.raf.projekat1.view.fragments.TicketListFragment;

public class PageAdapterMenu extends FragmentPagerAdapter {

    private final int FRAGMENT_COUNT = 4;
    public static final int FRAGMENT_STATISTICS = 0;
    public static final int FRAGMENT_NEW_TICKET = 1;
    public static final int FRAGMENT_TICKET_LIST = 2;
    public static final int FRAGMENT_PROFILE = 3;

    public PageAdapterMenu(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position){
            case FRAGMENT_STATISTICS: fragment = new StatisticsFragment(); break;
            case FRAGMENT_NEW_TICKET: fragment = new NewTicketFragment(); break;
            case FRAGMENT_TICKET_LIST: fragment = new TicketListFragment(); break;
            default: fragment = new ProfileFragment(); break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return FRAGMENT_COUNT;
    }
}
