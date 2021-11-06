package dev.mainardes.util.emojicache.util;

public enum EmojiCollection {

    MICROSOFT("microsoft", 309),
    APPLE("apple", 285);

    private final String identity;
    private final int id;

    EmojiCollection(final String identity, final int id){
        this.identity = identity;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getIdentity() {
        return identity;
    }
}
