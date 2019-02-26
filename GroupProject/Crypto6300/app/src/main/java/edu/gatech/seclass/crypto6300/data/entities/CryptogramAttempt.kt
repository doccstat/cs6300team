package edu.gatech.seclass.crypto6300.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
        tableName = "CryptogramAttempt",
        foreignKeys = [
            ForeignKey(entity = Cryptogram::class, parentColumns = ["id"], childColumns = ["cryptogram_id"], onDelete = ForeignKey.CASCADE),
            ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE)
        ]
)
data class CryptogramAttempt(
        @PrimaryKey(autoGenerate = true)
        val id: Long?,

        @ColumnInfo(name = "user_id", index = true)
        val userId: Int,
        @ColumnInfo(name = "cryptogram_id", index = true)
        val cryptogramId: Int,

        @ColumnInfo(name = "attempts_remaining")
        val attemptsRemaining: Int,
        @ColumnInfo(name = "submission")
        val currentSubmissionState: String = "",
        @ColumnInfo(name = "encrypted_phrase")
        val encryptedPhrase: String = "",
        @ColumnInfo(name = "is_completed")
        val isCompleted: Boolean = false,
        @ColumnInfo(name = "is_solved")
        val isSolved: Boolean = false
)