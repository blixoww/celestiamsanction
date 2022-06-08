package fr.blixow.celestiamsanction;

import java.util.Date;
import java.util.UUID;

public class Sanction {
    private final UUID player;

    private final String author;

    private Date left;
    private final SanctionType type;
    private String reason;

    public Sanction(UUID player, String author, SanctionType type, String reason) {
        this.player = player;
        this.author = author;
        this.type = type;
        this.reason = reason;
        this.left = null;
    }

    public Sanction(UUID player, String author, Date left, SanctionType type, String reason) {
        this(player, author, type, reason);
        this.left = left;
    }

    public void setLeft(Date left) {
        this.left = left;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UUID getPlayer() {
        return player;
    }

    public Date getLeft() {
        return left;
    }

    public SanctionType getType() {
        return type;
    }

    public String getReason() {
        return reason;
    }

}
