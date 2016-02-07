package dao;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

import adapters.DBAdapter;

public class PersonneDao {

	DBAdapter dbadapter;
    Context c;
    
	public PersonneDao(Context context) {
		this.c=context;
		dbadapter=new DBAdapter(c);
	}
	
	public void addPersonne(Personne personne) {
		dbadapter.open();
		long id=dbadapter.insertRow(personne.getNom(), personne.getPrenom(), personne.getAge(), personne.getTravail(), personne.getImage());
		utils.Message.show(c, ""+id);
		dbadapter.close();
	}
	
	public ArrayList<Personne> listPersonnes() {
		dbadapter.open();
		Cursor c=dbadapter.getAllRows();
		ArrayList<Personne> list=new ArrayList<Personne>();
		list=prepareList(c);
		dbadapter.close();
		return list;
	}
	
	public ArrayList<Personne> prepareList(Cursor c) {
		ArrayList<Personne>personnes=new ArrayList<Personne>();
		if (c.moveToFirst()) {
			long id=0;
			String nom="";
			String prenom="";
			int age=0;
			String travail="";
			byte[] image=null;
			do {
				id=c.getLong(DBAdapter.COL_ROWID);
				nom=c.getString(DBAdapter.COL_NOM);
				prenom=c.getString(DBAdapter.COL_PRENOM);
				age=c.getInt(DBAdapter.COL_AGE);
				travail=c.getString(DBAdapter.COL_TRAVAIL);
				image=c.getBlob(DBAdapter.COL_IMAGE);
				personnes.add(new Personne(nom, prenom, age, travail, image));
			} while (c.moveToNext());
		}
		return personnes;
	}
	
	public void deletePersonne(Context context,long pos) {
		dbadapter.open();
		if (dbadapter.deleteRow(pos)) {
			utils.Message.show(context, "Personne Supprim�e");
		}
		dbadapter.close();
	}
	
	
}
