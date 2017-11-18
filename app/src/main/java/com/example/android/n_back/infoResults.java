package com.example.android.n_back;

/**
 * Created by lobar on 23.10.2017.
 */

public class infoResults {
    private int rights;
    public int getRights(){return this.rights;}
    public void setRights(int rights){this.rights=rights;}

    private int bads;
    public int getBads(){return this.bads;}
    public void setBads(int bads){this.bads=bads;}

    private int all;
    public int getAll(){int sum = this.bads+this.rights; return sum;}
    public void setAll(int all){this.all=all;}
}
