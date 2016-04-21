package com.savekirk.helloagera.data;

public class NoteItem {
    public final String id;
    public final String content;
    public final String details;

    public NoteItem(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    @Override
    public String toString() {
            return content;
        }
}
