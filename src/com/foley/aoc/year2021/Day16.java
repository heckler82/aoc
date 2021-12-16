package com.foley.aoc.year2021;

import com.foley.aoc.util.Daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Solutions for day 16
 *
 * @author Evan Foley
 * @version 16 Dec 2021
 */
public class Day16 extends Daily {
    private Map<Character, String> map;
    private String binaryString;
    private Packet root;

    /**
     * Creates a new daily
     *
     * @param year The year
     * @param fileName The name of the input file
     */
    public Day16(int year, String fileName) {
        super(year, fileName);
        map = setupMap();
        binaryString = input[0].chars().boxed()
                .map(i -> map.get((char)i.intValue()))
                .collect(Collectors.joining(""));
        StringBuilder sb = new StringBuilder();
        parsePacket(binaryString, 0, null);
    }

    @Override
    /**
     * Accomplishes the first task for the day
     */
    public void task1() {
        System.out.printf("The sum of the version numbers is %d\n", root.getVersion());
    }

    @Override
    /**
     * Accomplishes the second task for the day
     */
    public void task2() {
        System.out.printf("The value from the expression is %d\n", root.getValue());
    }

    private int parsePacket(String str, int i, Packet parent) {
        int ver = Integer.parseInt(str.substring(i, i += 3), 2);
        int id = Integer.parseInt(str.substring(i, i += 3), 2);
        Packet p = new Packet(ver, id);
        if(parent != null) {
            parent.packets.add(p);
        } else {
            root = p;
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
            } else {
                numPackets = Integer.parseInt(str.substring(i, i += 11), 2);
                while(numPackets > 0) {
                    i += parsePacket(str.substring(i), 0, p);
                    numPackets--;
                }
            }
        }
        return i;
    }

    private Map<Character, String> setupMap() {
        return IntStream.rangeClosed(0, 15)
                .boxed()
                .collect(Collectors.toMap(
                        i -> Character.toUpperCase(Integer.toHexString(i).charAt(0)),
                        i -> String.format("%4s", Integer.toBinaryString(i)).replace(' ', '0')));
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
            return ver + packets.stream().mapToLong(Packet::getVersion).sum();
        }

        public long getValue() {
            switch(id) {
                case 0:
                    return packets.stream().mapToLong(Packet::getValue).sum();
                case 1:
                    return packets.stream().mapToLong(Packet::getValue).reduce(1L, (a, b) -> a * b);
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