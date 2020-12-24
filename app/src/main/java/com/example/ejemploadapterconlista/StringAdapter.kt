package com.example.ejemploadapterconlista

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random
import kotlin.random.nextInt

class StringAdapter(var listaString : MutableList<String>) : RecyclerView.Adapter<StringAdapter.StringViewHolder>()  {
    // hemos de incluir la variable check: checkBox
    class StringViewHolder(var root: View, var textView: TextView, var check: CheckBox) : RecyclerView.ViewHolder(root)

    // creamos una lista de boleanos (que nos diste la pista, gracias!)
    var listaDeBooleanos = mutableListOf<Boolean>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        val twTextView = view.findViewById<TextView>(R.id.textView)

        val check = view.findViewById<CheckBox>(R.id.checkBox)

        return StringViewHolder(view, twTextView, check)
    }

    override fun getItemCount(): Int {
        return listaString.size + 1
    }

    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {

        when (position){
            0->{
                holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.teal_700))
                holder.textView.text = "Borrar"
                holder.root.setOnClickListener {
                    val toast = Toast.makeText(it.context, "Borrando...", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                    var random = Random
                    // ponemos listaString.size porque no sabemos que tamaño va a tener la lista
                    var dropitem = random.nextInt(1..listaString.size-2)
                    // con esto "enganchamos" el item que queremos borrar de la lista
                    listaString.removeAt(dropitem)
                    listaDeBooleanos.removeAt(dropitem)
                    notifyDataSetChanged()
                }
                return

            }
            itemCount -1 ->{
                holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.teal_700))
                holder.textView.text = "Insertar"
                holder.root.setOnClickListener {
                    val toast = Toast.makeText(it.context, "Añadiendo...", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER,0,0)
                    toast.show()
                    listaString.add("PC - ${position+1}")
                    // esto lo ponemos para que no se junte el reciclado con la ultima inserción
                    it.setOnClickListener(null)
                    notifyDataSetChanged()
                }
                return

            }
            itemCount -2 ->{
                holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.teal_700))
                holder.textView.text = "Contador"
                holder.root.setOnClickListener {
                    var contador = 0
                    listaDeBooleanos.forEach{
                        if(it == true)
                            contador++
                    }
                    val toast = Toast.makeText(it.context, "Hay: $contador marcados", Toast.LENGTH_LONG)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()

                }
                return

            }
            else -> {
                val toast = Toast.makeText(holder.root.context, "Contador ", Toast.LENGTH_LONG)
                toast.setGravity(Gravity.CENTER,0,0)
                toast.show()
            }
        }


        // con esto vamos a generar que el check este check aleatoriamente o no
        var random = Random
        holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.teal_700))
        var checkear = random.nextInt(1..2)
        if (checkear == 1) {
            holder.check.isChecked = true
            listaDeBooleanos.add(true)
            holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.verde))
        }else {
            holder.check.isChecked = false
            listaDeBooleanos.add(false)
            holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.rojo))
        }

        holder.check.setOnCheckedChangeListener{ buttonView, isCheked ->
            if (isCheked){
                holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.verde))
                listaDeBooleanos.set(position,true)
            }else{
                holder.textView.setBackgroundColor(holder.textView.context.getColor(R.color.rojo))
                listaDeBooleanos.set(position,false)
            }

        }



        holder.textView.text = "${listaString[position]}"

    }

}

