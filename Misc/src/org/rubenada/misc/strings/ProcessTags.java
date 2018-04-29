package org.rubenada.misc.strings;

import java.util.*;

/**
 * Given a collection of Strings with the form: "source|number|comma-separated-list-of-tags",
 * extract all tags and print them from highest to lowest number of occurrences; in case two tags have the same
 * number of occurrences, print them if lexicographical order based on the tag
 */
public class ProcessTags {

    public static void main(String[] args) {
        ArrayList<String> stream = new ArrayList<>(
                Arrays.asList(
                        "system.load.1|1|host:a,role:web,availability-zone:us-east-1a",
                        "system.load.15|1|host:b,role:web,availability-zone:us-east-1b",
                        "system.cpu.user|20|host:a,role:web,availability-zone:us-east-1a",
                        "postgresql.locks|12|host:c,role:db,db_role:master,availability-zone:us-east-1e",
                        "postgresql.db.count|2|host:d,role:db,db_role:replica,availability-zone:us-east-1a",
                        "kafka.consumer.lag|20000|host:e,role:intake,availability-zone:us-east-1a",
                        "kafka.consumer.offset|3000000|host:e,role:intake,availability-zone:us-east-1a",
                        "kafka.broker.offset|25000|host:f,role:kafka,availability-zone:us-east-1a"
                ));
                // Expected result:
                // 6 availability-zone:us-east-1a
                // 3 role:web
                // 2 host:a
                // 2 host:e
                // 2 role:db
                // 2 role:intake
                // 1 availability-zone:us-east-1b
                // 1 availability-zone:us-east-1e
                // 1 db_role:master
                // 1 db_role:replica
                // 1 host:b
                // 1 host:c
                // 1 host:d
                // 1 host:f
                // 1 role:kafka

        process(stream);
    }

    public static void process(Collection<String> input) {

        // map to store: tag -> number of occurrences
        Map<String, Integer> occurrencesMap = new HashMap<>();
        for (String s : input) {
            String[] strings = s.split("\\|");
            String tags = strings[2];
            for (String tag : tags.split(",")) {
                Integer value = occurrencesMap.get(tag);
                occurrencesMap.put(tag, (value == null ? 1 : value + 1));
            }
        }

        // transform the map into an array of Tag
        Tag[] array = new Tag[occurrencesMap.size()];
        int i=0;
        for (String tag : occurrencesMap.keySet())
            array[i++] = new Tag(tag, occurrencesMap.get(tag));

        // sort the array and print the results
        Arrays.sort(array);
        for (Tag tag : array)
            System.out.println(tag);
    }

    // Auxiliary class that represents tag + number of occurrences, with the requested comparison mechanism
    static class Tag implements Comparable<Tag>{
        String name;
        int occurrences;

        Tag(String name, int occurrences) {
            this.name = name;
            this.occurrences = occurrences;
        }

        @Override
        public int compareTo(Tag other) {
            if (this.occurrences != other.occurrences)
                return -Integer.compare(this.occurrences, other.occurrences);

            return this.name.compareTo(other.name);
        }

        @Override
        public String toString() {
            return occurrences + " " + name;
        }
    }
}
