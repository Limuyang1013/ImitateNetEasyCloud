package com.stest.InnerFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.stest.adapter.SingerAdapter;
import com.stest.fragment.BaseFragment;
import com.stest.model.ArtistInfo;
import com.stest.model.MusicInfoDetail;
import com.stest.neteasycloud.R;
import com.stest.view.DividerListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static com.stest.neteasycloud.R.raw.hs;

/**
 * Created by Limuyang on 2016/8/31.
 * 歌手
 */
public class SingersFragment extends Fragment implements View.OnClickListener {

    @ViewInject(R.id.lv)
    DividerListView lv;
    @ViewInject(R.id.popup_menu)

    private SingerAdapter mAdapter;
    private List<ArtistInfo> Info;
    private List<MusicInfoDetail> musicInfo;
    List<String> artist;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_singers, container, false);
        ViewUtils.inject(this, v);
        initWidgets();
        return v;
    }

    //初始化控件
    private void initWidgets() {
        Info = new ArrayList<>();
        musicInfo=new ArrayList<>();
        musicInfo=DataSupport.findAll(MusicInfoDetail.class);
        artist=new ArrayList<>();
        //填充数据
        for (int i=0;i< musicInfo.size();i++){

            artist.add(musicInfo.get(i).getArtist());
        }
        //去重
        artist=removeDuplicate(artist);
        for (int i=0;i<artist.size();i++){
            ArtistInfo artistInfo=new ArtistInfo();
            artistInfo.setArtist_name(artist.get(i));
            List<MusicInfoDetail> num = DataSupport.where("artist = ?", artist.get(i)).find(MusicInfoDetail.class);
            artistInfo.setNumber_of_tracks(num.size());
            Info.add(artistInfo);
        }

        mAdapter=new SingerAdapter(getContext(),Info,R.layout.singles_item_layout);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
        lv.setAdapter(mAdapter);


    }

    //List去重
    public  List<String> removeDuplicate(List<String> list)   {
        for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )   {
            for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )   {
                if  (list.get(j).equals(list.get(i)))   {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    @Override
    public void onClick(View v) {

    }
}
