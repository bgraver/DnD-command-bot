package com.uwu.dnd.dba

import com.uwu.dnd.character.Character
import com.uwu.dnd.exceptions.EnvironmentVariableNotFound
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollection

private val mongoString by lazy {
    System.getenv("MONGO_CONNECTION_STRING")
        ?: throw EnvironmentVariableNotFound("MONGO_CONNECTION_STRING")
}

private val client = KMongo.createClient(mongoString)

val collection = client.getDatabase("characters")
    .getCollection<Character>("characters")
