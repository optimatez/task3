package com.example.task3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager


class MainActivity : AppCompatActivity(), OnItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val showSavedDataButton = findViewById<Button>(R.id.buttonShowSavedData)

        showSavedDataButton.setOnClickListener {
            val intent = Intent(this, SavedDataActivity::class.java)
            startActivity(intent)
        }

        val dataList = readCsvFile()
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(this)
        val adapter = OlympicDataAdapter(dataList, this)

        adapter.setOnItemClickListener(this)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun readCsvFile(): List<OlympicData> {
        val inputStream = resources.openRawResource(R.raw.medallists)
        val reader = BufferedReader(InputStreamReader(inputStream))
        val dataList = mutableListOf<OlympicData>()

        reader.readLine()

        reader.useLines { lines ->
            lines.forEach { line ->
                val values = line.split(",")
                if (values.size == 6) {
                    val olympicData = OlympicData(
                        values[0],
                        values[1],
                        values[2].toInt(),
                        values[3].toInt(),
                        values[4].toInt(),
                        values[5].toInt()
                    )
                    dataList.add(olympicData)
                }
            }
        }

        return dataList
    }

    override fun onItemClick(item: OlympicData) {
        val message = "You clicked on ${item.country}"
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

}