package com.zoobrew.rpi.sis;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class MainActivity extends FragmentActivity
	implements MenuFragment.OnMenuSelectedListener ,SubMenuFragment.OnSubMenuSelectedListener
{

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);    
    
    
        /* Check whether the activity is using the layout version with
       	   the fragment_container FrameLayout. If so, we must add the first fragment */
    
     if (findViewById(R.id.fragment_container) != null) {

        // However, if we're being restored from a previous state,
        // then we don't need to do anything and should return or else
        // we could end up with overlapping fragments.
        if (savedInstanceState != null) {
            return;
        }

        // Create an instance of MenuFragment
        MenuFragment firstFragment = new MenuFragment();

        // In case this activity was started with special instructions from an Intent,
        // pass the Intent's extras to the fragment as arguments
        firstFragment.setArguments(getIntent().getExtras());

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, firstFragment).commit();
    }
}
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    

public void onSubMenuSelected(int position) {
    // The user selected the SubMenu Item from the SubMenu
	/*

    // Capture the Item fragment from the activity layout
    SubMenuFragment smFrag = (SubMenuFragment)
          getSupportFragmentManager().findFragmentById(R.id.item_fragment);

    if (smFrag != null) {
        // If item frag is available, we're in two-pane layout...

        // Call a method in the ItemFragment to update its content
        smFrag.updateItemView(position);

    } else {
        // If the frag is not available, we're in the one-pane layout and must swap frags...

        // Create fragment and give it an argument for the selected article
        ItemFragment newFragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putInt(ItemFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }
    */
}

    public void onMenuSelected(int position) {
        // The user selected the SubMenu from the MenuFragment

        // Capture the SubMenu fragment from the activity layout
        SubMenuFragment submenuFrag = (SubMenuFragment)
              getSupportFragmentManager().findFragmentById(R.id.item_fragment);

        if (submenuFrag != null) {
            // If submenu fragment is available, we're in two-pane layout...

            // Call a method in the SubMenuFragment to update its content
            submenuFrag.updateSubMenuView(position);

        } 
        else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            SubMenuFragment newFragment = new SubMenuFragment();
            Bundle args = new Bundle();
            args.putInt(ItemFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

}