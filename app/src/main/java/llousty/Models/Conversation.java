package llousty.Models;

public class Conversation extends Model{
    private String participantsId;
    private String messageIdList;
    public Conversation(int id, String participantsId, String messageIdList) {
        super(id);
        this.participantsId = participantsId;
        this.messageIdList = messageIdList;
    }
    public String getParticipantsId() {
        return participantsId;
    }
    public void setParticipantsId(String participantsId) {
        this.participantsId = participantsId;
    }
    public String getMessageIdList() {
        return messageIdList;
    }
    public void setMessageIdList(String messageIdList) {
        this.messageIdList = messageIdList;
    }

}