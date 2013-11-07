package edu.grinnell.appdev.grinnelldirectory;

import edu.grinnell.appdev.grinnelldirectory.dummy.Profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    Profile mItem;

    public ProfileDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = Profile.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_detail, container, false);
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.textUsername)).setText(mItem.username);
            ((TextView) rootView.findViewById(R.id.textFirstName)).setText(mItem.firstname);
            ((TextView) rootView.findViewById(R.id.textLastName)).setText(mItem.lastname);
        }
        return rootView;
    }
    
    
}
