package llousty.Utils;

public class ParticipantFormat {
    public static String getConsistentIdString(int id1, int id2) {
        int smallerId = Math.min(id1, id2);
        int biggerId = Math.max(id1, id2);
        return "[" + smallerId + "," + biggerId + "]";
    }
}
