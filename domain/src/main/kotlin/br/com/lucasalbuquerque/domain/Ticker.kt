package br.com.lucasalbuquerque.domain

data class Ticker( val last : String,
                   val lowestAsk : String,
                   val highestBid : String,
                   val percentChange : String,
                   val baseVolume : String,
                   val quoteVolume : String,
                   val isFrozen : String,
                   val high24hr : String,
                   val low24hr : String) {}