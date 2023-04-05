package com.nashss.se.gutenberg.app.dao;

import com.nashss.se.gutenberg.data.FaveListCsv;

import java.util.List;

public class FavoritesDao {
    private final FaveListCsv faveListCsv;

    public FavoritesDao(FaveListCsv faveListCsv) {
        this.faveListCsv = faveListCsv;
    }

    /**
     * Get all the books in the system
     * 
     * @return A list of books
     */
    public List<String> getAll() {
        List<String> faveBookList = faveListCsv.getAll();
        return faveBookList;
    }

    public String addToFave(int id) {
        Boolean existFave = false;
        FaveListCsv tempFaveListCsv = new FaveListCsv();
        List<String> faveBookList = tempFaveListCsv.getAll();
        for (String faveID : faveBookList) {
            if (!faveID.equals("ID")) {
                if (Integer.parseInt(faveID) == id) {
                    existFave = true;
                }
            }
        }
        if (existFave == true) {
            return ("\n This book is already on the favorite list\n");
        } else {
            faveListCsv.addToFaveCsv(id);
            return ("\n Book ID: " + id + " has been added to your favorites list\n");
        }
    }
}

