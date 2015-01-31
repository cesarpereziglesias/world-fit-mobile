package com.worldfit.worldfit.fragment;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.worldfit.worldfit.R;

import com.worldfit.worldfit.fragment.dummy.DummyContent;
import com.worldfit.worldfit.model.Challenge;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 */
public class ChallengeListFragment extends ListFragment {

    private static final String TAG = ChallengeListFragment.class.getSimpleName();

    private Activity mParentActivity;

    private ListView mListChallenge;
    private ListAdapter mChallengeAdapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ChallengeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mainView = inflater.inflate(R.layout.fragment_challenge_list, container, false);
        this.mListChallenge = (ListView) mainView.findViewById(R.id.list_challenges);
        setup();
        initEvents();
        return mainView;
    }

    private void setup() {
        this.mListChallenge.setAdapter(new ListAdapter() {
            @Override
            public boolean areAllItemsEnabled() {
                return false;
            }

            @Override
            public boolean isEnabled(int position) {
                return false;
            }

            @Override
            public void registerDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver observer) {

            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return null;
            }

            @Override
            public int getItemViewType(int position) {
                return 0;
            }

            @Override
            public int getViewTypeCount() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }
        });
    }

    private void initEvents() {

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mParentActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mParentActivity = null;
    }


}
