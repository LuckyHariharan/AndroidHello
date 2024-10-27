package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.icu.util.Calendar
import android.provider.CalendarContract
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PickupCard(date: String, time: String, location: String, onAddToCalendar: ()-> Unit){
    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth()
    ){
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Next Pickup")
            Spacer(modifier = Modifier.height(8.dp))
            Text(text= "Date: $date")
            Text(text="Time: $time")
            Text(text= "Location: $location")
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = onAddToCalendar){
                Text(text = "Add to Calendar ")
            }
        }
    }
}

@Composable
fun PickupCardApp(onAddToCalendarClick : ()->Unit){
    val date="November 10, 2023"
    val time = "11:00 AM"
    val location = "123 Main St, Cityville"
    PickupCard(date,time,location, onAddToCalendarClick)
}

fun addEventToGoogleCalendar(context: Context, title: String, location: String, beginTime: Long, endTime: Long){
    val intent = Intent(Intent.ACTION_INSERT).apply{
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, title)
        putExtra(CalendarContract.Events.EVENT_LOCATION, location)
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime)
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime)
    }
    context.startActivity(intent)
}

fun getMillisForDate(year: Int, month: Int, day: Int, hour: Int, minute: Int): Long {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day, hour, minute)
    return calendar.timeInMillis
}