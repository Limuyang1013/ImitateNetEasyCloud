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
public class MyrankAdapter extends BaseAdapter {
    private Context context;
    private List<Map<String, Object>> listItems;
    private LayoutInflater layoutInflater;

    public final class ListItemView {
        public ImageView imageView;
        public TextView rank_first_txt;
        public TextView rank_second_txt;
        public TextView rank_third_txt;
    }

    public MyrankAdapter(Context context, List<Map<String, Object>> listItems) {
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
            convertView = layoutInflater.inflate(R.layout.ranking_listview_adapter, null);
            //获取布局视图
            listItemView.imageView = (ImageView) convertView.findViewById(R.id.item_image);
            listItemView.rank_first_txt = (TextView) convertView.findViewById(R.id.rank_first_txt);
            listItemView.rank_second_txt = (TextView) convertView.findViewById(R.id.rank_second_txt);
            listItemView.rank_third_txt = (TextView) convertView.findViewById(R.id.rank_third_txt);
            convertView.setTag(listItemView);

            listItemView.imageView.setBackgroundResource((Integer) listItems.get(position).get("imageView"));
            listItemView.rank_first_txt.setText((String) listItems.get(position).get("rank_first_txt"));
            listItemView.rank_second_txt.setText((String) listItems.get(position).get("rank_second_txt"));
            listItemView.rank_third_txt.setText((String) listItems.get(position).get("rank_third_txt"));

        } else {
            listItemView = (ListItemView) convertView.getTag();

        }
        return convertView;
    }
}
