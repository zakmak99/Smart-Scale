package com.example.smartscaleapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        findViewById<Button>(R.id.gSignInBtn).setOnClickListener {
            signInGoogle()
        }
        findViewById<Button>(R.id.emailSignInBtn).setOnClickListener {
            signInWithEmail()
        }
        findViewById<Button>(R.id.signupBtn).setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
        findViewById<Button>(R.id.forgotPasswordBtn).setOnClickListener {
            startActivity(Intent(this, ForgotPasswordActivity::class.java))

//pasword reset

        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_DISPLAY_NAME && resultCode == Activity.RESULT_OK) {
            val displayName = data?.getStringExtra("display_name")
            if (displayName != null) {
                // Handle the display name (e.g., save it, display it)
                // now go to mainactivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    private fun signInWithEmail() {

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)


        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    if (user != null) {
                        val firestoreManager = FireStoreManager()
                        val userData = User(user.email ?: "", user.displayName ?: "")

                        // Check if the user's email exists in Firestore
                        checkIfEmailExistsInFirestore(userData.email)
                    }
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun checkIfEmailExistsInFirestore(email: String) {
        val db = FirebaseFirestore.getInstance()
        val query = db.collection("users").whereEqualTo("email", email)

        query.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val querySnapshot = task.result
                if (querySnapshot != null && !querySnapshot.isEmpty) {
                    // Email exists in Firestore; no need to show display name prompt
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    // Email doesn't exist in Firestore; show display name prompt
                    showDisplayNamePrompt()
                }
            } else {
                Toast.makeText(this, "Error checking email in Firestore", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signInGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        launcher.launch(signInIntent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                handleResults(task)
            }
        }

    private fun handleResults(task: Task<GoogleSignInAccount>) {
        if (task.isSuccessful) {
            val account: GoogleSignInAccount? = task.result
            if (account != null) {
                updateUI(account)
            }
        } else {
            Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                val user = auth.currentUser
                if (user != null) {
                    val firestoreManager = FireStoreManager()
                    val userData = User(user.email ?: "", user.displayName ?: "")
                    addUserToFirestore(userData.email, userData.displayName) // Add user data to Firestore
                    println("Sign in with Google successful") // use for debugin statement
                    // Prompt the user to set their display name
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("email", account.email)
                    intent.putExtra("name", account.displayName)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }
    private fun addUserToFirestore(email: String, displayName: String) {
        val db = FirebaseFirestore.getInstance()
        val userUid = FirebaseAuth.getInstance().currentUser?.uid

        if (userUid != null) {
            val userData = hashMapOf(
                "email" to email,
                "displayName" to displayName
            )

            db.collection("users")
                .document(userUid)
                .set(userData)
                .addOnSuccessListener {
                    Log.d("Firestore", "User data added/updated successfully")
                }
                .addOnFailureListener { e ->
                    Log.e("Firestore", "Error adding/updating user data", e)
                }
        }
    }
    private fun showDisplayNamePrompt() {
        Log.d("DisplayNameActivity", "Checking for display name in SharedPreferences")
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val displayName = sharedPreferences.getString("displayName", null)

        if (displayName == null) {
            // Display name is not available; prompt the user to set it
            val intent = Intent(this, DisplayNameActivity::class.java)
            startActivityForResult(intent, REQUEST_DISPLAY_NAME)
        }
    }


    companion object {
        const val REQUEST_DISPLAY_NAME = 1
    }
}
// new fix