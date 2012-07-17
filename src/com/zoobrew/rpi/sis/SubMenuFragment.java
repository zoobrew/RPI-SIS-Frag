package com.zoobrew.rpi.sis;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SubMenuFragment extends ListFragment {
	    OnSubMenuSelectedListener smCallback;
	    
	    final static String ARG_POSITION = "position";
	    int smCurrentPosition = 0;

	    // The container Activity must implement this interface so the frag can deliver messages
	    public interface OnSubMenuSelectedListener {
	        /** Called by MenuFragment when a list item is selected */
	        public void onSubMenuSelected(int position);
	    }

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	    	
	    	// If activity recreated (such as from screen rotate), restore
	        // the previous article selection set by onSaveInstanceState().
	        // This is primarily necessary when in the two-pane layout.
	        if (savedInstanceState != null) {
	            smCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
	        }
	        
	        super.onCreate(savedInstanceState);

	        // We need to use a different list item layout for devices older than Honeycomb
	        int layout = Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ?
	                android.R.layout.simple_list_item_activated_1 : android.R.layout.simple_list_item_1;

	        // Create an array adapter for the list view, using the Ipsum headlines array
	        ArrayAdapter<String> smenu = new ArrayAdapter<String>(getActivity(), layout, Titles.SubMenu[0]);
	        setListAdapter(smenu);
	    }

	    @Override
	    public void onStart() {
	        super.onStart();

	        // When in two-pane layout, set the listview to highlight the selected list item
	        // (We do this during onStart because at the point the listview is available.)
	        if (getFragmentManager().findFragmentById(R.id.item_fragment) != null) {
	            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	        }
	        
	        // During startup, check if there are arguments passed to the fragment.
	        // onStart is a good place to do this because the layout has already been
	        // applied to the fragment at this point so we can safely call the method
	        // below that sets the article text.
	        Bundle args = getArguments();
	        if (args != null) {
	            // Set article based on argument passed in
	            updateSubMenuView(args.getInt(ARG_POSITION));
	        } else if (smCurrentPosition != -1) {
	            // Set article based on saved instance state defined during onCreateView
	            updateSubMenuView(smCurrentPosition);
	        }
	    }
	    
	    public void updateSubMenuView(int position) {
	        //ListView submenu = (ListView) getActivity().findViewById(R.id.submenu);
	        setListAdapter(new ArrayAdapter<String>(getActivity(), R.layout.item, Titles.SubMenu[position]));
	        smCurrentPosition = position;
	     
	    }

	    @Override
	    public void onAttach(Activity activity) {
	        super.onAttach(activity);

	        // This makes sure that the container activity has implemented
	        // the callback interface. If not, it throws an exception.
	        try {
	            smCallback = (OnSubMenuSelectedListener) activity;
	        } catch (ClassCastException e) {
	            throw new ClassCastException(activity.toString()
	                    + " must implement OnSubMenuSelectedListener");
	        }
	    }

	    @Override
	    public void onListItemClick(ListView l, View v, int position, long id) {
	        // Notify the parent activity of selected item
	        smCallback.onSubMenuSelected(position);
	        
	        // Set the item as checked to be highlighted when in two-pane layout
	        getListView().setItemChecked(position, true);
	    }
}