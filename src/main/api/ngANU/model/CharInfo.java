package ngANU.model;

import protocol.RoleBean;

public class CharInfo {
    public int id;
    public String name;
    public int level;
    public int cls;
    public int gender;

    public static CharInfo fromRoleBean(RoleBean role) {
        CharInfo info = new CharInfo();

        try {
            info.id = role.base.id;
            info.name = role.base.name.getString();
            info.cls = role.base.cls;
            info.gender = role.base.gender;
            info.level = role.status.level;

            return info;
        }
        catch (Exception pokemon) {
            return null;
        }
    }
}
