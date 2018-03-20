package blog.entity;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
public class Tool {
    int t_id;
    String t_name;
    String t_version;
    String t_owner;
    String t_abstract;
    String t_description;
    int t_download_cnt;
    int t_like;
    String t_time;
    String t_path;

    public String getT_path() {
        return t_path;
    }

    public void setT_path(String t_path) {
        this.t_path = t_path;
    }

    public String getT_time() {
        return t_time;
    }

    public void setT_time(String t_time) {
        this.t_time = t_time;
    }

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_version() {
        return t_version;
    }

    public void setT_version(String t_version) {
        this.t_version = t_version;
    }

    public String getT_owner() {
        return t_owner;
    }

    public void setT_owner(String t_owner) {
        this.t_owner = t_owner;
    }

    public String getT_abstract() {
        return t_abstract;
    }

    public void setT_abstract(String t_abstract) {
        this.t_abstract = t_abstract;
    }

    public String getT_description() {
        return t_description;
    }

    public void setT_description(String t_description) {
        this.t_description = t_description;
    }

    public int getT_download_cnt() {
        return t_download_cnt;
    }

    public void setT_download_cnt(int t_download_cnt) {
        this.t_download_cnt = t_download_cnt;
    }

    public int getT_like() {
        return t_like;
    }

    public void setT_like(int t_like) {
        this.t_like = t_like;
    }
}
