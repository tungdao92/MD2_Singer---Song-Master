package rikkei.academy.service.song;

import rikkei.academy.config.Config;
import rikkei.academy.model.Singer;
import rikkei.academy.model.Song;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongServiceIMPL implements ISongService{
    public static String PATH_SONG = "D:\\MD2-Singer-Song-master\\src\\rikkei\\academy\\database\\song.txt";
    public static List<Song> songList = new Config<Song>().readFile(PATH_SONG);


    @Override
    public List<Song> findAll() {
        new Config<Song>().writeFile(PATH_SONG, songList);
        return songList;
    }

    @Override
    public void save(Song song) {
        songList.add(song);

    }

    @Override
    public Song findByID(int id) {
        for (int i = 0; i < songList.size(); i++) {
            if (id == songList.get(i).getId()){
                return songList.get(i);
            }

        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public List<Song> sortByLike() {
        List<Song> listLike = new ArrayList<>();
        for (int i = 0; i < songList.size(); i++) {
            listLike.add(songList.get(i));

        }
        Collections.sort(listLike);
        List<Song> toplike = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            toplike.add(listLike.get(i));

        }
        return toplike;

    }
}
