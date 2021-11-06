package dev.mainardes.util.emojicache.cache;

import dev.mainardes.cache.EntryCreator;
import dev.mainardes.util.emojicache.util.EmojiCollection;
import dev.mainardes.util.emojicache.util.EmojiSize;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import static dev.mainardes.util.emojicache.util.EmojipediaURLBuilder.createURL;

public class EmojiCreator implements EntryCreator<String, Image> {

    private EmojiCollection collection;
    private EmojiSize size;

    public EmojiCreator(EmojiCollection collection, EmojiSize size){
        this.collection = collection;
        this.size = size;
    }

    public void setCollection(EmojiCollection collection) {
        this.collection = collection;
    }

    public void setSize(EmojiSize size) {
        this.size = size;
    }

    @Override
    public Image create(String unicode, OutputStream outputStream) {
        var url = createURL(unicode, collection, size);
        if (url != null){
            try (var input = new URL(url).openStream(); outputStream){
                var image = ImageIO.read(input);
                ImageIO.write(image, "png", outputStream);
                return SwingFXUtils.toFXImage(image, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Image get(InputStream inputStream) {
        try (inputStream){
            return new Image(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String name(String unicode) {
        return collection.getIdentity().toLowerCase() + "-" + unicode.toLowerCase() + "-" + size.getSize();
    }

    @Override
    public String getExtension() {
        return "png";
    }

}
