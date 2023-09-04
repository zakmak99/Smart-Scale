package com.example.smartscaleapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
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
                // You can handle the successful sign-in here
                // After successful sign-in, add user data to Firestore
                if (user != null) {
                    val firestoreManager = FireStoreManager()
                    val userData = User(user.email ?: "", user.displayName ?: "")
                    firestoreManager.addUser(userData)
                    println("Sign in successful") // Debug statement

                    // Navigate to the desired activity here
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish() // Optional: Close the login activity
                }
            } else {
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
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
                    firestoreManager.addUser(userData)
                    println("Sign in with Google successful") // Debug statement
                }
                    val intent: Intent = Intent(this, MainActivity::class.java)
                intent.putExtra("email", account.email)
                intent.putExtra("name", account.displayName)
                startActivity(intent)
            } else {
                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

            }
        }
    }
} // google login not being read by firestore database