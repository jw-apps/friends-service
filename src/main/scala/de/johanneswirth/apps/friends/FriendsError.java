package de.johanneswirth.apps.friends;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import de.johanneswirth.apps.common.Error;
import de.johanneswirth.apps.common.ErrorSerializer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@JsonSerialize(using= ErrorSerializer.class)
public enum FriendsError implements Error {
    ALREADY_FRIENDS("ALREADY_FRIENDS"),
    FRIENDREQUEST_EXISTS("FRIENDREQUEST_EXISTS");

    @NotEmpty
    private String errorMessage;

    @NotNull
    private boolean critical;

    FriendsError(String errorMessage, boolean critical) {
        this.errorMessage = errorMessage;
        this.critical = critical;
    }

    FriendsError(String errorMessage) {
        this.errorMessage = errorMessage;
        this.critical = true;
    }

    @JsonProperty("critical")
    public boolean isCritical() {
        return critical;
    }

    @JsonProperty("errorMessage")
    public String getError() {
        return errorMessage;
    }
}
