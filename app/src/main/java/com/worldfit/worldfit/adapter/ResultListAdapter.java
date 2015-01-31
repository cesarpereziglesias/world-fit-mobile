package com.worldfit.worldfit.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.worldfit.worldfit.R;
import com.worldfit.worldfit.activity.ChallengeActivity;
import com.worldfit.worldfit.model.Challenge;
import com.worldfit.worldfit.model.Result;

import java.util.List;

/**
 * Created by tonimc on 31/1/15.
 */
public class ResultListAdapter extends ArrayAdapter<Result> {

    private final static String TAG = ResultListAdapter.class.getSimpleName();

    private Context mContext;
    List<Result> mResultList;


    public ResultListAdapter(Context context, List<Result> values) {
        super(context,  R.layout.activity_challenge_result_item, values);
        this.mResultList = values;
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.activity_challenge_result_item, parent, false);
        }

        ((TextView)rowView.findViewById(R.id.participant_name)).setText(this.mResultList.get(position).getName() +
        " (" + this.mResultList.get(position).getValue() + " Steps) ");

        this.mResultList.get(position).getUser().setAvatar(mContext, (ImageView) rowView.findViewById(R.id.participant_avatar));

        return rowView;
    }

}
