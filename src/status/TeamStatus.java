package status;

import java.util.ArrayList;
import java.util.List;

public class TeamStatus {
    private List<CharacterStatus> members = new ArrayList<>();

    public void addMember(CharacterStatus member) {
        members.add(member);
    }

    public List<CharacterStatus> getMembers() {
        return members;
    }

}
