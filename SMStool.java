public class SMStool {
    private String toUsername;
    private String title;
    private boolean end;
    private String content;

    public SMStool(String toUsername, String title, String content, boolean end) {
        this.toUsername = toUsername;
        this.title = title;
        this.content = content;
        this.end = end;
    }

    public String getToUsername() {
        return toUsername;
    }

    public void setToUsername(String toUsername) {
        this.toUsername = toUsername;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    
}
