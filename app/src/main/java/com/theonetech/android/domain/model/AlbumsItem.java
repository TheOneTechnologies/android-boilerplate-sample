package com.theonetech.android.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumsItem {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("photoUrl")
        @Expose
        private String photoUrl;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("date")
        @Expose
        private String date;

        public AlbumsItem(int id, String name, String photoUrl, int count, String date) {
            this.id = id;
            this.name = name;
            this.photoUrl = photoUrl;
            this.count = count;
            this.date = date;

        }


    public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhotoUrl() {
            return photoUrl;
        }

        public void setPhotoUrl(String photoUrl) {
            this.photoUrl = photoUrl;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
