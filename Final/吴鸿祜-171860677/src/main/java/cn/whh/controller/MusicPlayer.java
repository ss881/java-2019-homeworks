package cn.whh.controller;

import javazoom.jl.player.Player;

public class MusicPlayer {
    String musicName;
    boolean isChanged;
    Player player;

    MusicPlayer(String pathname)
    {
        musicName=pathname;
        isChanged=false;
    }

    public void play()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true){
                        if(!isChanged)
                        {
                            player=new Player(this.getClass().getResourceAsStream(musicName));
                            //BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(musicName));
                            //player = new Player(buffer);
                            player.play();
                        }
                        else continue;
                    }

                }catch (Exception e)
                {
                    System.out.println(e);
                }
            }
        }).start();
    }

    public Player getPlayer(){return player;}

    public void setMusicName(String pathname)
    {
        isChanged=true;
        player.close();
        musicName=pathname;
        isChanged=false;
    }
}
