package com.a.model.controlled;

import com.all.model.Picture;
import com.all.draw.PictureInterface;
import com.all.utils.FileUtils;
import com.a.model.advance.Calabash;
import com.all.model.Color;
import com.all.model.Rank;

import java.util.ArrayList;
import java.util.List;

public class CalabashTeam implements PictureInterface {
    private List<Calabash> calabashes;

    public CalabashTeam() throws Exception {
        this.calabashes = new ArrayList<>();
        for(int i = 0; i < 7; ++i){
            Calabash calabash = new Calabash(Color.values()[i], Rank.values()[i], FileUtils.getImage("SRCFile/" + (i + 1) + ".jpg"));
            calabashes.add(calabash);
        }
    }

    @Override
    public List<Picture> getPictures() {
        List<Picture> pictureList = new ArrayList<>();
        for(int i = 0; i < 7; ++i){
            pictureList.add(calabashes.get(i).getPicture());
        }
        return  pictureList;
    }
}
