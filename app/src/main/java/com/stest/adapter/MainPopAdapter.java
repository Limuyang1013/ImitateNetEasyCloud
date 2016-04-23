package com.stest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.stest.neteasycloud.R;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/29.
 */
public class MainPopAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> listItems;
    private LayoutInflater layoutInflater;

    public final class ListItemView {
        public ImageView imageView;
        public TextView txt_name;
        public TextView txt_author;
    }

    public MainPopAdapter(Context context, List<Map<String, Object>> listItems) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.listItems = listItems;
    }

    @Override
    public int getCount() {
        return listItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListItemView listItemView = null;
        if (convertView == null) {
            listItemView = new ListItemView();
            convertView=layoutInflater.inflate(R.layout.main_pop_listview_item, null);
            //获取布局视图
            listItemView.imageView = (ImageView) convertView.findViewById(R.id.list_delete);
            listItemView.txt_author = (TextView) convertView.findViewById(R.id.txt_author);
            listItemView.txt_name = (TextView) convertView.findViewById(R.id.txt_name);
            convertView.setTag(listItemView);

            listItemView.imageView.setBackgroundResource((Integer) listItems.get(position).get("imageView"));
            listItemView.txt_author.setText((String) listItems.get(position).get("txt_author"));
            listItemView.txt_name.setText((String) listItems.get(position).get("txt_name"));
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }


        return convertView;
    }
}
