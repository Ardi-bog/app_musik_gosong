package com.example.boss.go_song.app;

public class dataGenre {

    private String id,genre;

    public dataGenre(){ }

    public dataGenre(String id, String genre){
      this.genre = genre;
      this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }



}
