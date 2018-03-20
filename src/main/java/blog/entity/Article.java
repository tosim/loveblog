package blog.entity;

/**
 * Created by Administrator on 2017/5/3 0003.
 */
public class Article {
    private Integer art_id;
    private String art_time;
    private String art_author;
    private String art_title;
    private String art_content;
    private String art_tag;
    private String art_class;
    private String art_music;
    private String art_video;
    private Integer art_like;
    private Integer art_dislike;
    private String art_img;
    private String art_abstract;
    private Integer art_view = 5;

    public Integer getArt_id() {
        return art_id;
    }

    public void setArt_id(Integer art_id) {
        this.art_id = art_id;
    }

    public String getArt_time() {
        return art_time;
    }

    public void setArt_time(String art_time) {
        this.art_time = art_time;
    }

    public String getArt_author() {
        return art_author;
    }

    public void setArt_author(String art_author) {
        this.art_author = art_author;
    }

    public String getArt_title() {
        return art_title;
    }

    public void setArt_title(String art_title) {
        this.art_title = art_title;
    }

    public String getArt_content() {
        return art_content;
    }

    public void setArt_content(String art_content) {
        this.art_content = art_content;
    }

    public String getArt_tag() {
        return art_tag;
    }

    public void setArt_tag(String art_tag) {
        this.art_tag = art_tag;
    }

    public String getArt_class() {
        return art_class;
    }

    public void setArt_class(String art_class) {
        this.art_class = art_class;
    }

    public String getArt_music() {
        return art_music;
    }

    public void setArt_music(String art_music) {
        this.art_music = art_music;
    }

    public String getArt_video() {
        return art_video;
    }

    public void setArt_video(String art_video) {
        this.art_video = art_video;
    }

    public Integer getArt_like() {
        return art_like;
    }

    public void setArt_like(Integer art_like) {
        this.art_like = art_like;
    }

    public Integer getArt_dislike() {
        return art_dislike;
    }

    public void setArt_dislike(Integer art_dislike) {
        this.art_dislike = art_dislike;
    }

    public String getArt_img() {
        return art_img;
    }

    public void setArt_img(String art_img) {
        this.art_img = art_img;
    }

    public String getArt_abstract() {
        return art_abstract;
    }

    public void setArt_abstract(String art_abstract) {
        this.art_abstract = art_abstract;
    }

    public Integer getArt_view() {
        return art_view;
    }

    public void setArt_view(Integer art_view) {
        this.art_view = art_view;
    }
}
