import java.util.ArrayList;
import java.util.List;

public class Post {
    private String caption;
    private User user;
    private List <Comment> comments = new ArrayList<Comment>();
    private static List <Post> posts = new ArrayList<Post>();
    public Post(String caption,User user) {
        this.caption = caption;
        this.user = user;
        posts.add(this);
    }
    public static void remove(int post_id){
        posts.remove(post_id);
    }
    public void add_comment(Comment comment){
        comments.add(comment);
    }
    public String get_caption() {
        return caption;
    }
    public User get_user() {
        return user;
    }
    public static Post get_post(int post_id){
        return posts.get(post_id);
    }
    public List<Comment> get_comments() {
        return comments;
    }
    public void show_post(){
        System.out.println("Caption: " + caption);
        System.out.println("Comments:");
        for (Comment comment:comments){
            System.out.println("  " + comment.get_text());
        }
    }
}
