package de.johanneswirth.apps.friends

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import io.dropwizard.db.DataSourceFactory

import javax.validation.Valid
import javax.validation.constraints.{NotEmpty, NotNull}

class FriendsConfiguration extends Configuration {

  @NotEmpty
  @JsonProperty
  var publicKey: String = _

  @Valid
  @NotNull
  @JsonProperty
  var database: DataSourceFactory = new DataSourceFactory

}
