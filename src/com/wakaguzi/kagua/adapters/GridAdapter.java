
package com.wakaguzi.kagua.adapters;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wakaguzi.kagua.R;
import com.wakaguzi.kagua.fragments.TestGridFragment;
import com.wakaguzi.kagua.util.ImageLoader;

public class GridAdapter extends BaseAdapter {

	private Activity activity;
    private ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    
    public GridAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
    }
    
    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return data.get(position);
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.crown_outlets_list_row, null);

        
        TextView NAME = (TextView)vi.findViewById(R.id.name); 
        TextView PHONE = (TextView)vi.findViewById(R.id.contact); 
        TextView LAST_VISIT= (TextView)vi.findViewById(R.id.visit);  
        ImageView thumb_image=(ImageView)vi.findViewById(R.id.list_image); 
        
        
        HashMap<String, String> hash = new HashMap<String, String>();
        hash = data.get(position);
       
        
        // Setting all values in Outlets Listview
        
        NAME.setText(hash.get(TestGridFragment.KEY_NAME));
        PHONE.setText(hash.get(TestGridFragment.KEY_PHONE));
        LAST_VISIT.setText(hash.get(TestGridFragment.KEY_LAST_VISIT));
        imageLoader.DisplayImage(hash.get(TestGridFragment.KEY_IMAGE_URL), thumb_image);
        return vi;
    }

}
