package com.example.todolist

import com.example.todolist.CardInfo


object DataObject {
    var listdata = mutableListOf<CardInfo>()

    fun setData(title: String, priority: String, deadLine:String, description:String) {
        listdata.add(CardInfo(title, priority, deadLine, description))
    }

    fun getAllData(): List<CardInfo> {
        return listdata
    }

    fun deleteAll(){
        listdata.clear()
    }

    fun getData(pos:Int): CardInfo {
        return listdata[pos]
    }

    fun deleteData(pos:Int){
        listdata.removeAt(pos)
    }

    fun updateData(pos:Int,title:String,priority:String, deadLine:String, description:String)
    {
        listdata[pos].title=title
        listdata[pos].priority=priority
        listdata[pos].deadLine = deadLine
        listdata[pos].description = description

    }

}