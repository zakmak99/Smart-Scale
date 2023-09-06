package com.example.smartscaleapp
import com.google.firebase.firestore.FirebaseFirestore
class FireStoreManager {
    private val db = FirebaseFirestore.getInstance()

    fun addUser(user: User) {
        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                // Successfully added the user
            }
            .addOnFailureListener { _ ->
                // Handle failure
            }
    }
    fun getUserById(userId: String, onComplete: (User?) -> Unit) {
        db.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val user = document.toObject(User::class.java)
                    onComplete(user)
                } else {
                    onComplete(null)
                }
            }
            .addOnFailureListener {
                onComplete(null)
            }
    }

    // Add a method to update user data by document ID
    fun updateUserById(userId: String, user: User, onComplete: (Boolean) -> Unit) {
        db.collection("users")
            .document(userId)
            .set(user)
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    // Add a method to delete a user by document ID
    fun deleteUserById(userId: String, onComplete: (Boolean) -> Unit) {
        db.collection("users")
            .document(userId)
            .delete()
            .addOnSuccessListener {
                onComplete(true)
            }
            .addOnFailureListener {
                onComplete(false)
            }
    }

    // Add a method to query users by a specific field (e.g., email)
    fun getUsersByField(fieldName: String, fieldValue: Any, onComplete: (List<User>) -> Unit) {
        db.collection("users")
            .whereEqualTo(fieldName, fieldValue)
            .get()
            .addOnSuccessListener { querySnapshot ->
                val userList = querySnapshot.documents.mapNotNull { document ->
                    document.toObject(User::class.java)
                }
                onComplete(userList)
            }
            .addOnFailureListener {
                onComplete(emptyList())
            }
    }
}
    // can add more

