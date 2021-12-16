package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Solutions for day 16
 *
 * @author Evan Foley
 * @version 16 Dec 2021
 */
public class Day16 extends Daily {
    private Map<Character, String> map;
    private String binaryString;
    private List<Packet> packets;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day16(int year, String fileName) {
        super(year, fileName);
        map = setupMap();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input[0].length(); i++) {
            sb.append(map.get(input[0].charAt(i)));
        }
        binaryString = sb.toString();
        packets = new ArrayList<>();
        parsePacket(binaryString, 0, null);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("The sum of the version numbers is %d\n", packets.stream().mapToLong(Packet::getVersion).sum());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The value from the expression is %d\n", packets.get(0).getValue());
    }

    private int parsePacket(String str, int i, Packet parent) {
        int ver = Integer.parseInt(str.substring(i, i += 3), 2);
        int id = Integer.parseInt(str.substring(i, i += 3), 2);
        Packet p = new Packet(ver, id);
        packets.add(p);
        if(parent != null) {
            parent.packets.add(p);
        }
        if(id == 4) {
            StringBuilder sb = new StringBuilder();
            char c = '1';
            while(c == '1') {
                String s = str.substring(i, i += 5);
                c = s.charAt(0);
                sb.append(s.substring(1));
            }
            p.val = Long.parseLong(sb.toString(), 2);
            return i;
        } else {
            int numPackets;
            if(str.charAt(i++) == '0') {
                numPackets = Integer.parseInt(str.substring(i, i += 15), 2);
                int res = 0;
                while(numPackets > 0) {
                    res = parsePacket(str.substring(i), 0, p);
                    numPackets -= res;
                    i += res;
                }
                return i;
            } else {
                numPackets = Integer.parseInt(str.substring(i, i += 11), 2);
                while(numPackets > 0) {
                    i += parsePacket(str.substring(i), 0, p);
                    numPackets--;
                }
                return i;
            }
        }
    }

    private Map<Character, String> setupMap() {
        var m = new HashMap<Character, String>();
        m.put('0', "0000");
        m.put('1', "0001");
        m.put('2', "0010");
        m.put('3', "0011");
        m.put('4', "0100");
        m.put('5', "0101");
        m.put('6', "0110");
        m.put('7', "0111");
        m.put('8', "1000");
        m.put('9', "1001");
        m.put('A', "1010");
        m.put('B', "1011");
        m.put('C', "1100");
        m.put('D', "1101");
        m.put('E', "1110");
        m.put('F', "1111");
        return m;
    }

    private class Packet {
        int ver;
        int id;
        long val;
        List<Packet> packets;

        public Packet(int ver, int id) {
            this.ver = ver;
            this.id = id;
            this.val = -1L;
            packets = new ArrayList<>();
        }

        public long getVersion() {
            return ver;
        }

        public long getValue() {
            switch(id) {
                case 0:
                    return packets.size() == 1 ? packets.get(0).getValue() : packets.stream().mapToLong(Packet::getValue).sum();
                case 1:
                    return packets.size() == 1 ? packets.get(0).getValue() : packets.stream().mapToLong(Packet::getValue).reduce(1L, (a, b) -> a * b);
                case 2:
                    return packets.stream().mapToLong(Packet::getValue).min().getAsLong();
                case 3:
                    return packets.stream().mapToLong(Packet::getValue).max().getAsLong();
                case 4:
                    return val;
                case 5:
                    return packets.get(0).getValue() > packets.get(1).getValue() ? 1L : 0L;
                case 6:
                    return packets.get(0).getValue() < packets.get(1).getValue() ? 1L : 0L;
                case 7:
                    return packets.get(0).getValue() == packets.get(1).getValue() ? 1L : 0L;
                default:
                    return 0L;
            }
        }
    }
}