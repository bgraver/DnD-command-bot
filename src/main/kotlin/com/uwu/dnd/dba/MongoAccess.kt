package com.uwu.dnd.dba

import com.uwu.dnd.character.Character
import com.uwu.dnd.exceptions.EnvironmentVariableNotFoundException
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

private val mongoString by lazy {
    System.getenv("MONGO_CONNECTION_STRING")
        ?: throw EnvironmentVariableNotFoundException("MONGO_CONNECTION_STRING")
}

private val client = KMongo.createClient(mongoString)

val collection = client.getDatabase("characters")
    .getCollection<Character>("characters")
