public class Comment {
    private String text;
    private User user;
    private Post post;
    public Comment(String text, User user, Post post) {
        this.text = text;
        this.user = user;
        this.post = post;
    }
    public String get_text() {
        return text;
    }
    public User get_user() {
        return user;
    }
    public Post get_post() {
        return post;
    }
}