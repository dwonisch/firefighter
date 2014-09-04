package woni.FireFighter.Common;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.woni.firefighter.common.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "section_number";
	private String key;
	private String district;
	private IConfiguration configuration;
	private AlarmView view;
	private Context context;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static PlaceholderFragment newInstance(Context context, int sectionNumber, String key, String district, IConfiguration configuration) {
		PlaceholderFragment fragment = new PlaceholderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		fragment.configuration = configuration;
		fragment.district = district;
		fragment.context = context;
		fragment.key = key;
		return fragment;
	}

	public PlaceholderFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if(view == null){
			view = new AlarmView(context, new District(key, district), configuration);
			//view.updateData();
		}
    	return view;
	}
	
	public void onRefresh(){
		if(view != null)
			view.updateData();
	}
}