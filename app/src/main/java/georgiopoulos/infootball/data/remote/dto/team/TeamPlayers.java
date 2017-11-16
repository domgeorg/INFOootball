package georgiopoulos.infootball.data.remote.dto.team;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

public class TeamPlayers{

    @SerializedName("player")
    @Expose
    private List<Player> player = null;

    /**
     * No args constructor for use in serialization
     */
    public TeamPlayers(){
    }

    /**
     * @param player
     */
    public TeamPlayers(List<Player> player){
        super(); this.player = player;
    }

    public List<Player> getPlayer(){
        return player;
    }

    public void setPlayer(List<Player> player){
        this.player = player;
    }

    @Override
    public int hashCode(){
        return new HashCodeBuilder().append(player).toHashCode();
    }

    @Override
    public boolean equals(Object other){
        if(other == this){
            return true;
        } if((other instanceof TeamPlayers) == false){
            return false;
        } TeamPlayers rhs = ((TeamPlayers)other);
        return new EqualsBuilder().append(player,rhs.player).isEquals();
    }

}
