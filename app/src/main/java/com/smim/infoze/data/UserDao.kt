package com.smim.infoze.data

import androidx.room.*

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun login(email: String, password: String): User?

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): User?

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): User?

    @Query("UPDATE users SET username = :newUsername, email = :newEmail, password = :newPassword WHERE email = :oldEmail")
    suspend fun updateUserInfo(oldEmail: String, newUsername: String, newEmail: String, newPassword: String)

    @Update
    suspend fun updateUser(user: User)





}
