package src.backend;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Friendsaver {

    public void saveFriend(String name, String ipAddress) throws IOException {
        // 1. Read existing friends
        List<String> friends = new ArrayList<>(Persistenz.readUsers());

        // 2. Add new friend
        friends.add(String.format("%s;%s", name, ipAddress));

        // 3. Write back to database
        Persistenz.writeUsers(friends);
    }

    public List<String[]> loadFriends() throws IOException {
        List<String[]> friendList = new ArrayList<>();
        List<String> friends = Persistenz.readUsers();

        for (String friend : friends) {
            String[] friendData = friend.split(";");
            friendList.add(friendData);
        }

        return friendList;
    }

    public void removeFriend(String name, String ipAddress) throws IOException {
        List<String> friends = new ArrayList<>(Persistenz.readUsers());

        friends.removeIf(friend -> friend.equals(String.format("%s;%s", name, ipAddress)));

        Persistenz.writeUsers(friends);
    }

    // ... (other methods if needed)
}
