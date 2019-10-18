package f196695_v206681.ft.unicamp.br.aulas.kotlin

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import android.support.v4.widget.DrawerLayout
import android.support.design.widget.NavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import f196695_v206681.ft.unicamp.br.aulas.R;
import kotlinx.android.synthetic.main.content_kotlin.*

class KotlinActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var memoria: String
    private lateinit var editText: EditText
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState != null){
            memoria = savedInstanceState.getString("chave", "")
        }

        Log.i("Kotlin", "onCreate")
        setContentView(R.layout.activity_kotlin)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        editText = findViewById(R.id.editText)
        textView = findViewById(R.id.textView)

        if (intent != null){
            textView.setText(intent.getStringExtra("chave"))
        }

        val btnPull = findViewById<Button>(R.id.btnPull)
        btnPull.setOnClickListener {
            textView.setText(memoria)
            Toast.makeText(this, "Valor Resgatado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.kotlin, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle){
        super.onSaveInstanceState(outState)
        outState.putString("chave", memoria)
    }

    override fun onStart() {
        super.onStart()
        Log.i("Kotlin", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("Kotlin", "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("Kotlin", "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.i("Kotlin", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("Kotlin", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("Kotlin", "onDestroy")
    }

    fun pushVariable(view: View) {
        memoria = editText.text.toString()
        Toast.makeText(this, "Valor Inserido", Toast.LENGTH_SHORT).show()
    }

}
