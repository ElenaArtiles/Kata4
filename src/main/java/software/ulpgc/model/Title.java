package software.ulpgc.model;

import java.util.List;

public record Title(String id, TitleType type, String primaryTitle, String originalTitle,
                    List<Genre> genres) {
    public enum TitleType {
        VideoGame,
        TvPilot,
        Movie,
        TvSeries,
        TvMiniSeries,
        Short,
        TvSpecial,
        TvShort,
        Video,
        TvMovie,
        TvEpisode
    }

    public enum Genre {
        Action,
        Adult,
        Adventure,
        Animation,
        Biography,
        Comedy,
        Crime,
        Documentary,
        Drama,
        Family,
        Fantasy,
        FilmNoir,
        GameShow,
        genres,
        History,
        Horror,
        Music,
        Musical,
        Mystery,
        News,
        RealityTV,
        Romance,
        SciFi,
        Short,
        Sport,
        TalkShow,
        Thriller,
        War,
        Western
    }

}
