package status;

import java.util.ArrayList;
import java.util.List;

public class TeamStatus {
    public static List<CharacterStatus> members = new ArrayList<>();

    public void addMember(CharacterStatus member) {
        members.add(member);
    }
    
    public static CharacterStatus getMember(int index) {
        if (index >= 0 && index < members.size()) {
            return members.get(index);
        } else {
            // 如果索引无效，可以返回null或者抛出一个异常
            return null;
        }
    }

    public static List<CharacterStatus> getMembers() {
        return members;
    }

    public static void reduceHealth(int index, int amount) {
        CharacterStatus member = getMember(index);
        if (member != null) {
            member.reduceHealth(amount);
        }
    }
}
