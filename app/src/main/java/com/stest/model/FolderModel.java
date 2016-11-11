package com.stest.model;

/**
 * Created by Limuyang on 2016/11/11.
 */

public class FolderModel {
    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public String getFolder_path() {
        return folder_path;
    }

    public void setFolder_path(String folder_path) {
        this.folder_path = folder_path;
    }

    private String folder_name;
    private String folder_path;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FolderModel)) return false;

        FolderModel model = (FolderModel) o;

        if (getFolder_name() != null ? !getFolder_name().equals(model.getFolder_name()) : model.getFolder_name() != null)
            return false;
        return getFolder_path() != null ? getFolder_path().equals(model.getFolder_path()) : model.getFolder_path() == null;

    }

    @Override
    public int hashCode() {
        int result = getFolder_name() != null ? getFolder_name().hashCode() : 0;
        result = 31 * result + (getFolder_path() != null ? getFolder_path().hashCode() : 0);
        return result;
    }
}
