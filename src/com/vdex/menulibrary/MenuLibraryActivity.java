package com.vdex.menulibrary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MenuLibraryActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.leaflet_list_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId())
    	{
	    	case R.id.menu_new_leaflet:
	    		 Intent intent = new Intent(this, AddLeafletActivity.class);
	    		 startActivity(intent);
	    		    //startActivityForResult(intent, PICK_CONTACT_REQUEST);
	    	return true;
    	
    	}
    	return super.onOptionsItemSelected(item);
    }
    
}