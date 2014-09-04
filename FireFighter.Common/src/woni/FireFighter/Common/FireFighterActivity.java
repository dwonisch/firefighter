package woni.FireFighter.Common;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.egoclean.android.widget.flinger.ViewFlinger;
import com.egoclean.android.widget.flinger.ViewFlinger.OnScreenChangeListener;
import com.woni.firefighter.common.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FireFighterActivity extends Activity {

	private static String SettingsFile = "FireFighter.settings";
	private static String LastSelectedDistrictKey = "LastSelectedDistrict";
    protected IConfiguration configuration;
    private AlarmView currentView;
    private ViewPager pager;
    private SectionsPagerAdapter adapter;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        SharedPreferences settings = getSharedPreferences(SettingsFile, 0);
        String lastDistrict = settings.getString(LastSelectedDistrictKey, configuration.getDistricts().keySet().iterator().next());
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adapter = new SectionsPagerAdapter(this, getFragmentManager(), configuration.getDistricts(), configuration);

        // Set up the ViewPager with the sections adapter.
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        
        pager.setOnPageChangeListener(new OnPageChangeListener() {
            public void onPageSelected(int position) {
            	AlarmView view = (AlarmView) pager.getChildAt(position);
            	view.updateData();
            }

            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // Code goes here
            }

            public void onPageScrollStateChanged(int state) {
                // Code goes here
            }
        });
        
        onRefresh();
        
       /* flinger.setOnScreenChangeListener(new OnScreenChangeListener() {
			
			public void onScreenChanging(View newScreen, int newScreenIndex) {
				// TODO Auto-generated method stub
			}
			
			public void onScreenChanged(View newScreen, int newScreenIndex) {
				// TODO Auto-generated method stub
				if(currentView != null)
					currentView.cancel();
				
				if(newScreen != null){
					AlarmView alarms = (AlarmView)newScreen;
					currentView = alarms;
					
					if(!alarms.getIsLoaded())
						onRefresh();
					
					SharedPreferences settings = getSharedPreferences(SettingsFile, 0);
					Editor editor = settings.edit();
					editor.putString(LastSelectedDistrictKey, alarms.getDistrict());
					editor.commit();
				}
			}
		});*/
    }
    
	public void onRefresh(){
		int current = pager.getCurrentItem();
		PlaceholderFragment f = (PlaceholderFragment)adapter.getItem(current);
		f.onRefresh();
	}

	public void onRefresh(View v){
		onRefresh();
    }
	
	private void onRefresh(AlarmView view){
    	AlarmView v = view;
    	v.updateData();
	}
	
	public void onSettingsClick(View view){
		
	}
}
