public class TP07_1_Rectangle {
    int width;
    int height;

    public TP07_1_Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int calculatePerimeter(){
        return (width + height) * 2;
    }

    public int calculateSurface(){
        return width * height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    
}