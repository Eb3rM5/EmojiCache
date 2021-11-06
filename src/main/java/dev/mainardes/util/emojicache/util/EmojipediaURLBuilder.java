package dev.mainardes.util.emojicache.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class EmojipediaURLBuilder {

    public static final String EMOJIPEDIA_CDN = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/%s/%s/%s/%s_%s.png";

    private static final Map<String, String> EMOJI_LIST;

    public static String createURL(String unicode, final EmojiCollection collection, final EmojiSize size) {
        final var description = EMOJI_LIST.get(unicode.toUpperCase());
        if (description != null){
            return String.format(EMOJIPEDIA_CDN,
                                        size.getSize(),
                                            collection.getIdentity(),
                                                collection.getId(),
                                                    description,
                                                        unicode.toLowerCase());
        }
        return null;
    }

    private static BufferedReader getReaderFromEmojiList(){
        var input = EmojipediaURLBuilder.class.getResourceAsStream("/emoji-list.txt");
        Objects.requireNonNull(input);
        return new BufferedReader(new InputStreamReader(input));
    }

    static {
        Map<String, String> list = null;
        try (var reader = getReaderFromEmojiList()){
            list = reader.lines()
                    .map(line->line.split(",", 2))
                    .collect(Collectors.toMap(entry-> entry[0],
                                                entry -> entry[1],
                                                    (key, value)->key));
        } catch (IOException e){
            list = null;
            e.printStackTrace();
        } finally {
            EMOJI_LIST = list;
        }
    }

    private EmojipediaURLBuilder() {}

}
