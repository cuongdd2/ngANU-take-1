package ngANU.model;
/*
    universal Bean for protocol.RoleBean

    protocol.RoleBean is not really a Bean, it's constructed with plenty of
    functional and behavioral methods as well as non static inner classes.
    This class is a proper java Bean mapped from RoleBean. Note that this
    class is not perfect representation, most fields/properties are not mapped
    and some are commented out because unused/unimportant
 */
import com.goldhuman.Common.Octets;
import org.apache.commons.codec.binary.Hex;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import protocol.RoleBean;

import java.util.Vector;

public class CharBean {
    static class User {
        public int cash;
        public int money;
        public int cash_add;
        public int cash_buy;
        public int cash_sell;
        public int cash_used;
        public byte status;
    }
    public User user;

    static class Base {
        public byte version;
        public int id;
        public Octets name;
        public int race;
        public int cls;
        public byte gender;
        public String custom_data;
        public String config_data;
        public int custom_stamp;
        public byte status;
        public int delete_time;
        public int create_time;
        public int lastlogin_time;
        public String help_states;
        public int spouse;
        public int userid;
        public int reserved1;
    }
    public Base base;

    public static class Status {
        public byte version;
        public int level;
        public int level2;
        public int exp;
        public int sp;
        public int pp;
        public int hp;
        public int mp;
        public float posx;
        public float posy;
        public float posz;
        public int worldtag;
        public int invader_state;
        public int invader_time;
        public int pariah_time;
        public int reputation;
        public String custom_status;
        public String filter_data;
        public String charactermode;
        public String instancekeylist;
        public int dbltime_expire;
        public int dbltime_mode;
        public int dbltime_begin;
        public int dbltime_used;
        public int dbltime_max;
        public int time_used;
        public String dbltime_data;
        public short storesize;
        public String petcorral;
        public String property;
        public String var_data;
        public String skills;
        public String storehousepasswd;
        public String waypointlist;
        public String coolingtime;
        public String npc_relation;
        public String multi_exp_ctrl;
        public String storage_task;
        public String faction_contrib;
        public String force_data;
        public String online_award;
        public String profit_time_data;
        public byte reserved32;
        public int reserved4;
        public int reserved5;
    }
    public Status status;

    public static class Inventory {
        public int id;
        public int pos;
        public int count;
        public int max_count;
        public String data;
        public int proctype;
        public int expire_date;
        public int guid1;
        public int guid2;
        public int mask;
    }
    public Inventory[] equipment;

    public static class Pocket {

    }
    public Pocket pocket;

    public static class Storehouse {

    }
    public Storehouse storehouse;

    public static class ExtendedProperties {

    }
    public ExtendedProperties ep;

    public static CharBean map(RoleBean role) {
        AbstractConverter<Octets, String> octetsConverter = new AbstractConverter<Octets, String>() {
            @Override
            public String convert(Octets source) {
                try {
                    return Hex.encodeHexString(source.getBytes());
                } catch (Exception e) {
                    return "";
                }
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        mapper.addConverter(octetsConverter);

        return mapper.map(role, CharBean.class);
    }
}