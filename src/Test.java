public class Test {
    public static void main(String[] args){
        try {
            User.add_user("user1","1@gmail.com","1234","User 1");
            User.add_user("user2","2@gmail.com","1234","User 2");
            User.login("user1","1234");
            User.make_post("Hello World");
            User.logout();
            User.login("user2","1234");
            User.make_comment(0,"Nice post");
            User.logout();
            User.login("user1","1234");
            User.make_comment(0,"Thanks");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            User.write_profile_to_file("user1.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try{
            User.load_users_from_file("users.txt");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            User.delete_post(0);
            User.write_profile_to_file("user1_post_deleted.txt");
            User.logout();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
