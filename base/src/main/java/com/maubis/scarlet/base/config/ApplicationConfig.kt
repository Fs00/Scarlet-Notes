package com.maubis.scarlet.base.config

import android.content.Context
import com.maubis.scarlet.base.core.folder.FolderActor
import com.maubis.scarlet.base.core.note.NoteActor
import com.maubis.scarlet.base.core.tag.TagActor
import com.maubis.scarlet.base.database.FoldersRepository
import com.maubis.scarlet.base.database.NotesRepository
import com.maubis.scarlet.base.database.TagsRepository
import com.maubis.scarlet.base.database.room.AppDatabase
import com.maubis.scarlet.base.database.room.folder.Folder
import com.maubis.scarlet.base.database.room.note.Note
import com.maubis.scarlet.base.database.room.tag.Tag

class ApplicationConfig(context: Context) {
  val database: AppDatabase = AppDatabase.createDatabase(context)
  val notesRepository = NotesRepository()
  val tagsRepository = TagsRepository()
  val foldersRepository = FoldersRepository()

  fun noteActions(note: Note) = NoteActor(note)
  fun tagActions(tag: Tag) = TagActor(tag)
  fun folderActions(folder: Folder) = FolderActor(folder)
}