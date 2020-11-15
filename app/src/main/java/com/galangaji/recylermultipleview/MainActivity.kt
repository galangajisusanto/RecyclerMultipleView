package com.galangaji.recylermultipleview

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.galangaji.recylermultipleview.model.LoadMore
import com.galangaji.recylermultipleview.model.Promo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var dataAdapter: DataAdapter
    private lateinit var manager: LayoutManager

    private var dataList: List<Any> = listOf(
        Promo(
            "promo_1",
            "Super Cash Back",
            "Beli Minimal 1 juta Cashback sampain 100.000 khusus untuk pengguna lama"
        ),
        Promo(
            "promo_2",
            "Beli Bayak Lebih murah",
            "Potongan harga sampai dengan 50% minimal belanja 20.000.000"
        ),
        Promo(
            "promo_3",
            "Gratis Ongkir",
            "Tanpa batas gratis onkir kemanapun *syarat dan ketentuan berlaku"
        ),
        LoadMore("Load more")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataAdapter = DataAdapter(this)
        manager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recyclerPromo.apply {
            adapter = dataAdapter
            layoutManager = manager
            addItemDecoration(object : ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: State
                ) {
                    outRect.set(10, 10, 10, 10)
                }
            }
            )
        }
        dataAdapter.addAllData(dataList)
    }
}