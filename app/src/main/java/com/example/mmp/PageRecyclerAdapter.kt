package com.example.mmp

import android.content.Context
import android.graphics.Point
import android.view.*
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import com.squareup.picasso.Picasso

class PageRecyclerAdapter(
    private val products: Array<Product>, private val context: Context,
    private val adapterOnClick: (Product) -> Unit
) :
    androidx.recyclerview.widget.RecyclerView.Adapter<PageRecyclerAdapter.MyViewHolder>() {


    inner class MyViewHolder internal constructor(view: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

        internal val nameView: TextView = view.findViewById(R.id.name)
        internal val ic: ImageView = view.findViewById(R.id.ic)
        internal val cardView: CardView = view.findViewById(R.id.card)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PageRecyclerAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.product_card, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val product = products[position]
        holder.nameView.text = product.name

        val displayMetrics = context.resources.displayMetrics

        val lp = holder.ic.layoutParams
        lp.height = displayMetrics.widthPixels
        holder.ic.layoutParams = lp

        Picasso.get().load(product.img).resize(
            context.resources.displayMetrics.widthPixels,
            context.resources.displayMetrics.widthPixels
        ).into(holder.ic)


        holder.cardView.setOnClickListener {
            adapterOnClick(product)
        }

        /*

        holder.btnView.setOnClickListener{
            if(MainActivity.ordProd[product] == null){
                MainActivity.ordProd[product] = 1
            }else MainActivity.ordProd[product] = MainActivity.ordProd[product]!! + 1

            MainActivity.badge.number++
            MainActivity.badge.isVisible = true



            //holder.ic.startAnimation(AnimationUtils.loadAnimation(context,R.anim.icon_anim))

        }

        */

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = products.size
}