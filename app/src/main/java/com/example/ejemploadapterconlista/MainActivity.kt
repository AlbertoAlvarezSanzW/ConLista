package com.example.ejemploadapterconlista

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ejemploadapterconlista.databinding.ActivityMainBinding
import kotlin.random.Random
import kotlin.random.nextInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter : StringAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRecyclerView()
    }

    private fun createRecyclerView() {
        // creación de la lista:
        val listaString = mutableListOf<String>("PC - 1")
        // creación del random:
        var random = Random
        var inicio = random.nextInt(5..10)
        // realizamos un for desde 1, porque tenemos ya un elemento creado
        for (i in 1..inicio){
            listaString.add("PC - ${i}")
        }

        adapter = StringAdapter(listaString.toMutableList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

}