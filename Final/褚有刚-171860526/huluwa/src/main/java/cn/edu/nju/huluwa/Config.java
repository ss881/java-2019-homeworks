package cn.edu.nju.huluwa;

import cn.edu.nju.huluwa.util.PropPath;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

@PropPath(getPropPath = "/props/config.prop")
public class Config {
    public static int ROW;
    public static int COL;
    public static int GRID_WIDTH;
    public static int GRID_HEIGHT;

    public static double INIT_HEALTH;
    public static double INIT_DAMAGE;
    public static double INCR_DAMAGE;
    public static long SLEEP_TIME;

    public static double BULLET_SPEED;
    public static int BULLET_RADIUS;
    public static int REFRESH_INTER;    // milliseconds

    static {
        PropPath propPath = Config.class.getAnnotation(PropPath.class);
        Properties props = new Properties();
        try (Reader reader = new InputStreamReader(
                Config.class.getResourceAsStream(propPath.getPropPath()), "utf-8")) {
            props.load(reader);
            ROW = Integer.parseInt(props.getProperty("ROW"));
            COL = Integer.parseInt(props.getProperty("COL"));
            GRID_WIDTH = Integer.parseInt(props.getProperty("GRID_WIDTH"));
            GRID_HEIGHT = Integer.parseInt(props.getProperty("GRID_HEIGHT"));
            INIT_HEALTH = Double.parseDouble(props.getProperty("INIT_HEALTH"));
            INIT_DAMAGE = Double.parseDouble(props.getProperty("INIT_DAMAGE"));
            INCR_DAMAGE = Double.parseDouble(props.getProperty("INCR_DAMAGE"));
            SLEEP_TIME = Long.parseLong(props.getProperty("SLEEP_TIME"));
            BULLET_SPEED = Double.parseDouble(props.getProperty("BULLET_SPEED"));
            BULLET_RADIUS = Integer.parseInt(props.getProperty("BULLET_RADIUS"));
            REFRESH_INTER = Integer.parseInt(props.getProperty("REFRESH_INTER"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Config() {

    }
}
