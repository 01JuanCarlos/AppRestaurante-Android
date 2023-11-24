package com.cibertec.apprestaurante.Activity


import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.cibertec.apprestaurante.Cocina.CocinaActivity
import com.cibertec.apprestaurante.Mesa.MesasActivity
import com.cibertec.apprestaurante.R
import com.google.android.material.navigation.NavigationView


class RolActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rol)

        val btnme = findViewById<ImageView>(R.id.btn_menu)
        btnme.setOnClickListener{
            startActivity(Intent(this, AdministracionActivity::class.java))
        }
        val btnMesaero = findViewById<Button>(R.id.btnMesero)
        btnMesaero.setOnClickListener {
            startActivity(Intent(this, MesasActivity:: class.java))
        }

        val btnCocinero = findViewById<Button>(R.id.btncocinero)
        btnCocinero.setOnClickListener {
            startActivity(Intent(this, CocinaActivity::class.java))
        }
        configurationDrawer()

    }

    fun configurationDrawer() {

        val navView = findViewById<NavigationView>(R.id.nav_view)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        navView.setNavigationItemSelectedListener { menuItem ->

            drawerLayout.closeDrawer(GravityCompat.START)

            when(menuItem.itemId) {
                R.id.nav_home -> {
                    // sdsdsdszdsdsdsd
                    true
                }
                R.id.M_productos -> {
                    // sdsdsdszdsdsdsd
                    startActivity(Intent(this, AddplatosActivity::class.java))

                    true
                }
                R.id.M_categorias -> {
                    // sdsdsdszdsdsdsd
                    startActivity(Intent(this, AddcategoriasActivity::class.java))
                    true
                }
                else -> {
                    false
                }
            }

        }

    }

}