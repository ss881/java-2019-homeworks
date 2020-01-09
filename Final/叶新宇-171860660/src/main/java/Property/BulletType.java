package Property;

public enum BulletType {
    CALABASH("cbullet.png"),
    MONSTER("mbullet.png"),
    FLAME("flame.png"),
    WATER("water.png"),
    STINGER("stinger.png");
    final private String srcPath;
    BulletType(String srcPath) {
        this.srcPath = srcPath;
    }
    public String getImagePath() {
        return srcPath;
    }
}
