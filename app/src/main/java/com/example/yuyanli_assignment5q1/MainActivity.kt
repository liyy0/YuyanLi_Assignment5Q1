package com.example.yuyanli_assignment5q1


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private lateinit var imageView: ImageView

    // Array of drawable resource IDs
    val images = arrayOf( R.drawable.foodpic,R.drawable.treepic,R.drawable.montainpic)
    // Initialize with an invalid resource ID to indicate no image selected
    private var currentImageResId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        imageView = findViewById(R.id.imageView)
        val button: Button = findViewById(R.id.button)

        // Load SharedPreferences
        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val savedText = sharedPreferences.getString("input", "")

        val savedImageResId = sharedPreferences.getInt("image", -1)

        editText.setText(savedText)
        // Check if a valid image resource ID was saved and only then set the image
        if (savedImageResId != -1) {
            imageView.setImageResource(savedImageResId)
            currentImageResId = savedImageResId
        }

        button.setOnClickListener {
            val randomIndex = Random.nextInt(images.size)
            currentImageResId = images[randomIndex]
            imageView.setImageResource(currentImageResId)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        myEdit.putString("input", editText.text.toString())
        // Save the current image resource ID, or -1 if no image is selected
        myEdit.putInt("image", currentImageResId)
        myEdit.apply()
    }
}
