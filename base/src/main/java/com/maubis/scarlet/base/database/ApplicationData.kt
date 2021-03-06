package com.maubis.scarlet.base.database

import android.content.Context
import com.maubis.scarlet.base.core.note.NoteActor
import com.maubis.scarlet.base.database.room.AppDatabase
import com.maubis.scarlet.base.database.room.note.Note
import com.maubis.scarlet.base.database.room.widget.WidgetDao

class ApplicationData(context: Context) {
  private val database: AppDatabase = AppDatabase.createDatabase(context)

  val notes = NotesRepository(database.notes())
  val tags = TagsRepository(database.tags())
  val folders = FoldersRepository(database.folders())
  val widgets: WidgetDao = database.widgets()

  fun noteActions(note: Note) = NoteActor(note)
}