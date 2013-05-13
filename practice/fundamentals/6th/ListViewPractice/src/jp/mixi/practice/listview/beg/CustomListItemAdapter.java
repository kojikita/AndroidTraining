
package jp.mixi.practice.listview.beg;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomListItemAdapter extends ArrayAdapter<String> {
    
    @SuppressWarnings("unused")
    private static final String TAG = CustomListItemAdapter.class.getSimpleName();
    
    private LayoutInflater mLayoutInflater;
    
    public CustomListItemAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
        mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        
        if (convertView == null) {
            view = mLayoutInflater.inflate(R.layout.custom_list_item, parent, false);
        } else {
            view = convertView;
        }
        
        String item = getItem(position);
        
        TextView text = (TextView) view.findViewById(R.id.TitleText);
        text.setText("Title:" + item);
        TextView text2 = (TextView) view.findViewById(R.id.SubTitleText);
        text2.setText("SubTitle:" + item);
        
        return view;
    }
}