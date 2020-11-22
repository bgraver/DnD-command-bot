package com.uwu.dnd
import com.beust.klaxon.Klaxon
import java.io.File

data class Config(
  val client_id: String,
  val client_secret: String,
  val public_key: String,
  val token: String
)

fun setConfig(fileName: String): Config? {
  val file = File(fileName)
  return Klaxon().parse<Config>(file)
}

