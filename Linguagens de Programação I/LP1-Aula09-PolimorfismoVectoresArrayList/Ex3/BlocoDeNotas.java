package Ex3;

import java.util.ArrayList;

public class BlocoDeNotas {
    ArrayList<String> notes = new ArrayList<String>();

    public void addNote(String note) {
        notes.add(note);
    }

    public void removeNote(int noteNumber) {
        String note = this.findNote(noteNumber);
        if (note != null) {
            notes.remove(noteNumber);
        }
        else{
            System.out.println("Nota não encontrada");
        }
    }

    public void listNotes() {
        System.out.println("-----------Notas-----------");

        for (int i = 0; i < notes.size(); i++) {
            System.out.println(i + " - " + notes.get(i));
        }
        System.out.println("---------------------------");
    }

    public String findNote(int noteNumber) {
        if (noteNumber >= notes.size()) {
            System.out.println("Nota não encontrada");
            return null;
        }

        return notes.get(noteNumber);
    }

    public void changeNote (int noteNumber, String newNote) {
        String note = this.findNote(noteNumber);
        if (note != null) {
            notes.set(noteNumber, newNote);
        }
        else{
            System.out.println("Nota não encontrada");
        }
    }

}
