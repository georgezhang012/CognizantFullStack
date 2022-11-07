package model;

public enum RoomType {
    SINGLE("1"),
    DOUBLE("2");

    private String label;
    private RoomType(String label) {
        this.label=label;
    }

    public static RoomType fromString(String label) {
        for(RoomType roomType:values()) {
            if(roomType.label.equals(label)) {
                return roomType;
            }
        }

        return null;
    }

}
