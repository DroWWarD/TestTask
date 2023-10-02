package org.example;

public class StartPosition {
    int indentHeight;
    int indentWidth;

    public int getIndentHeight() {
        return indentHeight;
    }

    public void setIndentHeight(int indentHeight) {
        this.indentHeight = indentHeight;
    }

    public int getIndentWidth() {
        return indentWidth;
    }

    public void setIndentWidth(int indentWidth) {
        this.indentWidth = indentWidth;
    }

    public StartPosition(int indentHeight, int indentWidth) {
        this.indentHeight = indentHeight;
        this.indentWidth = indentWidth;
    }
}
