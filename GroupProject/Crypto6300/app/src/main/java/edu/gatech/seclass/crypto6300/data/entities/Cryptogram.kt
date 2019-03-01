package edu.gatech.seclass.crypto6300.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(
        tableName = "Cryptogram"
)
@Parcelize
data class Cryptogram(
        @PrimaryKey(autoGenerate = true)
        val id: Long?,

        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "solution")
        val solution: String,
        @ColumnInfo(name = "difficulty")
        val difficulty: Int,

        @Embedded
        val maxAttempts: Attempts
) : Parcelable {
    constructor(
            name: String = "",
            solution: String = "",
            difficulty: Int,
            maxAttempts: Attempts
    ) : this(null, name, solution, difficulty, maxAttempts)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Cryptogram

        if (name != other.name) return false
        if (solution != other.solution) return false
        if (difficulty != other.difficulty) return false
        if (maxAttempts != other.maxAttempts) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + solution.hashCode()
        result = 31 * result + difficulty
        result = 31 * result + maxAttempts.hashCode()
        return result
    }
}

data class Attempts(
        val easy: Int,
        val normal: Int,
        val hard: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(easy)
        parcel.writeInt(normal)
        parcel.writeInt(hard)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Attempts> {
        override fun createFromParcel(parcel: Parcel): Attempts {
            return Attempts(parcel)
        }

        override fun newArray(size: Int): Array<Attempts?> {
            return arrayOfNulls(size)
        }
    }
}