package com.example.readerscommunity

import com.google.firebase.Timestamp


data class EventModel (val branch: String?= null, val currentSeatAvailable: Int?=null, val date: String?= null,
                       val description: String?= null, val eventName: String ?= null,
                       val extraInfo: String ?= null, val imgUrl: String?= null, val postedOn: Timestamp?= null,
                       val semester: String?= null, val timings: String?= null, val totalSeat: Int?= null,
                       val venue: String?= null, val what_to_bring: String?= null)