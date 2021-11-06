package dev.mainardes.util.emojicache.util;

public enum EmojiSize {

    x72(72),
    x144(144),
    x160(160);

    private final int size;

    EmojiSize(final int size){
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
