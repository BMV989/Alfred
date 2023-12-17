package org.chatbot.app.Alfred.telegram;

import org.chatbot.app.Alfred.youtube.Items;

public class ItemsArray {
    private final Items[] items;
    private int pos = 0;
    public ItemsArray(Items[] items) {
        this.items = items;
    }
    public long length() {
        return items.length;
    }
    public Items next() {
        pos = (pos + 1) % items.length;
        return items[pos];
    }
    public Items prev() {
        pos = (pos - 1) % items.length;
        if (pos < 0 ) pos += items.length;
        return items[pos];
    }

}
