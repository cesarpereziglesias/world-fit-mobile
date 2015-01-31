package com.worldfit.worldfit.adapter;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.model.Challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tonimc on 31/1/15.
 */
public class ChallengeListAdapter extends ArrayAdapter<Challenge> {

    private final static String TAG = ChallengeListAdapter.class.getSimpleName();

    private LayoutInflater inflater;
    private Context mContext;
    List<Challenge> mListChallenges;


    public ChallengeListAdapter(Context context, List<Challenge> values) {
        super(context,  R.layout.fragment_challenge_list_item, values);
        this.mListChallenges = values;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.fragment_challenge_list_item, parent, false);
        }

        ((TextView)rowView.findViewById(R.id.title)).setText(this.mListChallenges.get(position).getName());

        initEvents(this.mListChallenges.get(position), rowView);

        return rowView;
    }

    private void initEvents(final Challenge challenge, final View row) {
        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, challenge.toString());
                Intent intent = new Intent(mContext, ChallengeActivity.class);
                intent.setAction(ContactsContract.CommonDataKinds.Note.ACTION_SHOW);
                intent.putExtra(ContactsContract.CommonDataKinds.Note.EXTRA_NOTE, challenge.toBundle());
            }
        });
    }


    @Override
    public long getItemId(int position) {
        return this.mNotes.get(position).getId();
    }
}
