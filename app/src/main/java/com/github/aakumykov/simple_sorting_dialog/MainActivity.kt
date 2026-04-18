package com.github.aakumykov.simple_sorting_dialog

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.github.aakumykov.simple_sorting_dialog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, FragmentMain())
            .commit()

        binding.appInfoButton.setOnClickListener { showAppProperties() }
    }

    private fun showAppProperties() {
        val uri = "package:$packageName".toUri()
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, uri)
        if (intent.resolveActivity(packageManager) != null) { startActivity(intent) }
    }

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }
}