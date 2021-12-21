package de.johanneswirth.apps.friends.services

import com.codahale.metrics.annotation.{ExceptionMetered, Timed}
import de.johanneswirth.apps.common.SuccessStatus.OK
import de.johanneswirth.apps.common.VerificationHelper.userID
import de.johanneswirth.apps.common.{IStatus, Secured}
import de.johanneswirth.apps.friends.FriendsError.{ALREADY_FRIENDS, FRIENDREQUEST_EXISTS}
import de.johanneswirth.apps.friends.db.FriendsDAO
import org.jdbi.v3.core.Jdbi

import javax.validation.Valid
import javax.validation.constraints.NotNull
import javax.ws.rs.{Consumes, GET, POST, Path, Produces}
import javax.ws.rs.core.{Context, MediaType, SecurityContext}

class FriendsService(jdbi: Jdbi) {
  private val dao = jdbi.onDemand(classOf[FriendsDAO])

  @POST
  @Consumes(Array(MediaType.APPLICATION_JSON))
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Secured
  @Valid
  @NotNull
  @Timed
  @ExceptionMetered
  @Path("request")
  def createFriendRequest(otherId: Long, @Context securityContext: SecurityContext): IStatus = {
    val userId = userID(securityContext)
    if (dao.existsFriendRequest(otherId, userId).isPresent) {
      dao.addFriend(userId, otherId)
      dao.deleteFriendRequest(otherId, userId)
      OK
    } else if (dao.existsFriendRequest(userId, otherId).isPresent) {
      FRIENDREQUEST_EXISTS
    } else if (dao.areFriends(userId, otherId)) {
      ALREADY_FRIENDS
    } else {
      dao.addFriendRequest(userId, otherId);
      OK
    }
  }

  @GET
  @Produces(Array(MediaType.APPLICATION_JSON))
  @Secured
  @Valid
  @NotNull
  @Timed
  @ExceptionMetered def getFriends(@Context securityContext: SecurityContext): IStatus = {
    val userId = userID(securityContext)
    OK(dao.getFriends(userId))
  }
}
