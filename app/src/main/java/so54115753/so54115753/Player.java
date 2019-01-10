package so54115753.so54115753;

public class Player {

    private String playerName;
    private long playerID;
    private int playerAge;
    private int playerWeight;
    private int playerHeight;
    private long foreignKey; // (reference to user that owns this player)

    public Player(String name, int age, int weight, int height, long id, long foreignkey) {

        this.playerName = name;
        this.playerAge = age;
        this.playerWeight = weight;
        this.playerHeight = height;
        this.playerID = id;
        this.foreignKey = foreignkey;
    }

    public Player(String name, int age, int weight, int height) {
        this(name,age,weight,height,-1,-1);
    }

    public Player() {
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerAge(int playerAge) {
        this.playerAge = playerAge;
    }

    public int getPlayerAge() {
        return playerAge;
    }

    public void setPlayerWeight(int playerWeight) {
        this.playerWeight = playerWeight;
    }

    public int getPlayerWeight() {
        return playerWeight;
    }

    public void setPlayerHeight(int playerHeight) {
        this.playerHeight = playerHeight;
    }

    public int getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerID(long playerID) {
        this.playerID = playerID;
    }

    public long getPlayerID() {
        return playerID;
    }

    public void setForeignKey(long foreignKey) {
        this.foreignKey = foreignKey;
    }

    public long getForeignKey() {
        return foreignKey;
    }
}
