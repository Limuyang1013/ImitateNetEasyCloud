package com.stest.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.stest.model.MusicInfoDetail;

import java.util.List;

/**
 * Created by Limuyang on 2016/9/1.
 */
public class MusicUtils {
    /**
     * 扫描歌曲
     */
    public static void scanMusic(Context context, List<MusicInfoDetail> musicList) {
        musicList.clear();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor == null) {
            return;
        }
        while (cursor.moveToNext()) {
            // 是否为音乐
            int isMusic = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if (isMusic == 0) {
                continue;
            }
            long id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            // 标题
            String title = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            // 艺术家
            String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            // 专辑
            String album = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
            // 持续时间
            long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            // 音乐uri
            String uri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            // 专辑封面id，根据该id可以获得专辑图片uri
            long albumId = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            String coverUri = getCoverUri(context, albumId);
            // 音乐文件名
            String fileName = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
            // 音乐文件大小
            long fileSize = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            // 发行时间
            String year = cursor.getString((cursor.getColumnIndex(MediaStore.Audio.Media.YEAR)));
            MusicInfoDetail music = new MusicInfoDetail();
            music.setId(id);
            music.setType(MusicInfoDetail.Type.LOCAL);
            music.setTitle(title);
            music.setArtist(artist);
            music.setAlbum(album);
            music.setDuration(duration);
            music.setUri(uri);
            music.setCoverUri(coverUri);
            music.setFileName(fileName);
            music.setFileSize(fileSize);
            music.setYear(year);
            musicList.add(music);
        }
        cursor.close();
    }

    private static String getCoverUri(Context context, long albumId) {
        String uri = null;
        Cursor cursor = context.getContentResolver().query(
                Uri.parse("content://media/external/audio/albums/" + albumId),
                new String[]{"album_art"}, null, null, null);
        if (cursor != null) {
            cursor.moveToNext();
            uri = cursor.getString(0);
            cursor.close();
        }
        return uri;
    }


}
