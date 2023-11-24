import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String username;
    private String email;
    private String password;
    private String full_name;
    private List <Post> posts = new ArrayList<Post>();
    private static List <User> users = new ArrayList<User>();
    private static User current_user;
    private User(String username,String email,String password,String full_name) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
        users.add(this);
    }
    public static void add_user(String username,String email,String password,String full_name){
        new User(username,email,password,full_name);
    }
    public static void load_users_from_file(String filename) throws Exception{
        try {
            String file_content = new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filename)));
            String[] lines = file_content.split("\n");
            for (String line : lines) {
                String[] parts = line.split(",");
                User.add_user(parts[0], parts[1], parts[2], parts[3]);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading from file: " + e.getMessage());
        }
    }
    public static void delete_post(int post_id) throws Exception{
        if (current_user == null){
            throw new Exception("No user is logged in");
        }
        if(current_user.posts.contains(Post.get_post(post_id))){
            current_user.posts.remove(Post.get_post(post_id));
            Post.remove(post_id);
        }
        else
            throw new Exception("Post does not belong to current user");
    }
    public static User login(String username,String password) throws Exception{
        for (User user:users){
            if (user.username == username && user.password == password){
                current_user = user;
                return user;
            }
        }
        throw new Exception("Invalid username or password");
    }
    public static void logout() throws Exception{
        if (current_user == null){
            throw new Exception("No user is logged in");
        }
        current_user = null;
    }
    
    public static void make_post(String post_body) throws Exception{
        if (current_user == null){
            throw new Exception("No user is logged in");
        }
        Post new_post =
                new Post(post_body,current_user);
        current_user.posts.add(new_post);
    }
    public static void make_comment(int post_id,String comment_body) throws Exception{
        if (current_user == null){
            throw new Exception("No user is logged in");
        }
        Comment new_comment =
                new Comment(comment_body,current_user,Post.get_post(post_id));
        Post.get_post(post_id).add_comment(new_comment);
    }
    public static void write_profile_to_file(String filename) throws Exception{
        if (current_user == null){
            throw new Exception("No user is logged in");
        }
        try {
            FileWriter writer = new FileWriter(filename);
            writer.write("Username: " + current_user.username + "\n");
            writer.write("Full Name: " + current_user.full_name + "\n");
            writer.write("Email: " + current_user.email + "\n");
            writer.write("Posts:\n");
            for (Post post : current_user.posts) {
                List<Comment> comments = post.get_comments();
                writer.write("\t" + post.get_caption() + "\n");
                writer.write("\tComments:\n");
                for (Comment comment : comments) {
                    writer.write("\t\t" + comment.get_text() + "\n\t\t\tBy: " + comment.get_user().full_name + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to file: " + e.getMessage());
        }
    }
}
