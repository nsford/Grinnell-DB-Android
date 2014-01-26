package edu.grinnell.appdev.grinnelldirectory;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import edu.grinnell.appdev.grinnelldirectory.dummy.Profile;

public class BasicSearchFragment extends Fragment{
	TextView firstNameText;
	TextView lastNameText;
	Button submitButton;
	Button switchButton;

	SearchFormActivity mActivity;
	View mView;

	// An intent for ProfileListActivity
	Intent listIntent;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_simple_search, container, false);
		mActivity = (SearchFormActivity) getActivity();
		
		initializeViews(mActivity); // Initialize all of the variables.
		addListenerOnSubmitButton(); // Add a listener to the submit button.
		return mView;
	}
	
	public void initializeViews(Context c) {
		firstNameText = (TextView) mView.findViewById(R.id.first_text);
		lastNameText = (TextView) mView.findViewById(R.id.last_text);
		submitButton = (Button) mView.findViewById(R.id.submit_button);
		switchButton = (Button) mView.findViewById(R.id.detailed_switch_button);

		
		switchButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				DetailedSearchFragment detailSearch = new DetailedSearchFragment();
				getFragmentManager().beginTransaction()
						.replace(R.id.fragment_container, detailSearch).commit();
			}
		});
		
		listIntent = new Intent(mActivity, ProfileListActivity.class);
	}

	
	
	// Adds a listener to the submit button.
		// The responding method gets the information from the fields,
		// and pieces together a valid DB request URL.
		// This is passed to RequestTask.
		// The ProfileListActivity intent is started.
		public void addListenerOnSubmitButton() {

			final Context context = mActivity;
			submitButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {

					String theURL = "https://itwebapps.grinnell.edu/classic/asp/campusdirectory/GCdefault.asp?transmit=true&blackboardref=true&LastName="
							+ mActivity.cleanString(lastNameText.getText().toString())
							+ "&LNameSearch=startswith&FirstName="
							+ mActivity.cleanString(firstNameText.getText().toString());

					// TODO: Get rid of the fucking uberstring

					ArrayList<Profile> profileList;
					try {
						profileList = new RequestTask().execute(theURL).get();
						ProfileListActivity.setData(profileList);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					startActivity(listIntent);
				}
			});
		}
}
