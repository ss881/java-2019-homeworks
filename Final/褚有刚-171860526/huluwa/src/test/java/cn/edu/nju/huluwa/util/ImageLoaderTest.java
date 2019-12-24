package cn.edu.nju.huluwa.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ImageLoaderTest {

    @Test(expected = Exception.class)
    public void getImage() {
        ImageLoader.getImage("aaa");
    }
}