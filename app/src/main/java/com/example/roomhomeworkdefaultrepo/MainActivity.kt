package com.example.roomhomeworkdefaultrepo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDataBase.getInstance(this)!!




        val list : ArrayList<User> = ArrayList()

        loadData(list)

        binding.saveDataButton.setOnClickListener {
            val name : String = binding.nameEditText.text.toString()
            val birthday : String = binding.birthdayEditText.text.toString()
            val email : String = binding.emailEditText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().insertData(User(name, birthday, email))
                list.add(User(name, birthday, email))
                loadData(list)
            }


        }

       /* binding.memoRecycler.adapter = Adapter(list){ data, position ->
            Toast.makeText(this, "아이템이 삭제됨", Toast.LENGTH_SHORT).show()

            CoroutineScope(Dispatchers.IO).launch {
                db.userDao().deleteData(User)
                list.remove(data)
                loadData(list)
                binding.memoRecycler.adapter!!.notifyItemRemoved(position)
            }

        }*/

    }

   private fun loadData(list : ArrayList<User>) {
       CoroutineScope(Dispatchers.IO).launch {

           userList = db.userDao().getAll()
           nameList = db.userDao().getAllName()
           birthdayList = db.userDao().getAllBirthday()
           emailList = db.userDao().getAllEmail()

           runOnUiThread {
               binding.memoRecycler.adapter!!.notifyItemInserted(list.size.minus(1))
           }
       }
    }



}