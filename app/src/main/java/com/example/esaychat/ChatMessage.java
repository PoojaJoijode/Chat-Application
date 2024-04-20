package com.example.esaychat;

import android.net.Uri;

public class ChatMessage {
    private String text;
    private Uri imageUri;

    public ChatMessage(String text, Uri imageUri) {
        this.text = text;
        this.imageUri = imageUri;
    }

    public String getText() {
        return text;
    }

    public Uri getImageUri() {
        return imageUri;
    }
}
