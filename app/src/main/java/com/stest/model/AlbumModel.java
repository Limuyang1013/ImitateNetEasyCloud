package com.stest.model;

/**
 * Created by Limuyang on 2016/11/11.
 */

public class AlbumModel {
    private String album;

    public String getCoverUri() {
        return coverUri;
    }

    public void setCoverUri(String coverUri) {
        this.coverUri = coverUri;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    private String coverUri;
    private String artist;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AlbumModel)) return false;

        AlbumModel model = (AlbumModel) o;

        if (!getAlbum().equals(model.getAlbum())) return false;
        if (getCoverUri() != null ? !getCoverUri().equals(model.getCoverUri()) : model.getCoverUri() != null)
            return false;
        return getArtist() != null ? getArtist().equals(model.getArtist()) : model.getArtist() == null;

    }

    @Override
    public int hashCode() {
        int result = getAlbum().hashCode();
        result = 31 * result + (getCoverUri() != null ? getCoverUri().hashCode() : 0);
        result = 31 * result + (getArtist() != null ? getArtist().hashCode() : 0);
        return result;
    }
}
