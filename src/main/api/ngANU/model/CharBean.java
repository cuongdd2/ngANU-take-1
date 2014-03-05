package ngANU.model;
/*
    universal Bean for protocol.RoleBean

    protocol.RoleBean is not really a Bean, it's constructed with plenty of
    functional and behavioral methods as well as non static inner classes.
    Thus cannot be serialized using convention, only using internal procedures.
    It also does not support serializing extended_properties (ep) property.
    This class is (almost) a proper java Bean mapped from RoleBean. Note that this
    class is not perfect representation, most fields/properties are not mapped
    and some are commented out due to being unused/unimportant
 */
import com.goldhuman.Common.Octets;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import protocol.RoleBean;

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
        public String name;
        public int race;
        public int cls;
        public byte gender;
        public HexOctet custom_data;
        public HexOctet config_data;
        public int custom_stamp;
        public byte status;
        public int delete_time;
        public int create_time;
        public int lastlogin_time;
        public HexOctet help_states;
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
        public HexOctet custom_status;
        public HexOctet filter_data;
        public HexOctet charactermode;
        public HexOctet instancekeylist;
        public int dbltime_expire;
        public int dbltime_mode;
        public int dbltime_begin;
        public int dbltime_used;
        public int dbltime_max;
        public int time_used;
        public HexOctet dbltime_data;
        public short storesize;
        public HexOctet petcorral;
        public HexOctet property;
        public HexOctet var_data;
        public HexOctet skills;
        public HexOctet storehousepasswd;
        public HexOctet waypointlist;
        public HexOctet coolingtime;
        public HexOctet npc_relation;
        public HexOctet multi_exp_ctrl;
        public HexOctet storage_task;
        public HexOctet faction_contrib;
        public HexOctet force_data;
        public HexOctet online_award;
        public HexOctet profit_time_data;
        public byte reserved32;
        public int reserved4;
        public int reserved5;
    }
    public Status status;

    public static class ExtendedProperties {
        public int vitality;
        public int energy;
        public int strength;
        public int agility;
        public int max_hp;
        public int max_mp;
        public int hp_gen;
        public int mp_gen;
        public int walk_speed;
        public int run_speed;
        public int swim_speed;
        public int flight_speed;
        public int attack;
        public int damage_low;
        public int damage_high;
        public int attack_speed;
        public int attack_range;
        public int[] addon_damage_low;
        public int[] addon_damage_high;
        public int damage_magic_low;
        public int damage_magic_high;
        public int[] resistance;
        public int defense;
        public int armor;
        public int max_ap;
    }
    public ExtendedProperties ep;

    public static class Inventory {
        public int id;
        public int pos;
        public int count;
        public int max_count;
        public HexOctet data;
        public int proctype;
        public int expire_date;
        public int guid1;
        public int guid2;
        public int mask;
    }
    public Inventory[] equipment;

    public static class Pocket {
        public int capacity;
        public int timestamp;
        public int money;
        public Inventory[] items;
        public int reserved1;
        public int reserved2;
    }
    public Pocket pocket;

    public static class Storehouse {
        public int capacity;
        public int money;
        public Inventory[] items;
        public byte size1;
        public byte size2;
        public Inventory[] dress;
        public Inventory[] material;
        public int reserved;
    }
    public Storehouse storehouse;

    public static CharBean map(RoleBean role) {
        AbstractConverter<Octets, String> octetsToStringConverter = new AbstractConverter<Octets, String>() {
            @Override
            public String convert(Octets source) {
                try {
                    //return new String(source.getBytes(), "UTF-8");
                    return source.getString();
                } catch (Exception e) {
                    return "";
                }
            }
        };
        AbstractConverter<Octets, HexOctet> octetsToHexStringConverter = new AbstractConverter<Octets, HexOctet>() {
            @Override
            public HexOctet convert(Octets source) {
                try {
                    return new HexOctet(source.getBytes());
                } catch (Exception e) {
                    return null;
                }
            }
        };
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setFieldMatchingEnabled(true);
        mapper.addConverter(octetsToStringConverter);
        mapper.addConverter(octetsToHexStringConverter);

        return mapper.map(role, CharBean.class);
    }
}