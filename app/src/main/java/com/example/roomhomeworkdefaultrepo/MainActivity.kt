package com.example.roomhomeworkdefaultrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomhomeworkdefaultrepo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var db : AppDataBase
    private lateinit var nameList : List<String>
    private lateinit var birthdayList : List<String>
    private lateinit var emailList : List<String>
    private lateinit var userList : List<User>
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.memoRecycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        adapter = Adapter(this)
        binding.memoRecycler.adapter = adapter


        db = AppDataBase.getInstance(this)!!
        val list : ArrayList<User> = ArrayList()

        loadData()

        binding.saveDataButton.setOnClickListener {
            val name : String = binding.nameEditText.text.toString()
            val birthday : String = binding.birthdayEditText.text.toString()
            val email : String = binding.emailEditText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().insertData(User(name, birthday, email))
                list.add(db.userDao().getAll())
                binding.memoRecycler.adapter!!.notifyItemInserted(list.size.minus(1))
                loadData()
            }


        }

        binding.memoRecycler.adapter = Adapter(list){data, position ->
            Toast.makeText(this, "아이템이 삭제됨", Toast.LENGTH_SHORT).show()
            list.remove(data)
            binding.memoRecycler.adapter!!.notifyItemRemoved(position)
        }


    }

    private fun loadData() {
       CoroutineScope(Dispatchers.IO).launch {

           userList = db.userDao().getAll()
           nameList = db.userDao().getAllName()
           birthdayList = db.userDao().getAllBirthday()
           emailList = db.userDao().getAllEmail()

           runOnUiThread {
               //동기코드 (앞에서 돌아가는 코드, .setText 어뎁터 설정, 뷰 설정)


           }
       }
    }



}