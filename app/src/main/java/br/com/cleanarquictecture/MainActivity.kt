package br.com.cleanarquictecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.local.TesteLocal
import br.com.network.dto.TesteNetwork
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        TesteLocal().teste()
        TesteNetwork().teste()
    }
}