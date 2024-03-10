package status;

import java.util.ArrayList;
import java.util.List;

public class TeamStatus {
    public static List<CharacterStatus> members = new ArrayList<>();

    public void addMember(CharacterStatus member) {
        members.add(member);
    }

    public static List<CharacterStatus> getMembers() {
        return members;
    }

}
