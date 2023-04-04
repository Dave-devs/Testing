package com.dave_devs.testing.mock

class UserRepository {

    //Populate user DB
    val userList = listOf<User>(
        User(1, "John", "john@gmail.com", "asysfs2d6"),
        User(2, "Mark", "mark@gmail.com", "gsstw6362"),
        User(3, "Russel", "russel@gmail.com", "afsgs8wc7"),
    )

    //Function to login a user
    fun loginUser(email: String, password: String): LoginStatus {
        
        //Fetch from DB
        val users = userList.filter{ user -> user.email == email}
        return if(users.size == 1) {
            if (users[0].password == password) {
                LoginStatus.Success
            } else {
                LoginStatus.InvalidPassword
            }
        } else {
            LoginStatus.InvalidUser
        }
    }
}