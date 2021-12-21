package de.johanneswirth.apps.friends.db

import de.johanneswirth.apps.common.User
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper
import org.jdbi.v3.sqlobject.statement.{SqlQuery, SqlScript, SqlUpdate}

import java.util.Optional

trait FriendsDAO {

  @SqlScript("insert into friends (user_id_a, user_id_b) values (:user_a, :user_b)")
  @SqlScript("insert into friends (user_id_a, user_id_b) values (:user_b, :user_a)")
  def addFriend(userA: Long, userB: Long): Unit

  @SqlQuery("select users.id, users.username from friends join users on users.id = friends.user_id_b where user_id_a = :user_id")
  @RegisterConstructorMapper(classOf[User])
  def getFriends(userID: Long): List[User]

  @SqlUpdate("insert into friendRequests (user_id_a, user_id_b) values (:user_a, :user_b)")
  def addFriendRequest(userA: Long, userB: Long): Unit

  @SqlQuery("select user_id_b from friendRequests where user_id_a = :user_id_a and user_id_b = :user_id_b")
  def existsFriendRequest(userA: Long, userB: Long): Optional[Long]

  @SqlUpdate("delete from friendRequests where user_id_a = :user_id_a and user_id_b = :user_id_b")
  def deleteFriendRequest(userA: Long, userB: Long): Unit

  @SqlQuery("select * from friends where user_id_a = :user_id_a and user_id_b = :user_id_b")
  def areFriends(userA: Long, userB: Long): Boolean
}
