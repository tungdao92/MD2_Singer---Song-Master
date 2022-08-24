package rikkei.academy.view.song;

import com.sun.javaws.IconUtil;
import rikkei.academy.config.Config;
import rikkei.academy.controller.SingerController;
import rikkei.academy.controller.SongController;
import rikkei.academy.model.Singer;
import rikkei.academy.model.Song;
import rikkei.academy.view.Main;

import java.util.ArrayList;
import java.util.List;

public class ViewSong {
    private SongController songController = new SongController();
    private List<Song> songList = songController.showListSong();
    private SingerController singerController = new SingerController();
    List<Singer> singerList = singerController.showListSinger();

    public ViewSong() {
        System.out.println("-----------SONG MANAGE------------");
        System.out.println("1. Create Song");
        System.out.println("2. Show List Song");
        System.out.println("3. Detail Song");
        System.out.println("4. Top 5 Like Song");
//        System.out.println("5. Delete Song");
//        System.out.println("6. Sort Song");
        System.out.println("0: Back to MENU");
        int chooseSong = Config.scanner().nextInt();
        switch (chooseSong) {
            case 1:
                formCreatSong();
                break;
            case 2:
                formShowListSong();
                break;
            case 3:
                formDetailSong();
                break;
            case 4:
                top5LikeSong();
                break;
//            case 5:
//                formDeleteSinger();
//                break;
//            case 6:
//                formShowListSingerAfterSort();
//                break;
            case 0:
                new Main();
                break;
        }

    }

    private void formCreatSong() {
        int idSong;
        if (songList.size() == 0) {
            idSong = 1;
        } else {
            idSong = songList.get(songList.size() - 1).getId() + 1;
        }
        System.out.println("Enter the name SOng");
        String name = Config.scanner().nextLine();
        List<Singer> listSelect = new ArrayList<>();
        listSelect = selectSinger(listSelect);

        Song song = new Song(idSong, name, 0, 0, listSelect);
        songController.creatSong(song);
        songController.showListSong();
        System.out.println("Creat song success");
        showListSong();
    }

    private void formShowListSong() {
        showListSong();
        System.out.println("Enter any key to continue - Enter Quit to exit");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            new ViewSong();
        }
    }

    private void showListSong() {
        System.out.println("======ID========NAME========LISTEN=======LIKE=======");
        for (int i = 0; i < songList.size(); i++) {
            System.out.println("======" + songList.get(i).getId() + "=======" + songList.get(i).getName() + "=======" + songList.get(i).getListen() + "======" + songList.get(i).getLike() + "======" + songList.get(i).getSingerList());

        }
    }

    private List<Singer> selectSinger(List<Singer> listSelect) {

        System.out.println("=======ID=======NAME========");
        for (int i = 0; i < singerList.size(); i++) {
            System.out.println("===" + singerList.get(i).getId() + "=====" + singerList.get(i).getName());

        }
        System.out.println("Enter the id singer select");
        int idSinger = Config.scanner().nextInt();
        Singer singer = singerController.detailSinger(idSinger);
        listSelect.add(singer);
        System.out.println("Enter any key to continue - Enter Quit to exit");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            return listSelect;

        } else {
            selectSinger(listSelect);
        }
        return listSelect;
    }
    private  void formDetailSong(){
        showListSong();
        System.out.println("Enter the id of song");
        int idSong = Config.scanner().nextInt();
        if (songController.detailSong(idSong) == null){
            System.out.println("The song doesn't exist");
        }else {
            Song song = songController.detailSong(idSong);
            song.setListen(song.getListen()+1);
            System.out.println("======ID======NAME======LISTEN======LIKE======");
            System.out.println("======"+song.getId()+ "======" + song.getName()+ "======"+ song.getListen() + "======" + song.getLike()+"========");
            for (int i = 0; i < song.getSingerList().size(); i++) {
                System.out.println( "Performing Singer: "+song.getSingerList().get(i).getName());
            }
            System.out.println("Enter 'like' to Like Song");
            String like = Config.scanner().nextLine();
            if (like.equalsIgnoreCase("like")){
                song.setLike(song.getLike()+1);
                songController.showListSong();
            }
            System.out.println("Enter Quit to back Song Manager");
            String exitSelect = Config.scanner().nextLine();
            if (exitSelect.equalsIgnoreCase("quit")) {
                new ViewSong();
            }

        }
    }
    private void top5LikeSong(){
        SongController songController = new SongController();
        List<Song> listLike = songController.sortByLike();
        System.out.println("======ID========NAME========LISTEN=======LIKE=======");
        for (Song song: listLike) {
            System.out.println("======"+song.getId()+ "======" + song.getName()+ "======"+ song.getListen() + "======" + song.getLike()+"========");
        }
        System.out.println("Enter Quit to back Song Manager");
        String exitSelect = Config.scanner().nextLine();
        if (exitSelect.equalsIgnoreCase("quit")) {
            new ViewSong();
        }


    }
}


