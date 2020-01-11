package com.all.control;

import com.all.draw.PictureInterface;

import java.io.IOException;

public interface EnemyTeamInterface extends PictureInterface {
    void Change(String path, String orderPath) throws IOException;
}
