package ngANU.model;

import org.apache.commons.codec.binary.Hex;

import javax.xml.bind.annotation.XmlValue;

public class HexOctet {
    @XmlValue
    private String hexOctets;
    public void setHexOctets(byte[] octets) {
        hexOctets = Hex.encodeHexString(octets);
    }
    public String getHexOctets() {
        return hexOctets;
    }

    public HexOctet() {}
    public HexOctet(byte[] octets) { setHexOctets(octets); }

    @Override
    public String toString() {
        return hexOctets;
    }
}
