package com.example.notekeeper;

import android.net.Uri;
import android.provider.BaseColumns;

public final class NoteKeeperProviderContract {
    private NoteKeeperProviderContract()    { }
    public static final String AUTHORITY = "com.example.notekeeper.provider";
    public static final Uri AUTHORITY_URI = Uri.parse("content://" + AUTHORITY);
    protected interface CoursesIdColumns  {
        public static final String COLUMN_COURSE_ID = "course_id";          //since this column is needed in the two tables, there's need to create a separate interface for it to avoid mix-up
    }
    //this can be used directly only in the content provider implementation
    protected interface CourseColumns  {
        public static final String COLUMN_COURSE_TITLE = "course_title";
    }
    protected interface NoteColumns {
        public static final String COLUMN_NOTE_TITLE = "note_title";
        public static final String COLUMN_NOTE_TEXT = "note_text";
    }
    public static final class Courses implements BaseColumns, CourseColumns, CoursesIdColumns {    //therefore, if there's need to access the course title column, it'll be Courses.COLUMN_NOTE_TITLE
        public static final String PATH = "courses";
        //  content://com.example.notekeeper.provider/courses
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
    }
    public static final class Notes implements BaseColumns, NoteColumns, CoursesIdColumns, CourseColumns {    //Notes class exposes the constants from the noteColumns interface (BaseColumns for _ID)
        public static final String PATH = "notes";
        public static final Uri CONTENT_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH);
        public static final String PATH_EXPANDED = "notes_expanded";
        public static final Uri CONTENT_EXPAND_URI = Uri.withAppendedPath(AUTHORITY_URI, PATH_EXPANDED);
    }
}

/**
 * This is a contract class for the content provider
 * It contains classes for the tables(specify columns) that'll be exposed by the content provider as well as the URIs*/