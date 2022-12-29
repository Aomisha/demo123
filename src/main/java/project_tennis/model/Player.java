package project_tennis.model;

import jakarta.persistence.*;

@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "player_name")
    private String name;
    @Column(name = "players_age")
    private String age;
    @Column(name = "players_lvl")
    private String level;
    @Column(name = "players_tg_user_name")
    private String username;

    @Column(name = "players_chatid")
    private String chatId;

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", level='" + level + '\'' +
                ", username='" + username + '\'' +
                ", chatId='" + chatId + '\'' +
                '}';
    }

    public Player(String name, String age, String level, String username, String chatId) {
        this.name = name;
        this.age = age;
        this.level = level;
        this.username = username;
        this.chatId = chatId;
    }

    public Player() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }
}
