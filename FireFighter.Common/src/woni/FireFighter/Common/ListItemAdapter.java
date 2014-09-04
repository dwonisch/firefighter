package woni.FireFighter.Common;

import java.util.ArrayList;
import java.util.List;

import com.woni.firefighter.common.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListItemAdapter extends ArrayAdapter<Mission>{
    Context context;
	
    public ListItemAdapter(Context context, int resource){
    	super(context, resource, new ArrayList<Mission>());
    	this.context = context;
    }

   public ListItemAdapter(Context context, int textViewResourceId,
            List<Mission> missions) {
        super(context, textViewResourceId, missions);
        this.context = context;
    }
   
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
        	try {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.row, null);
            } catch(Exception ex){
            	ex.printStackTrace();
            }
        }
        Mission item = super.getItem(position);
        if (item != null) {
        	String alarm = item.getAlarm();
        	
        	SetView(v, R.id.alarm, AlarmCategories.getCategoryByKey(alarm).getText());
        	SetView(v, R.id.date, item.getDate());
        	SetView(v, R.id.time, item.getTime());
        	SetView(v, R.id.station, item.getStation());
        }
        return v;
    }       
    
    private void SetView(View v, int view, String value){
    	
        TextView titleView = (TextView) v.findViewById(view);
        if(titleView != null){
            titleView.setText(value);
        }
    }

}
