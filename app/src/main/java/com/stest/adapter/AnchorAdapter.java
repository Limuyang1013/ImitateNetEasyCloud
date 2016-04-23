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
 * Created by muyang on 2016/3/30.
 */
public class AnchorAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Map<String, Object>> anchorItems;

    public AnchorAdapter(Context context, List<Map<String, Object>> anchorItems) {
        this.context = context;
        this.anchorItems = anchorItems;
        layoutInflater = LayoutInflater.from(context);

    }

    public final class AnchorItemsView {
        private ImageView imageView;
        public TextView txt_team;
        public TextView txt_place;

    }

    @Override
    public int getCount() {
        return anchorItems.size();
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

        AnchorItemsView anchorItemsView = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.anchor_listview_adapter, null);
            anchorItemsView = new AnchorItemsView();

            anchorItemsView.imageView = (ImageView) convertView.findViewById(R.id.anchor_image);
            anchorItemsView.txt_team = (TextView) convertView.findViewById(R.id.anchor_first_txt);
            anchorItemsView.txt_place = (TextView) convertView.findViewById(R.id.anchor_second_txt);
            convertView.setTag(anchorItemsView);

            anchorItemsView.imageView.setBackgroundResource((Integer) anchorItems.get(position).get("imageView"));
            anchorItemsView.txt_team.setText((String) anchorItems.get(position).get("txt_team"));
            anchorItemsView.txt_place.setText((String ) anchorItems.get(position).get("txt_place"));

        } else {
            anchorItemsView = (AnchorItemsView) convertView.getTag();
        }
        return convertView;
    }
}
