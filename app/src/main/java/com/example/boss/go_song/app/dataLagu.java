package com.example.boss.go_song.app;

public class dataLagu {
    private String id;
    private String judul;
    private String artis;

    public dataLagu(){}
    public dataLagu(String id, String judul, String artis){
        this.judul = judul;
        this.artis = artis;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getArtis() {
        return artis;
    }

    public void setArtis(String artis) {
        this.artis = artis;
    }

}
